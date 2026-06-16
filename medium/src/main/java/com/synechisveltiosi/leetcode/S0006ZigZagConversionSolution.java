package com.synechisveltiosi.leetcode;

import java.util.stream.Stream;

public class S0006ZigZagConversionSolution {

    static class TraversalSimulationSolution {
        /**
         * Converts a string into its ZigZag representation and then reads it
         * <p>
         * row by row.
         * <p>
         * <p>
         * <p>
         * Example:
         * <p>
         * <p>
         * <p>
         * PAYPALISHIRING, numRows = 4
         * <p>
         * <p>
         * <p>
         * P     I     N
         * <p>
         * A   L S   I G
         * <p>
         * Y A   H R
         * <p>
         * P     I
         * <p>
         * <p>
         * <p>
         * Result: "PINALSIGYAHRPI"
         * <p>
         * <p>
         * <p>
         * Algorithm:
         * <p>
         * 1. Create a buffer for each row.
         * <p>
         * 2. Traverse the input string character by character.
         * <p>
         * 3. Append each character to the current row.
         * <p>
         * 4. Move downward until the last row is reached.
         * <p>
         * 5. Reverse direction and move upward until the first row is reached.
         * <p>
         * 6. Repeat until all characters are processed.
         * <p>
         * 7. Concatenate all rows to produce the final result.
         * <p>
         * <p>
         * <p>
         * Time Complexity: O(n)
         * <p>
         * Space Complexity: O(n)
         */

        public String convert(String s, int numRows) {

            // No zigzag pattern is formed when there is only one row
            // or the number of rows is greater than the string length.

            if (numRows == 1 || numRows >= s.length()) {
                return s;

            }

            // Create one buffer per row to store zigzag characters.
            StringBuffer[] rows = Stream.generate(StringBuffer::new).limit(numRows).toArray(StringBuffer[]::new);
            // Current row being populated.
            int rowIndex = 0;
            // Movement direction:
            // +1 = moving down
            // -1 = moving up

            // Starts at -1 because the first character is placed in row 0,

            // which immediately triggers a direction change to move downward.

            int direction = -1;
            for (char c : s.toCharArray()) {
                // Place the current character into the active row.
                rows[rowIndex].append(c);
                // When we reach the first or last row,
                // reverse the traversal direction.
                if (rowIndex == 0 || rowIndex == numRows - 1) {
                    direction = -direction;
                }
                // Move to the next row according to the current direction.
                rowIndex += direction;

            }

            // Read all rows from top to bottom to build the final string.
            return String.join("", rows);
        }
    }

    static class CyclePatternSolution {
        public String convert(String s, int numRows) {
            if (numRows == 1 || numRows >= s.length()) {
                return s;
            }
            StringBuilder result = new StringBuilder();
            int cycle = 2 * numRows - 2;

            for (int row = 0; row < numRows; row++) {
                for (int j = row; j < s.length(); j += cycle) {
                    result.append(s.charAt(j));

                    int diagonal = j + cycle - 2 * row;

                    if (row != 0 && row != numRows - 1 &&
                            diagonal < s.length()) {
                        result.append(s.charAt(diagonal));
                    }
                }
            }

            return result.toString();
        }
    }
}
