package ITLG.toni.aweleClientFx;
/**---------------------------------------------------------------
 * La classe ScoreData représente les champs de la table des scores sql.
 *  Auteur : Umina Antonino
 *  Jeu Awele : Client FX
 *  Date : 09/06/2024
 *  -------------------------------------------------------------
 */
public class ScoreData {
    private String j1  ;
    private int    sc1 ;
    private String date;
    private String j2  ;
    private int    sc2 ;
    // constructeur
    public ScoreData(String champ1, int champ2, String champ3, String champ4, int champ5) {
        this.j1   = champ1;
        this.sc1  = champ2;
        this.date = champ3;
        this.j2   = champ4;
        this.sc2  = champ5;
    }

    public String getJ1() {
        return j1;
    }

    public int getSc1() {
        return sc1;
    }

    public String getDate() {
        return date;
    }

    public String getJ2() {
        return j2;
    }

    public int getSc2() {
        return sc2;
    }

}
