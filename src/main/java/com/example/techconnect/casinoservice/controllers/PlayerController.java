package com.example.techconnect.casinoservice.controllers;

import com.example.techconnect.casinoservice.components.ChipClient;
import com.example.techconnect.casinoservice.models.Game;
import com.example.techconnect.casinoservice.models.Player;
import com.example.techconnect.casinoservice.repositories.GameRepository;
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

    @Autowired
    GameRepository gameRepository;

    @Autowired
    ChipClient chipClient;

    //INDEX all players
    @GetMapping(value = "/players")
    public ResponseEntity<List<Player>> getAllPlayers() {
        return new ResponseEntity<>(playerRepository.findAll(), HttpStatus.OK);
    }

    //INDEX all players in roulette game
    @GetMapping(value="/roulette/{id}/players/")
    public ResponseEntity getAllPlayersByGameId(@PathVariable Long id) {
        if(gameRepository.findById(id).isPresent()){
        List<Player> foundPlayers = playerRepository.findAllPlayersByGameId(id);
        if (!foundPlayers.isEmpty()) {
            return new ResponseEntity(foundPlayers, HttpStatus.OK);
        } else {
            return new ResponseEntity("No players in this game.", HttpStatus.OK);
        }}
        return new ResponseEntity("This game does not exist.", HttpStatus.NOT_FOUND);
    }

    // Buy chips for player
    @GetMapping(value="/players/{id}/buy")
    public ResponseEntity buyChipsForPlayer(@PathVariable Long id, @RequestParam (name = "amount", required = true) int amountOfMoney ){
        Optional<Player> player = playerRepository.findById(id);
        if ( player.isPresent() ) {
            double moneyInWallet = player.get().getMoneyInWallet();
            int numberOfChips = player.get().getNumberOfChips();
            if (moneyInWallet >= amountOfMoney){

                //Get Chip Value from Client
                player.get().setNumberOfChips((amountOfMoney * chipClient.requestChipValue()) + numberOfChips);
                player.get().setMoneyInWallet(moneyInWallet - amountOfMoney);
                playerRepository.save(player.get());
                return new ResponseEntity(String.format("Player %s with id %d has bought %d chips and now has £%.0f money in wallet.", player.get().getName(), id, player.get().getNumberOfChips(), player.get().getMoneyInWallet()), HttpStatus.OK);
            } else {
                return new ResponseEntity(String.format("Player %s with id %d does not have enough money to buy chips. Player has £%.0f money in wallet.", player.get().getName(), id, player.get().getMoneyInWallet()), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity(String.format("Player with id %d does not exist.", id), HttpStatus.NOT_FOUND);
    }

    // Cash in chips for player
    @GetMapping(value="/players/{id}/cashin")
    public ResponseEntity cashInChipsForPlayer(@PathVariable Long id){
        Optional<Player> player = playerRepository.findById(id);
        if ( player.isPresent() ) {
            double moneyInWallet = player.get().getMoneyInWallet();
            int numberOfChips = player.get().getNumberOfChips();
            if (numberOfChips > 0){
                player.get().setNumberOfChips(0);
                player.get().setMoneyInWallet(moneyInWallet + (numberOfChips / 5));
                playerRepository.save(player.get());
                return new ResponseEntity(String.format("Player %s with id %d has cashed in all %d chips and now has £%.0f money in wallet.", player.get().getName(), id, numberOfChips, player.get().getMoneyInWallet()), HttpStatus.OK);
            } else {
                return new ResponseEntity(String.format("Player %s with id %d does not have any chips to cash in.", player.get().getName(), id), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity(String.format("Player with id %d does not exist.", id), HttpStatus.NOT_FOUND);
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

    // CREATE player
    @PostMapping(value = "/players")
    public ResponseEntity<Player> postPlayer(@RequestBody Player player) {
        playerRepository.save(player);
        return new ResponseEntity<>(player, HttpStatus.CREATED);
    }

    // Add player to game
    @PutMapping(value = "/players/{id}/add")
    public ResponseEntity addPlayerToGame(@PathVariable Long id, @RequestBody Long gameId){
        Optional<Player> player = playerRepository.findById(id);
        if ( player.isPresent() ) {
            Optional<Game> game = gameRepository.findById(gameId);
            if (game.isPresent()) {
                player.get().setGame(game.get());
                playerRepository.save(player.get());
                return new ResponseEntity(String.format("Player %s with id %d has been added to game %s with game id %d.", player.get().getName(), id, game.get().getName(), gameId), HttpStatus.OK);
            } else {
                return new ResponseEntity(String.format("Sorry the game with game id %d does not exist.", gameId), HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity(String.format("Player with id %d does not exist.", id), HttpStatus.NOT_FOUND);
    }

    // Remove player from game
    @GetMapping(value = "/players/{id}/remove")
    public ResponseEntity removePlayerFromGame(@PathVariable Long id) {
        Optional<Player> player = playerRepository.findById(id);
        if (player.isPresent()) {
            if (player.get().getGame() != null) {
                Long gameId = player.get().getGame().getId();
                String gameName = player.get().getGame().getName();
                player.get().setGame(null);
                player.get().getBets().clear();
                playerRepository.save(player.get());
                return new ResponseEntity(String.format("Player %s with id %d has been removed from game %s with game id %d.", player.get().getName(), id, gameName, gameId), HttpStatus.OK);
            }
            return new ResponseEntity(String.format("Player %s with id %d is not in any game so cannot be removed.", player.get().getName(), id), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(String.format("Player with id %d does not exist.", id), HttpStatus.NOT_FOUND);
    }

}
