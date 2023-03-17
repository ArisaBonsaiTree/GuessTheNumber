package com.av.guessTheNumber.dao;

import com.av.guessTheNumber.entity.Round;

import java.util.List;

public interface RoundDao {
    List<Round> getRoundsByGameId(int gameId);

    Round addRound(Round round);
}
