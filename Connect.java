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

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        while (true) {
            try {
                Class.forName("com.mysql.jdbc.Driver");

                System.out.println("Connecting to database...");
                Connection conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);

                stmt = conn.createStatement();
                String sql;
                sql = "CREATE DATABASE IF NOT EXISTS `pfwco_lab4`;\n"
                        + "CREATE TABLE `pfwco_lab4`.`pracownik` ( \n"
                        + "`id` INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY, \n"
                        + "`nazwisko` VARCHAR(50) NOT NULL , \n"
                        + "`pensja` VARCHAR(10) NOT NULL , \n"
                        + "`firma` VARCHAR(400) NOT NULL\n"
                        + "); \n"
                        + "INSERT INTO `pfwco_lab4`.`pracownik`(nazwisko, pensja, firma) VALUES \n"
                        + "(\"Cichy\", \"3\", \"Goudex\"),\n"
                        + "(\"Marcinek\", \"333\", \"testowa\"),\n"
                        + "(\"Komar\",\"23\",\"Dostra\"),\n"
                        + "(\"Wesoły\",\"52\",\"Kwantowa\");";
                stmt.execute(sql);
                sql = "SELECT * FROM pfwco_lab4`.`pracownik";
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nazwisko = rs.getString("nazwisko");
                    String pensja = rs.getString("pensja");
                    String firma = rs.getString("firma");

                    System.out.println("ID: " + id);
                    System.out.println(", nazwisko: " + nazwisko);
                    System.out.println(", pensja: " + pensja);
                    System.out.println(", firma: " + firma);
                }
                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException | ClassNotFoundException se) {
                se.printStackTrace();
            }
        }
    }
}

