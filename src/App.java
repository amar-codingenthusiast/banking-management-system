import java.sql.SQLException;
import java.util.Scanner;

class App {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        while (true) {
            System.out.println("\n*** WELCOME TO BANKING MANAGEMENT SYSTEM ***");
            System.out.println("1. Signup");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            String choice = sc.next();
            switch (choice) {
                case "1":
                    sc.nextLine();
                    System.out.print("Enter Full Name: ");
                    String fullName = sc.nextLine();
                    System.out.print("Enter Email: ");
                    String email1 = sc.nextLine();
                    System.out.print("Enter Password: ");
                    String password1 = sc.nextLine();
                    System.out.print("Enter Initial Amount: ");
                    int balance = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Security Pin: ");
                    String securityPin = sc.nextLine();
                    email1 = Account.signup(fullName, email1, password1, balance, securityPin);
                    if (email1 != null) {
                        transactionMenu(email1);
                    }
                    break;
                case "2":
                    sc.nextLine();
                    System.out.print("Email: ");
                    String email2 = sc.nextLine();
                    System.out.print("Password: ");
                    String password2 = sc.nextLine();
                    email2 = Account.login(email2, password2);
                    if (email2 != null) {
                        transactionMenu(email2);
                    }
                    break;
                case "3":
                    System.out.println("THANK YOU FOR USING BANKING MANAGEMENT SYSTEM!!!\nExiting System!");
                    return;
                default:
                    System.out.println("Enter Valid Choice");
                    break;
            }
        }
    }

    public static void transactionMenu(String email) throws SQLException {
        while (true) {
            System.out.println("\n** TRANSACTION MENU **");
            System.out.println("1. Withdraw Money");
            System.out.println("2. Deposit Money");
            System.out.println("3. Transfer Money");
            System.out.println("4. Check Balance");
            System.out.println("5. Log Out");
            System.out.print("Enter your choice: ");
            String choice = sc.next();
            switch (choice) {
                case "1":
                    sc.nextLine();
                    System.out.print("Enter Amount: ");
                    int amount1 = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Security Pin: ");
                    String securityPin1 = sc.nextLine();
                    Transaction.withdrawMoney(email, amount1, securityPin1);
                    break;
                case "2":
                    sc.nextLine();
                    System.out.print("Enter Amount: ");
                    int amount2 = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Security Pin: ");
                    String securityPin2 = sc.nextLine();
                    Transaction.depositMoney(email, amount2, securityPin2);
                    break;
                case "3":
                    sc.nextLine();
                    System.out.print("Enter Receiver's Account Number: ");
                    int receiverAcNo = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Amount: ");
                    int amount3 = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Security Pin: ");
                    String securityPin3 = sc.nextLine();
                    Transaction.transferMoney(email, receiverAcNo, amount3, securityPin3);
                    break;
                case "4":
                    sc.nextLine();
                    System.out.print("Enter Security Pin: ");
                    String securityPin4 = sc.nextLine();
                    Transaction.checkBalance(email, securityPin4);
                    break;
                case "5":
                    System.out.println("Log Out Successfully");
                    return;
                default:
                    System.out.println("Enter Valid Choice");
                    break;
            }
        }
    }
}