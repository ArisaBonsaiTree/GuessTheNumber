package com.av.guessTheNumber.entity;

import javax.persistence.*;

@Entity
@Table(name = "Game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gameId", nullable = false)
    private Integer gameId;



}
