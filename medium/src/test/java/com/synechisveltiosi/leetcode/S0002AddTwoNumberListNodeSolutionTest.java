package com.synechisveltiosi.leetcode;

import com.synechisveltiosi.leetcode.leetcode.leetcode.models.ListNode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class S0002AddTwoNumberListNodeSolutionTest {
    private final S0002AddTwoNumberListNodeSolution.MathAlgoSolution solution = new S0002AddTwoNumberListNodeSolution.MathAlgoSolution();

    private static Stream<Arguments> addTwoNumbersInput() {
        return Stream.of(
                Arguments.of(new int[]{2, 4, 3}, new int[]{5, 6, 4}, new int[]{7, 0, 8}),
                Arguments.of(new int[]{0}, new int[]{0}, new int[]{0}),
                Arguments.of(new int[]{9, 9, 9, 9, 9, 9, 9}, new int[]{9, 9, 9, 9}, new int[]{8, 9, 9, 9, 0, 0, 0, 1}),
                Arguments.of(new int[]{5}, new int[]{5}, new int[]{0, 1})
        );
    }

    private static ListNode toListNode(int[] values) {
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;

        for (int value : values) {
            current.setNext(new ListNode(value));
            current = current.getNext();
        }

        return dummyHead.getNext();
    }

    private static int[] toArray(ListNode node) {
        List<Integer> values = new ArrayList<>();

        while (node != null) {
            values.add(node.getVal());
            node = node.getNext();
        }

        return values.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    @ParameterizedTest
    @MethodSource("addTwoNumbersInput")
    void addTwoNumbers(int[] firstNumber, int[] secondNumber, int[] expected) {
        ListNode actual = solution.addTwoNumbers(toListNode(firstNumber), toListNode(secondNumber));

        assertArrayEquals(expected, toArray(actual));
    }

}