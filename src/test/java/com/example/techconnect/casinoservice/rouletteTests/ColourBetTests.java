package com.example.techconnect.casinoservice.rouletteTests;

import com.example.techconnect.casinoservice.enums.RouletteSetUp;
import com.example.techconnect.casinoservice.models.Player;
import com.example.techconnect.casinoservice.models.bets.ColourBet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@SpringBootTest
public class ColourBetTests {

    //CASE: I Have bet on the correct colour
    @Test
    public void isCorrectColour(){
        //given we have a player
        Player player = new Player("John", 44, 140);

        //and we have made a bet
        ColourBet colourBet = new ColourBet(10, player, "black");
        //And have a roulette value of the correct colour
        RouletteSetUp rouletteValue = RouletteSetUp.Eight;
        //When: I pass the roulette spin into is successful
        //Then: I expect the result to be true
        assertTrue(colourBet.isBetSuccessful(rouletteValue));
    }

    //Case: I have bet on the wrong colour
    @Test
    public void isWrongColour(){
        //given we have a player
        Player player = new Player("John", 44, 140);

        //and we have made a bet
        ColourBet colourBet = new ColourBet(10, player, "red");
        //And have a roulette value of the correct colour
        RouletteSetUp rouletteValue = RouletteSetUp.Eight;
        //When: I pass the roulette spin into is successful
        //Then: I expect the result to be true
        assertFalse(colourBet.isBetSuccessful(rouletteValue));
    }
}
