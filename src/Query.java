public class Query {
    static String accountExists = "SELECT * FROM accounts WHERE email = ?";
    static String signup = "INSERT INTO accounts (full_name, email, password, balance, security_pin) VALUES (?, ?, ?, ?, ?)";
    static String login = "SELECT * FROM accounts WHERE email = ? AND password = ?";
    static String getAccountNumber = "SELECT account_number from accounts WHERE email = ?";
    static String isPinValid = "SELECT * FROM accounts WHERE email = ? AND security_pin = ?";
    static String withdraw = "UPDATE accounts SET balance = balance - ? WHERE email = ?";
    static String deposit = "UPDATE accounts SET balance = balance + ? WHERE email = ?";
    static String checkBalance = "SELECT balance FROM accounts WHERE email = ? AND security_pin = ?";
    static String amountAddedToReceiversAc = "UPDATE accounts SET balance = balance + ? WHERE account_number = ?";
}