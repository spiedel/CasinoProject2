package com.example.techconnect.casinoservice.controllerTests;

import com.example.techconnect.casinoservice.CasinoserviceApplication;
import com.example.techconnect.casinoservice.enums.RouletteSetUp;
import com.example.techconnect.casinoservice.models.Player;
import com.example.techconnect.casinoservice.models.bets.Bet;
import com.example.techconnect.casinoservice.repositories.BetRepository;
import com.example.techconnect.casinoservice.repositories.GameRepository;
import com.example.techconnect.casinoservice.repositories.PlayerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = {CasinoserviceApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
public class BetControllerTests {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    BetRepository betRepository;

    @Autowired
    GameRepository gameRepository;

    @Test
    public void canGetBetsByPlayerIdAndGameId(){
        ResponseEntity<Bet[]> response = testRestTemplate.getForEntity("/roulette/1/players/1/bets", Bet[].class);
        Bet[] bets = response.getBody();
        assertTrue(bets[0].isBetSuccessful(RouletteSetUp.Eighteen));
        assertEquals(20, bets[0].getAmountBet());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }



}
