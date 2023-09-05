package com.github.eunsukko.codingtest;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Solution2Test {
    private final Solution2 solution2 = new Solution2();

    // 입력
    // ["20230905 123123 abc CREATED YES 2000"]
    // ["20230905 123123 abc CREATED 200"]
    // ["20230905"]

    // 기대하는 결과
    // [["DIFFERENT 20230905 abc"]]
    @Test
    public void testCase1() {
        var inputDeliveries = new String[]{"20230905 123123 abc CREATED YES 2000"};
        var inputBillings = new String[]{"20230905 123123 abc CREATED 200"};
        var queries = new String[]{"20230905"};

        assertThat(solution2.solution(inputDeliveries, inputBillings, queries))
                .isEqualTo(new String[][]{{"DIFFERENT 20230905 abc"}});
    }

    // 입력
    // ["20230905 123123 abc CREATED YES 2000"]
    // ["20230905 123123 abc CREATED 2000"]
    // ["20230905"]

    // 기대하는 결과
    // [[]]
    @Test
    public void testCase2__모두동일한_경우() {
        var inputDeliveries = new String[]{"20230905 123123 abc CREATED YES 2000"};
        var inputBillings = new String[]{"20230905 123123 abc CREATED 2000"};
        var queries = new String[]{"20230905"};

        assertThat(solution2.solution(inputDeliveries, inputBillings, queries))
                .isEqualTo(new String[][]{{}});
    }

    // 입력
    // ["20230905 123123 aaa CREATED YES 2000", "20230905 123123 bbb CREATED YES 2000"]
    // ["20230905 123123 aaa CREATED 2000"]
    // ["20230905"]

    // 기대하는 결과
    // [["DELIVERY_ONLY 20230905 bbb]]
    @Test
    public void testCase3__배달팀에만_존재하는_경우() {
        var inputDeliveries = new String[]{"20230905 123123 CREATED YES 2000"};
        var inputBillings = new String[]{"20230905 123123 CREATED 2000"};
        var queries = new String[]{"20230905"};

        assertThat(solution2.solution(inputDeliveries, inputBillings, queries))
                .isEqualTo(new String[][]{{}});
    }

    // 입력
    // ["20230905 123123 aaa CREATED NO 2000"]
    // ["20230905 123123 aaa CREATED 2000"]
    // ["20230905"]

    // 기대하는 결과
    // [["BILLING_ONLY 20230905 aaa]]
    @Test
    public void testCase4__빌링팀에만_존재하는_경우() {
        var inputDeliveries = new String[]{"20230905 123123 CREATED YES 2000"};
        var inputBillings = new String[]{"20230905 123123 CREATED 2000"};
        var queries = new String[]{"20230905"};

        assertThat(solution2.solution(inputDeliveries, inputBillings, queries))
                .isEqualTo(new String[][]{{}});
    }

    // 입력
    // ["20230905 123123 aaa CREATED NO 2000"]
    // ["20230905 123123 aaa CREATED 2000"]
    // ["20230905", "20230905"]

    // 기대하는 결과
    // [["BILLING_ONLY 20230905 aaa], ["BILLING_ONLY 20230905 aaa]]
    @Test
    public void testCase5__동일날짜로_쿼리가_가능합니다() {
        var inputDeliveries = new String[]{"20230905 123123 CREATED YES 2000"};
        var inputBillings = new String[]{"20230905 123123 CREATED 2000"};
        var queries = new String[]{"20230905"};

        assertThat(solution2.solution(inputDeliveries, inputBillings, queries))
                .isEqualTo(new String[][]{{}});
    }

    //
}