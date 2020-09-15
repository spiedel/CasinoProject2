package com.example.techconnect.casinoservice.rouletteTests;

import com.example.techconnect.casinoservice.enums.RouletteSetUp;
import com.example.techconnect.casinoservice.models.Game;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameTest {
    @Test
    public void canSpin() {
        Game game = new Game("roulette");

        //when seeded with 3
        Random random = new Random(3);
        ArrayList<RouletteSetUp> rouletteList = new ArrayList<>();

        for (RouletteSetUp rouletteSetUp:RouletteSetUp.values()) {
            rouletteList.add(rouletteSetUp);
        }

        assertEquals(RouletteSetUp.Eighteen,game.spin(random, rouletteList));
    }
}
