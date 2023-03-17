package com.av.guessTheNumber.service;

import com.av.guessTheNumber.dao.GameDao;
import com.av.guessTheNumber.dao.RoundDao;
import com.av.guessTheNumber.entity.Game;
import com.av.guessTheNumber.entity.Guess;
import com.av.guessTheNumber.entity.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceLayer {
    private static String REDACTED = "****";

    @Autowired
    GameDao gameDao;

    @Autowired
    RoundDao roundDao;

    public String beginGame() {
        Game game = new Game();
        game.setGeneratedNumber(generateRandomNumber());
        game = gameDao.addGame(game);

        return "Game " + game.getGameId() + " has been created!";
    }

    public List<Game> getAllGames(){
        List<Game> games = gameDao.getAllGames();

        for(Game game: games){
            if(game.isInProgress()){
                game.setGeneratedNumber(REDACTED);
            }
        }
        return games;
    }


    public Round makeGuess(Guess guess) {
        return null;
    }



    private String generateRandomNumber(){
        List<Integer> digits = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            digits.add(i);
        }
        Collections.shuffle(digits);
        return "" + digits.get(0) + digits.get(1) + digits.get(2) + digits.get(3);
    }

    public Game getGameById(int gameId) {
        Game game = gameDao.getGameById(gameId);

        if(game.isInProgress()){
            game.setGeneratedNumber(REDACTED);
        }

        return game;
    }

    public List<Round> getRoundsForGame(int gameId) {
        return roundDao.getRoundsByGameId(gameId);
    }
}
