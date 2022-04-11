import java.sql.*;
public class app {
    public static void main(String[] args) throws SQLException {

//        Connection con= jdbc.getConnection("system","oracle");
        jdbc_mysql.getConnection("jdbc","root","");
//        jdbc.select("select * from accounts order by acc_no asc");
        jdbc_mysql.createTable("accounts","acc_no int primary key,holdername varchar(225),balance float");
        jdbc_mysql.DESC("accounts");
    }

}
