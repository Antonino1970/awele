package ITLG.toni.aweleClientFx;
/**---------------------------------------------------------------
 * La classe Game afficheles messages consoles.
 *  Auteur : Umina Antonino
 *  Jeu Awele : Client FX
 *  Date : 21/04/2024
 *  -------------------------------------------------------------
 */

public class Game {

    /**
     * Méthode pour afficher les messages consoles.
     * @param id le numéro du message de retour du serveur.
     * @return le message a afficher.
     */
   public String sowMessage(int id) {
       String sowResponse;
       switch (id) {
           case -5:
               sowResponse = "Appuyer sur Start";
               break;
           case -4:
               sowResponse = "Attendre son tour";
               break;
           case -3:
               sowResponse = "trou jouer affamé";
               break;
           case -2:
               sowResponse = "coup invalide";
               break;
           case -1:
               sowResponse = "joueur adverse est affamé et ne peut pas être nourri !!! Fin !!!";

               break;
           case 6:
               sowResponse = "coup Joué 6 ";
               break;
           case 5:
               sowResponse = "coup Joué 5 ";
               break;
           case 4:
               sowResponse = "coup Joué 4 ";
               break;
           case 3:
               sowResponse = "égalité";
               break;
           case 2:
               sowResponse = "joueur 2 gagne ";
               break;
           case 1:
               sowResponse = "joueur 1 gagne ";
               break;
           case 0:
               sowResponse = "ok";
               break;
           default:
               sowResponse = "Code non géré";
       }
       return sowResponse;
   }

}
