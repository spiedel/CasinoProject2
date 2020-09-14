package com.example.techconnect.casinoservice.models.bets;

import com.example.techconnect.casinoservice.enums.RouletteSetUp;
import com.example.techconnect.casinoservice.models.Player;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("colour")
public class ColourBet extends Bet {

    @Column
    private String colourBetOn;

    public ColourBet(String colourBetOn, int amountBet, Player player) {
        super(amountBet, player);
        this.colourBetOn = colourBetOn;
    }

    //Player uses constructor to make bet.
    public ColourBet(){
    }

    public String getColourBetOn() {
        return colourBetOn;
    }

    public void setColourBetOn(String colourBetOn) {
        this.colourBetOn = colourBetOn;
    }


    @Override
    public Boolean isBetSuccessful(RouletteSetUp rouletteValue) {
        if (rouletteValue.getColour().equals(colourBetOn)){
            return true;
        } else
        {
            return false;
        }
        //is colour from game the same as the colour the person bet on.

    }
}
