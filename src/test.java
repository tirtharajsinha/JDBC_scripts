import java.sql.*;

public class test {
    public static void main(String[] args) {
        Connection con = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            try {
                con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "oracle");
                System.out.println("Database successfully connected.");
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM person1");
                while(rs.next()){
                    System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
                }

            } catch (SQLException ex) {
                // log an exception. fro example:
                System.out.println("Failed to create the database connection."+ex);
            }
        } catch (ClassNotFoundException ex) {
            // log an exception. for example:
            System.out.println("Driver not found.");
        }

        

       
    }
}
