package com.av.guessTheNumber.service;

import com.av.guessTheNumber.dao.GameDao;
import com.av.guessTheNumber.dao.RoundDao;
import com.av.guessTheNumber.entity.Game;
import com.av.guessTheNumber.entity.Guess;
import com.av.guessTheNumber.entity.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        if(game == null || guess.getUserGuess() == null) return null;

        String answer = game.getGeneratedNumber();
        String userGuess = guess.getUserGuess();

        Map<String, Integer> answerKey = new HashMap<>();

        for (int i = 0; i < answer.length(); i++) {
            String digit = answer.substring(i, i + 1);
            answerKey.put(digit, answerKey.getOrDefault(digit, 0) + 1);
        }

        int exact = 0;
        int partial = 0;

        Map<String, Integer> remainingDigits = new HashMap<>(answerKey);

        for (int i = 0; i < userGuess.length(); i++) {
            String digit = userGuess.substring(i, i + 1);

            if (answerKey.containsKey(digit)) {

                if (answer.substring(i, i + 1).equals(digit)) {
                    exact++;
                    remainingDigits.put(digit, remainingDigits.get(digit) - 1);
                } else if (remainingDigits.getOrDefault(digit, 0) > 0) {
                    partial++;
                    remainingDigits.put(digit, remainingDigits.get(digit) - 1);
                }
            }
        }
        // Round Id is unknwon
        Round round = new Round();

        round.setGameId(game.getGameId());
        round.setUserGuess(userGuess);
        round.setPartialCorrect(partial);
        round.setExactCorrect(exact);
        round.setTimeStamp(LocalDateTime.now());

        if(exact == game.getGeneratedNumber().length()){
            game.setInProgress(false);
            gameDao.updateGame(game);
        }

        return roundDao.addRound(round);
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
