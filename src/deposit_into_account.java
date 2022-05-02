import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;

public class deposit_into_account {
    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        Connection con= jdbc.getConnection("system","oracle");

        System.out.print("Enter account number : ");
        int acc_id=Integer.parseInt(br.readLine());
        jdbc.select("select * from accounts where acc_no="+acc_id);
        System.out.print("Enter deposit ammount : ");
        int balance=Integer.parseInt(br.readLine());

        System.out.println("You transaction is being processed : ");
        String QueryString="update accounts set balance=balance+"+balance;
        jdbc.raw(QueryString);
        jdbc.select("select * from accounts where acc_no="+acc_id);
        jdbc.closeConnection();
    }
}
