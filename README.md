# Login page and authentication  use java swing
<p>This program is made using Swing, Java, Maven, and MySQL. This program can create an account that has a feature to check whether the passwords entered are the same or not, and will display a message if the passwords entered are not the same.</p>

## Tech Stack
<ol>
<div align="left">
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" height="40" alt="java logo"  />
  <img width="12" />
  <img src="https://cdn.simpleicons.org/apachemaven/C71A36" height="40" alt="apachemaven logo"  />
  <img width="12" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/mysql/mysql-original.svg" height="40" alt="mysql logo"  />
  <p>And Swing as GUI Java</p>
</div>

## Setup Project
  1\. Clone this repo 
  ```bash
  git clone https://github.com/RH203/login-page-java.git
  ```
  2\. Setup Maven Project:
  
    1. Go into the pom.xml file, then press Refersh to download all dependencies.
    2. In this project I use java version 17.
  **Don't forget to change the Java version here and adjust it to the existing Java you have. Example:**
```xml
  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>17</maven.compiler.source>
    <maven.compiler.target>17</maven.compiler.target>
  </properties>
```
  3\. Setup database MySQL.

  First run Xampp and start apache and mysql.

   After that create database

```
CREATE DATABASE login_java;
```
  Please install MySQL below and import it into the database that has been created. I recommend using phpMyAdmin or DataGrip.
  [File MySQL](https://drive.google.com/file/d/1U9x3er-aAVyNlZ8-UJPiHdAc4kuRL1W1/view?usp=sharing).

4\.because I use Intellij (I recommend Intellij IDEA). Open the terminal and enter folder, and type.
```bash
idea64 .
```
5\. Create a /db folder and create a ConnectDatabase class. After that copy the code below. **Make sure to create a db folder inside the directory.**

```bash
login-java\src\main\java\login\java\maven
```
```java
// Copy this code
package login.java.maven.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectDatabase {
  static final String DB_URL = "jdbc:mysql://localhost:3306/login_java"; // change 3360 with your current port on Xampp
  static final String USER = "root";
  static final String PASS = "";

  public void createUser(String userPengguna, String passPengguna) throws SQLException, ClassNotFoundException {
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         PreparedStatement stmt = conn.prepareStatement("INSERT INTO customer (user_pengguna, password_pengguna) VALUES (?, ?)")) {

      stmt.setString(1, userPengguna);
      stmt.setString(2, passPengguna);
      stmt.executeUpdate();
    }
  }

  public boolean loginUser(String userPengguna, String passPengguna) throws SQLException, ClassNotFoundException {
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         PreparedStatement stmt = conn.prepareStatement("SELECT user_pengguna, password_pengguna FROM customer WHERE user_pengguna = ? AND password_pengguna = ?")) {

      stmt.setString(1, userPengguna);
      stmt.setString(2, passPengguna);

      try (ResultSet rs = stmt.executeQuery()) {
        return rs.next();
      }
    }
  }

  public boolean checkUsername(String username) throws SQLException, ClassNotFoundException {
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         PreparedStatement stmt = conn.prepareStatement("SELECT user_pengguna FROM customer WHERE BINARY user_pengguna = ?")) {

      stmt.setString(1, username);

      try (ResultSet rs = stmt.executeQuery()) {
        return rs.next();
      }
    }
  }
}
```

6\. Run App.java inside a folder below.

```\login-java\src\main\java\login\java\maven\App.java```

7\. Done, you can try to create an account first.

###

## Progress project
![Progress]( https://progress-bar.dev/100/?title=Progress)

 
