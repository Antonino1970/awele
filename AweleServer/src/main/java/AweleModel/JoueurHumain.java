package AweleModel;

/**---------------------------------------------------------------
 * La classe JoueurHumain représente un joueur humain dans le jeu.
 *  Auteur : Umina Antonino
 *  Jeu Awele : Serveur REST
 *  Date : 21/04/2024
 *  -------------------------------------------------------------
 */
public class JoueurHumain extends Joueur {

    //----------------------constructeurs------------------------------------
    /**
     * Constructeur de la classe JoueurHumain.
     * @param nom Le nom du joueur humain.
     */
    public JoueurHumain(String nom) {
        super(nom, -2, 0, null);
    }

    //----------------------methodes--------------------------

    /**
     * Méthode pour que le joueur humain choisisse un trou, pour une utilisation different comme un scanner.
     * @param nbrTrou Le nombre total de trous disponibles.
     * @param trou Le trou.
     * @return Le numéro du trou choisi par le joueur sous forme d'un int.
     */
    @Override
    public int joueurChoisirUnTrou(int nbrTrou,int trou) {
        return trou;
    }

}
