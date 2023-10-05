package com.github.eunsukko.cjfghks;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Solution1Test {
    private final Solution1 solution = new Solution1();

    @Test
    public void test1() {
        assertThat(solution.solution(new int[]{1, 2, 3, 4}, new int[]{12, 6, 7}))
                .isEqualTo(2);
    }
}