package AweleModel;
/**---------------------------------------------------------------
 * La classe Trou représente un trou sur un plateau de jeu.
 *  Auteur : Umina Antonino
 *  Jeu Awele : Serveur REST
 *  Date : 21/04/2024
 *  -------------------------------------------------------------
 */

public class Trou {
    private int valeur; // La valeur associée à ce trou

    /**
     * Constructeur de la classe Trou.
     *
     * @param valeur La valeur à assigner à ce trou.
     */
    public Trou(int valeur) {
        this.valeur = valeur;
    }

    /**
     * Getter pour obtenir la valeur de ce trou.
     *
     * @return La valeur de ce trou.
     */
    public int getValeur() {
        return valeur;
    }

    /**
     * Setter pour définir la valeur de ce trou.
     *
     * @param valeur La nouvelle valeur à assigner à ce trou.
     */
    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    /**
     * Vérifie si ce trou est recoltable.
     *
     * @return true si la valeur de ce trou est 2 ou 3, sinon false.
     */
    public boolean isRecoltable() {
        return (valeur == 2 || valeur == 3);
    }

    /**
     * Vérifie si ce trou est affamé.
     *
     * @return true si la valeur de ce trou est 0, sinon false.
     */
    public boolean isAffamed() {
        return valeur == 0;
    }
}

