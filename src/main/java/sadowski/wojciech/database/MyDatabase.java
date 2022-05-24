package sadowski.wojciech.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MyDatabase {
    public static Connection connect(){
        Connection connection = null;
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("C:\\Java\\intelliJ\\projects\\simpleEmployeeManagmentApp\\src\\main\\resources\\database.properties"));
            String url = properties.getProperty("jdbc.url");
            String username = properties.getProperty("jdbc.username");
            String password = properties.getProperty("jdbc.password");
            connection = DriverManager.getConnection(url, username, password);
        } catch (IOException e) {
            System.out.println("IOException");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("SQLException");
            e.printStackTrace();
        }
        return connection;
    }
}
