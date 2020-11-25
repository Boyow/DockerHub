import java.sql.*;
import java.util.*;

/**
 *
 * @author Marcin
 */
public class Connect {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://10.0.10.3:3306/mkozicki";

    static final String USER = "mkozicki";
    static final String PASS = "mkozicki";

    static Statement statement = null;
    static Connection conn = null;

    public static void main(String[] args) throws SQLException {
        Connect c = new Connect();
        c.openConnection();
        if (c.openConnection() != null) {
            c.initDB();
            c.operations();
        }
    }

    public void initDB() throws SQLException {
        String sql;
        sql = "CREATE TABLE IF NOT EXISTS `pracownik` ( \n"
                + "`id` INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY, \n"
                + "`nazwisko` VARCHAR(50) NOT NULL , \n"
                + "`pensja` VARCHAR(10) NOT NULL , \n"
                + "`firma` VARCHAR(400) NOT NULL\n"
                + "); \n"
                + "INSERT INTO `pracownik` (nazwisko, pensja, firma) VALUES \n"
                + "(\"Cichy\", \"3\", \"Goudex\"),\n"
                + "(\"Marcinek\", \"333\", \"testowa\"),\n"
                + "(\"Komar\",\"23\",\"Dostra\"),\n"
                + "(\"Wesoły\",\"52\",\"Kwantowa\");";
        statement = conn.createStatement();
        statement.executeUpdate(sql);
        statement.close();
    }

    public void operations() throws SQLException {
        System.out.println("Baza dancyh mkozicki");
        System.out.println("1. Wyświetl pracowników");
        System.out.println("2. Dodaj pracownika");
        System.out.println("3. Edytuj pracownika");
        System.out.println("4. Usuń pracownika");
        Scanner s = new Scanner(System.in);
        String userResponse = s.nextLine();
        switch (userResponse) {
            case "1":
                showDB();
                break;
        }
    }

    public Connection openConnection() throws SQLException {
        if (conn == null) {
            try {
                Class.forName(JDBC_DRIVER);
                conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
                System.out.println("CONNECTION SUCCESSFUL");
            } catch (ClassNotFoundException ex) {
                System.out.println("CONNECTION ERROR");
            }
        }
        return conn;
    }

    private void showDB() throws SQLException {
        String sql;
        sql = "SELECT * FROM pracownik";
        statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            int id = rs.getInt("id");
            String nazwisko = rs.getString("nazwisko");
            String pensja = rs.getString("pensja");
            String firma = rs.getString("firma");

            System.out.print("ID: " + id);
            System.out.print(", nazwisko: " + nazwisko);
            System.out.print(", pensja: " + pensja);
            System.out.println(", firma: " + firma);
        }
        rs.close();
        statement.close();
    }

}
