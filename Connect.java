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

    private String sql;
    private Scanner s;

    public static void main(String[] args) throws SQLException {
        Connect c = new Connect();
        c.openConnection();
        if (c.openConnection() != null) {
            c.initDB();
            c.operations();
        }
    }

    public void initDB() throws SQLException {
        sql = "CREATE TABLE IF NOT EXISTS `pracownik` ( \n"
                + "`id` INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY, \n"
                + "`nazwisko` VARCHAR(50) NOT NULL , \n"
                + "`pensja` VARCHAR(10) NOT NULL , \n"
                + "`firma` VARCHAR(400) NOT NULL\n"
                + ");";
        statement = conn.createStatement();
        statement.executeUpdate(sql);
        statement.close();
        checkNewTable();
    }

    public void operations() throws SQLException {
        System.out.println("========================");
        System.out.println("1. Wyświetl pracowników");
        System.out.println("2. Dodaj pracownika");
        System.out.println("3. Edytuj pracownika");
        System.out.println("4. Usuń pracownika");
        System.out.println("0. Zamknij bazę");
        System.out.println("========================");
        s = new Scanner(System.in);
        String userResponse = s.nextLine();
        switch (userResponse) {
            case "1":
                showDB();
                break;
            case "2":
                addToDB();
                break;
            case "3":
                editFromDB();
                break;
            case "4":
                deleteFromDB();
                break;
            case "0":
                break;
            default:
                operations();
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
        System.out.println("Pracownicy");
        
        sql = "SELECT * FROM pracownik";
        statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            int id = rs.getInt("id");
            String nazwisko = rs.getString("nazwisko");
            String pensja = rs.getString("pensja");
            String firma = rs.getString("firma");

            System.out.print("ID: " + id);
            System.out.print(",\t nazwisko: " + nazwisko);
            System.out.print(",\t pensja: " + pensja);
            System.out.println(",\t firma: " + firma);
        }
        rs.close();
        statement.close();
        s = new Scanner(System.in);
        s.nextLine();

        operations();
    }

    private void checkNewTable() throws SQLException {
        sql = "SELECT * FROM pracownik";
        statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        if (rs.next() == false) {
            sql = "INSERT INTO `pracownik`(`nazwisko`, `pensja`, `firma`) VALUES ('Cichy','3','Goudex'), ('Marcinek','333','Testowa'), ('Komar','23','Dostra'), ('Wesoły','52','Kwantowa') ";
            statement.executeUpdate(sql);
        }
        statement.close();
    }

    private void addToDB() throws SQLException {
        String nazwisko, pensja, firma;
        s = new Scanner(System.in);
        statement = conn.createStatement();

        System.out.println("Dodawanie pracownika");
        System.out.print("Nazwisko: ");
        nazwisko = s.nextLine();
        System.out.print("Pensja: ");
        pensja = s.nextLine();
        System.out.print("Firma: ");
        firma = s.nextLine();

        sql = "INSERT INTO `pracownik`(`nazwisko`, `pensja`, `firma`) VALUES (\"" + nazwisko + "\",\"" + pensja + "\",\"" + firma + "\")";
        try {
            statement.executeUpdate(sql);
            statement.close();

            System.out.println("Pracownik dodany");
        } catch (Exception e) {
        }

        operations();
    }

    private void editFromDB() throws SQLException {
        String nazwisko, pensja, firma;
        s = new Scanner(System.in);
        statement = conn.createStatement();

        System.out.println("Dodawanie pracownika");
        System.out.print("Podaj id pracownika: ");
        String idNumber = s.nextLine();

        sql = "SELECT * FROM pracownik where id = \"" + idNumber + "\"";
        ResultSet rs = statement.executeQuery(sql);
        if (rs.next() != false) {
            System.out.print("ID: " + rs.getString("id") + ", \t");
            System.out.print("nazwisko: " + rs.getString("nazwisko") + ", \t");
            System.out.print("pensja: " + rs.getString("pensja") + ", \t");
            System.out.println("firma: " + rs.getString("firma"));

            System.out.print("Nazwisko: ");
            nazwisko = s.nextLine();
            System.out.print("Pensja: ");
            pensja = s.nextLine();
            System.out.print("Firma: ");
            firma = s.nextLine();

            sql = "UPDATE `pracownik` SET `nazwisko`=\"" + nazwisko + "\",`pensja`=\"" + pensja + "\",`firma`=\"" + firma + "\" WHERE id = \"" + idNumber + "\"";
            statement.executeUpdate(sql);
            statement.close();

            System.out.println("Dane zaktualizowane");
        }

        operations();
    }

    private void deleteFromDB() throws SQLException {
        s = new Scanner(System.in);
        statement = conn.createStatement();

        System.out.println("Usuwanie pracownika");
        System.out.print("Podaj id pracownika: ");
        String idNumber = s.nextLine();

        sql = "DELETE FROM `pracownik` WHERE id = \"" + idNumber + "\"";
        try {
            statement.executeUpdate(sql);
            statement.close();
            System.out.println("Pracownik usunięty");
        } catch (Exception e) {
        }

        operations();
    }

}
