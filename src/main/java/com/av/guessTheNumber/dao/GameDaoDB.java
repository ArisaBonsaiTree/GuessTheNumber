package com.av.guessTheNumber.dao;

import com.av.guessTheNumber.entity.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class GameDaoDB implements GameDao{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public Game addGame(Game game) {
        final String ADD_GAME = "INSERT INTO game(numberGenerated, inProgress) VALUES (?, ?)";
        jdbcTemplate.update(ADD_GAME, game.getGeneratedNumber(), true);
        game.setGameId(getLastGameId());

        return game;
    }


    @Override
    public List<Game> getAllGames() {
        final String GET_ALL_GAMES = "SELECT * FROM game";
        return jdbcTemplate.query(GET_ALL_GAMES, new GameMapper());
    }


    @Override
    public Game getGameById(int gameId) {
        try{
            final String GAME_BY_ID = "SELECT * FROM game WHERE gameId = ?";
            return jdbcTemplate.queryForObject(GAME_BY_ID, new GameMapper(), gameId);
        }catch (DataAccessException e) {
            return null;
        }
    }


    private int getLastGameId() {
        final String LAST_GAME_ID = "SELECT gameId FROM game ORDER BY gameId DESC LIMIT 1";
        Integer lastGameId = jdbcTemplate.queryForObject(LAST_GAME_ID, Integer.class);
        return lastGameId != null ? lastGameId : 0;
    }

    private static final class GameMapper implements RowMapper<Game>{
        @Override
        public Game mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Game(rs.getInt("gameId"),
                    rs.getString("numberGenerated"),
                    rs.getBoolean("inProgress")
            );
        }
    }

    @Override
    public void updateGame(Game game) {
        final String CHANGE_PROGRESS = "UPDATE game SET inProgress = ? WHERE gameId = ?";
        jdbcTemplate.update(CHANGE_PROGRESS, game.isInProgress(), game.getGameId());
    }
}
