package com.example.techconnect.casinoservice.controllers;

import com.example.techconnect.casinoservice.models.Game;
import com.example.techconnect.casinoservice.models.Player;
import com.example.techconnect.casinoservice.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RouletteController {

    @Autowired
    GameRepository gameRepository;

    //INDEX roulette game
    @GetMapping(value = "/roulette")
    public ResponseEntity<List<Game>> getAllGameInformation() {
        return new ResponseEntity<>(gameRepository.findAll(), HttpStatus.OK);
    }

    //INDEX specific roulette game
    @GetMapping(value = "roulette/{id}")
    public ResponseEntity getRouletteGameById(@PathVariable Long id) {
        Optional<Game> game = gameRepository.findById(id);
        if (game.isPresent()) {
            return new ResponseEntity(game, HttpStatus.OK);
        } else {
            return new ResponseEntity(String.format("Sorry the game with game id %d does not exist.", id), HttpStatus.NOT_FOUND);
        }
    }

    //CREATE new roulette game
    @PostMapping(value = "/roulette")
    public ResponseEntity<Game> postGame(@RequestBody Game game){
        gameRepository.save(game);
        return new ResponseEntity<>(game, HttpStatus.CREATED);
    }
}
