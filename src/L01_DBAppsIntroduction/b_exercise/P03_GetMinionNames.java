package L01_DBAppsIntroduction.b_exercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class P03_GetMinionNames {
    public static void main(String[] args) throws SQLException {

        Connection connection = P01_ConnectToDB.dbConnection("minions_db");

        Scanner scanner = new Scanner(System.in);
        int villainId = Integer.parseInt(scanner.nextLine());

        PreparedStatement villainStatement = connection.prepareStatement(
                "SELECT name FROM villains WHERE id = ?"
        );
        villainStatement.setInt(1, villainId);
        ResultSet villainSet = villainStatement.executeQuery();

        if (!villainSet.next()) {
            System.out.printf("No villain with ID %d exists in the database.", villainId);
            return;
        }

        String villainName = villainSet.getString("name");
        System.out.println("Villain: " + villainName);

        PreparedStatement minionStatement = connection.prepareStatement(
                "SELECT name, age from minions\n" +
                        "JOIN minions_villains AS mv ON id = mv.minion_id\n" +
                        "WHERE mv.villain_id = ?;");

        minionStatement.setInt(1, villainId);

        ResultSet minionSet = minionStatement.executeQuery();

        for (int i = 1; minionSet.next() ; i++) {
            String name = minionSet.getString("name");
            int age = minionSet.getInt("age");

            System.out.printf("%d. %s %d%n", i, name , age);
        }
        connection.close();
    }
}
