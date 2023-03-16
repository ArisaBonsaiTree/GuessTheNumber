package com.av.guessTheNumber.dao;

import com.av.guessTheNumber.entity.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class RoundDaoDB implements RoundDao{

    @Autowired
    JdbcTemplate jdbcTemplate;


    public static final class RoundMapper implements RowMapper<Round>{
        @Override
        public Round mapRow(ResultSet rs, int rowNum) throws SQLException {
            Round round = new Round();
            round.setRoundId(rs.getInt("roundId"));
            round.setGameId(rs.getInt("gameId"));
            round.setUserGuess(rs.getString("userGuess"));

            round.setTimeStamp(rs.getTimestamp("createdAt").toLocalDateTime());

            return round;

        }
    }
}
