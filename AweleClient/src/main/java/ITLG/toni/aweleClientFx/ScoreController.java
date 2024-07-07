package ITLG.toni.aweleClientFx;
/**---------------------------------------------------------------
 * La classe ScoreController représente le controller de la fenêtre modale des scores.
 *  Auteur : Umina Antonino
 *  Jeu Awele : Client FX
 *  Date : 09/06/2024
 *  -------------------------------------------------------------
 */

import io.swagger.client.ApiException;
import io.swagger.client.model.RestScore;
import io.swagger.client.model.RestTabScore;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import java.util.ArrayList;
import java.util.List;

public class ScoreController {
    // tableau de la fenêtre modale.
    @FXML private TableView<ScoreData> scoreTable;

    /**
     * Méthode pour remplir la fenêtre modale avec les données passée par le serveur.
     * @param restTabScore La liste de champs passée par le serveur.
     */
    public void setScoreTable(RestTabScore restTabScore) throws ApiException {
        // Initialise la TableView
        List<ScoreData> scoreDataList = new ArrayList<>();
        if (restTabScore.getScore()!= null){
            // Boucle à travers chaque élément dans restTabScore
            for (RestScore element  : restTabScore.getScore()) {
                // Ajouter un nouvel objet ScoreData à la liste
                //ScoreData scoreData = new ScoreData(element.getPlayer1(),element.getScorePlayer1(),element.getDate(),element.getPlayer2(),element.getScorePlayer2());
                //scoreTable.getItems().add(scoreData);
                scoreDataList.add(new ScoreData(
                        element.getPlayer1(),
                        element.getScorePlayer1(),
                        element.getDate(),
                        element.getPlayer2(),
                        element.getScorePlayer2()
                ));
            }
            scoreTable.getItems().addAll(scoreDataList);
        }

    }


}

