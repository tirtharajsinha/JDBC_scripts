import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class commit_rollback {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Connection con=jdbc.getConnection("system", "oracle");
        System.out.println("Welcome to money transaction application : ");
        System.out.print("Enter sender's account no.");
        int sender = Integer.parseInt(br.readLine());
        System.out.print("Enter receiver's account no.");
        int receiver = Integer.parseInt(br.readLine());
        System.out.print("Enter transaction amount :");
        int amount = Integer.parseInt(br.readLine());

        int selectcol=jdbc.select("select * from accounts where acc_no="+sender);
        if(selectcol==0){
            System.out.println("sender account not found");
            System.exit(0);
        }
        int balance=Integer.parseInt(jdbc.fetch("select balance from accounts where acc_no="+sender));
        if(balance<amount){
            System.out.println("Not enough balance in sender's account.");
            System.exit(0);
        }
        con.setAutoCommit(false);
        execute(con,"update accounts set balance=balance-"+amount+" where acc_no="+sender);

        System.out.println("ammout deducted from sender's account.");

        selectcol=jdbc.select("select * from accounts where acc_no="+receiver);
        if(selectcol==0){
            System.out.println("receiver account not found");
            con.rollback();
            System.exit(0);
        }
        int pbalance=Integer.parseInt(jdbc.fetch("select balance from accounts where acc_no="+receiver));
        execute(con,"update accounts set balance=balance+"+amount+" where acc_no="+receiver);
        int nbalance=Integer.parseInt(jdbc.fetch("select balance from accounts where acc_no="+receiver));
        if(pbalance+amount==nbalance){
            System.out.println("Transaction successful.");
            con.commit();
        }
        else{
            System.out.println("Transaction failed.");
            con.rollback();
        }



    }
    public static ResultSet execute(Connection con,String query) {
        try {
            Statement stmt = con.createStatement();
            return stmt.executeQuery(query);
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }

    }
}
