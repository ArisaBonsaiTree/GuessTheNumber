package com.av.guessTheNumber.entity;

public class Game {

    private int gameId;
    private String generatedNumber;
    private boolean inProgress;



    public Game(int gameId, String generatedNumber, boolean inProgress) {
        this.gameId = gameId;
        this.generatedNumber = generatedNumber;
        this.inProgress = inProgress;
    }

    public Game() {

    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public boolean isInProgress() {
        return inProgress;
    }

    public void setInProgress(boolean inProgress) {
        this.inProgress = inProgress;
    }

    public String getGeneratedNumber() {
        return generatedNumber;
    }

    public void setGeneratedNumber(String generatedNumber) {
        this.generatedNumber = generatedNumber;
    }
}
