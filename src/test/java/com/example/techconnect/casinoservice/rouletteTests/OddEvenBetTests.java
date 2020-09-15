package com.example.techconnect.casinoservice.rouletteTests;

import com.example.techconnect.casinoservice.enums.RouletteSetUp;
import com.example.techconnect.casinoservice.models.Player;
import com.example.techconnect.casinoservice.models.bets.NumberBet;
import com.example.techconnect.casinoservice.models.bets.OddEvenBet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OddEvenBetTests {
    @Test
    public void isCorrectOddEven(){
        //given we have a player
        Player player = new Player("John", 44, 140);

        //and we have made a bet
        OddEvenBet oddEvenBet = new OddEvenBet(20, player, "odd");
        //And have a roulette value of the correct colour
        RouletteSetUp rouletteValue = RouletteSetUp.Seventeen;
        //When: I pass the roulette spin into is successful
        //Then: I expect the result to be true
        assertTrue(oddEvenBet.isBetSuccessful(rouletteValue));
    }

    @Test
    public void isWrongOddEven(){
        //given we have a player
        Player player = new Player("John", 44, 140);

        //and we have made a bet
        OddEvenBet oddEvenBet = new OddEvenBet(20, player, "even");
        //And have a roulette value of the correct colour
        RouletteSetUp rouletteValue = RouletteSetUp.Seventeen;
        //When: I pass the roulette spin into is successful
        //Then: I expect the result to be true
        assertFalse(oddEvenBet.isBetSuccessful(rouletteValue));
    }
}
