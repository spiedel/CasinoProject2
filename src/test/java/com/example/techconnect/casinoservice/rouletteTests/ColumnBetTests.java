package com.example.techconnect.casinoservice.rouletteTests;

import com.example.techconnect.casinoservice.enums.RouletteSetUp;
import com.example.techconnect.casinoservice.models.Player;
import com.example.techconnect.casinoservice.models.bets.ColumnBet;
import com.example.techconnect.casinoservice.models.bets.HighLowBet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@SpringBootTest
public class ColumnBetTests {
    @Test
    public void isCorrectFirstCol(){
        //given we have a player
        Player player = new Player("John", 44, 140);
        //and we have made a bet
        ColumnBet columnBet = new ColumnBet(20, player, 1);

        //And have a roulette value of the correct colour
        RouletteSetUp rouletteValue = RouletteSetUp.Four;
        //When: I pass the roulette spin into is successful
        //Then: I expect the result to be true
        assertTrue(columnBet.isBetSuccessful(rouletteValue));
    }

    @Test
    public void isIncorrectFirstCol(){
        //given we have a player
        Player player = new Player("John", 44, 140);
        //and we have made a bet
        ColumnBet columnBet = new ColumnBet(20, player, 1);

        //And have a roulette value of the correct colour
        RouletteSetUp rouletteValue = RouletteSetUp.ThirtyFive;
        //When: I pass the roulette spin into is successful
        //Then: I expect the result to be true
        assertFalse(columnBet.isBetSuccessful(rouletteValue));
    }

    @Test
    public void isCorrectSecondCol(){
        //given we have a player
        Player player = new Player("John", 44, 140);
        //and we have made a bet
        ColumnBet columnBet = new ColumnBet(20, player, 2);

        //And have a roulette value of the correct colour
        RouletteSetUp rouletteValue = RouletteSetUp.Two;
        //When: I pass the roulette spin into is successful
        //Then: I expect the result to be true
        assertTrue(columnBet.isBetSuccessful(rouletteValue));
    }

    @Test
    public void isIncorrectSecondCol(){
        //given we have a player
        Player player = new Player("John", 44, 140);
        //and we have made a bet
        ColumnBet columnBet = new ColumnBet(20, player, 2);

        //And have a roulette value of the correct colour
        RouletteSetUp rouletteValue = RouletteSetUp.Four;
        //When: I pass the roulette spin into is successful
        //Then: I expect the result to be true
        assertFalse(columnBet.isBetSuccessful(rouletteValue));
    }

    @Test
    public void isCorrectThirdCol(){
        //given we have a player
        Player player = new Player("John", 44, 140);
        //and we have made a bet
        ColumnBet columnBet = new ColumnBet(20, player, 3);

        //And have a roulette value of the correct colour
        RouletteSetUp rouletteValue = RouletteSetUp.Six;
        //When: I pass the roulette spin into is successful
        //Then: I expect the result to be true
        assertTrue(columnBet.isBetSuccessful(rouletteValue));
    }

    @Test
    public void isIncorrectThirdCol(){
        //given we have a player
        Player player = new Player("John", 44, 140);
        //and we have made a bet
        ColumnBet columnBet = new ColumnBet(20, player, 3);

        //And have a roulette value of the correct colour
        RouletteSetUp rouletteValue = RouletteSetUp.TwentyEight;
        //When: I pass the roulette spin into is successful
        //Then: I expect the result to be true
        assertFalse(columnBet.isBetSuccessful(rouletteValue));
    }
}
