package AweleModel;
import java.sql.*;
public class Connexion {
    private String DBPath = "Chemin aux base de donnee SQLite";
    private Connection connection = null;
    private Statement statement = null;
    public Connexion(String dBPath) {
        DBPath = dBPath;
    }



    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + DBPath);
            statement = connection.createStatement();
            System.out.println("Connexion a " + DBPath + " avec succès");
        } catch (ClassNotFoundException notFoundException) {
            notFoundException.printStackTrace();
            System.out.println("Erreur de connexion");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.out.println("Erreur de connexion");
        }
    }
    public void close() {
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ResultSet query(String request) {
        ResultSet resultat = null;
        try {
            resultat = statement.executeQuery(request);
            if (resultat == null) {
                System.out.println("pas de resultat");

            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur dans la request : " + request);
        }
        return resultat;
    }
    public void addScore(ScoreDataClass score) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO T_Score VALUES(NULL,?,?,?,?,?)");
            preparedStatement.setString(1, score.getPseudoJ1());
            preparedStatement.setInt(2, score.getScoreJ1());
            preparedStatement.setDate(3, score.getDate());
            preparedStatement.setString(4, score.getPseudoJ2());
            preparedStatement.setInt(5, score.getScoreJ2());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public Connection getConnection() {
        return this.connection;
    }
}
