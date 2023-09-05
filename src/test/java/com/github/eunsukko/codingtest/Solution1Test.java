package com.github.eunsukko.codingtest;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Solution1Test {
    private final Solution1 solution1 = new Solution1();

    // solution(int[][] events, int m, String[] payments)

    private final String[][] ans1 = new String[][]{
            new String[]{"covy", ""},
            new String[]{"covy", "teo"}
    };

    // [[1, 25, 300000], [15, 30, 100000]]	2	["teo 1 50000", "covy 15 100000", "felix 17 50000", "teo 20 100000", "covy 25 200000", "felix 30 50000"]
    // [["covy", ""], ["covy", "teo"]]
    @Test
    public void testCase1() {
        var inputEvents = new int[][]{
                new int[]{1, 25, 300000},
                new int[]{15, 30, 100000}
        };
        var inputPayments = new String[]{
                "teo 1 50000", "covy 15 100000", "felix 17 50000", "teo 20 100000", "covy 25 200000", "felix 30 50000"
        };

        assertThat(solution1.solution(inputEvents, 2, inputPayments))
                .isEqualTo(ans1);
    }


    private final String[][] ans2 = new String[][]{
            new String[]{"felix", "teo"},
            new String[]{"", ""}
    };

    // [[1, 30, 100000], [30, 30, 100000]]	2	["teo 1 50000", "felix 2 50000", "covy 3 50000", "teo 6 50000", "felix 6 50000", "covy 6 50000", "felix 8 70000", "felix 30 70000"]
    // [["felix", "teo"], ["", ""]]
    @Test
    public void testCase2() {
        var inputEvents = new int[][]{
                new int[]{1, 30, 100000},
                new int[]{30, 30, 100000}
        };
        var inputPayments = new String[]{
                "teo 1 50000", "felix 2 50000", "covy 3 50000", "teo 6 50000", "felix 6 50000", "covy 6 50000", "felix 8 70000", "felix 30 70000"
        };

        assertThat(solution1.solution(inputEvents, 2, inputPayments))
                .isEqualTo(ans2);
    }
}