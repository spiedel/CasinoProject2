package com.example.techconnect.casinoservice.controllers;

import com.example.techconnect.casinoservice.models.Player;
import com.example.techconnect.casinoservice.models.bets.Bet;
import com.example.techconnect.casinoservice.repositories.BetRepository;
import com.example.techconnect.casinoservice.repositories.GameRepository;
import com.example.techconnect.casinoservice.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BetController {

    @Autowired
    BetRepository betRepository;

    @Autowired
    GameRepository gameRepository;

    @Autowired
    PlayerRepository playerRepository;

    // INDEX all players bets in roulette game
    @GetMapping(value="/roulette/{gameId}/players/{playerId}/bets")
    public ResponseEntity getAllBetsByPlayerIdAndGameId(@PathVariable Long gameId, @PathVariable Long playerId) {
        if(gameRepository.findById(gameId).isPresent()){
            List<Bet> foundBets = betRepository.findAllBetsByPlayerIdAndPlayerGameId(playerId, gameId);
            if (!foundBets.isEmpty()) {
                return new ResponseEntity(foundBets, HttpStatus.OK);
            } else {
                return new ResponseEntity(String.format("No bets made by player %s with id %d in this game.", playerRepository.getOne(playerId).getName(), playerId), HttpStatus.OK);
            }}
        return new ResponseEntity("This game does not exist.", HttpStatus.NOT_FOUND);
    }

}
