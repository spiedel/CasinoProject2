package com.example.techconnect.casinoservice.enums;

public enum RouletteSetUp {
    One(1,"red"),
    Two(2,"black"),
    Three(3,"red"),
    Four(4,"black"),
    Five(5 , "red"),
    Six(6 , "black"),
    Seven(7, "red"),
    Eight(8, "black"),
    Nine(9, "red"),
    Ten(10, "black"),
    Eleven(11,"black"),
    Twelve(12, "red"),
    Thirteen(13 ,"black"),
    Fourteen(14, "red"),
    Fifteen(15, "black"),
    Sixteen(16, "red"),
    Seventeen(17, "black"),
    Eighteen(18 ,"red"),
    Nineteen(19, "red"),
    Twenty(20 ,"black"),
    TwentyOne(21, "red"),
    TwentyTwo (22, "black"),
    TwentyThree (23, "red"),
    TwentyFour(24, "black"),
    TwentyFive(25, "red"),
    TwentySix(26, "black"),
    TwentySeven(27, "red"),
    TwentyEight(28, "black"),
    TwentyNine(29, "black"),
    Thirty(30 ,"red"),
    ThirtyOne(31, "black"),
    ThirtyTwo(23, "red"),
    ThirtyThree (33, "black"),
    ThirtyFour(34, "red"),
    ThirtyFive(35, "black"),
    ThirtySix(36, "red");

    private final int value;
    private final String colour;

    RouletteSetUp( int value, String colour){
        this.value =value;
        this.colour = colour;
    }

    public int getValue() {
        return value;
    }

    public String getColour() {
        return colour;
    }
}


