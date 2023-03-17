package com.av.guessTheNumber.dao;

import com.av.guessTheNumber.entity.Game;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(scripts = "/clear_database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class GameDaoDBTest {

    @Autowired
    GameDao gameDao;

    @Test
    void addGame() {
        Game game = new Game(1, "9876", true);
        game = gameDao.addGame(game);
        Game getGame = gameDao.getGameById(game.getGameId());
        // Had to override our equals method to get this to work!
        assertEquals(game, getGame);
    }

    @Test
    @Transactional
    void getAllGames() {
        Game game = new Game();
        game = gameDao.addGame(game);

        Game game2 = new Game();
        game = gameDao.addGame(game2);

        List<Game> games = gameDao.getAllGames();
        System.out.println(games.size());
        assertEquals(2, games.size());
    }

    @Test
    void getGameById() {
        Game game1 = new Game();
        Game game2 = new Game();
        Game game3 = new Game();
        Game game4 = new Game();
        Game game5 = new Game();

        gameDao.addGame(game1);
        gameDao.addGame(game2);
        gameDao.addGame(game3);
        gameDao.addGame(game4);
        gameDao.addGame(game5);


        assertEquals(game5.getGameId(), 5);
    }

    @Test
    void updateGame() {
        gameDao.addGame(new Game());
        Game getGame = gameDao.getGameById(1);

        assertTrue(getGame.isInProgress());

        getGame.setInProgress(false);
        gameDao.updateGame(getGame);

        assertFalse(getGame.isInProgress());
    }
}