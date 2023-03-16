package com.av.guessTheNumber.dao;

import com.av.guessTheNumber.entity.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RoundDaoDB implements RoundDao{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Round> getRoundsByGameId(int gameId) {
        try{
            final String ROUND_BY_ID = "SELECT * FROM round WHERE gameId = ? ORDER BY createdAt";
            List<Round> rounds = jdbcTemplate.query(ROUND_BY_ID, new RoundMapper(), gameId);
            return rounds;
        }catch (DataAccessException e){
            return null;
        }
    }

    public static final class RoundMapper implements RowMapper<Round>{
        @Override
        public Round mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Round(
                    rs.getInt("roundId"),
                    rs.getInt("gameId"),
                    rs.getString("userGuess"),
                    rs.getTimestamp("createdAt").toLocalDateTime()
            );
        }
    }
}
