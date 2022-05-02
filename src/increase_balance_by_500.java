import java.sql.Connection;
import java.sql.SQLException;

public class increase_balance_by_500 {
    public static void main(String[] args) throws SQLException {
        Connection con= jdbc.getConnection("system","oracle");
        jdbc.select("select * from accounts");
        System.out.println("incremented balance by 500.");
        String QueryString="update accounts set balance=balance+500";
        jdbc.raw(QueryString);
        jdbc.select("select * from accounts");
    }
}
