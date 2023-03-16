package com.av.guessTheNumber.dao;

import com.av.guessTheNumber.entity.Game;

import java.util.List;

public interface GameDao {
    List<Game> getAllGames();

    Game addGame(Game game);
}
