package AweleModel;

import io.swagger.model.RestBoard;
import io.swagger.model.RestPlayer;

import java.sql.Date;
import java.time.LocalDate;

/**---------------------------------------------------------------
 * La classe Jeu représente le jeu d'Awele.
 *  Auteur : Umina Antonino
 *  Jeu Awele : Serveur REST
 *  Date : 21/04/2024
 *  -------------------------------------------------------------
 */

public class Jeu {
    // Instance unique du jeu (Singleton)
    private static Jeu uniqueInstance;
    // Plateau de jeu
    private Plateau plateau;
    // Joueur humain
    private Joueur joueur1;
    // Joueur ordinateur ou humain
    private Joueur joueur2;
    // l'indice dela ligne temporaire
    private int indiceLigne;
    // l'indice du trou de la ligne du joueur
    private int indiceTrou;
    // variable int de la valeur recolté
    private int recolte = 0 ;
    // status du jeu -1 not run, 0 = run, 1 = player1 win, 2 = player2 win , 3 = both win
    private int status;
    // tour 0 =  tour joueur id 0 , tour 1 = tour joueur id  2
    private int turn = 0 ;
    // tableau complet passé au client
    private RestBoard restBoard ;
    private RestPlayer player1 ;
    private RestPlayer player2 ;
    private int trouJouer ;




    /**
     * Constructeur privé de la classe Jeu.
     */
    private Jeu() {
        plateau = new Plateau();
        status = -1 ;
        }

