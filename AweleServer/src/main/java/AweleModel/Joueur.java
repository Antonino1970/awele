package AweleModel;
/**---------------------------------------------------------------
 * La classe abstraite Joueur représente un joueur dans le jeu Awele.
 *  Auteur : Umina Antonino
 *  Jeu Awele : Serveur REST
 *  Date : 21/04/2024
 *  -------------------------------------------------------------
 */

public abstract class Joueur {

    private String pseudo ;
    private int    score  ;
    private int    id     ;
    private Ligne ligne   ;

    /**
     * Constructeur de la classe Joueur.
     * @param pseudo Le pseudo du joueur.
     * @param score Le score du joueur.
     * @param ligne La ligne associée au joueur.
     */
    public Joueur(String pseudo, int id, int score, Ligne ligne) {
        this.pseudo = pseudo;
        this.score = score;
        this.ligne = ligne ;
        this.id= id;
    }


    // Méthodes getter et setter

    public void setId(int id) {this.id = id; }
    public void setScore(int score) {this.score = score; }
    public String getPseudo() {
        return this.pseudo;
    }
    public int getScore() {
        return score;
    }
    public Ligne getLigneJoueur() {
        return id==0 ?Ligne.Ligne1 :Ligne.Ligne0;
    }
    public int getId() {return id;}

    // Méthodes
    /**
     * Méthode abstraite pour que le joueur choisisse un trou.
     * @param nbrTrou Le nombre total de trous disponibles.
     * @return Le trou choisi par le joueur.
     */
    public abstract int joueurChoisirUnTrou(int nbrTrou,int trou);
    /**
     * Méthode pour incrementer le score du joueur d'une valeur de récolte.
     * @param valeur La valeur qui sera ajoutée au score.
     * @return void .
     */
    public void incrementeScoreJoueur(int valeur){
        score = score + valeur;
    }

    /**
     * Méthode pour remettre le score du joueur a 0.
     *
     * @return void .
     */
    public void resetScore(){
        score = 0;
    }

}
