package com.example.techconnect.casinoservice.models.bets;

import com.example.techconnect.casinoservice.enums.RouletteSetUp;
import com.example.techconnect.casinoservice.models.Player;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("dozen")
public class DozenBet extends Bet{
    private String className = "DozenBet";

    @Column
    private int dozenBetOn;

    public DozenBet(int amountBet, Player player, int dozenBetOn) {
        super(amountBet, player);
        this.dozenBetOn = dozenBetOn;
    }

    public DozenBet() {
    }

    @Override
    public Boolean isBetSuccessful(RouletteSetUp rouletteValue) {
        switch (dozenBetOn) {
            case 1:
                return firstDozen(rouletteValue);
            case 2:
                return secondDozen(rouletteValue);
            case 3:
                return thirdDozen(rouletteValue);
            default:
                return false;
        }
    }

    public Boolean firstDozen(RouletteSetUp rouletteValue) {
        return rouletteValue.getValue() <= 12;
    }

    public Boolean secondDozen(RouletteSetUp rouletteValue) {
        return rouletteValue.getValue() > 12 && rouletteValue.getValue() <= 24;
    }

    public Boolean thirdDozen(RouletteSetUp rouletteValue) {
        return rouletteValue.getValue() > 24;
    }

    @Override
    public int getReturn() {
        return 2 * getAmountBet();
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getDozenBetOn() {
        return dozenBetOn;
    }

    public void setDozenBetOn(int dozenBetOn) {
        this.dozenBetOn = dozenBetOn;
    }
}
