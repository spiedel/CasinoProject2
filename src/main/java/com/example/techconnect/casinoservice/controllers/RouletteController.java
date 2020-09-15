package com.example.techconnect.casinoservice.controllers;

import com.example.techconnect.casinoservice.components.RouletteSpinSetup;
import com.example.techconnect.casinoservice.enums.RouletteSetUp;
import com.example.techconnect.casinoservice.models.Game;
import com.example.techconnect.casinoservice.models.Player;
import com.example.techconnect.casinoservice.models.bets.Bet;
import com.example.techconnect.casinoservice.payloads.GameSummary;
import com.example.techconnect.casinoservice.repositories.BetRepository;
import com.example.techconnect.casinoservice.repositories.GameRepository;
import com.example.techconnect.casinoservice.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class RouletteController {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    RouletteSpinSetup rouletteSpinSetup;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    BetRepository betRepository;

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

    // SPIN
    @GetMapping(value = "/roulette/{id}/spin")
    public ResponseEntity spin(@PathVariable Long id) {
        Optional<Game> game = gameRepository.findById(id);
        GameSummary gameSummary = new GameSummary();
        if (game.isPresent()) {
            RouletteSetUp result = game.get().spin(rouletteSpinSetup.getRandom(), rouletteSpinSetup.getRouletteList());
            List<Player> players = game.get().getPlayers();
            for (Player player : players) {
                gameSummary.getPlayerWinnings().put(player.getName(), 0);
                List<Bet> bets = player.getBets();
                for (Bet bet : bets) {
                    if (bet.isBetSuccessful(result)) {
                        int numberOfChips = player.getNumberOfChips();
                        numberOfChips += bet.getReturn();
                        player.setNumberOfChips(numberOfChips);
                        int getCurrentWinnings = gameSummary.getPlayerWinnings().get(player.getName()) + bet.getReturn();
                        gameSummary.getPlayerWinnings().put(player.getName(), getCurrentWinnings);
                    } else {
                        int numberOfChips = player.getNumberOfChips();
                        numberOfChips -= bet.getAmountBet();
                        player.setNumberOfChips(numberOfChips);
                        int getCurrentLosses = gameSummary.getPlayerWinnings().get(player.getName()) - bet.getAmountBet();
                        gameSummary.getPlayerWinnings().put(player.getName(), getCurrentLosses);
                    }
                    betRepository.deleteById(bet.getId());

                }
                player.getBets().clear();

                playerRepository.save(player);
            }
            gameSummary.setWinningNumber(result);
            return new ResponseEntity(gameSummary, HttpStatus.OK);
        } else {
            return new ResponseEntity(String.format("Sorry the game with game id %d does not exist.", id), HttpStatus.NOT_FOUND);
        }
    }



        //CREATE new roulette game
        @PostMapping(value = "/roulette")
        public ResponseEntity<Game> postGame (@RequestBody Game game){
            gameRepository.save(game);
            return new ResponseEntity<>(game, HttpStatus.CREATED);
        }

    }

