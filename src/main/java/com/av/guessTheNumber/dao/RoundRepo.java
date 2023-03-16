package com.av.guessTheNumber.dao;

import com.av.guessTheNumber.entity.Round;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoundRepo extends JpaRepository<Round, Integer> {
}
