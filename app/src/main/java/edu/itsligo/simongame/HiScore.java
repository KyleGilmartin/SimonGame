package edu.itsligo.simongame;

public class HiScore {
    int  score_id;          // primary score
    String game_date;       // date in 02 DEC 2020
    String player_name;     // JOE
    int score;              // score - should be even

    /*
     * Constructors
     */
    public HiScore(int score_id, String game_date, String player_name, int score) {
        this.score_id = score_id;
        this.game_date = game_date;
        this.player_name = player_name;
        this.score = score;
    }
    public HiScore() {
    }

    /*
     * Getter and setter methods
     */
    public HiScore(String game_date, String player_name, int score) {
        this.game_date = game_date;
        this.player_name = player_name;
        this.score = score;
    }

    public int getScore_id() {
        return score_id;
    }

    public void setScore_id(int score_id) {
        this.score_id = score_id;
    }

    public String getGame_date() {
        return game_date;
    }

    public void setGame_date(String game_date) {
        this.game_date = game_date;
    }

    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


}
