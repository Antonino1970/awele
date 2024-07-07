package AweleModel;
import java.util.Random;

 /**---------------------------------------------------------------
 * La classe Computer représente un joueur contrôlé par l'intelligence artificielle.
 *  Auteur : Umina Antonino
 *  Jeu Awele : Serveur REST
 *  Date : 21/04/2024
 *  -------------------------------------------------------------
 */
public class Computer extends Joueur{
    /**
     * Constructeur de la classe Computer.
     */
    public Computer() {
        super("AI", 1, 0, Ligne.Ligne0);
    }

    /**
     * Méthode pour générer un nombre aléatoire.
     * @param max La valeur maximale du nombre généré.
     * @return Un nombre aléatoire entre 1 et le max spécifié inclusivement.
     */
    public static int generateRandomNumber(int max) {
        Random rand = new Random();
        return rand.nextInt(max) ;
    }

    /**
     * Méthode pour que l'ordinateur choisisse un trou.
     * @param nbrTrou Le nombre total de trous disponibles.
     * @return Le trou choisi par l'ordinateur.
     */
    @Override
    public int joueurChoisirUnTrou(int nbrTrou ,int trou) {
        trou =generateRandomNumber(nbrTrou);
        System.out.println(getPseudo() + " choisi : " + (trou + 1));
        return trou;
    }
}
