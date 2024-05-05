import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Account {
    public static int getAccountNumber(String email) throws SQLException {
        Connection conn = DB.connect();
        PreparedStatement ps = conn.prepareStatement(Query.getAccountNumber);
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("account_number");
        } else {
            System.out.println("Account Number doesn't Exist!");
            return 0;
        }
    }

    public static boolean accountExists(String email) throws SQLException {
        Connection conn = DB.connect();
        PreparedStatement ps = conn.prepareStatement(Query.accountExists);
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            System.out.println("Account Already Exists for this Email Address, try to log in!!");
            return false;
        } else {
            return true;
        }
    }

    public static String signup(String fullName, String email, String password, int balance, String securityPin)
            throws SQLException {
        Connection conn = DB.connect();
        if (accountExists(email)) {
            PreparedStatement ps = conn.prepareStatement(Query.signup);
            ps.setString(1, fullName);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setInt(4, balance);
            ps.setString(5, securityPin);
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Account Created Successfully\nYour Account Number is: " + getAccountNumber(email));
                return email;
            } else {
                System.out.println("Signup Failed!");
                return null;
            }
        } else {
            return null;
        }
    }

    public static String login(String email, String password) throws SQLException {
        Connection conn = DB.connect();
        PreparedStatement ps = conn.prepareStatement(Query.login);
        ps.setString(1, email);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            System.out.println("Login Successfull!");
            return email;
        } else {
            System.out.println("Login Failed!");
            return null;
        }
    }
}
