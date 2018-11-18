package com.example.vicnjake.planethop;

public class GameInfo {
    private boolean isPlaying;
    private int score;
    private int hiscore;

    private final int MIN_GRAVITY_DIFF = 30;
    private final int MAX_GRAVITY_DIFF = 250;
    private final int MIN_PLANET_SIZE = 100;
    private final int MAX_PLANET_SIZE = 300;

    public GameInfo () {
        isPlaying = false;
        score = 0;
    }

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
