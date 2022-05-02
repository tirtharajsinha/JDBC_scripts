import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.io.*;
public class insert_into_accounts {
    public static void main(String[] args) throws Exception {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

//        Connection con= jdbc.getConnection("system","oracle");
        Connection con=jdbc.mysql_getConnection("jdbc","root","");
//        jdbc.raw("drop table accounts",con);
        jdbc.createTable("accounts","acc_no number primary key,holdername varchar2(225),balance float");
        jdbc.DESC("person");
        int CurrentId=jdbc.generateId("accounts","acc_no");
//        System.out.println(jdbc.generateId("accounts","acc_no",con));
        for(int i=CurrentId;i<CurrentId+2;i++){
            System.out.println("New entry on id: "+i);
            System.out.print("Enter name : ");
            String name=br.readLine();
            System.out.print("Enter balance : ");
            int balance=Integer.parseInt(br.readLine());
            jdbc.raw(String.format("INSERT INTO accounts values(%d,'%s',%d)",i,name,balance));
        }

        jdbc.select("select * from accounts");
    }
}
