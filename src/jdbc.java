import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import java.sql.*;

public class jdbc {

    private static Connection con;

    public static void main(String[] args) {

    }

//    establish the database connection
    public static Connection getConnection(String db_name, String pass) {

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            try {
                con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", db_name, pass);
                System.out.println("Database successfully connected.");
            } catch (SQLException ex) {
                // log an exception. fro example:
                System.out.println("Failed to create the database connection.");
            }
        } catch (ClassNotFoundException ex) {
            // log an exception. for example:
            System.out.println("Driver not found.");
        }
        return con;
    }
    public static Connection mysql_getConnection(String db_name, String username,String pass) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+db_name, username, pass);
                System.out.println("Database successfully connected.");
            } catch (SQLException ex) {
                // log an exception. fro example:
                System.out.println("Failed to create the database connection.");
            }
        } catch (ClassNotFoundException ex) {
            // log an exception. for example:
            System.out.println("Driver not found.");
        }
        return con;
    }


// close the database connection
    public static void closeConnection() {
        try {
            con.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
// display data in the tabuler form on select query.
    public static int select(String query) {
        try {


            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();

            int columnnum = rsmd.getColumnCount();
            boolean iscol=false;
            int totalCol=0;

            while (rs.next()) {
                if(!iscol){
                    for (int i = 1; i <= columnnum; i++) {
                        String name = rsmd.getColumnName(i);
                        System.out.print(name + " | ");
                    }
                    iscol=true;
                    System.out.println();
                }
                totalCol++;
                for (int i = 1; i <= columnnum; i++) {
                    System.out.print("---------");
                }
                System.out.println();

                for (int i = 1; i <= columnnum; i++) {
                    System.out.print(rs.getString(i) + " | ");
                }
                System.out.println();


            }
            return totalCol;


        } catch (Exception e) {

            System.out.println("Table Issue \n"+e.toString());
        }
        return 0;
    }

//    get the single value from the databese.
    public static String fetch(String query) {

        String result;
        try {


            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
//            ResultSetMetaData rsmd = rs.getMetaData();
            rs.next();
            result = rs.getString(1);


        } catch (Exception e) {

            result = e.toString();
        }
        return result;
    }

//    run the raw query
    public static ResultSet raw(String query) {
        try {
            Statement stmt = con.createStatement();
            return stmt.executeQuery(query);
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }

    }


//    check the table info
    public static void DESC(String table) throws SQLException {
        if(!isTable(table)){
            return;
        }
        table=table.toUpperCase();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from "+table);
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnnum = rsmd.getColumnCount();
        for (int i = 1; i <= columnnum; i++) {
            String name = rsmd.getColumnName(i);
            String type=rsmd.getColumnTypeName(i);
            int typenum=rsmd.getPrecision(i);
            System.out.println(name + " | " + type+"("+typenum+")");
        }




    }

//    check table exists or not
    public static Boolean isTable(String table) throws SQLException {

        table=table.toUpperCase();
        DatabaseMetaData dbm;
        Statement stmt = con.createStatement();
        dbm = con.getMetaData();
        //checking for is table existed
        ResultSet rs = dbm.getTables(null, null, table, null);
        if(rs.next()){
            System.out.println("Table existed !");
            return true;
        }
        else{
            System.out.println("Table not existed !");
            return false;
        }
    }

//    create a table if table not exists.
    public static Boolean createTable(String table,String columns) throws SQLException {

        if(isTable(table)){
            return false;
        }
        try {
            Statement stmt = con.createStatement();
            String QueryStatement=String.format("create table %s(%s)",table,columns);
            stmt.executeQuery(QueryStatement);
            System.out.println("Table created");
            return true;
        } catch (Exception e) {
            System.out.println("Table not created\n"+e.toString());
            return false;
        }
    }

    public static int generateId(String table,String Idname){
        try {

            String QueryStatement=String.format("select Max(%s) from %s",Idname,table);
            String newid=fetch(QueryStatement);

            return Integer.parseInt(newid)+1;
        } catch (Exception e) {
            System.out.println("query error:\n"+e.toString());
            return 1;
        }
    }






}
