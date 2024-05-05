import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Transaction {
    public static void withdrawMoney(String email, int amount, String securityPin) throws SQLException {
        Connection conn = DB.connect();
        conn.setAutoCommit(false);
        PreparedStatement ps1 = conn.prepareStatement(Query.isPinValid);
        ps1.setString(1, email);
        ps1.setString(2, securityPin);
        ResultSet rs = ps1.executeQuery();
        if (rs.next()) {
            int balance = rs.getInt("balance");
            if (amount <= balance) {
                PreparedStatement ps2 = conn.prepareStatement(Query.withdraw);
                ps2.setInt(1, amount);
                ps2.setString(2, email);
                int affectedRows = ps2.executeUpdate();
                if (affectedRows > 0) {
                    System.out.println("Rs " + amount + " Withdrew Successfully");
                    conn.commit();
                } else {
                    System.out.println("Transaction Failed!");
                    conn.rollback();
                }
            } else {
                System.out.println("Insufficient Balance!");
            }
        } else {
            System.out.println("Invalid Security Pin!");
        }
        conn.setAutoCommit(true);
    }

    public static void depositMoney(String email, int amount, String securityPin) throws SQLException {
        Connection conn = DB.connect();
        conn.setAutoCommit(false);
        PreparedStatement ps1 = conn.prepareStatement(Query.isPinValid);
        ps1.setString(1, email);
        ps1.setString(2, securityPin);
        ResultSet rs = ps1.executeQuery();
        if (rs.next()) {
            PreparedStatement ps2 = conn.prepareStatement(Query.deposit);
            ps2.setInt(1, amount);
            ps2.setString(2, email);
            int affectedRows = ps2.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Rs " + amount + " Deposited Successfully");
                conn.commit();
            } else {
                System.out.println("Transaction Failed!");
                conn.rollback();
            }
        } else {
            System.out.println("Invalid Security Pin!");
        }
        conn.setAutoCommit(true);
    }

    public static void transferMoney(String email, int receiverAcNo, int amount, String securityPin)
            throws SQLException {
        Connection conn = DB.connect();
        conn.setAutoCommit(false);
        if (receiverAcNo != 0 && receiverAcNo != Account.getAccountNumber(email)) {
            PreparedStatement ps = conn.prepareStatement(Query.isPinValid);
            ps.setString(1, email);
            ps.setString(2, securityPin);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int balance = rs.getInt("balance");
                if (amount <= balance) {
                    PreparedStatement creditPS = conn.prepareStatement(Query.amountAddedToReceiversAc);
                    PreparedStatement debitPS = conn.prepareStatement(Query.withdraw);
                    creditPS.setInt(1, amount);
                    creditPS.setInt(2, receiverAcNo);
                    debitPS.setInt(1, amount);
                    debitPS.setString(2, email);
                    int rowsAffected1 = debitPS.executeUpdate();
                    int rowsAffected2 = creditPS.executeUpdate();
                    if (rowsAffected1 > 0 && rowsAffected2 > 0) {
                        System.out.println("Transaction Successful!\nRs " + amount + " Transferred Successfully");
                        conn.commit();
                    } else {
                        System.out.println("Invalid account number");
                        conn.rollback();
                    }
                } else {
                    System.out.println("Insufficient Balance!");
                }
            } else {
                System.out.println("Invalid Security Pin!");
            }
        } else {
            System.out.println("Invalid account number");
        }
        conn.setAutoCommit(true);
    }

    public static void checkBalance(String email, String securityPin) throws SQLException {
        Connection conn = DB.connect();
        PreparedStatement ps = conn.prepareStatement(Query.checkBalance);
        ps.setString(1, email);
        ps.setString(2, securityPin);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int balance = rs.getInt("balance");
            System.out.println("Your Current Balance: " + balance);
        } else {
            System.out.println("Invalid Security Pin!");
        }
    }
}
