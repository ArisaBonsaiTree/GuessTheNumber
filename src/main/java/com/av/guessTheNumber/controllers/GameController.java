package com.av.guessTheNumber.controllers;

import com.av.guessTheNumber.dao.GameRepo;
import com.av.guessTheNumber.entity.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@CrossOrigin
public class GameController {
    @Autowired
    private GameRepo gameRepo;

    @GetMapping("/hello")
    public ResponseEntity<String> helloWorld() {
        return ResponseEntity.status(HttpStatus.OK).body("Hello World");
    }

    @GetMapping("/games")
    public List<Game> getAllGames(){
        return gameRepo.findAll();
    }


}