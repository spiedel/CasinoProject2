package com.example.techconnect.casinoservice.components;

import com.example.techconnect.casinoservice.models.bets.Bet;
import com.example.techconnect.casinoservice.models.Game;
import com.example.techconnect.casinoservice.models.Player;
import com.example.techconnect.casinoservice.models.bets.ColourBet;
import com.example.techconnect.casinoservice.repositories.BetRepository;
import com.example.techconnect.casinoservice.repositories.GameRepository;
import com.example.techconnect.casinoservice.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    GameRepository gameRepository;

    @Autowired
    BetRepository betRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Player player1 = new Player("Colin", 30, 100);
        playerRepository.save(player1);

        Game roulette = new Game("Roulette");
        gameRepository.save(roulette);

        player1.setGame(roulette);
        playerRepository.save(player1);

        //Bet bet1 = new Bet(20, player1);
        //betRepository.save(bet1);

        Bet bet3 = new ColourBet("red", 20, player1);
        betRepository.save(bet3);


    }
}
