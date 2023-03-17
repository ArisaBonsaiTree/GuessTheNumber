package com.av.guessTheNumber.service;

import com.av.guessTheNumber.dao.GameDao;
import com.av.guessTheNumber.dao.RoundDao;
import com.av.guessTheNumber.entity.Game;
import com.av.guessTheNumber.entity.Guess;
import com.av.guessTheNumber.entity.Round;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(scripts = "/clear_database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class ServiceLayerTest {

    private final String REDACTED = "****";

    @Autowired
    ServiceLayer serviceLayer;

    @Autowired
    GameDao gameDao;

    @Autowired
    RoundDao roundDao;


    @Test
    void beginGame() {
        String result = serviceLayer.beginGame();
        assertEquals(result, "Game 1 has been created!");

        String resultTwo = serviceLayer.beginGame();
        assertEquals(resultTwo, "Game 2 has been created!");
    }

    @Test
    void getAllGames() {
        List<Game> games = serviceLayer.getAllGames();
        assertEquals(0, games.size());

        serviceLayer.beginGame();
        serviceLayer.beginGame();
        serviceLayer.beginGame();
        List<Game> gamesAdded = serviceLayer.getAllGames();

        assertEquals(3, gamesAdded.size());
    }

    @Test
    void makeGuess() {
        serviceLayer.beginGame();

        Guess guess = new Guess(1, "1234");

        Round round = serviceLayer.makeGuess(guess);

        assertEquals(round.getGameId(), serviceLayer.getGameById(1).getGameId());

    }

    @Test
    void getGameById() {
        serviceLayer.beginGame();
        Game game = serviceLayer.getGameById(1);
        assertEquals(REDACTED, game.getGeneratedNumber());
    }

    @Test
    void getRoundsForGame() {
        serviceLayer.beginGame();
        Guess guess = new Guess(1, "1234");
        Guess guess2 = new Guess(1, "1234");
        Guess guess3 = new Guess(1, "1234");

        serviceLayer.makeGuess(guess);
        serviceLayer.makeGuess(guess2);
        serviceLayer.makeGuess(guess3);

        List<Round> rounds = serviceLayer.getRoundsForGame(1);
        assertEquals(rounds.size(), 3);



    }
}