    /**
     * getter de la classe Jeu.
     */
    public int getTurn() {
        return turn;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    /**
     * setter de la classe Jeu.
     */

    public void setNewJoueur1(String nom) {
        joueur1 = new JoueurHumain(nom);
    }
    public void setNewJoueur2(String nom) {
        joueur2 = new JoueurHumain(nom);
    }
    public void setNewComputer() {
        joueur2 = new Computer();
        player2 = new RestPlayer();
        player2.score(0);
        player2.id(1);
        player2.setName(joueur2.getPseudo());
        System.out.println("AI connecté");
    }

    /**
     * Méthode pour obtenir le joueur ordinateur.
     * @return Le joueur 2 (ici AI ou joueur humain 2)
     */
    public Joueur getJoueur2() {
        return this.joueur2;
    }

    /**
     * Méthode pour obtenir le joueur humain. *
     * @return Le joueur humain
     */
    public Joueur getJoueur1() {
        return this.joueur1;
    }
    /**
     * Méthode permettant d'obtenir l'instance unique du jeu.
     * @return L'instance unique du jeu
     */
    public static Jeu getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Jeu();
        }
        return uniqueInstance;
    }

    /**
     * Méthode pour permettre au joueur de jouer un trou choisi. Redemande tant que le trou choisi est à zéro
     * @param joueurCourant Le joueur actuel
     * @param trouPasser trou choisi par le joueur
     * @return l'indice dans tabLigne du trou choisi.
     */
    public int jeuChoisirTrou(Joueur joueurCourant,int trouPasser){
        indiceTrou  = joueurCourant.joueurChoisirUnTrou(plateau.getNbrTrou(),trouPasser);
        if (joueurCourant instanceof Computer){
            while (plateau.estTrouAffame(plateau.getTabLigneTest()[indiceTrou])) {
                System.out.println("ce trou est affamé !!!");
                indiceTrou = joueurCourant.joueurChoisirUnTrou(plateau.getNbrTrou(), trouPasser);
            }
        }
        return indiceTrou;
    }

    /**
     * Méthode pour incrémenter le score d'un joueur.
     * @param joueur Le joueur dont le score doit être incrémenté
     * @param recolte Le nombre de graines récoltées
     */
    public void incrementeScore(Joueur joueur, int recolte) {
        joueur.incrementeScoreJoueur(recolte);
    }


    /**
     * Méthode de semailles des graines.
     * @param trou Le trou à semer
     * @param id L'identifiant du joueur
     * @return Le statut du jeu après la semaille
     */
    public int serveurSeme(int trou,int id){
        if (id == turn && status == 0) { // si le tour est celui de l'id du joueur et que le jeu est en cours
            Ligne ligneCourante = (turn == 0) ? Ligne.Ligne1 : Ligne.Ligne0;
            Joueur joueurCourant = (joueur1.getId() == turn) ? joueur1 : joueur2;
            Joueur joueurAdverse = (joueurCourant == joueur1) ? joueur2 : joueur1;
            trou = jeuChoisirTrou(joueurCourant,  trou);
            // si le joueur adverse est affamé et qu'il ne peut être nourri par le joueur en cours, la partie est finie.
            if (plateau.estJoueurAffame(joueurAdverse) && !plateau.peutNourrirAdversaire(joueurCourant)) {
                System.out.println("joueur " + joueurAdverse.getPseudo() + " est affamé et ne peut pas être nourrit !!! Fin !!! ");
                statusJeux();
                return -1;
            // sinon, on simule le coup
            } else {
                if (plateau.estJoueurAffame(joueurAdverse)) { // avertissement : joueur adverse affamé.
                    System.out.println("joueur " + joueurAdverse.getPseudo() + " est affamé !!! veuillez le nourrir svp ");
                }
                // position sur la ligne de simulation par rapport au joueur en cours.
                indiceLigne = plateau.returnIndice(ligneCourante, trou );
                trouJouer = trou;
                // semaille avec retour du dernier indice ou on a semé
                indiceLigne = plateau.semer(indiceLigne);
                // tant que l'indice est sur le champ adverse est que la valeur est récoltable (2 ou 3), on augmente le score du joueur et on met a 0 la valeur du trou recolter
                while ((plateau.champsAdverse(indiceLigne, ligneCourante)) && (plateau.recoltable(plateau.getTabLigneTest()[indiceLigne]))) {
                    int recolt = plateau.recolter(indiceLigne);
                    recolte = recolt+ recolte ;
                    indiceLigne--;
                    // début du tableau, on retourne à l'indice de fin
                    if (indiceLigne == -1) {
                        indiceLigne = plateau.getNbrTrou() * 2 - 1;
                    }
                }
            }
            // si a la fin de la semaille le joueur adverse est affamé le coup n'est pas valide, on actualise le tableau avec les tableaux réels
            if (plateau.estJoueurAffame(joueurAdverse)) {
                System.out.println("coup invalide , choisir un autre trou ");
                recolte = 0;
                plateau.copyTabjToTabLigne();
                return -2;

            // sinon, on valide le coup, on actualise les tableaux réels et la récolte si > 0
            } else {
                System.out.println(joueurCourant.getPseudo() + " joue le trou " + (trou +1));
                incrementeScore(joueurCourant, recolte);
                if (recolte > 0) {
                    System.out.println(joueurCourant.getPseudo() + " récolte : " + recolte);
                }
                recolte = 0;
                // on met le tableau réel à jour avec tabLigne
                plateau.copyTabLigneToTabj();
                plateau.afficherPlateauJeu(plateau.getTabLigneTest(), this);
                // on change de joueur
                turn = (turn == 0) ? 1 : 0;
            }
            //test des status
            statusJeux();
            if (status >0){
                return status;
            }


            // AI joue, s'il y a un seul joueur humain
            if (joueur2 instanceof Computer && id != turn && status == 0){
                serveurSeme(trou,1);
            }
        }
        return 0 ;
    }

    /**
     * Méthode pour déterminer le statut du jeu.
     */
    public void statusJeux(){
        // test des égalitées
        if (joueur1.getScore() == 24 && joueur2.getScore() == 24) {
            System.out.println(" !!!!!!!!!!!!!! égalitée!!!!!!!!!!!!!");
            addScoreToDB();
            status = 3 ;
        }
        // test si le score d'un joueur dépasse 24
        else if (joueur1.getScore() > 24 || joueur2.getScore() > 24) {
            addScoreToDB();
            if (joueur1.getScore() > 24){
                System.out.println(joueur1.getPseudo()+" !!!!!!!! GAGNE !!!!!!!!!");
                status = 1 ;
            }
           else{
               System.out.println(joueur2.getPseudo()+" !!!!!!!! GAGNE !!!!!!!!!");
                status = 2;
            }

        }
        else {
            status = 0 ;
        }
    }
    /**
     * Méthode pour déterminer le statut du jeu.
     * @return String sur le status du jeu.
     */
    public String statusJeuxStop(){
        // test des égalitées
        if (joueur1.getScore() == joueur2.getScore() ) {
            status = 3 ;
            return "!!!!!!!!!!!!  Egalitée  !!!!!!!!!!!!!!!" ;

        }
        // test si le score d'un joueur dépasse 24
        else if (joueur1.getScore() > joueur2.getScore() ) {
            status = 1 ;
            return  ("!!!!!!!!!!!!!! "+joueur1.getPseudo()+" gagne !!!!!!!!!!!!!!!");
        }
        else {
            status = 2 ;
            return  ("!!!!!!!!!!!!!! "+joueur2.getPseudo()+" gagne  !!!!!!!!!!!!!!!!!!!");
        }
    }


    /**
     * Méthode pour connecter un joueur au jeu.
     * @param name Le nom du joueur
     * @return 0 si le joueur 1 est connecté, 1 si le joueur 2 est connecté, -1 si aucun emplacement disponible
     */
        public int connection(String name){
        if (joueur1 == null) {
            setNewJoueur1(name);
            joueur1.setId(0);
            player1 = new RestPlayer();
            player1.score(0);
            player1.id(0);
            player1.setName(name);
            System.out.println("joueur1 , id : " + player1.getId()+"  connecté avec le pseudo : "+ joueur1.getPseudo());
            return 0;
        }
        else if (joueur2==null) {
            setNewJoueur2(name);
            joueur2.setId(1);
            player2 = new RestPlayer();
            player2.score(0);
            player2.id(1);
            player2.setName(name);
            System.out.println("joueur2 , id : "+ player2.getId() +"  connecté avec le pseudo : "+ joueur2.getPseudo());
            return 1;
        }
        else {
            System.out.println("plus de place");
            return -1;
        }

    }

    /**
     * Méthode pour déconnecter un joueur du jeu.
     * @param id L'identifiant du joueur à déconnecter
     * @return Un message indiquant la déconnexion
     */
    public String deconnecteJoueur(int id) {
        //joueur 1 se déconnecte
        if (joueur1 != null && (joueur1.getId() == id)) {
            joueur1 = null;
            if (joueur2 != null && joueur2 instanceof Computer) {
                joueur2 = null;
                System.out.println("AI déco ");
            }
            status = -1;
            return "joueur 1 déconnecté";
        }
        //joueur 2 se déconnecte
        else if (joueur2 != null && (joueur2.getId() == id)) {
            joueur2 = null;
            status = -1 ;
            return "joueur 2 déconnecté";
        }
        else {
            return "id non attribuée";
        }
    }

    /**
     * Méthode pour convertir le plateau de jeu en un objet RestBoard.
     * @return Un objet RestBoard représentant le plateau de jeu
     */
    public RestBoard setTabToRestBoard(){
        restBoard = new RestBoard();
        for (Trou element : getPlateau().getTabJ1Reel()) {
            restBoard.addHolesPlayer2Item(element.getValeur());
        }
        for (Trou element : getPlateau().getTabJ2Reel()) {
            restBoard.addHolesPlayer1Item(element.getValeur());        }
        return restBoard;
    }

    /**
     * Méthode pour obtenir un objet RestPlayer selon l'identifiant du joueur.
     * @param id L'identifiant du joueur
     * @return Un objet RestPlayer représentant le joueur correspondant à l'identifiant
     */
    public RestPlayer getRestPlayer(int id){
        if (joueur1 != null && joueur1.getId()==id){
            player1.setName(joueur1.getPseudo());
            player1.id(id);
            player1.score(joueur1.getScore());
            return player1;
        }
        else if (joueur2 != null && joueur2.getId()==id){
            player2.setName(joueur2.getPseudo());
            player2.id(id);
            player2.score(joueur2.getScore());
            return player2;
        }
        else {
            return null;
        }
    }
    /**
     * Méthode pour effectuer les tests avant une semaille de graines dans un trou spécifique.
     * @param hole Le trou où effectuer la semaille
     * @param id L'identifiant du joueur
     * @return Le statut du jeu après la semaille
     */
    public int gameSow(int hole,int id ){
        // id 0 ou 1 , le joueur existe ,la partie est est cour ,et c'est son tour.
        if ((id==1||id==0) && getPlayerById(id)!= null && getStatus()==0 && getPlayerById(id).getId() == getTurn()) {
            Ligne ligne = getPlayerById(id).getLigneJoueur();
            Trou t[] = getPlateau().getLigne(ligne);
            // si le trou == 0 on ne joue pas
            if (getPlateau().estTrouAffame(t[hole - 1])) {
                System.out.println("trou jouer affamé");
                return -3 ;

                // si le trou n'est pas affamé on joue'
            } else {
                int response ;
                response = serveurSeme(hole - 1, id);
                return response;
            }
        }
        else {
            System.out.println("invalide !!!!!!");
            return -2 ;
        }
    }

    /**
     * Méthode pour récupérer le joueur correspondant à un identifiant donné.
     * @param id L'identifiant du joueur
     * @return Le joueur correspondant à l'identifiant donné, null si aucun joueur ne correspond
     */
    public Joueur getPlayerById(int id) {
        if (joueur1 != null && joueur1.getId()==id){
            return joueur1;
        }
        else if (joueur2 != null && joueur2.getId()==id){
            return joueur2;
        }
        return null;
    }

    /**
     * Méthode pour démarrer ou arrêter la partie.
     * @param id L'identifiant du joueur
     * @return Un message indiquant le début ou la fin de la partie
     */
    public String jeuStartStop(int id){
        // jeu tourne et je suis connecté, on stoppe
        if (status == 0 && id >=0){ //si joueur est connecté et jeu en cours
            System.out.println(" stop game ");
            System.out.println(statusJeuxStop());
            plateau.afficherPlateauJeu(plateau.getTabLigneTest(), this);
            statusJeux();
            turn = -1 ;
            return statusJeuxStop();
        }
        else { //jeu arrêté et je suis connecté, on démarre
            if (joueur2 != null && id >=0) {
                refreshGame();
                return "Partie démarrée avec 2 joueurs ";
            }
            else if (joueur1 != null && id >=0){
                setNewComputer();
                refreshGame();
                return "Partie démarrée avec 1 joueur et une IA ";
            }
            return "Pas de joueur connecté ";
        }
    }

    /**
     * Méthode pour rafraîchir la partie en réinitialisant le plateau et les autres paramètres.
     */
    public void refreshGame(){
        plateau = new Plateau();
        turn = 0 ;
        status = 0 ;
        joueur1.setScore(0);joueur2.setScore(0);
        //joueur2.resetScore();
        plateau.afficherPlateauJeu(plateau.getTabLigneTest(), this);
    }

        /**
         * Méthode pour ajouter les scores a la db.
         *
         */
    public void addScoreToDB(){
        LocalDate date = LocalDate.now();
        // Score b = new Score("toni", "marc", Date.valueOf(date), 34,5);
        ScoreDataClass score = new ScoreDataClass(joueur1.getPseudo(), joueur1.getScore(), Date.valueOf(date),joueur2.getPseudo(), joueur2.getScore());
        //connexion.addScore(b);
        Connexion connexion = new Connexion("DatabaseScore.db");
        connexion.connect();
        connexion.addScore(score);
        connexion.close();

    }

}