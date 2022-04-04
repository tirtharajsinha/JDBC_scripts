import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.io.*;
public class insert_into_accounts {
    public static void main(String[] args) throws Exception {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        Connection con= jdbc.getConnection("system","oracle");
//        jdbc.raw("drop table accounts",con);
        jdbc.createTable("accounts","acc_no number primary key,holdername varchar2(225),balance float",con);
        jdbc.DESC("person",con);
        int CurrentId=jdbc.generateId("accounts","acc_no",con);
//        System.out.println(jdbc.generateId("accounts","acc_no",con));
        for(int i=CurrentId;i<CurrentId+2;i++){
            System.out.println("New entry on id: "+i);
            System.out.print("Enter name : ");
            String name=br.readLine();
            System.out.print("Enter balance : ");
            int balance=Integer.parseInt(br.readLine());
            jdbc.raw(String.format("INSERT INTO accounts values(%d,'%s',%d)",i,name,balance),con);
        }

        jdbc.select("select * from accounts",con);
    }
}
