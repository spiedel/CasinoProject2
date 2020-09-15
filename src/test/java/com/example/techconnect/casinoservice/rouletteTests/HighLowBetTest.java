package com.example.techconnect.casinoservice.rouletteTests;

import com.example.techconnect.casinoservice.enums.RouletteSetUp;
import com.example.techconnect.casinoservice.models.Player;
import com.example.techconnect.casinoservice.models.bets.HighLowBet;
import com.example.techconnect.casinoservice.models.bets.NumberBet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@SpringBootTest
public class HighLowBetTest {
    @Test
    public void isCorrectHigh(){
        //given we have a player
        Player player = new Player("John", 44, 140);
        //and we have made a bet
        HighLowBet numberBet = new HighLowBet(20, player, "High");

        //And have a roulette value of the correct colour
        RouletteSetUp rouletteValue = RouletteSetUp.Thirty;
        //When: I pass the roulette spin into is successful
        //Then: I expect the result to be true
        assertTrue(numberBet.isBetSuccessful(rouletteValue));
    }

    //Case: I have bet on the wrong colour
    @Test
    public void isWrongHigh(){
        //given we have a player
        Player player = new Player("John", 44, 140);

        //and we have made a bet
        HighLowBet numberBet = new HighLowBet(20, player, "High");
        //And have a roulette value of the correct colour
        RouletteSetUp rouletteValue = RouletteSetUp.Ten;
        //When: I pass the roulette spin into is successful
        //Then: I expect the result to be true
        assertFalse(numberBet.isBetSuccessful(rouletteValue));
    }

    @Test
    public void isCorrectLow(){
        //given we have a player
        Player player = new Player("John", 44, 140);

        //and we have made a bet
        HighLowBet numberBet = new HighLowBet(20, player, "low");
        //And have a roulette value of the correct colour
        RouletteSetUp rouletteValue = RouletteSetUp.Seventeen;
        //When: I pass the roulette spin into is successful
        //Then: I expect the result to be true
        assertTrue(numberBet.isBetSuccessful(rouletteValue));
    }

    //Case: I have bet on the wrong colour
    @Test
    public void isWrongLow(){
        //given we have a player
        Player player = new Player("John", 44, 140);

        //and we have made a bet
        HighLowBet numberBet = new HighLowBet(20, player, "Low");
        //And have a roulette value of the correct colour
        RouletteSetUp rouletteValue = RouletteSetUp.Thirty;
        //When: I pass the roulette spin into is successful
        //Then: I expect the result to be true
        assertFalse(numberBet.isBetSuccessful(rouletteValue));
    }
}
