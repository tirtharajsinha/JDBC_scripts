import java.sql.*;
public class app {
    public static void main(String[] args) throws SQLException {

        Connection con= jdbc.getConnection("system","oracle");
        jdbc.select("select * from accounts order by acc_no asc");
    }

}
