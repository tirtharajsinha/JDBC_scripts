import java.sql.*;
public class app {
    public static void main(String[] args) throws SQLException {

        Connection con= jdbc.getConnection("system","oracle");
        jdbc.DESC("person",con);
    }

}
