import java.sql.*;

/**
 *
 * @author Marcin
 */
public class Connect {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://10.0.10.3:3306/mkozicki";

    static final String USER = "mkozicki";
    static final String PASS = "mkozicki";

    static Statement stmt = null;
    static Connection conn = null;

    public static void main(String[] args) throws SQLException {
        Connect c = new Connect();
        c.openConnection();
    }

    public Connection openConnection() throws SQLException {
        if (conn == null) {
            try {
                    Class.forName(JDBC_DRIVER);
                    conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
                    System.out.println("CONNECTION SUCCESSFUL");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("CONNECTION ERROR");
                }
        }
        return conn;
    }

}
