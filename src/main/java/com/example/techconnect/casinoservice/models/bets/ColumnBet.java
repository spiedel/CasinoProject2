package com.example.techconnect.casinoservice.models.bets;


import com.example.techconnect.casinoservice.enums.RouletteSetUp;
import com.example.techconnect.casinoservice.models.Player;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("column")
public class ColumnBet extends Bet{
    private String className = "ColumnBet";

    @Column
    private int columnBetOn;

    public ColumnBet(int amountBet, Player player, int columnBetOn) {
        super(amountBet, player);
        this.columnBetOn = columnBetOn;
    }

    public ColumnBet() {
    }

    @Override
    public Boolean isBetSuccessful(RouletteSetUp rouletteValue) {
        return rouletteValue.getColumn() == columnBetOn;
    }

    @Override
    public int getReturn() {
        return 2*getAmountBet();
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getColumnBetOn() {
        return columnBetOn;
    }

    public void setColumnBetOn(int columnBetOn) {
        this.columnBetOn = columnBetOn;
    }
}
