import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;

public class show_balance_more_than_5000 {
    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        Connection con= jdbc.getConnection("system","oracle");

        System.out.println("All accounts with balance more than 5000");
        int selectcol=jdbc.select("select holdername,balance from accounts where balance>5000");
        if(selectcol==0){
            System.out.println("No account matched the account number.");
        }

    }
}
