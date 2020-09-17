package com.example.techconnect.casinoservice.models;

import com.example.techconnect.casinoservice.enums.RouletteSetUp;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "game")
    @JsonIgnoreProperties(value = "game", allowSetters = true)
    private List<Player> players;

//    private ArrayList<RouletteSetUp> rouletteList;
//    private Random random;

    //constructors
    public Game(String name) {
        this.name = name;
        this.players = new ArrayList<>();
    }

    public Game() {
    }

    //methods
    public RouletteSetUp spin(Random random, ArrayList<RouletteSetUp> rouletteList) {
        int index = random.nextInt(36);
        return rouletteList.get(index);
    }


    //getters + setters
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

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
