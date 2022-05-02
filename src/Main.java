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
       Login login = new Login();
    }

    public static void Connect() {
        try {
            String url = "jdbc:sqlserver://localhost:1433;database=supermarket;encrypt=true;trustServerCertificate=true;";
            String user = "SA";
            String password = "!Database123";
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected!");
        }

        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
}
