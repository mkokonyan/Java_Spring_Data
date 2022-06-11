package L01_DBAppsIntroduction.b_exercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

public class P08_IncreaseMinionsAge {
    public static void main(String[] args) throws SQLException {

        Connection connection = P01_ConnectToDB.dbConnection("minions_db");

        Scanner scanner = new Scanner(System.in);

        int[] idsArr = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();


        for (int currentId : idsArr) {
            PreparedStatement minionSelectStatement = connection.prepareStatement(
                    "UPDATE minions SET name = LOWER(name), age = age+1  WHERE id = ?"
            );
            minionSelectStatement.setInt(1, currentId);
            minionSelectStatement.executeUpdate();
        }

        PreparedStatement minionsSelect = connection.prepareStatement("SELECT id, name, age FROM minions");
        ResultSet minionsSet = minionsSelect.executeQuery();
        while (minionsSet.next()) {
            int minionAge = minionsSet.getInt("age");
            String minionName = minionsSet.getString("name");

            System.out.println(minionName +  " " + minionAge);
        }

        connection.close();
    }
}
