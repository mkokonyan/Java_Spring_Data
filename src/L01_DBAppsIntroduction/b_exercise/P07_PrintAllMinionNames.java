package L01_DBAppsIntroduction.b_exercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class P07_PrintAllMinionNames {
    public static void main(String[] args) throws SQLException {

        Connection connection = P01_ConnectToDB.dbConnection("minions_db");

        PreparedStatement statement = connection.prepareStatement(
          "SELECT name FROM minions"
        );

        ResultSet printSet = statement.executeQuery();

        List<String> printList = new ArrayList<>();

        while (printSet.next()) {
            printList.add(printSet.getString("name"));
        }

        for (int i = 0; i < printList.size() / 2; i++) {
            System.out.println(printList.get(i));
            System.out.println(printList.get(printList.size() - i - 1));
        }
    }
}
