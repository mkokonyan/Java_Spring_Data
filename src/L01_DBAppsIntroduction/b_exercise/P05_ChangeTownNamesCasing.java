package L01_DBAppsIntroduction.b_exercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P05_ChangeTownNamesCasing {
    public static void main(String[] args) throws SQLException {

        Connection connection = P01_ConnectToDB.dbConnection("minions_db");

        Scanner scanner = new Scanner(System.in);
        String countryName = scanner.nextLine();

        PreparedStatement updateTownNames = connection.prepareStatement(
                "UPDATE towns SET name = upper(name) WHERE country = ?;"
        );
        updateTownNames.setString(1, countryName);


        int updatedCount = updateTownNames.executeUpdate();

        if (updatedCount == 0) {
            System.out.println("No town names were affected");
            return;
        }
        System.out.println(updatedCount + " town names were affected.");

        PreparedStatement selectAllTowns = connection.prepareStatement(
                "SELECT name FROM towns WHERE country = ?"
        );

        selectAllTowns.setString(1, countryName);
        ResultSet townsSet = selectAllTowns.executeQuery();

        List<String> townsList = new ArrayList<>();

        while (townsSet.next()) {
            townsList.add(townsSet.getString("name"));
        }
        System.out.println(townsList);
    }
}
