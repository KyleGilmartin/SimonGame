package edu.itsligo.simongame;

public class GetterSetter {
    private String player_name;
    private int score;

    public GetterSetter(String player_name, int score) {
        this.player_name = player_name;
        this.score = score;
    }

    public GetterSetter() {

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
