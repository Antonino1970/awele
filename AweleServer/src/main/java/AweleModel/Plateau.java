package AweleModel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**---------------------------------------------------------------
 *  Auteur : Umina Antonino
 *  Jeu Awele : Serveur REST
 *  Date : 21/04/2024
 *  class Plateau : support qui contient les trous du jeu
 *  -------------------------------------------------------------
 */
public class Plateau {
    private Trou[] tabJ1Reel; // Tableau représentant les trous du joueur 2.
    private Trou[] tabJ2Reel; // Tableau représentant les trous du joueur 1.
    private Trou[] tabLigneTest; // Tableau représentant la ligne complète du plateau.
    private final int nbrTrou       = 6   ; // Nombre de trous par joueur.
    private final int graineDepart  = 4   ; // Nombre initial de graines par trou.
    private String    cheminFichier = "template/initTab.txt"; //fichier de remplissage du tableau

    /**
     * Constructeur de la classe Plateau.
     * Initialise les tableaux et remplit le plateau, soit avec initPlateau via un fichier texte,
     * soit via remplirTableau via les graines de départ.
     * Il faut ensuite actualiser les tabj avec copyTabLigneToTabj() dans le 1er cas,
     * avec copyTabjToTabLigne(tabLigne) dans l'autre cas
     */
    public Plateau() {
        tabJ1Reel = new Trou[nbrTrou]   ;
        for (int i = 0; i < tabJ1Reel.length; i++) {
            tabJ1Reel[i] = new Trou(graineDepart);
        }
        tabJ2Reel = new Trou[nbrTrou]   ;
        for (int i = 0; i < tabJ2Reel.length; i++) {
            tabJ2Reel[i] = new Trou(graineDepart);
        }
        tabLigneTest = new Trou[nbrTrou*2] ;
        for (int i = 0; i < tabLigneTest.length; i++) {
                tabLigneTest[i] = new Trou(graineDepart);
        }
        //remplirPlateau(); //remplissage via une valeur de départ pour tabj1 et tabj2
        try {
            initPlateauFile(); //via fichier txt

        } catch (IOException e) {
        e.printStackTrace();
        }
        copyTabLigneToTabj();
        //copyTabjToTabLigne(tabLigne); //copie des tabj vers tabligne
    }

    //get
    //---------
    public Trou[] getTabJ1Reel() {
        return tabJ1Reel;
    }
    public Trou[] getTabJ2Reel() {
        return tabJ2Reel;
    }

    /**
     * Méthode pour obtenir le tableau représentant la ligne du plateau.
     * @return Le tableau représentant la ligne du plateau.
     */
    public Trou[] getTabLigneTest() {
        return tabLigneTest;
    }

    /**
     * Méthode pour obtenir le nombre de trous de chaque ligne sur le plateau.
     * @return Le nombre de trous sur le plateau.
     */
    public int getNbrTrou() {
        return nbrTrou ;
    }

    /**
     * Méthode pour obtenir la ligne correspondante à une ligne spécifiée.
     * @param ligne La ligne spécifiée (Ligne.Ligne0 ou Ligne.Ligne1).
     * @return Le tableau représentant la ligne correspondante.
     */
    public Trou[] getLigne(Ligne ligne) {
        return (ligne == Ligne.Ligne0) ? tabJ1Reel : tabJ2Reel;
    }

    /**
     * Méthode pour retourner l'indice de début d'une ligne spécifiée.
     * @param ligne La ligne spécifiée (Ligne.Ligne0 ou Ligne.Ligne1).
     * @return L'indice de début de la ligne spécifiée.
     */
    public int returnIndiceJoueur(Ligne ligne ) {
        return (ligne == Ligne.Ligne0) ? 0 : nbrTrou ;
    }

    /**
     * Retourne l'indice correspondant à un trou choisi dans une ligne spécifiée.
     * @param ligne La ligne spécifiée (Ligne.Ligne0 ou Ligne.Ligne1).
     * @param trou  Le trou choisi.
     * @return L'indice du tableau complet par rapport au trou choisi dans la ligne spécifiée.
     */
    public int returnIndice(Ligne ligne , int trou) {
        if (ligne == Ligne.Ligne0) {
            return 0+trou;
        } else return nbrTrou+trou;
    }

    //méthodes---------------------------------
    //-----------------------------------------


