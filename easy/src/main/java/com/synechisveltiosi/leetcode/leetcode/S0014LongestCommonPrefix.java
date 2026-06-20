package com.synechisveltiosi.leetcode.leetcode;

import java.util.Arrays;

public class S0014LongestCommonPrefix {
    private S0014LongestCommonPrefix() {
        /* This utility class should not be instantiated */
    }

    public String longestCommonPrefix(String[] strs) {
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (!strs[i].startsWith(prefix)) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) {
                    return "";

                }
            }
        }
        return prefix;
    }

    static class HorizontalScanningSolution {
        public String longestCommonPrefix(String[] strs) {
            if (strs.length == 0) return "";
            String prefix = strs[0];
            for (int i = 1; i < strs.length; i++)
                while (
                        strs[i].indexOf(prefix) != 0
                ) {
                    prefix = prefix.substring(0, prefix.length() - 1);
                    if (prefix.isEmpty()) return "";
                }
            return prefix;
        }
    }

    static class VerticalScanningSolution {
        public String longestCommonPrefix(String[] strs) {
            if (strs == null || strs.length == 0) return "";
            for (int i = 0; i < strs[0].length(); i++) {
                char c = strs[0].charAt(i);
                for (int j = 1; j < strs.length; j++) {
                    if (
                            i == strs[j].length() || strs[j].charAt(i) != c
                    ) return strs[0].substring(0, i);
                }
            }
            return strs[0];
        }
    }

    static class BruteForceSolution {
        public String longestCommonPrefix2(String[] strInput) {
            Arrays.sort(strInput);

            String first = strInput[0];
            String last = strInput[strInput.length - 1];

            int i = 0;
            while (i < first.length() && first.charAt(i) == last.charAt(i)) {
                i++;
            }

            return first.substring(0, i);
        }
    }
}
