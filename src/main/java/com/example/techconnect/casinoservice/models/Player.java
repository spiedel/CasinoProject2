package com.example.techconnect.casinoservice.models;

import com.example.techconnect.casinoservice.models.bets.Bet;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private int age;

    @Column(name = "money")
    private double moneyInWallet;

    @Column
    private int numberOfChips;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = true)
    @JsonIgnoreProperties("players")
    private Game game;

    @OneToMany(mappedBy = "player")
    @JsonIgnoreProperties("player")
    private List<Bet> bets;

    public Player(String name, int age, double moneyInWallet) {
        this.name = name;
        this.age = age;
        this.moneyInWallet = moneyInWallet;
    }

    public Player() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getMoneyInWallet() {
        return moneyInWallet;
    }

    public void setMoneyInWallet(double moneyInWallet) {
        this.moneyInWallet = moneyInWallet;
    }

    public int getNumberOfChips() {
        return numberOfChips;
    }

    public void setNumberOfChips(int numberOfChips) {
        this.numberOfChips = numberOfChips;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public List<Bet> getBets() {
        return bets;
    }

    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }
}
