package com.github.eunsukko.cjfghks;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Solution2Test {
    private final Solution2 solution = new Solution2();

    @Test
    public void test1() {
        assertThat(solution.solution(3, 2))
                .isEqualTo(new int[][]{new int[]{2,1}, new int[]{3,6}, new int[]{4,5}});
    }
}