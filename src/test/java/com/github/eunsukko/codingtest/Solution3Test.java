package com.github.eunsukko.codingtest;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Solution3Test {
    private Solution3 solution = new Solution3();

    // 입력
    // ["a 100"]

    // 기대하는 결과
    // ["a 100"]
    @Test
    public void testCase1_단순저장이_가능하다() {
        var commands = Arrays.asList("a 100");

        assertThat(solution.solution(toInput(commands)))
                .isEqualTo(new String[]{"a 100"});
    }

    // 입력
    // ["a 100","a 50"]

    // 기대하는 결과
    // ["a 50"]
    @Test
    public void testCase2_동일_변수에_다시저장시_덮어쓴다() {
        var commands = Arrays.asList("a 100","a 50");

        assertThat(solution.solution(toInput(commands)))
                .isEqualTo(new String[]{"a 50"});
    }

    // 입력
    // ["b 100","a 50", "b 50"]

    // 기대하는 결과
    // ["a 50","b 50"]
    @Test
    public void testCase3_여러_변수에_저장할수_있다() {
        var commands = Arrays.asList("b 100","a 50", "b 50");

        assertThat(solution.solution(toInput(commands)))
                .isEqualTo(new String[]{"a 50","b 50"});
    }

    // 입력
    // ["b 50","tx begin","a 50","tx end","c 50"]

    // 기대하는 결과
    // ["a 50","b 50","c 50"]
    @Test
    public void testCase4_트랜잭션이_시작하고_종료되면_그_사이값들은_저장된다() {
        var commands = Arrays.asList("b 50","tx begin","a 50", "tx end","c 50");

        assertThat(solution.solution(toInput(commands)))
                .isEqualTo(new String[]{"a 50","b 50","c 50"});
    }

    // 입력
    // ["b 50","tx begin","a 50","tx rollback","tx end","c 50"]

    // 기대하는 결과
    // ["b 50","c 50"]
    @Test
    public void testCase5_트랜잭션이_시작하고_종료되는_사이에_롤백으로_설정되면_그_사이값들은_무시된다() {
        var commands = Arrays.asList("b 50","tx begin","a 50","tx rollback","tx end","c 50");

        assertThat(solution.solution(toInput(commands)))
                .isEqualTo(new String[]{"b 50","c 50"});
    }

    // 입력
    // ["b 50","tx begin","a 50","c 50"]

    // 기대하는 결과
    // ["b 50"]
    @Test
    public void testCase6_트랜잭션이_종료되지_않으면_시작이후_값은_저장하지_않는다() {
        var commands = Arrays.asList("b 50","tx begin","a 50","c 50");

        assertThat(solution.solution(toInput(commands)))
                .isEqualTo(new String[]{"b 50"});
    }

    // 입력
    // ["b 50","tx begin","a 50","tx rollback","tx end","c 50","tx begin","a 70","tx end"]

    // 기대하는 결과
    // ["a 70","b 50","c 50"]
    @Test
    public void testCase7_앞선_트랜잭션이_롤백되도_뒤에_트랜잭션은_영향받지_않는다() {
        var commands = Arrays.asList("b 50","tx begin","a 50","tx rollback","tx end","c 50","tx begin","a 70","tx end");

        assertThat(solution.solution(toInput(commands)))
                .isEqualTo(new String[]{"a 70","b 50","c 50"});
    }

    // 입력
    // ["c 10","tx begin","a 50","tx begin","b 50","tx rollback","tx end","tx end"]

    // 기대하는 결과
    // ["c 10"]
    @Test
    public void testCase8_중첩된_트랜잭션인_경우_내부에서_롤백되어도_전체가_롤백됩니다() {
        var commands = Arrays.asList("c 10","tx begin","a 50","tx begin","b 50","tx rollback","tx end","tx end");

        assertThat(solution.solution(toInput(commands)))
                .isEqualTo(new String[]{"c 10"});
    }

    // 입력
    // ["c 10","tx begin","tx rollback","a 50","tx begin","b 50","tx end","tx end"]

    // 기대하는 결과
    // ["c 10"]
    @Test
    public void testCase9_중첩된_트랜잭션인_경우_외부에서_롤백되어도_전체가_롤백됩니다() {
        var commands = Arrays.asList("c 10","tx begin","tx rollback","a 50","tx begin","b 50","tx end","tx end");

        assertThat(solution.solution(toInput(commands)))
                .isEqualTo(new String[]{"c 10"});
    }

    private String[] toInput(List<String> commands) {
        return commands.toArray(new String[commands.size()]);
    }
}