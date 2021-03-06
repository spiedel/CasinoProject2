package com.example.techconnect.casinoservice.controllerTests;

import com.example.techconnect.casinoservice.CasinoserviceApplication;
import com.example.techconnect.casinoservice.enums.RouletteSetUp;
import com.example.techconnect.casinoservice.models.Player;
import com.example.techconnect.casinoservice.models.bets.*;
import com.example.techconnect.casinoservice.repositories.BetRepository;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = {CasinoserviceApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
@TestPropertySource(locations = "classpath:application-testing.properties")
public class BetControllerTests extends AbstractJUnit4SpringContextTests {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    BetRepository betRepository;
    

    @Test
    public void canGetBetsIfPlayerExistsInGame(){
        ResponseEntity<Bet[]> response = testRestTemplate.getForEntity("/roulette/1/players/1/bets", Bet[].class);
        Bet[] bets = response.getBody();
        assertTrue(bets[0].isBetSuccessful(RouletteSetUp.Eighteen));
        assertEquals(20, bets[0].getAmountBet());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void cantGetBetsIfPlayerDoesntExistInGame(){
        ResponseEntity<String> response = testRestTemplate.getForEntity("/roulette/1/players/2/bets", String.class);
        String message = response.getBody();
        assertEquals("This player with id 2 does not exist in this game.", message);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void cantGetBetsIfPlayerHasMadeNoBets(){
        ResponseEntity<String> response = testRestTemplate.getForEntity("/roulette/1/players/4/bets", String.class);
        String message = response.getBody();
        assertEquals("No bets made yet by player noBets with id 4 in this game.", message);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void cantGetBetsIfGameDoesntExist(){
        ResponseEntity<String> response = testRestTemplate.getForEntity("/roulette/3/players/1/bets", String.class);
        String message = response.getBody();
        assertEquals("This game with id 3 does not exist.", message);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void cantPostBetsGreaterThanMoney(){
        ColourBet colourBet = new ColourBet();
        colourBet.setColourBetOn("red");
        colourBet.setAmountBet(9000);


        HttpEntity<Bet> requestPayLoad = new HttpEntity<>(colourBet);
        ResponseEntity<Bet> response = testRestTemplate.postForEntity("/roulette/1/players/3/createbet", requestPayLoad, Bet.class);
        assertEquals(201, response.getStatusCodeValue());
        Long betId = response.getBody().getId();
        Bet foundBet = betRepository.findById(betId).get();
        assertTrue(foundBet.isBetSuccessful(RouletteSetUp.Eighteen));

        ResponseEntity<String> response2 = testRestTemplate.postForEntity("/roulette/1/players/3/createbet", requestPayLoad, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response2.getStatusCode());
        assertEquals("Player Bob with id 3 does not have enough chips to bet in this game.", response2.getBody());
    }

    @Test
    public void canPostColourBet(){
        ColourBet colourBet = new ColourBet();
        colourBet.setColourBetOn("red");
        HttpEntity<Bet> requestPayLoad = new HttpEntity<>(colourBet);
        ResponseEntity<Bet> response = testRestTemplate.postForEntity("/roulette/1/players/3/createbet", requestPayLoad, Bet.class);
        assertEquals(201, response.getStatusCodeValue());
        Long betId = response.getBody().getId();
        Bet foundBet = betRepository.findById(betId).get();
        assertTrue(foundBet.isBetSuccessful(RouletteSetUp.Eighteen));
    }

    @Test
    public void canPostNumberBet(){
        NumberBet numberBet = new NumberBet();
        numberBet.setNumber(20);
        HttpEntity<Bet> requestPayLoad = new HttpEntity<>(numberBet);
        ResponseEntity<Bet> response = testRestTemplate.postForEntity("/roulette/1/players/3/createbet", requestPayLoad, Bet.class);
        assertEquals(201, response.getStatusCodeValue());
        Long betId = response.getBody().getId();
        Bet foundBet = betRepository.findById(betId).get();
        assertTrue(foundBet.isBetSuccessful(RouletteSetUp.Twenty));
    }

    @Test
    public void canPostOddEvenBet(){
        OddEvenBet oddEvenBet = new OddEvenBet();
        oddEvenBet.setOddOrEven("even");
        HttpEntity<Bet> requestPayLoad = new HttpEntity<>(oddEvenBet);
        ResponseEntity<Bet> response = testRestTemplate.postForEntity("/roulette/1/players/3/createbet", requestPayLoad, Bet.class);
        assertEquals(201, response.getStatusCodeValue());
        Long betId = response.getBody().getId();
        Bet foundBet = betRepository.findById(betId).get();
        assertTrue(foundBet.isBetSuccessful(RouletteSetUp.Twenty));
    }

    @Test
    public void canPostColumnBet(){
        ColumnBet columnBet = new ColumnBet();
        columnBet.setColumnBetOn(1);
        HttpEntity<Bet> requestPayLoad = new HttpEntity<>(columnBet);
        ResponseEntity<Bet> response = testRestTemplate.postForEntity("/roulette/1/players/3/createbet", requestPayLoad, Bet.class);
        assertEquals(201, response.getStatusCodeValue());
        Long betId = response.getBody().getId();
        Bet foundBet = betRepository.findById(betId).get();
        assertTrue(foundBet.isBetSuccessful(RouletteSetUp.Ten));
    }

    @Test
    public void canPostDozenBet(){
        DozenBet dozenBet = new DozenBet();
        dozenBet.setDozenBetOn(1);
        HttpEntity<Bet> requestPayLoad = new HttpEntity<>(dozenBet);
        ResponseEntity<Bet> response = testRestTemplate.postForEntity("/roulette/1/players/3/createbet", requestPayLoad, Bet.class);
        assertEquals(201, response.getStatusCodeValue());
        Long betId = response.getBody().getId();
        Bet foundBet = betRepository.findById(betId).get();
        assertTrue(foundBet.isBetSuccessful(RouletteSetUp.Ten));
    }
    @Test
    public void canGetBetsIfGameExists(){
        ResponseEntity<Bet[]> response = testRestTemplate.getForEntity("/roulette/1/bets", Bet[].class);
        Bet[] bets = response.getBody();
        assertTrue(bets[0].isBetSuccessful(RouletteSetUp.Eighteen));
        assertEquals(20, bets[0].getAmountBet());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void canGetBetsIfGameDoesNotExists(){
        ResponseEntity<String> response = testRestTemplate.getForEntity("/roulette/20/bets", String.class);
        String message = response.getBody();
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("This game with id 20 does not exist.", message);
    }

    @Test
    public void cantGetBetsIfGameExistsButNoBetsMade(){
        ResponseEntity<String> response = testRestTemplate.getForEntity("/roulette/2/bets", String.class);
        String message = response.getBody();
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No bets made yet in this game.",message);
    }

    @Test
    public void canGetAllBets() {
        ResponseEntity<Bet[]> response = testRestTemplate.getForEntity("/bets", Bet[].class);
        Bet[] bets = response.getBody();
        assertEquals(20, bets[0].getAmountBet());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
