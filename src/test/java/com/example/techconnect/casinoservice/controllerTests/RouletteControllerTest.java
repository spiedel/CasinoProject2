package com.example.techconnect.casinoservice.controllerTests;


import com.example.techconnect.casinoservice.CasinoserviceApplication;
import com.example.techconnect.casinoservice.enums.RouletteSetUp;
import com.example.techconnect.casinoservice.models.Game;
import com.example.techconnect.casinoservice.models.Player;
import com.example.techconnect.casinoservice.models.bets.Bet;
import com.example.techconnect.casinoservice.models.bets.NumberBet;
import com.example.techconnect.casinoservice.payloads.GameSummary;
import com.example.techconnect.casinoservice.repositories.BetRepository;
import com.example.techconnect.casinoservice.repositories.GameRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = {CasinoserviceApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
@TestPropertySource(locations = "classpath:application-testing.properties")

public class RouletteControllerTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    GameRepository gameRepository;

    @Autowired
    BetRepository betRepository;

    @Test
    public void canGetAllGames() {
        ResponseEntity<Game[]> response = testRestTemplate.getForEntity("/roulette", Game[].class);
        Game[] games = response.getBody();
        assertEquals("Roulette", games[0].getName());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void canGetOneGame() {
        ResponseEntity<Game> response = testRestTemplate.getForEntity("/roulette/1", Game.class);
        Game game = response.getBody();
        assertEquals("Roulette", game.getName());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void canSpin() {
        NumberBet numberBet = new NumberBet();
        numberBet.setNumber(20);
        HttpEntity<Bet> requestPayLoad = new HttpEntity<>(numberBet);
        ResponseEntity<Bet> response = testRestTemplate.postForEntity("/roulette/1/players/3/createbet", requestPayLoad, Bet.class);
        assertEquals(201, response.getStatusCodeValue());
        Long betId = response.getBody().getId();
        Bet foundBet = betRepository.findById(betId).get();
        assertTrue(foundBet.isBetSuccessful(RouletteSetUp.Twenty));
        ResponseEntity<GameSummary> response2 = testRestTemplate.getForEntity("/roulette/1/spin", GameSummary.class);
        GameSummary result = response2.getBody();
        assertNotNull(result);
    }

    @Test
    public void canPostGame(){
        Game game = new Game ("Roulette");
        HttpEntity<Game> requestPayLoad = new HttpEntity<>(game);
        ResponseEntity<Game> response = testRestTemplate.postForEntity("/roulette", requestPayLoad, Game.class);
        assertEquals(201, response.getStatusCodeValue());
        Long gameId = response.getBody().getId();
        Game foundGame = gameRepository.findById(gameId).get();
        assertEquals("Roulette", foundGame.getName());
    }

}
