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

    @Override
    public String toString() {
        return "Game{" +
                "gameId=" + gameId +
                ", generatedNumber='" + generatedNumber + '\'' +
                ", inProgress=" + inProgress +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()){
            return false;
        }

        Game otherGameObject = (Game) object;

        return gameId == otherGameObject.gameId && inProgress == otherGameObject.inProgress &&
                (generatedNumber != null ? generatedNumber.equals(otherGameObject.generatedNumber) : otherGameObject.generatedNumber == null);
    }
}
