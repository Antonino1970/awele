package AweleModel;

import java.sql.Date;

public class ScoreDataClass {
    private int idScore;
    private String pseudoJ1;
    private String pseudoJ2;
    private Date date;

    private int scoreJ1;
    private int scoreJ2;

    public ScoreDataClass(int idScore,String pseudoJ1, int scoreJ1, Date date, String pseudoJ2, int scoreJ2) {
        this.idScore = idScore;
        this.pseudoJ1 = pseudoJ1;
        this.scoreJ1 = scoreJ1;
        this.date = date;
        this.pseudoJ2 = pseudoJ2;
        this.scoreJ2 = scoreJ2;
    }
    public ScoreDataClass(String pseudoJ1, int scoreJ1, Date date, String pseudoJ2, int scoreJ2) {

        this.pseudoJ1 = pseudoJ1;
        this.scoreJ1 = scoreJ1;
        this.date = date;
        this.pseudoJ2 = pseudoJ2;
        this.scoreJ2 = scoreJ2;
    }
    public int getIdScore() {
        return idScore;
    }
    public String getPseudoJ1() {
        return pseudoJ1;
    }
    public String getPseudoJ2() {
        return pseudoJ2;
    }
    public Date getDate() {
        return date;
    }
    public int getScoreJ1() {
        return scoreJ1;
    }
    public int getScoreJ2() {
        return scoreJ2;
    }

}
