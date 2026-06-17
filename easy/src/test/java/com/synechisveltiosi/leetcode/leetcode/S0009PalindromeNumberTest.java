package com.synechisveltiosi.leetcode.leetcode;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class S0009PalindromeNumberTest {

    private S0009PalindromeNumber s0009PalindromeNumber = new S0009PalindromeNumber();

    @ParameterizedTest
    @MethodSource("palindromeInput")
    void isPalindrome(int x, boolean expected) {
        boolean result = s0009PalindromeNumber.isPalindrome(x);
        assertEquals(expected, result);
    }

    static Stream<Arguments> palindromeInput() {
        return Stream.of(
                Arguments.of(121, true),
                Arguments.of(-121, false),
                Arguments.of(10, false)
        );
    }
}