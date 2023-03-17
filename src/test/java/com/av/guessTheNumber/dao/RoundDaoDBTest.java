package com.av.guessTheNumber.dao;

import com.av.guessTheNumber.entity.Game;
import com.av.guessTheNumber.entity.Round;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(scripts = "/clear_database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class RoundDaoDBTest {

    @Autowired
    RoundDao roundDao;

    @Autowired
    GameDao gameDao;


    private String[] userGuess = {"5276", "8613", "3970", "4812", "5902", "4637", "2169", "7396", "9507", "6084"};
    private Random random = new Random();
    private final int MAX_LOOP = 5;

    @Test
    void getRoundsByGameId() {
        Game game = new Game(1, "1234", true);
        gameDao.addGame(game);


        for (int i = 0; i < MAX_LOOP; i++) {
            Round round = new Round();

            round.setGameId(game.getGameId());
            round.setUserGuess(userGuess[random.nextInt(userGuess.length)]);
            round.setPartialCorrect(0);
            round.setExactCorrect(4);
            round.setTimeStamp(LocalDateTime.now());

            roundDao.addRound(round);
        }

        List<Round> rounds = roundDao.getRoundsByGameId(1);
        assertEquals(rounds.size(), MAX_LOOP);


    }

    @Test
    void addRound() {
        Game game = new Game(1, "1234", true);
        gameDao.addGame(game);

        Round round = new Round();

        round.setGameId(game.getGameId());
        round.setUserGuess("1234");
        round.setPartialCorrect(0);
        round.setExactCorrect(4);
        round.setTimeStamp(LocalDateTime.now());
        roundDao.addRound(round);

        assertEquals(game.getGameId(), round.getGameId());

    }
}