    /**
     * Méthode pour initialiser le plateau à partir d'un fichier texte.
     * @throws IOException Si une erreur d'entrée/sortie se produit lors de la lecture du fichier.
     */
    public void initPlateauFile() throws IOException     {
        try (BufferedReader buffReader = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            while ((ligne = buffReader.readLine()) != null) {
                String[] valeursString = ligne.split("\\|");
                for (int i = 0 ; i<nbrTrou*2 ; i++) {
                    tabLigneTest[i] = new Trou(Integer.valueOf(valeursString[i]));
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Remplit le plateau de jeu en initialisant les trous des joueurs avec un nombre de graines spécifié.
     */
    public void remplirPlateau() {
        for (int i = 0 ; i<nbrTrou ; i++) {
            tabJ1Reel[i] = new Trou(graineDepart);
            tabJ2Reel[i] = new Trou(graineDepart);
        }
    }
    /**
     * Remplit la ligne complète du plateau avec un nombre de graines spécifié.
     */
    public void remplirLigne() {
        for (int i = 0 ; i<nbrTrou*2 ; i++) {
            tabLigneTest[i] = new Trou(graineDepart);
        }
    }

    /**
     * Synchronise les lignes des joueurs avec la ligne complète du plateau.
     */
    public void copyTabLigneToTabj(){
        for (int i = 0; i<nbrTrou ; i++){
            tabJ1Reel[i].setValeur(tabLigneTest[i].getValeur());
            tabJ2Reel[i].setValeur(tabLigneTest[i+nbrTrou].getValeur());

        }
    }

    /**
     * Synchronise un tableau de ligne complète avec les lignes des joueurs.
     * @return void
     */
    public void copyTabjToTabLigne(){
        for (int i = 0; i < tabLigneTest.length/2 ; i++) {
            tabLigneTest[i].setValeur(tabJ1Reel[i].getValeur())  ;
            tabLigneTest[i+nbrTrou].setValeur(tabJ2Reel[i].getValeur());
        }
    }
    /**
     * Affiche les scores des joueurs et un tableau de lignes qui simule un plateau de jeu awelé.
     * @param tab le tableau tabLigne qui sera splité en 2 parties.
     * @param j l'instance du jeu pour récupèré les scores des joueurs.
     * @return void
     */
    public void afficherPlateauJeu(Trou[] tab, Jeu j) {
        System.out.println("|| Score de "+j.getJoueur2().getPseudo()+" = "+j.getJoueur2().getScore()+" ||");
        //System.out.println("--------------------------------------");
        System.out.println("---6-----5-----4-----3-----2-----1----");
        System.out.println("--------------------------------------");
        System.out.print("|| ");
        for (int i= (tab.length/2)-1; i>=0 ; i--) {
            if (tabJ1Reel[i].getValeur()<10) {
                System.out.print(tabJ1Reel[i].getValeur() + "  || ");
            }
            else
                System.out.print(tabJ1Reel[i].getValeur() + " || ");
        }
        System.out.println();
        System.out.print("|| ");
        for (int i=0 ; i<(tab.length)/2 ; i++) {
            if (tabJ2Reel[i].getValeur()<10) {
                System.out.print(tabJ2Reel[i].getValeur() + "  || ");
            }
            else
                System.out.print(tabJ2Reel[i].getValeur() + " || ");
        }
        System.out.println();
        System.out.println("--------------------------------------");
        System.out.println("---1-----2-----3-----4-----5-----6----");
        //System.out.println("--------------------------------------");
        System.out.println("|| Score de "+j.getJoueur1().getPseudo()+" = "+j.getJoueur1().getScore()+" ||");
    }

    /**
     * Vérifie si le joueur est affamé, c'est-à-dire s'il ne possède aucune graine dans ses trous.
     * @param joueur Le joueur dont on souhaite vérifier s'il est affamé.
     * @return true si le joueur est affamé, sinon false.
     */
   public boolean estJoueurAffame(Joueur joueur) {
        int j = returnIndiceJoueur(joueur.getLigneJoueur());
        int tot = 0;
        for (int i = j;i<nbrTrou+j;i++) {
            tot += tabLigneTest[i].getValeur();
        }
        return tot == 0 ;
    }

    /**
     * Vérifie si le joueur peut nourrir son adversaire en semant des graines dans ses trous.
     * @param joueur Le joueur dont on souhaite vérifier s'il peut nourrir son adversaire.
     * @return true si le joueur peut nourrir son adversaire, sinon false.
     */
    public boolean peutNourrirAdversaire(Joueur joueur){
        Trou[] tab = getLigne(joueur.getLigneJoueur());
        for (int i =0 ;i<tab.length;i++){
            if(tab[i].getValeur()>=nbrTrou-i)
                return true;
        }
        return false;
    }

    /**
     * Redistribue les graines dans la ligne spécifiée en sautant l'indice de départ.
     * @param indiceLigne L'indice de départ pour la redistribution.
     * @return L'indice final après la redistribution.
     */
    public int semer(int indiceLigne ) {
        int graines = tabLigneTest[indiceLigne].getValeur();
        int indiceDepart = indiceLigne;
        tabLigneTest[indiceLigne].setValeur(0);
        while (graines > 0) {
            indiceLigne++;
            if (indiceLigne==indiceDepart) {
                indiceLigne++;
            }
            if (indiceLigne==nbrTrou*2)  {
                indiceLigne= 0;
            }
            int newValeur = tabLigneTest[indiceLigne].getValeur();
            tabLigneTest[indiceLigne].setValeur(newValeur+1);
            graines--;
        }
        return  indiceLigne ;
    }

    /**
     * Vérifie si un trou spécifié dans une ligne est affamé, c'est-à-dire s'il ne contient aucune graine.
     * @param trou Le trou à vérifier.
     * @return true si le trou est affamé, sinon false.
     */
    public boolean estTrouAffame(Trou trou) {
        return trou.isAffamed();
    }
    /**
     * Vérifie si les graines dans un trou spécifié sont recoltables par un joueur.
     * @param trou le trou à vérifier.
     * @return true si les graines sont recoltables, sinon false.
     */
    public boolean recoltable(Trou trou) {
        return trou.isRecoltable();
    }

    /**
     * Vérifie si un trou spécifié dans la ligne appartient au champ adverse du joueur courant.
     * @param indiceLigne L'indice du trou à vérifier.
     * @param ligneCourante La ligne du joueur courant.
     * @return true si le trou appartient au champ adverse, sinon false.
     */
    public boolean champsAdverse(int indiceLigne, Ligne ligneCourante) {
        return ((indiceLigne<nbrTrou) ? Ligne.Ligne0 : Ligne.Ligne1) != ligneCourante;
    }

    /**
     * Récolte les graines d'un trou spécifié dans la ligne.
     * @param indiceLigne L'indice du trou à récolter.
     * @return Le nombre de graines récoltées.
     */
    public int recolter(int indiceLigne) {
        int score ;
        score = tabLigneTest[indiceLigne].getValeur();
        tabLigneTest[indiceLigne].setValeur(0);
        return score ;
    }
}

