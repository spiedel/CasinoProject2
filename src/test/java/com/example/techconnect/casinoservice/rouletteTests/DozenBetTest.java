package com.example.techconnect.casinoservice.rouletteTests;

import com.example.techconnect.casinoservice.enums.RouletteSetUp;
import com.example.techconnect.casinoservice.models.Player;
import com.example.techconnect.casinoservice.models.bets.DozenBet;
import com.example.techconnect.casinoservice.models.bets.HighLowBet;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@SpringBootTest
public class DozenBetTest {
    @Test
    public void isCorrectFirstDozen(){
        //given we have a player
        Player player = new Player("John", 44, 140);
        //and we have made a bet
        DozenBet dozenBet = new DozenBet(20, player, 1);

        //And have a roulette value of the correct colour
        RouletteSetUp rouletteValue = RouletteSetUp.Ten;
        //When: I pass the roulette spin into is successful
        //Then: I expect the result to be true
        assertTrue(dozenBet.isBetSuccessful(rouletteValue));
    }

    @Test
    public void isWrongFirstDozen(){
        //given we have a player
        Player player = new Player("John", 44, 140);
        //and we have made a bet
        DozenBet dozenBet = new DozenBet(20, player, 1);

        //And have a roulette value of the correct colour
        RouletteSetUp rouletteValue = RouletteSetUp.ThirtyFive;
        //When: I pass the roulette spin into is successful
        //Then: I expect the result to be true
        assertFalse(dozenBet.isBetSuccessful(rouletteValue));
    }

    @Test
    public void isCorrectSecondDozen(){
        //given we have a player
        Player player = new Player("John", 44, 140);
        //and we have made a bet
        DozenBet dozenBet = new DozenBet(20, player, 2);

        //And have a roulette value of the correct colour
        RouletteSetUp rouletteValue = RouletteSetUp.Nineteen;
        //When: I pass the roulette spin into is successful
        //Then: I expect the result to be true
        assertTrue(dozenBet.isBetSuccessful(rouletteValue));
    }

    @Test
    public void isWrongSecondDozen(){
        //given we have a player
        Player player = new Player("John", 44, 140);
        //and we have made a bet
        DozenBet dozenBet = new DozenBet(20, player, 2);

        //And have a roulette value of the correct colour
        RouletteSetUp rouletteValue = RouletteSetUp.Two;
        //When: I pass the roulette spin into is successful
        //Then: I expect the result to be true
        assertFalse(dozenBet.isBetSuccessful(rouletteValue));
    }

    @Test
    public void isCorrectThirdDozen(){
        //given we have a player
        Player player = new Player("John", 44, 140);
        //and we have made a bet
        DozenBet dozenBet = new DozenBet(20, player, 3);

        //And have a roulette value of the correct colour
        RouletteSetUp rouletteValue = RouletteSetUp.TwentyNine;
        //When: I pass the roulette spin into is successful
        //Then: I expect the result to be true
        assertTrue(dozenBet.isBetSuccessful(rouletteValue));
    }

    @Test
    public void isWrongThirdDozen(){
        //given we have a player
        Player player = new Player("John", 44, 140);
        //and we have made a bet
        DozenBet dozenBet = new DozenBet(20, player, 3);

        //And have a roulette value of the correct colour
        RouletteSetUp rouletteValue = RouletteSetUp.One;
        //When: I pass the roulette spin into is successful
        //Then: I expect the result to be true
        assertFalse(dozenBet.isBetSuccessful(rouletteValue));
    }
}
