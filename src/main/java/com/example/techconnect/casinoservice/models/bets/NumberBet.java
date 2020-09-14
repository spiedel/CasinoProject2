package com.example.techconnect.casinoservice.models.bets;

import com.example.techconnect.casinoservice.enums.RouletteSetUp;
import com.example.techconnect.casinoservice.models.Player;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("number")
public class NumberBet extends Bet {


    private String className = "NumberBet";


    @Column(name= "number_bet_on")
    private int number;


    //Constructor
    public NumberBet(int amountBet, Player player, int number) {
        super(amountBet, player);
        this.number = number;
    }
    //Default constructor
    public NumberBet() {

    }

    @Override
    public Boolean isBetSuccessful(RouletteSetUp rouletteValue) {
        if (rouletteValue.getValue() == number){
            return true;
        } else {
            return false;
        }
    }

    //Getters and Setter
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
