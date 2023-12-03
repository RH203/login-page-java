package login.java.maven.classes;

import login.java.maven.database.ConnectDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginPage {
  private JFrame frame;
  private JTextField usernameField;
  private JPasswordField passwordField;

  public LoginPage() {
    frame = new JFrame("Login Page");
    frame.setSize(600, 600);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel panel = new JPanel();
    frame.add(panel);

    placeComponents(panel);

    frame.setVisible(true);
  }

  private void placeComponents(JPanel panel) {
    panel.setLayout(null);

    JLabel titleLabel = new JLabel("Login Page");
    titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
    titleLabel.setBounds(200, 10, 200, 30);
    titleLabel.setHorizontalAlignment(JLabel.CENTER);
    panel.add(titleLabel);

    JLabel userLabel = new JLabel("Username:");
    userLabel.setBounds(10, 60, 80, 25);
    panel.add(userLabel);

    usernameField = new JTextField(20);
    usernameField.setBounds(100, 60, 165, 25);
    panel.add(usernameField);

    JLabel passwordLabel = new JLabel("Password:");
    passwordLabel.setBounds(10, 90, 80, 25);
    panel.add(passwordLabel);

    passwordField = new JPasswordField(20);
    passwordField.setBounds(100, 90, 165, 25);
    panel.add(passwordField);

    JButton loginButton = new JButton("Login");
    loginButton.setBounds(10, 120, 80, 25);
    loginButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        ConnectDatabase db = new ConnectDatabase();

        try {
          boolean loginSuccess = db.loginUser(username, password);

          if (loginSuccess) {
            JOptionPane.showMessageDialog(null, "Selamat datang " + username);
          } else {
            JOptionPane.showMessageDialog(null, "Login gagal. Periksa username dan password Anda.");
          }
        } catch (SQLException | ClassNotFoundException ex) {
          ex.printStackTrace();
          JOptionPane.showMessageDialog(null, "Error during login process.");
        }
      }
    });
    panel.add(loginButton);

    JButton createButton = new JButton("Create account");
    createButton.setBounds(100, 120, 150, 30);
    createButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        openCreatePage();
      }
    });
    panel.add(createButton);
  } // end place components

  private void openCreatePage() {
    frame.dispose();
    CreatePage createPage = new CreatePage(this);
  }

  public void showLoginPage() {
    frame.setVisible(true);
  }
}
