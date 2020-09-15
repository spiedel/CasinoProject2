package com.example.techconnect.casinoservice.enums;

public enum RouletteSetUp {
    One(1,"red", 1, 1),
    Two(2,"black", 2, 1),
    Three(3,"red",3, 1),
    Four(4,"black" ,1, 2),
    Five(5 , "red", 2, 2),
    Six(6 , "black", 3, 2),
    Seven(7, "red", 1, 3),
    Eight(8, "black", 2, 3),
    Nine(9, "red", 3, 3),
    Ten(10, "black", 1, 4),
    Eleven(11,"black", 2, 4),
    Twelve(12, "red", 3, 4),
    Thirteen(13 ,"black", 1, 5),
    Fourteen(14, "red", 2, 5),
    Fifteen(15, "black", 3, 5),
    Sixteen(16, "red", 1, 6),
    Seventeen(17, "black", 2, 6),
    Eighteen(18 ,"red", 3, 6),
    Nineteen(19, "red", 1, 7),
    Twenty(20 ,"black", 2, 7),
    TwentyOne(21, "red", 3, 7),
    TwentyTwo (22, "black", 1, 8),
    TwentyThree (23, "red", 2, 8),
    TwentyFour(24, "black", 3, 8),
    TwentyFive(25, "red", 1, 9),
    TwentySix(26, "black", 2, 9),
    TwentySeven(27, "red", 3, 9),
    TwentyEight(28, "black", 1, 10),
    TwentyNine(29, "black", 2, 10),
    Thirty(30 ,"red", 3, 10),
    ThirtyOne(31, "black", 1, 11),
    ThirtyTwo(23, "red", 2, 11),
    ThirtyThree (33, "black", 3, 11),
    ThirtyFour(34, "red", 1, 12),
    ThirtyFive(35, "black", 2, 12),
    ThirtySix(36, "red", 3, 12);

    private final int value;
    private final String colour;
    private final int column;
    private final int row;

    RouletteSetUp( int value, String colour, int column, int row){
        this.value =value;
        this.colour = colour;
        this.column = column;
        this.row = row;
    }

    public int getValue() {
        return value;
    }

    public String getColour() {
        return colour;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
}


