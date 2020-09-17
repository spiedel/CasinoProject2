package com.example.techconnect.casinoservice.testcomponents;


import com.example.techconnect.casinoservice.models.Game;
import com.example.techconnect.casinoservice.models.Player;
import com.example.techconnect.casinoservice.models.bets.*;
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

        Player player2 = new Player("Imogen", 20, 100);
        playerRepository.save(player2);

        Player player3 = new Player("Bob", 50, 100);
        player3.setNumberOfChips(10000);
        playerRepository.save(player3);

        Player playerNoBets = new Player("noBets", 30, 300);
        playerRepository.save(playerNoBets);

        Player playerNoGame = new Player("noGame", 50, 100);
        playerRepository.save(playerNoGame);

        Player playerNoChips = new Player("noChips", 20, 10);
        playerRepository.save(playerNoChips);




        Game roulette = new Game("Roulette");
        gameRepository.save(roulette);

        Game roulette2 = new Game("Roulette2");
        gameRepository.save(roulette2);



        player1.setGame(roulette);
        playerRepository.save(player1);

        player3.setGame(roulette);
        playerRepository.save(player3);

        playerNoBets.setGame(roulette);
        playerRepository.save(playerNoBets);


        Player playerToBeDeleted = new Player("PlayerToBeDeleted", 40, 20);
        playerToBeDeleted.setGame(roulette);
        playerRepository.save(playerToBeDeleted);



        //Bet bet1 = new Bet(20, player1);
        //betRepository.save(bet1);

        ColourBet bet3 = new ColourBet(20, player1, "red");
        betRepository.save(bet3);

        NumberBet bet4 = new NumberBet(20,player2,17);
        betRepository.save(bet4);

        OddEvenBet bet5 = new OddEvenBet(25, player2, "odd");
        betRepository.save(bet5);

        ColumnBet bet6 = new ColumnBet(12, player2, 2);
        betRepository.save(bet6);

        DozenBet bet7 = new DozenBet(12, player2, 3);
        betRepository.save(bet7);


    }
}