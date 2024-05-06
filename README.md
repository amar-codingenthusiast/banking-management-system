# Banking Management System

A simple banking management system implemented in Java with JDBC and MySQL. This project allows users to sign up, log in, perform transactions like deposit, withdrawal, transfer, and check balance.

## Features

- **Sign Up:** Users can create a new account with a full name, email, password, initial balance, and security pin.
- **Login:** Existing users can log in using their email and password.
- **Transaction:** Users can perform various transactions like deposit, withdrawal, transfer, and check balance.
- **Secure:** Implements security measures such as security pin validation and password verification.

## Technologies Used
- Java
- OOPs (Object Oriented Programming)
- JDBC (Java Database Connectivity)
- MySQL

## Usage

1. Clone the repository:
```
git clone <repo_url>
```

2. Set up MySQL Database:
 - Look at my database set up first [here](Database_layout.png)
 - Create database named `banking_management_system`.
 - Create table named `accounts`.
 - In DB.java file, put your database password.
 - Add [MySQL jar](mysql-connector-j-8.4.0.jar) file in Referenced Libraries in Java Projects in VS Code.

3. Compile and run the Java application:
```
javac *.java
java App
```

## Demo Video
You can see the demo version of this app [here](https://drive.google.com/file/d/1Px-hOQVv_qf2_q06WM8p5STrthN46a5X/view?usp=drive_link)

## Contributing
Contributions are welcome! Feel free to open issues or pull requests for any improvements, bug fixes, or new features.

## License
This project is licensed under the [MIT License](LICENSE).
