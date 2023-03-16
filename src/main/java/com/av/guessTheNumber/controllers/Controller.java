package com.av.guessTheNumber.controllers;

import com.av.guessTheNumber.entity.Game;
import com.av.guessTheNumber.entity.Guess;
import com.av.guessTheNumber.entity.Round;
import com.av.guessTheNumber.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    ServiceLayer serviceLayer;

    // POST – Starts a game, generates an answer, and sets the correct status.
    // Should return a 201 CREATED message as well as the created gameId.
    @PostMapping("/begin")
    public ResponseEntity<String> beginGame(){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(serviceLayer.beginGame());
    }

    // POST – Makes a guess by passing the guess and gameId in as JSON.
    // The program must calculate the results of the guess and mark the game
    // finished if the guess is correct.
    // It returns the Round object with the results filled in.
    @PostMapping("/guess")
    public Round makeGuess(@RequestBody Guess guess){
        return serviceLayer.makeGuess(guess);
    }

    // GET – Returns a list of all games. Be sure in-progress games do not display their answer.
    @GetMapping("/game")
    public List<Game> getAllGames(){
        return serviceLayer.getAllGames();
    }

    // GET – Returns a specific game based on ID. Be sure in-progress games do not display their answer.
    @GetMapping("/game/{gameId}")
    public Game getGameById(@PathVariable("gameId") int gameId){
        return serviceLayer.getGameById(gameId);
    }


    // GET – Returns a list of rounds for the specified game sorted by time.
    @GetMapping("/rounds/{gameId}")
    public List<Round> getRoundsForGame(@PathVariable("gameId") int gameId){
        return serviceLayer.getRoundsForGame(gameId);
    }

}
