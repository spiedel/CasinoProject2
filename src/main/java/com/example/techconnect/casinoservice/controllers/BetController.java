package com.example.techconnect.casinoservice.controllers;

import com.example.techconnect.casinoservice.models.Player;
import com.example.techconnect.casinoservice.models.bets.Bet;
import com.example.techconnect.casinoservice.repositories.BetRepository;
import com.example.techconnect.casinoservice.repositories.GameRepository;
import com.example.techconnect.casinoservice.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BetController {

    @Autowired
    BetRepository betRepository;

    @Autowired
    GameRepository gameRepository;

    @Autowired
    PlayerRepository playerRepository;

    // INDEX all players bets in roulette game
    @GetMapping(value = "/roulette/{gameId}/players/{playerId}/bets")
    public ResponseEntity getAllBetsByPlayerIdAndGameId(@PathVariable Long gameId, @PathVariable Long playerId) {
        if (gameRepository.findById(gameId).isPresent()) {
            Optional<Player> player = playerRepository.findById(playerId);
            if (player.isPresent() && playerRepository.findAllPlayersByGameId(gameId).contains(player.get())) {
                List<Bet> foundBets = betRepository.findAllBetsByPlayerIdAndPlayerGameId(playerId, gameId);
                if (!foundBets.isEmpty()) {
                    return new ResponseEntity(foundBets, HttpStatus.OK);
                } else {
                    return new ResponseEntity(String.format("No bets made yet by player %s with id %d in this game.", playerRepository.getOne(playerId).getName(), playerId), HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity(String.format("This player with id %d does not exist in this game.", playerId), HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity(String.format("This game with id %d does not exist.", gameId), HttpStatus.NOT_FOUND);
    }


    // CREATE bet
    @PostMapping(value = "/roulette/{gameId}/players/{playerId}/createbet")
    public ResponseEntity<Bet> postBet(@RequestBody Bet bet, @PathVariable Long gameId, @PathVariable Long playerId) {
        if (gameRepository.findById(gameId).isPresent()) {
            Optional<Player> player = playerRepository.findById(playerId);
            if (player.isPresent() && playerRepository.findAllPlayersByGameId(gameId).contains(player.get())) {
                if (player.get().getNumberOfChips() > bet.getAmountBet()) {
                    bet.setPlayer(player.get());
                    betRepository.save(bet);
                    return new ResponseEntity<>(bet, HttpStatus.CREATED);
                } else {
                    return new ResponseEntity(String.format("Player %s with id %d does not have enough chips to bet in this game.", playerRepository.getOne(playerId).getName(), playerId), HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity(String.format("This player with id %d does not exist in this game.", playerId), HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity(String.format("This game with id %d does not exist.", gameId), HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/roulette/{gameId}/bets")
    public ResponseEntity getAllBetsByGameId(@PathVariable Long gameId) {
        if (gameRepository.findById(gameId).isPresent()) {
            List<Bet> foundBets = betRepository.findAllBetsByPlayerGameId(gameId);
            if (!foundBets.isEmpty()) {
                return new ResponseEntity(foundBets, HttpStatus.OK);
            } else {
                return new ResponseEntity("No bets made yet in this game.", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity(String.format("This game with id %d does not exist.", gameId), HttpStatus.NOT_FOUND);
        }
    }
}


