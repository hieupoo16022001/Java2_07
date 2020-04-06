package SQLUpdate;
import java.sql.*;

public class JDBCupdateTest{
    public static void main(String[] args) {
        try (
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ebookshop?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                    "root","");
        Statement stmt = conn.createStatement();
            ){
            String strUpdate = "update books set price = price*50% where name = 'A Cup of Java',set qty = 0 where name = 'A Teaspoon of Java'";
            System.out.println("The SQL statement is: " + strUpdate + "\n");
            int countUpdate = stmt.executeUpdate(strUpdate);
            System.out.println(countUpdate + "record affected.\n");

            String strSelect = "select * from books where name = 'A Cup of Java',where name = 'A Teaspoon of Java'";
            System.out.println("the SQL statement is: " + strSelect + "\n");
            ResultSet rset =  stmt.executeQuery(strSelect);
            while (rset.next()){
                System.out.println(rset.getInt("id") + ","
                        + rset.getString("author") + ","
                        + rset.getString("title") + ","
                        + rset.getDouble("price") + ","
                        + rset.getInt("qty"));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
