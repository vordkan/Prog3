import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connessione {
    private static final String URL = "jdbc:mysql://localhost:3307/programmazione3";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "password";
    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
