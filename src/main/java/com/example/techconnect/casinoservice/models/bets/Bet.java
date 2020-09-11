package com.example.techconnect.casinoservice.models.bets;

import com.example.techconnect.casinoservice.models.Player;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "bets")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int amountBet;

    @ManyToOne
    @JoinColumn(name = "player_id")
    @JsonIgnoreProperties("bets")
    private Player player;

    public Bet(int amountBet, Player player) {
        this.amountBet = amountBet;
        this.player = player;
    }

    public Bet() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAmountBet() {
        return amountBet;
    }

    public void setAmountBet(int amountBet) {
        this.amountBet = amountBet;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
