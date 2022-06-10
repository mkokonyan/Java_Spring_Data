package L01_DBAppsIntroduction.b_exercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class P02_GetVillainsNames {
    public static void main(String[] args) throws SQLException {

        Connection connection = P01_ConnectToDB.dbConnection("minions_db");

        PreparedStatement statement = connection.prepareStatement(
                "SELECT name, count(DISTINCT mv.minion_id) AS minion_count FROM villains AS v\n" +
                "JOIN minions_villains AS mv on v.id = mv.villain_id\n" +
                "GROUP BY mv.villain_id\n" +
                "HAVING minion_count > 15\n" +
                "ORDER BY minion_count DESC;");

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            String villainName = resultSet.getString("name");
            int minionCount = resultSet.getInt("minion_count");
            System.out.println(villainName + " " + minionCount);
        }
        connection.close();
    }
}
