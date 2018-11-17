package com.example.vicnjake.planethop;

public class GameInfo {
    private boolean isPlaying;
    private int score;
    private int hiscore;

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getHiscore() {
        return hiscore;
    }

    public void setHiscore(int hiscore) {
        this.hiscore = hiscore;
    }
}
