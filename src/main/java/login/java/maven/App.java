package login.java.maven;

import javax.swing.SwingUtilities;
import login.java.maven.classes.LoginPage;

/**
 * Login page
 *
 */
public class App {

  public static void main( String[] args ) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run () {
        new LoginPage();
      }
    });
  }
}
