package com.av.guessTheNumber.entity;

import java.time.LocalDateTime;
import java.util.Date;

public class Round {
    private int roundId;
    private int gameId;

    private String userGuess;

    private int partialCorrect;
    private int exactCorrect;

    private LocalDateTime timeStamp;


    public Round(int roundId, int gameId, String userGuess, LocalDateTime timeStamp) {
        this.roundId = roundId;
        this.gameId = gameId;
        this.userGuess = userGuess;
        this.timeStamp = timeStamp;
    }

    public Round(int roundId, int gameId, String userGuess, int partialCorrect, int exactCorrect, LocalDateTime timeStamp) {
        this.roundId = roundId;
        this.gameId = gameId;
        this.userGuess = userGuess;
        this.partialCorrect = partialCorrect;
        this.exactCorrect = exactCorrect;
        this.timeStamp = timeStamp;
    }

    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
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

    public int getPartialCorrect() {
        return partialCorrect;
    }

    public void setPartialCorrect(int partialCorrect) {
        this.partialCorrect = partialCorrect;
    }

    public int getExactCorrect() {
        return exactCorrect;
    }

    public void setExactCorrect(int exactCorrect) {
        this.exactCorrect = exactCorrect;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}
