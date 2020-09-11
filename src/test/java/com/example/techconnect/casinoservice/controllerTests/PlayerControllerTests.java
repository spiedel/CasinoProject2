package com.example.techconnect.casinoservice.controllerTests;

import com.example.techconnect.casinoservice.CasinoserviceApplication;
import com.example.techconnect.casinoservice.enums.RouletteSetUp;
import com.example.techconnect.casinoservice.models.Game;
import com.example.techconnect.casinoservice.models.Player;
import com.example.techconnect.casinoservice.models.bets.Bet;
import com.example.techconnect.casinoservice.models.bets.ColourBet;
import com.example.techconnect.casinoservice.repositories.BetRepository;
import com.example.techconnect.casinoservice.repositories.PlayerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = {CasinoserviceApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
public class PlayerControllerTests {
    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    PlayerRepository playerRepository;

    @Test
    public void canGetAllPlayers() {
        ResponseEntity<Player[]> response = testRestTemplate.getForEntity("/players", Player[].class);
        Player[] players = response.getBody();
        assertEquals("Colin", players[0].getName());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void canGetOnePlayer() {
        ResponseEntity<Player> response = testRestTemplate.getForEntity("/players/1", Player.class);
        Player player = response.getBody();
        assertEquals("Colin", player.getName());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void cantGetPlayerDoesntExist() {
        ResponseEntity<String> response = testRestTemplate.getForEntity("/players/1000000", String.class);
        String message = response.getBody();
        assertEquals("Player with id 1000000 does not exist.", message);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void canPostPlayer(){
        Player player = new Player ("John",34, 100);
        HttpEntity<Player> requestPayLoad = new HttpEntity<>(player);
        ResponseEntity<Player> response = testRestTemplate.postForEntity("/players", requestPayLoad, Player.class);
        assertEquals(201, response.getStatusCodeValue());
        Long playerId = response.getBody().getId();
        Player foundPlayer = playerRepository.findById(playerId).get();
        assertEquals("John", foundPlayer.getName());
    }
}
