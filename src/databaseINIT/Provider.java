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
        this.DB_URL = "jdbc:postgresql://localhost:5432/ispw";
        this.USER = "superuser";
        this.PASS = "password";
    }



    public Connection getConnection()
    {
        Connection conn = null;
        try {

//			System.out.println(this.DB_URL);
//			System.out.println(this.USER);
//			System.out.println(this.PASS);

            Class.forName(this.JDBC_DRIVER);
            conn = DriverManager.getConnection(this.DB_URL, this.USER, this.PASS);

        } catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
            System.out.println("errore boh");
        }
        return conn;
    }



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
            //Handle errors for JDBC
            rs = null;
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
            rs = null;;
        }

        return rs;
    }


}
