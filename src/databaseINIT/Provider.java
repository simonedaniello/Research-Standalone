package databaseINIT;

import java.sql.*;

public class Provider {

    private String JDBC_DRIVER;
    private String DB_URL;
    private String USER;
    private String PASS;



    public Provider()
    {
        this.JDBC_DRIVER = "org.postgresql.Driver";
        this.DB_URL = "xxx";
        this.USER = "xxx";
        this.PASS = "xxx";
    }



    public Connection getConnection()
    {
        Connection conn = null;
        try {

            Class.forName(this.JDBC_DRIVER);
            conn = DriverManager.getConnection(this.DB_URL, this.USER, this.PASS);

        } catch(Exception e){
            e.printStackTrace();
        }
        return conn;
    }


/*non utilizzato ma potrebbe risultare utile in futuro*/

    public ResultSet getStatement(String SQL, Connection conn)
    {
        Statement stmt = null;
        ResultSet rs;
        try {

            stmt = conn.createStatement();

            rs = stmt.executeQuery(SQL);

            if(!rs.next())
            {
                System.out.println("non ho trovato nulla");
            }

        }catch(SQLException se){
            rs = null;
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
            rs = null;
        }

        return rs;
    }


}
