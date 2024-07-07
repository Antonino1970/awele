package ITLG.toni.aweleClientFx;
/**---------------------------------------------------------------
 * La classe AweleControlleur control des boutons et fonctions.
 *  Auteur : Umina Antonino
 *  Jeu Awele : Client FX
 *  Date : 21/04/2024
 *  -------------------------------------------------------------
 */
import io.swagger.client.ApiException;
import io.swagger.client.model.RestTabScore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import io.swagger.client.api.DefaultApi;
import io.swagger.client.model.RestBoard;
import io.swagger.client.model.RestPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AweleControlleur {
    DefaultApi apiInstance;
    String srvIP = "127.0.0.1";

    {
        apiInstance = new DefaultApi();
        apiInstance.getApiClient().setBasePath("http://" + srvIP + ":8080/v1");
    }
    private RestPlayer restPlayer1;
    private RestPlayer restPlayer2;
    private RestBoard restBoard;
    private RestTabScore restTabScore;
    private List<Integer> myBoard = null ;
    private List<Integer> hisBoard = null ;
    private int monId = -1 ;
    private int idAdverse = -2 ;
    private int turn = -1 ;
    private int gameState ;
    private Game game = new Game();
    private String msg ;

    /**
     * Méthode pour afficher l'id du joueur adverse.
     * @param id id du joueur en cours.
     * @return l'id du joueur averse.
     */
    public int getIdAdverse(int id){
        return (idAdverse= (id == 0 ? 1 : 0));
    }


    //----------------------------liens FXML---------------------------------------
    @FXML private Label nomJoueur1;
    @FXML private Label nomJoueur2;
    @FXML private Button connect,disconect ,start,scoreTab;
    @FXML private TextField monNom,nameScore;
    @FXML private Button b11, b12, b13, b14, b15, b16, b21, b22, b23, b24, b25, b26;

    @FXML private Label score2, score1 , monID , myturn ,hisTurn , status ;
    @FXML private TextFlow sortiePrintJ1,sortiePrintJ2;

    /*
    -----------------------------------------------------------------------------------
    ------------------------  SET LIENS  -----------------------------------------------
    ------------------------------------------------------------------------------------
     */

    /**
     * Méthode pour afficher l'état du tour du joueur.
     */
    public void setTurn() throws ApiException {
        if (apiInstance.getTurn() != null){
            turn = apiInstance.getTurn().getId();
            if (turn != monId){
                hisTurn.setText("His turn");
                myturn.setText("Waiting");
            }
            else if (turn == monId){
                hisTurn.setText("Waiting");
                myturn.setText("My turn");
            }
        }
        else {
            hisTurn.setText("Waiting");
            myturn.setText("Waiting");
        }
    }
    /**
     * Méthode pour afficher le plateau du jeu.
     */
    public void setBoard() throws ApiException {
        restBoard = apiInstance.getBoard();
        String valeurb11 = "4" ;
        String valeurb12 = "4" ;
        String valeurb13 = "4" ;
        String valeurb14 = "4" ;
        String valeurb15 = "4" ;
        String valeurb16 = "4" ;
        String valeurb21 = "4" ;
        String valeurb22 = "4" ;
        String valeurb23 = "4" ;
        String valeurb24 = "4" ;
        String valeurb25 = "4" ;
        String valeurb26 = "4" ;
        if (monId==1 || monId==-1){//je suis le joueur2
            valeurb21 = String.valueOf(restBoard.getHolesPlayer2().get(0));
            valeurb22 = String.valueOf(restBoard.getHolesPlayer2().get(1));
            valeurb23 = String.valueOf(restBoard.getHolesPlayer2().get(2));
            valeurb24 = String.valueOf(restBoard.getHolesPlayer2().get(3));
            valeurb25 = String.valueOf(restBoard.getHolesPlayer2().get(4));
            valeurb26 = String.valueOf(restBoard.getHolesPlayer2().get(5));

            valeurb11 = String.valueOf(restBoard.getHolesPlayer1().get(0));
            valeurb12 = String.valueOf(restBoard.getHolesPlayer1().get(1));
            valeurb13 = String.valueOf(restBoard.getHolesPlayer1().get(2));
            valeurb14 = String.valueOf(restBoard.getHolesPlayer1().get(3));
            valeurb15 = String.valueOf(restBoard.getHolesPlayer1().get(4));
            valeurb16 = String.valueOf(restBoard.getHolesPlayer1().get(5));

        }
        else if (monId == 0){//je suis le joueur1
            valeurb21 = String.valueOf(restBoard.getHolesPlayer1().get(0));
            valeurb22 = String.valueOf(restBoard.getHolesPlayer1().get(1));
            valeurb23 = String.valueOf(restBoard.getHolesPlayer1().get(2));
            valeurb24 = String.valueOf(restBoard.getHolesPlayer1().get(3));
            valeurb25 = String.valueOf(restBoard.getHolesPlayer1().get(4));
            valeurb26 = String.valueOf(restBoard.getHolesPlayer1().get(5));

            valeurb11 = String.valueOf(restBoard.getHolesPlayer2().get(0));
            valeurb12 = String.valueOf(restBoard.getHolesPlayer2().get(1));
            valeurb13 = String.valueOf(restBoard.getHolesPlayer2().get(2));
            valeurb14 = String.valueOf(restBoard.getHolesPlayer2().get(3));
            valeurb15 = String.valueOf(restBoard.getHolesPlayer2().get(4));
            valeurb16 = String.valueOf(restBoard.getHolesPlayer2().get(5));

        }
        b21.setText(valeurb21);
        b22.setText(valeurb22);
        b23.setText(valeurb23);
        b24.setText(valeurb24);
        b25.setText(valeurb25);
        b26.setText(valeurb26);

        b11.setText(valeurb11);
        b12.setText(valeurb12);
        b13.setText(valeurb13);
        b14.setText(valeurb14);
        b15.setText(valeurb15);
        b16.setText(valeurb16);

        // Mettre à jour l'état de clicabilité des boutons en fonction de leur valeur
        updateButtonState(b21, Integer.parseInt(b21.getText()));
        updateButtonState(b22, Integer.parseInt(b22.getText()));
        updateButtonState(b23, Integer.parseInt(b23.getText()));
        updateButtonState(b24, Integer.parseInt(b24.getText()));
        updateButtonState(b25, Integer.parseInt(b25.getText()));
        updateButtonState(b26, Integer.parseInt(b26.getText()));
        if (apiInstance.getState() == -1 || monId==-1){
            disableAllButtons();

        }

    }

    /**
     * Méthode pour afficher les joueurs et leurs scores.
     */
    public void setPlayer() throws ApiException {
        if (monId >-1){//si je suis connecté
            idAdverse = getIdAdverse(monId);
            nomJoueur1.setText(apiInstance.getPlayer(monId).getName());
            score1.setText(String.valueOf(apiInstance.getPlayer(monId).getScore()));

            // si le joueur adverse existe
            if ((apiInstance.getPlayer(idAdverse) != null))  {
                nomJoueur2.setText(apiInstance.getPlayer(idAdverse).getName());
                score2.setText(String.valueOf(apiInstance.getPlayer(idAdverse).getScore()));
            }
            else {
                nomJoueur2.setText("Joueur en attente");
                score2.setText("Null");
            }

        }
        // je ne suis pas connecté et 2 joueurs existe (spectateur)
        else if((apiInstance.getPlayer(0) != null) && (apiInstance.getPlayer(1) != null)) {
            nomJoueur1.setText(apiInstance.getPlayer(0).getName());
            score1.setText(String.valueOf(apiInstance.getPlayer(0).getScore()));
            nomJoueur2.setText(apiInstance.getPlayer(1).getName());
            score2.setText(String.valueOf(apiInstance.getPlayer(1).getScore()));
            afficheTextDown("Pas de place pour l'instant");
        }
        // 0 joueurs connectés.
        else if((apiInstance.getPlayer(0) == null) && (apiInstance.getPlayer(1) == null)) {
            nomJoueur2.setText("Joueur en attente");
            score2.setText("Null");
            nomJoueur1.setText("Joueur en attente");
            score1.setText("Null");
        }
        else { //un seul adversaire connecté, mais pas moi.
            if ((apiInstance.getPlayer(0) != null)) {
                nomJoueur2.setText(apiInstance.getPlayer(0).getName());
                score2.setText(String.valueOf(apiInstance.getPlayer(0).getScore()));

                nomJoueur1.setText("Joueur en attente");
                score1.setText("Null");
            }

            else if((apiInstance.getPlayer(1) != null)) {
                nomJoueur2.setText(apiInstance.getPlayer(1).getName());
                score2.setText(String.valueOf(apiInstance.getPlayer(1).getScore()));

                nomJoueur1.setText("Joueur en attente");
                score1.setText("Null");
            }
        }

        monID.setText(String.valueOf(monId));
    }
    /**
     * Méthode pour afficher le statut du jeu.
     */
    public void setStatus() throws ApiException {
        gameState = apiInstance.getState();
        status.setText(String.valueOf(gameState));
        switch (gameState){
            case 3 :
                afficheTextUP("égalité");
                break;
            case 2 :
                restPlayer2 = apiInstance.getPlayer(1);
                afficheTextUP(restPlayer2.getName()+" gagne");
                break;
            case 1 :
                restPlayer1 = apiInstance.getPlayer(0);
                afficheTextUP(restPlayer1.getName()+" gagne");
                break;
            case 0 :
                turn = apiInstance.getTurn().getId();
                if (turn==monId){
                    afficheTextUP("A vous de jouer ");
                }
                else if (monId>=0){
                    afficheTextUP("C'est au tour de votre adversaire de jouer");
                }
                else {
                    afficheTextUP("Spectateur");
                }
                break;
            default:
                sortiePrintJ2.getChildren().clear();
                System.out.println("");
        }
    }

    /**
     * Méthode pour afficher le bouton Start stop selon le status du jeu.
     */
    public void setStartStop() throws ApiException {
        if(apiInstance.getState()==0){
            start.setText("STOP");
        }
        else {
            start.setText("START");
        }
    }

    /**
     * Méthode pour activer le bouton Start stop selon le status du jeu.
     */
    public void setconection() throws ApiException {
        if(monId>=0){
            connect.setDisable(true);
            disconect.setDisable(false);
        }
        else {
            disconect.setDisable(true);
            connect.setDisable(false);
        }
    }

    /*
    ---------------------------------------------------------------------------------

    ------------------------ methodes des clicks -------------------------------------
    */

    /**
     * Méthode pour le bouton de récuperation du tableau des scores.
     */
    @FXML
    public void getTabScore() throws ApiException {
        try {
            // on passe à restTabScore le tableau reçu par la requête SQL via l'API.
            String nom = nameScore.getText();
            restTabScore = apiInstance.getScore(nom);


            // Charger le fichier FXML de la fenêtre modale
            FXMLLoader fxmlLoader = new FXMLLoader(AweleControlleur.class.getResource("scoreTab.fxml"));
            Parent root = fxmlLoader.load();

            // Obtenir le contrôleur de la fenêtre modale
            ScoreController modalController = fxmlLoader.getController();

            // Passer les données au contrôleur de la fenêtre modale
            modalController.setScoreTable(restTabScore);

            // Créer la fenêtre modale
            Stage modalStage = new Stage();
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.setTitle("Tableau des scores");
            modalStage.setScene(new Scene(root));
            modalStage.showAndWait();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode pour le bouton connect selon l'ID du joueur.
     */
    @FXML public void connectPlayer() {
        try {
            if ( monId == -1 ) {//je ne suis pas connecté
                // il y a une place
                if ((monId = apiInstance.connect(monNom.getText())) > -1) {
                    refresh();
                    afficheTextUP("Vous êtes connecté");
                }
                // 2 joueurs deja connecté
                else {
                    refresh();
                    afficheTextUP("Plus de place");
                }
            }
            else {
                afficheTextUP("Vous êtes déja connecté ");
            }
        }
        catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Méthode pour le bouton deconnect selon l'ID du joueur.
     */
    @FXML public void deconnectPlayer() {
        try {
            if (monId != -1) {//si je suis  connecté
                apiInstance.deconnect(monId);
                monId = -1 ;
                // afficheTextUP("Vous êtes déconnecté");
            }

            else {
                afficheTextUP("Vous n'êtes pas connecté");
            }
        }
        catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Méthode pour le bouton Start-stop.
     */
    @FXML public void startStop() throws ApiException {
        msg = apiInstance.startstop(monId);
        afficheTextUP(msg);
        System.out.println("start stop");
    }

    /**
     * Méthode pour executer les trous du plateau cliqué.
     * @param event l'evenement bouton cliqué.
     *
     */
    @FXML
    private void handleButtonClick(ActionEvent event) throws ApiException {
        if (apiInstance.getState() == 0 ){
            Button clickedButton = (Button) event.getSource();// déclenche l'événement du clic sur un bouton
            String buttonIndex = clickedButton.getId(); // Récupérer l'indice du bouton à partir de son ID

            System.out.println("Bouton sélectionné : " + buttonIndex);
            int value = Integer.valueOf(buttonIndex);
            int sowIntRetour;
            sowIntRetour = apiInstance.sow(value,monId);
            msg = game.sowMessage(sowIntRetour);
            if (sowIntRetour>0){
                disableAllButtons();
                //msg = apiInstance.startstop(monId);
            }
            else if (sowIntRetour == -1 ){
                msg = "adversaire affamé et ne peut être nourrit !!! Fin !!! ";
                apiInstance.startstop(monId);
            }
            updateTextFlow2(msg);
        }

    }
/*
"""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""
""""""""""""""""""""""""   Methodes """"""""""""""""""""""""""""""""""""""""""""""""""""
 */
    /**
     * Méthode pour désactiver les trous a zéro.
     * @param button le bouton a desactiver.
     * @param value la valeur du bouton.
     *
     */
    public void updateButtonState(Button button, int value) {
        button.setDisable(value == 0);
    }

    /**
     * Méthode pour désactiver tous les boutons des trous quand le jeu est arrêté.
     *
     */
    public void disableAllButtons() {
        List<Button> allButtons = Arrays.asList(b21, b22, b23, b24, b25, b26,b11,b12,b13,b14,b15,b16);
        for (Button button : allButtons) {
            button.setDisable(true);
        }
    }

    /**
     * Méthode pour rafraichir l'écran du client.
     */
    @FXML public void refresh() throws ApiException {
        setPlayer();
        setTurn();
        setBoard();
        setStatus();
        setStartStop();
        setconection();
        sortiePrintJ1.getChildren().clear();
    }


    /**
     * Méthode pour 'affichage du texte en haut dans la console fx.
     * @param txt le texte qu'il faut afficher
     */
    public void afficheTextUP(String txt){
        sortiePrintJ2.getChildren().clear(); // Effacer le contenu actuel du TextFlow
        Text text = new Text(txt);
        text.setStyle("-fx-font-weight: bold;");
        text.setStyle("-fx-fill: white; -fx-font-size: 34px;"); // Changer la couleur en bleu et la taille en 14 pixels
        sortiePrintJ2.getChildren().add(text);
    }

    /**
     * Méthode pour 'affichage du texte en bas dans la console fx.
     * @param txt le texte qu'il faut afficher
     */
    public void afficheTextDown(String txt){
        sortiePrintJ1.getChildren().clear(); // Effacer le contenu actuel du TextFlow
        Text text = new Text(txt);
        text.setStyle("-fx-font-weight: bold;");
        text.setStyle("-fx-fill: white; -fx-font-size: 34px;"); // Changer la couleur en bleu et la taille en 14 pixels
        sortiePrintJ1.getChildren().add(text);
    }

    /**
     * Méthode pour 'affichage du texte en bas dans la console avec des couleurs d'alertes.
     * @param message le texte qu'il faut afficher
     */
    private void updateTextFlow2(String message) {
        sortiePrintJ2.getChildren().clear();
        Text text = new Text(message);
        text.setStyle("-fx-font-weight: bold;");
        text.setStyle("-fx-fill: #c21f1f; -fx-font-size: 34px;");
        sortiePrintJ2.getChildren().add(text);
        Text text2 = new Text("test");
        Text text3 = new Text("text");
        text2.setStyle("-fx-font-weight: bold;");
        text2.setStyle("-fx-fill: #ffd500; -fx-font-size: 34px;");
        text3.setStyle("-fx-font-weight: bold;");
        text3.setStyle("-fx-fill: red; -fx-font-size: 34px;");

    }

}
