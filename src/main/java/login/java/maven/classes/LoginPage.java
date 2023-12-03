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
    frame.setSize(400, 200);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel panel = new JPanel();
    frame.add(panel);
    placeComponents(panel);

    frame.setVisible(true);
  }

  private void placeComponents(JPanel panel) {
    panel.setLayout(null);

    JLabel userLabel = new JLabel("Username:");
    userLabel.setBounds(10, 20, 80, 25);
    panel.add(userLabel);

    usernameField = new JTextField(20);
    usernameField.setBounds(100, 20, 165, 25);
    panel.add(usernameField);

    JLabel passwordLabel = new JLabel("Password:");
    passwordLabel.setBounds(10, 50, 80, 25);
    panel.add(passwordLabel);

    passwordField = new JPasswordField(20);
    passwordField.setBounds(100, 50, 165, 25);
    panel.add(passwordField);

    JButton loginButton = new JButton("Login");
    loginButton.setBounds(10, 80, 80, 25);
    loginButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        ConnectDatabase db = new ConnectDatabase();

        try {
          // Call the loginUser method
          boolean loginSuccess = db.loginUser(username, password);

          if (loginSuccess) {
            // Display a welcome message
            JOptionPane.showMessageDialog(null, "Selamat datang, " + username);
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
  }
}
