package com.synechisveltiosi.leetcode;

import java.util.stream.Stream;

public class S0006ZigZagConversionSolution {
    private S0006ZigZagConversionSolution() {
        /* This utility class should not be instantiated */
    }


    static class TraversalSimulationSolution {
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

            // Edge cases:
            // If only one row exists, zigzag is identical to original string.
            // Also if number of rows is greater than string length,
            // every character occupies its own row, so no conversion needed.
            if (numRows == 1 || numRows >= s.length()) {
                return s;
            }

            // Stores final converted string
            StringBuilder result = new StringBuilder();

            // One complete zigzag cycle length
            //
            // Example: numRows = 4
            //
            // P
            // A L
            // Y A
            // P
            //
            // Going down = 4 chars
            // Going up diagonally = 2 chars
            //
            // cycle = 4 + 2 = 6
            //
            // Formula:
            // cycle = 2 * numRows - 2

            int cycle = 2 * numRows - 2;

            // Process row by row
            for (int row = 0; row < numRows; row++) {

                // Pick all vertical characters for this row
                for (int j = row; j < s.length(); j += cycle) {

                    // Vertical character
                    result.append(s.charAt(j));

                    // Middle rows have one additional diagonal character
                    //
                    // Example:
                    //
                    // Row 1:
                    // P     I     N
                    // A   L S   I G
                    // Y A   H R
                    // P     I
                    //
                    // For row=1:
                    // after A comes diagonal L
                    //
                    // diagonal index calculation:
                    // Middle rows contain an additional diagonal character within each cycle.
                    // Distance from the current vertical character (j) to the diagonal character
                    // is (cycle - 2 * row), therefore:

                    // diag = j + (cycle - 2 * row)
                    // Example (numRows = 4, cycle = 6):
                    // Row 1: 1 -> 5  (gap = 4)
                    // Row 2: 2 -> 4  (gap = 2)
                    int diagonal = j + (cycle - 2 * row);

                    // First row and last row do NOT have diagonal chars
                    //
                    // Ensure diagonal index is inside string bounds
                    if (row != 0 &&
                            row != numRows - 1 &&
                            diagonal < s.length()) {

                        result.append(s.charAt(diagonal));
                    }
                }
            }

            return result.toString();
        }
    }
}
