package com.example.techconnect.casinoservice.payloads;

import com.example.techconnect.casinoservice.enums.RouletteSetUp;
import com.example.techconnect.casinoservice.models.Player;

import java.util.HashMap;

public class GameSummary {

    private HashMap<String, Integer> playerWinnings;
    private RouletteSetUp winningNumber;

    public GameSummary() {
        this.playerWinnings = new HashMap<>();
    }

    public HashMap<String, Integer> getPlayerWinnings() {
        return playerWinnings;
    }

    public void setPlayerWinnings(HashMap<String, Integer> playerWinnings) {
        this.playerWinnings = playerWinnings;
    }

    public RouletteSetUp getWinningNumber() {
        return winningNumber;
    }

    public void setWinningNumber(RouletteSetUp winningNumber) {
        this.winningNumber = winningNumber;
    }
}
