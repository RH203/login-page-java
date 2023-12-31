package login.java.maven.classes;

import login.java.maven.database.ConnectDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CreatePage {
  private JFrame frame;
  private JTextField newUsernameField;
  private JPasswordField newPasswordField, checkPasswordField;

  public CreatePage(LoginPage loginPage) {
    frame = new JFrame("Create Account");
    frame.setSize(600, 600);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    JPanel panel = new JPanel();
    frame.add(panel);

    placeComponents(panel, loginPage);

    frame.setVisible(true);
  }

  private void placeComponents(JPanel panel, LoginPage loginPage) {
    panel.setLayout(null);

    JLabel titleLabel = new JLabel("Create Account");
    titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
    titleLabel.setBounds(100, 10, 200, 30);
    titleLabel.setHorizontalAlignment(JLabel.CENTER);
    panel.add(titleLabel);

    // Username
    JLabel newUserLabel = new JLabel("New Username:");
    newUserLabel.setBounds(10, 60, 100, 25);
    panel.add(newUserLabel);

    newUsernameField = new JTextField(20);
    newUsernameField.setBounds(120, 60, 165, 25);
    panel.add(newUsernameField);

    // Password
    JLabel newPasswordLabel = new JLabel("New Password:");
    newPasswordLabel.setBounds(10, 90, 100, 25);
    panel.add(newPasswordLabel);

    newPasswordField = new JPasswordField(20);
    newPasswordField.setBounds(120, 90, 165, 25);
    panel.add(newPasswordField);

    // Confirm password
    JLabel checkPasswordLabel = new JLabel("Confirm Password:");
    checkPasswordLabel.setBounds(10, 120, 120, 25);
    panel.add(checkPasswordLabel);

    checkPasswordField = new JPasswordField(20);
    checkPasswordField.setBounds(120, 120, 145, 25);
    panel.add(checkPasswordField);



    JButton createAccountButton = new JButton("Create Account");
    createAccountButton.setBounds(120, 150, 150, 30);
    createAccountButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String username = newUsernameField.getText();
        if (!checkUsername(username)) {
          if (checkPassword()) {
            String password = new String(newPasswordField.getPassword());
            createAccount(username, password);
            JOptionPane.showMessageDialog(null, "Account created successfully.");
            frame.dispose();
            loginPage.showLoginPage();
          } else {
            JOptionPane.showMessageDialog(null, "Passwords do not match.");
          }
        } else {
          frame.dispose();
          loginPage.showLoginPage();
          JOptionPane.showMessageDialog(null, "Username already exists.");
        }
      }
    });
    panel.add(createAccountButton);
  }

  private boolean checkPassword() {
    String password = new String(newPasswordField.getPassword());
    String passwordConfirm = new String(checkPasswordField.getPassword());
    return password.equals(passwordConfirm);
  }

  private boolean checkUsername(String username) {
    try {
      ConnectDatabase db = new ConnectDatabase();
      return db.checkUsername(username);
    } catch (SQLException | ClassNotFoundException ex) {
      ex.printStackTrace();
      JOptionPane.showMessageDialog(null, "Error during registration process.");
      return false;
    }
  }

  private void createAccount(String username, String password) {
    try {
      ConnectDatabase db = new ConnectDatabase();
      db.createUser(username, password);
    } catch (SQLException | ClassNotFoundException ex) {
      ex.printStackTrace();
      JOptionPane.showMessageDialog(null, "Error during registration process.");
    }
  }
}