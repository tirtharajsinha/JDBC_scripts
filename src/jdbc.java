import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import java.sql.*;

public class jdbc {

    private static Connection con;

    public static void main(String[] args) {

//        String  result= fetch("select loc from dept where deptno=20");
//        System.out.println(result);

    }

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

    public static void closeConnection(Connection con) {
        try {
            con.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public static void select(String query, Connection con) {
        try {


            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();

            int columnnum = rsmd.getColumnCount();
            for (int i = 1; i <= columnnum; i++) {
                String name = rsmd.getColumnName(i);
                System.out.print(name + " | ");
            }
            System.out.println();
            while (rs.next()) {
                for (int i = 1; i <= columnnum; i++) {
                    System.out.print("---------");
                }
                System.out.println();

                for (int i = 1; i <= columnnum; i++) {
                    System.out.print(rs.getString(i) + " | ");
                }
                System.out.println();


            }


        } catch (Exception e) {

            System.out.println("Table Issue \n"+e.toString());
        }
    }

    public static String fetch(String query, Connection con) {
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

    public static void raw(String query, Connection con) {
        try {
            Statement stmt = con.createStatement();
            stmt.executeQuery(query);
        } catch (Exception e) {
            System.out.println(e.toString());

        }

    }
    public static void DESC(String table, Connection copn) throws SQLException {
        if(!isTable(table,con)){
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
    public static Boolean isTable(String table,Connection con) throws SQLException {

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

    public static Boolean createTable(String table,String columns,Connection con) throws SQLException {

        if(isTable(table,con)){
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





}