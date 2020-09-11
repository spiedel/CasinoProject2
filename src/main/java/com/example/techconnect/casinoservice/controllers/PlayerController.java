package com.example.techconnect.casinoservice.controllers;

import com.example.techconnect.casinoservice.models.Player;
import com.example.techconnect.casinoservice.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PlayerController {

    @Autowired
    PlayerRepository playerRepository;


    //INDEX all players
    @GetMapping(value = "/players")
    public ResponseEntity<List<Player>> getAllPlayers() {
        return new ResponseEntity<>(playerRepository.findAll(), HttpStatus.OK);
    }

    //SHOW one player
    @GetMapping(value = "/players/{id}")
    public ResponseEntity getPlayerById(@PathVariable Long id) {
        Optional<Player> player = playerRepository.findById(id);
        if ( player.isPresent() ) {
            return new ResponseEntity(player, HttpStatus.OK);
        }

        return new ResponseEntity(String.format("Player with id %d does not exist.", id), HttpStatus.NOT_FOUND);
    }

    //CREATE player
    @PostMapping(value = "/players")
    public ResponseEntity<Player> postPlayer(@RequestBody Player player) {
        playerRepository.save(player);
        return new ResponseEntity<>(player, HttpStatus.CREATED);
    }
}
