package ITLG.toni.aweleClientFx;
/**---------------------------------------------------------------
 * La classe AweleApplication Classe hérité de la classe Application .
 *  Auteur : Umina Antonino
 *  Jeu Awele : Client FX
 *  Date : 21/04/2024
 *  -------------------------------------------------------------
 */
import io.swagger.client.ApiException; // Importation de la classe ApiException du package io.swagger.client, qui est utilisée pour gérer les exceptions de l'API
import javafx.application.Application; // Importation de la classe Application du package javafx.application, qui est utilisée comme point d'entrée pour les applications JavaFX
import javafx.application.Platform; // Importation de la classe Platform du package javafx.application, qui fournit un accès à l'API de la plateforme JavaFX
import javafx.fxml.FXMLLoader; // Importation de la classe FXMLLoader du package javafx.fxml, qui est utilisée pour charger des fichiers FXML
import javafx.scene.Scene; // Importation de la classe Scene du package javafx.scene, qui représente une scène JavaFX
import javafx.stage.Stage; // Importation de la classe Stage du package javafx.stage, qui représente une fenêtre JavaFX
import java.io.IOException; // Importation de la classe IOException du package java.io, qui est utilisée pour gérer les exceptions d'E/S
import java.util.concurrent.Executors; // Importation de la classe Executors du package java.util.concurrent, qui est utilisée pour créer des tâches exécutées périodiquement
import java.util.concurrent.ScheduledExecutorService; // Importation de la classe ScheduledExecutorService du package java.util.concurrent, qui est utilisée pour planifier l'exécution de tâches
import java.util.concurrent.TimeUnit; // Importation de la classe TimeUnit du package java.util.concurrent, qui représente une unité de temps
import javafx.stage.WindowEvent;// pour la gestion de la fenêtre(fermeture)

public class AweleApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Chargement du fichier FXML qui définit l'interface utilisateur de l'application
        FXMLLoader fxmlLoader = new FXMLLoader(AweleApplication.class.getResource("awele.fxml"));
        // Création de la scène en utilisant le contenu du fichier FXML chargé
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);
        // Récupération du contrôleur associé à la scène
        AweleControlleur control = fxmlLoader.getController();
        // Configuration de la scène sur la fenêtre principale de l'application
        stage.setScene(scene);
        // Définition du titre de la fenêtre
        stage.setTitle("AWELE");
        // Affichage de la fenêtre
        stage.show();
        stage.setOnCloseRequest((WindowEvent event) -> {
            // Arrêtez tout quand on ferme la fenetre fx
            System.exit(0);
        });
        // Création d'un planificateur pour exécuter périodiquement une tâche
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // Planification de l'exécution d'une tâche toutes les secondes
        scheduler.scheduleAtFixedRate(() -> {
            try {
                // Exécution de la tâche de mise à jour de l'interface utilisateur sur le thread de l'application JavaFX
                Platform.runLater(() -> {
                    try {
                        // Appel de la méthode refresh() du contrôleur pour mettre à jour l'interface utilisateur
                        control.refresh();
                    } catch (ApiException e) {
                        // Gestion des exceptions ApiException
                        e.printStackTrace();
                    }
                });
            } catch (Exception e) {
                // Gestion des exceptions générales
                e.printStackTrace();
            }
        }, 0, 2, TimeUnit.SECONDS); // Démarrage immédiat de la tâche, puis exécution périodique toutes les secondes
    }

    // Point d'entrée de l'application
    public static void main(String[] args) {
        // Lancement de l'application JavaFX
        launch();
    }
}
