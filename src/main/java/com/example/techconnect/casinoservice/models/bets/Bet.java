package com.example.techconnect.casinoservice.models.bets;

import com.example.techconnect.casinoservice.enums.RouletteSetUp;
import com.example.techconnect.casinoservice.models.Player;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.persistence.*;

@Table(name = "bets")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "bet_type", discriminatorType = DiscriminatorType.STRING)
// What does this mean??
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "className")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ColourBet.class, name = "ColourBet"),
        @JsonSubTypes.Type(value = NumberBet.class, name = "NumberBet"),
        @JsonSubTypes.Type(value = OddEvenBet.class, name = "OddEvenBet")
})
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

    //methods
    public abstract Boolean isBetSuccessful(RouletteSetUp rouletteValue);

    //getters + setters
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
