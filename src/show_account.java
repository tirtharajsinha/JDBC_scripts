import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;

public class show_account {
    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        Connection con= jdbc.getConnection("system","oracle");

        System.out.print("Enter account number : ");
        int acc_id=Integer.parseInt(br.readLine());
        int selectcol=jdbc.select("select * from accounts where acc_no="+acc_id,con);
        if(selectcol==0){
            System.out.println("No account matched the account number.");
        }

    }
}
