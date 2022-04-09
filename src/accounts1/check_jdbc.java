package accounts1;

import java.sql.Connection;
import java.sql.SQLException;

public class check_jdbc {
    public static void main(String[] args) throws SQLException {
        Connection con= jdbc.getConnection("system","oracle");

    }
}
