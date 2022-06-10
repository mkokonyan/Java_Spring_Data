package L01_DBAppsIntroduction.b_exercise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class P01_ConnectToDB {

    public static Connection dbConnection(String db_name) throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "");

        return DriverManager
                .getConnection(String.format("jdbc:mysql://localhost:3306/%s", db_name), properties);
    }
}
