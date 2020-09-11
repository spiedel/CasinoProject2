package com.example.techconnect.casinoservice.models.bets;

import com.example.techconnect.casinoservice.models.Player;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class ColourBet extends Bet {

    @Column
    private String colourBetOn;

    public ColourBet(String colourBetOn, int amountBet, Player player) {
        super(amountBet, player);
        this.colourBetOn = colourBetOn;
    }

    //Player uses constructor to make bet.
    public ColourBet(){
    }

    public String getColourBetOn() {
        return colourBetOn;
    }

    public void setColourBetOn(String colourBetOn) {
        this.colourBetOn = colourBetOn;
    }


//    public boolean isBetSuccessful(RouletteSetUp rouletteValue) {
//        if (rouletteValue.getColour().equals(colourBetOn)){
//            return true;
//        } else
//        {
//            return false;
//        }
//        //is colour from game the same as the colour the person bet on.
//
//    }


    //    public void makeBet(Scanner scanner, Player player){
//        System.out.println("How many chips do you want to bet?");
//        while(!scanner.hasNextInt()){
//            System.out.println("Please enter a number.");
//            scanner.nextLine();
//        }
//        int betAmount = scanner.nextInt();
//        scanner.nextLine();
//        if (player.hasEnoughChipsToBet(betAmount)){
//            this.amountBet = betAmount;
//            System.out.println("What colour do you want to bet on? Please select red or black.");
//            String colour = scanner.nextLine();
//            if (colour.equalsIgnoreCase("red") || colour.equalsIgnoreCase("black")){
//                this.colourBetOn = colour;
//            } else {
//                System.out.println("You must select either red or black. Please try again.");
//                makeBet(scanner, player);
//            }
//        } else {
//            System.out.println("Sorry you don't have enough chips to bet with. Please try again.");
//            makeBet(scanner, player);
//        }
//
//    }
}
