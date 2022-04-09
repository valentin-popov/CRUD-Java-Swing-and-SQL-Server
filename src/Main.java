import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.*;
public class Main {
    static Connection con;
    PreparedStatement pst;
    public static void main(String[] args) throws IOException {
        Connect();
        //Home home = new Home();
       Login login = new Login();
    }

    public static void Connect() {
        try {
            //Class.forName("com.mysql.cj.jdbc.Driver");
            //con = DriverManager.getConnection("jdbc:mysql://localhost/supermarket", "root", "!Database123");
            SQLServerDataSource ds = new SQLServerDataSource();
            ds.setUser("SA");
            ds.setPassword("!Database123");
            ds.setServerName("localhost");
            ds.setPortNumber(1433);
            ds.setDatabaseName("supermarket");
            con = ds.getConnection();
            System.out.println("Connected!");
        }

        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
}
