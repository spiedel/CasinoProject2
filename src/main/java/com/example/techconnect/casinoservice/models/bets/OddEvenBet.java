package com.example.techconnect.casinoservice.models.bets;

import com.example.techconnect.casinoservice.enums.RouletteSetUp;
import com.example.techconnect.casinoservice.models.Player;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("odd_or_even")
public class OddEvenBet extends Bet {
    private String className = "OddEvenBet";

    @Column
    private String oddOrEven;

    public OddEvenBet(int amountBet, Player player, String oddOrEven) {
        super(amountBet, player);
        this.oddOrEven = oddOrEven;
    }

    public OddEvenBet() {
    }

    @Override
    public Boolean isBetSuccessful(RouletteSetUp rouletteValue) {
        if(this.oddOrEven.equalsIgnoreCase("odd")){
            return testOdd( rouletteValue);
        } else{
            return testEven(rouletteValue);
        }
    }


    public int getReturn() {
        return getAmountBet();
    }

    private boolean testEven(RouletteSetUp rouletteValue) {
        return rouletteValue.getValue()%2 ==0;
    }

    private boolean testOdd(RouletteSetUp rouletteValue) {
        return rouletteValue.getValue()%2 ==1;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getOddOrEven() {
        return oddOrEven;
    }

    public void setOddOrEven(String oddOrEven) {
        this.oddOrEven = oddOrEven;
    }
}
