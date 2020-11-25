import java.sql.*;

public class Main {
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://database:3306/mkozicki";

   static final String USER = "mkozicki";
   static final String PASS = "mkozicki";
   
   public static void main(String[] args) {
   Connection conn = null;
   Statement stmt = null;
   try{
      Class.forName("com.mysql.jdbc.Driver");

      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);

      stmt = conn.createStatement();
      String sql;
      sql = "SELECT * FROM pracownik";
      ResultSet rs = stmt.executeQuery(sql);

      while(rs.next()){
         int id  = rs.getInt("id");
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
   }catch(SQLException se){
      se.printStackTrace();
   }catch(Exception e){
      e.printStackTrace();
   }finally{
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }
   }
 }
}