package AweleModel;

import java.sql.*;
import java.time.LocalDate;

public class MainTest {
    public static void main(String[] args) {
        Connexion connexion = new Connexion("DatabaseScore.db");
        connexion.connect();
        LocalDate date = LocalDate.now();

        //ScoreDataClass b = new ScoreDataClass("toto",38, Date.valueOf(date),"marc",6);
        //connexion.addScore(b);
        //ResultSet resultSet = connexion.query("SELECT * FROM T_Score");
        String nom = "toTto";

        ResultSet resultSet = null;
        try {
            String query = "SELECT * FROM T_Score WHERE pseudoJ1 = LOWER(?) OR pseudoJ2 = LOWER(?)";
            //String query = "SELECT * FROM T_Score WHERE pseudoJ1 = ? OR pseudoJ2 = ?";
            PreparedStatement preparedStatement = connexion.getConnection().prepareStatement(query);
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, nom);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println("Joueur 1 : " + resultSet.getString("pseudoJ1") +
                        " - Joueur 2 : " + resultSet.getString("pseudoJ2") +
                        " - Date : " + resultSet.getDate("Date") +
                        " - score Joueur 1 : " + resultSet.getInt("scoreJ1") +
                        " - score Joueur 2 : " + resultSet.getInt("scoreJ2"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            connexion.close();
        }
    }
}

