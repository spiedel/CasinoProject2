package com.example.techconnect.casinoservice.models.bets;

import com.example.techconnect.casinoservice.enums.RouletteSetUp;
import com.example.techconnect.casinoservice.models.Player;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("high_low")
public class HighLowBet extends Bet{


    private String className = "HighLowBet";

    @Column(name= "high_or_low")
    private String betHighLow;

    public HighLowBet(int amountBet, Player player, String betHighLow) {
        super(amountBet, player);
        this.betHighLow = betHighLow;
    }

    public HighLowBet() {

    }

    @Override
    public Boolean isBetSuccessful(RouletteSetUp rouletteValue) {
        if(this.betHighLow.equalsIgnoreCase("high")){
            return testHigh(rouletteValue);
        }
        else if (this.betHighLow.equalsIgnoreCase("low")){
            return testLow(rouletteValue);
        }

        return false;
    }

    @Override
    public int getReturn() {
        return getAmountBet();
    }

    public Boolean testHigh(RouletteSetUp rouletteSetUp){
        if(rouletteSetUp.getValue()>= 19){
            return true;
        }
        return false;
    }

    public Boolean testLow(RouletteSetUp rouletteSetUp){
        if(rouletteSetUp.getValue()<= 18){
            return true;
        }
        return false;
    }


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getBetHighLow() {
        return betHighLow;
    }

    public void setBetHighLow(String betHighLow) {
        this.betHighLow = betHighLow;
    }
}
