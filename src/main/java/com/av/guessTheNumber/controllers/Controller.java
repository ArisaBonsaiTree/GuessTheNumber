package com.av.guessTheNumber.controllers;

import com.av.guessTheNumber.entity.Game;
import com.av.guessTheNumber.entity.Guess;
import com.av.guessTheNumber.entity.Round;
import com.av.guessTheNumber.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    ServiceLayer serviceLayer;


    @PostMapping("/begin")
    public String beginGame(){
        return serviceLayer.beginGame();
    }

    @PostMapping("/guess")
    public Round makeGuess(@RequestBody Guess guess){
        return serviceLayer.makeGuess(guess);

    }


    // game
    @GetMapping("/game")
    public List<Game> getAllGames(){
        return serviceLayer.getAllGames();
    }

    // game/{gameId}


    // round/{gameId}

}
