package com.github.eunsukko.cjfghks;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Solution3Test {
    private final Solution3 solution = new Solution3();

    @Test
    public void test1() {
        assertThat(solution.solution(6, new int[]{6, 4, 10, 9,8,4}, new int[][]{new int[]{4,1}, new int[]{3,2}, new int[]{1,6}, new int[]{3, 5}, new int[]{5,1}}))
                .isEqualTo(3);
    }
}