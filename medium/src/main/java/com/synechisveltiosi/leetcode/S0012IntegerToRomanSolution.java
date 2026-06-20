package com.synechisveltiosi.leetcode;



public class S0012IntegerToRomanSolution {
    private static final int[] values = {
            1000,
            900,
            500,
            400,
            100,
            90,
            50,
            40,
            10,
            9,
            5,
            4,
            1,
    };
    private static final String[] symbols = {
            "M",
            "CM",
            "D",
            "CD",
            "C",
            "XC",
            "L",
            "XL",
            "X",
            "IX",
            "V",
            "IV",
            "I",
    };

    private record RomanNumeral(int value, String symbol) {
    }

    private static final RomanNumeral[] ROMAN_NUMERALS = {
            new RomanNumeral(1000, "M"),
            new RomanNumeral(900, "CM"),
            new RomanNumeral(500, "D"),
            new RomanNumeral(400, "CD"),
            new RomanNumeral(100, "C"),
            new RomanNumeral(90, "XC"),
            new RomanNumeral(50, "L"),
            new RomanNumeral(40, "XL"),
            new RomanNumeral(10, "X"),
            new RomanNumeral(9, "IX"),
            new RomanNumeral(5, "V"),
            new RomanNumeral(4, "IV"),
            new RomanNumeral(1, "I")
    };

    public String intToRoman1(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length && num > 0; i++) {
            while (values[i] <= num) {
                num -= values[i];
                sb.append(symbols[i]);
            }
        }
        return sb.toString();
    }

    public String intToRoman2(int num) {
        StringBuilder roman = new StringBuilder();
        for (RomanNumeral romanNumeral : ROMAN_NUMERALS) {
            while (num >= romanNumeral.value()) {
                roman.append(romanNumeral.symbol());
                num -= romanNumeral.value();
            }
        }
        return roman.toString();
    }
}
