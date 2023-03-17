package com.av.guessTheNumber.service;

import com.av.guessTheNumber.dao.GameDao;
import com.av.guessTheNumber.dao.RoundDao;
import com.av.guessTheNumber.entity.Game;
import com.av.guessTheNumber.entity.Guess;
import com.av.guessTheNumber.entity.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
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
        Game game = gameDao.getGameById(guess.getGameId());
        if(game == null || guess.getUserGuess() == null){
            return null;
        }

        String answer = game.getGeneratedNumber();
        String userGuess = guess.getUserGuess();

        // Suppose the answer is "1234"
        // 1 -> 0
        // 2 -> 1
        // Key is the digit and value is their digit place

        Map<String, String> answerKey = new HashMap<>();
        for(int i = 0; i < answer.length(); i++){
            answerKey.put(answer.substring(i, i + 1), String.valueOf(i));
        }

        int exact = 0;
        int partial = 0;

        for(int i = 0; i < userGuess.length(); i++){
            String currentDigit = userGuess.substring(i, i + 1);

            if(answerKey.containsKey(currentDigit)){
                if(answerKey.get(currentDigit).equals(i)){
                    exact++;
                }else{
                    partial++;
                }
            }
        }

        if(exact == 4){
            game.setInProgress(false);
            gameDao.updateGame(game);
        }

        return roundDao.addRound(new Round(game.getGameId(), userGuess, exact, partial));

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
