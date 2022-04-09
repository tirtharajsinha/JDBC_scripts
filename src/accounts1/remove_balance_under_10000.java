import java.sql.Connection;
import java.sql.SQLException;

public class remove_balance_under_10000 {
    public static void main(String[] args) throws SQLException {
        Connection con= jdbc.getConnection("system","oracle");
        jdbc.select("select * from accounts",con);
        System.out.println("removed all row with balance less than 10000.");
        String QueryString="delete from accounts where balance<10000";
        jdbc.raw(QueryString,con);
        jdbc.select("select * from accounts",con);
    }
}
