package com.synechisveltiosi.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class S0017LetterCombinationPhoneNumberSolution {
    private List<String> combinations = new ArrayList<>();
    private Map<Character, String> letters = Map.of(
            '2',
            "abc",
            '3',
            "def",
            '4',
            "ghi",
            '5',
            "jkl",
            '6',
            "mno",
            '7',
            "pqrs",
            '8',
            "tuv",
            '9',
            "wxyz"
    );
    private String phoneDigits;
    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) return combinations;
        phoneDigits = digits;
        backtrack(0, new StringBuilder());
        return combinations;
    }

    private void backtrack(int index, StringBuilder path) {
        if (path.length() == phoneDigits.length()) {
            combinations.add(path.toString());
            return;
        }
        String possibleLetters = letters.get(phoneDigits.charAt(index));
        for (char letter : possibleLetters.toCharArray()) {
            path.append(letter);
            backtrack(index + 1, path);
            path.deleteCharAt(path.length() - 1);
        }

    }
}