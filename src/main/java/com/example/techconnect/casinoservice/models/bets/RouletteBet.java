package com.example.techconnect.casinoservice.models.bets;

import com.example.techconnect.casinoservice.models.Player;

import javax.persistence.Entity;

@Entity
public class RouletteBet extends Bet {

    public RouletteBet(int amountBet, Player player) {
        super(amountBet, player);
    }

    public RouletteBet() {
    }


}
