import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class remove_account {
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        Connection con= jdbc.getConnection("system","oracle");

        String deleteStatement = "delete from accounts where acc_no=?";
        PreparedStatement deleteTotal = con.prepareStatement(deleteStatement);

        System.out.print("Enter account no : ");
        int closeId=Integer.parseInt(br.readLine());
        deleteTotal.setInt(1, closeId);


//        deleteTotal.executeQuery();

        if(jdbc.raw("delete from accounts where acc_no="+closeId) == null){
            System.out.println("failed");
        }

    }
}
