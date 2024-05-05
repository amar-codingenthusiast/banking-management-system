import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    static Connection conn = null;

    public static Connection connect() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/";
        String db = "banking_management_system";
        String userName = "root";
        String password = "********"; // put your password
        conn = DriverManager.getConnection(url + db, userName, password);
        return conn;
    }
}
