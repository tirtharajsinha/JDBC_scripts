import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class insert_using_prepared {
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        Connection con= jdbc.getConnection("system","oracle");

        String insertStatement = "INSERT INTO accounts values(?,?,?)";
        PreparedStatement insertTotal = con.prepareStatement(insertStatement);

        int CurrentId=jdbc.generateId("accounts","acc_no",con);
        System.out.print("Enter name : ");
        String name=br.readLine();
        System.out.print("Enter balance : ");
        int balance=Integer.parseInt(br.readLine());
        insertTotal.setInt(1, CurrentId);
        insertTotal.setString(2, name);
        insertTotal.setInt(3,balance);

        insertTotal.executeQuery();
        jdbc.select("select * from accounts order by acc_no asc",con);

    }
}
