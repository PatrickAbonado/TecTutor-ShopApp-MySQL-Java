package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataConnect {
    private Connection con;
    private DataConnect()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/tectutor_training","TestUser","TestUser81");
            //System.out.println("Data connected");
            /*
             * jdbc-protocol
             * mysql-sub protocol
             * localhost-address of mysql
             * 3306-port number of mysql
             */
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }

    }
    public static Connection getConnect() {
        DataConnect d1 = new DataConnect();
        return d1.con;
    }



}
