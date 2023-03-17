package com.av.guessTheNumber.entity;

public class Guess {
    private int gameId;
    private String userGuess;

    public Guess(int gameId, String userGuess) {
        this.gameId = gameId;
        this.userGuess = userGuess;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getUserGuess() {
        return userGuess;
    }

    public void setUserGuess(String userGuess) {
        this.userGuess = userGuess;
    }
}
