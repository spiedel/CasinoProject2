package com.example.techconnect.casinoservice.components;

import com.example.techconnect.casinoservice.enums.RouletteSetUp;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Random;

@Component
public class RouletteSpinSetup {
    Random random;
    ArrayList<RouletteSetUp> rouletteList;

    public RouletteSpinSetup() {
        random = new Random();
        rouletteList = new ArrayList<>();

        for (RouletteSetUp rouletteSetUp:RouletteSetUp.values()) {
            rouletteList.add(rouletteSetUp);
        }
    }

    public Random getRandom() {
        return random;
    }

    public ArrayList<RouletteSetUp> getRouletteList() {
        return rouletteList;
    }
}
