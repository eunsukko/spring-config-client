package com.github.eunsukko.codingtest;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Solution2Test {
    private final Solution2 solution2 = new Solution2();

    // 입력
    // ["20230905 R00001 D00001 COMPLETED YES 2000"]
    // ["20230905 R00001 D00001 COMPLETED 200"]
    // ["20230905"]

    // 기대하는 결과
    // [["DIFFERENT 20230905 D00001 "]]
    @Test
    public void testCase1() {
        var inputDeliveries = new String[]{"20230905 R00001 D00001 COMPLETED YES 2000"};
        var inputBillings = new String[]{"20230905 R00001 D00001 COMPLETED 200"};
        var queries = new String[]{"20230905"};

        assertThat(solution2.solution(inputDeliveries, inputBillings, queries))
                .isEqualTo(new String[][]{{"DIFFERENT 20230905 D00001 "}});
    }

    // 입력
    // ["20230905 R00001 D00001 COMPLETED YES 2000"]
    // ["20230905 R00001 D00001 COMPLETED 2000"]
    // ["20230905"]

    // 기대하는 결과
    // [[]]
    @Test
    public void testCase2__모두동일한_경우() {
        var inputDeliveries = new String[]{"20230905 R00001 D00001 COMPLETED YES 2000"};
        var inputBillings = new String[]{"20230905 R00001 D00001 COMPLETED 2000"};
        var queries = new String[]{"20230905"};

        assertThat(solution2.solution(inputDeliveries, inputBillings, queries))
                .isEqualTo(new String[][]{{}});
    }

    // 입력
    // ["20230905 R00001 D00001 COMPLETED YES 2000", "20230905 R00001 D00002 COMPLETED YES 2000"]
    // ["20230905 R00001 D00001 COMPLETED 2000"]
    // ["20230905"]

    // 기대하는 결과
    // [["DELIVERY_ONLY 20230905 D00002"]]
    @Test
    public void testCase3__배달팀에만_존재하는_경우() {
        var inputDeliveries = new String[]{"20230905 R00001 D00001 COMPLETED YES 2000", "20230905 R00001 D00002 COMPLETED YES 2000"};
        var inputBillings = new String[]{"20230905 R00001 D00001 COMPLETED 2000"};
        var queries = new String[]{"20230905"};

        assertThat(solution2.solution(inputDeliveries, inputBillings, queries))
                .isEqualTo(new String[][]{{"DELIVERY_ONLY 20230905 D00002"}});
    }

    // 입력
    // ["20230905 R00001 D00001 COMPLETED NO 2000"]
    // ["20230905 R00001 D00001 COMPLETED 2000"]
    // ["20230905"]

    // 기대하는 결과
    // [["BILLING_ONLY 20230905 D00001"]]
    @Test
    public void testCase4__빌링팀에만_존재하는_경우() {
        var inputDeliveries = new String[]{"20230905 R00001 D00001 COMPLETED NO 2000"};
        var inputBillings = new String[]{"20230905 R00001 D00001 COMPLETED 2000"};
        var queries = new String[]{"20230905"};

        assertThat(solution2.solution(inputDeliveries, inputBillings, queries))
                .isEqualTo(new String[][]{{"BILLING_ONLY 20230905 D00001"}});
    }

    // 입력
    // ["20230905 R00001 D00001 COMPLETED NO 2000"]
    // ["20230905 R00001 D00001 COMPLETED 2000"]
    // ["20230905", "20230905"]

    // 기대하는 결과
    // [["BILLING_ONLY 20230905 D00001], ["BILLING_ONLY 20230905 D00001]]
    @Test
    public void testCase5__동일날짜로_쿼리가_가능합니다() {
        var inputDeliveries = new String[]{"20230905 R00001 D00001 COMPLETED NO 2000"};
        var inputBillings = new String[]{"20230905 R00001 D00001 COMPLETED 2000"};
        var queries = new String[]{"20230905", "20230905"};

        assertThat(solution2.solution(inputDeliveries, inputBillings, queries))
                .isEqualTo(new String[][]{{"BILLING_ONLY 20230905 D00001"}, {"BILLING_ONLY 20230905 D00001"}});
    }

    // 입력
    // ["20230905 222222 D00001 COMPLETED YES 2000", "20230905 111111 D00002 COMPLETED YES 2000"]
    // ["20230905 111111 D00001 COMPLETED 2000", "20230905 444444 D00003 CANCELLED 50000"]
    // ["20230905"]

    // 기대하는 결과
    // [["BILLING_ONLY 20230905 D00003", "DELIVERY_ONLY 20230905 D00002", "DIFFERENT 20230905 D00001"]]
    @Test
    public void testCase6__여러비교결과가_섞여있습니다() {
        var inputDeliveries = new String[]{"20230905 222222 D00001 COMPLETED YES 2000", "20230905 333333 D00002 COMPLETED YES 2000"};
        var inputBillings = new String[]{"20230905 111111 D00001 COMPLETED 2000", "20230905 444444 D00003 CANCELLED 50000"};
        var queries = new String[]{"20230905"};

        assertThat(solution2.solution(inputDeliveries, inputBillings, queries))
                .isEqualTo(new String[][]{{"BILLING_ONLY 20230905 D00003", "DELIVERY_ONLY 20230905 D00002", "DIFFERENT 20230905 D00001"}});
    }


    // 입력
    // ["20230905 222222 D00001 COMPLETED YES 2000", "20230906 111111 D00001 COMPLETED YES 2000", "20230907 111111 D00001 COMPLETED YES 2000", "20230905 111111 D00002 COMPLETED YES 2000"]
    // ["20230905 111111 D00001 COMPLETED 2000", "20230906 111111 D00001 COMPLETED 2000", "20230907 111111 D00001 CANCELLED 2000", "20230905 444444 D00003 CANCELLED 50000"]
    // ["20230907", "20230906", "20230905"]

    // 기대하는 결과
    // [["DIFFERENT 20230907 D00001"], [], ["BILLING_ONLY 20230905 D00003", "DELIVERY_ONLY 20230905 D00002", "DIFFERENT 20230905 D00001"]]
    @Test
    public void testCase7__여러일자의_데이터가_섞여있습니다() {
        var inputDeliveries = new String[]{"20230905 222222 D00001 COMPLETED YES 2000", "20230906 111111 D00001 COMPLETED YES 2000", "20230907 111111 D00001 COMPLETED YES 2000", "20230905 333333 D00002 COMPLETED YES 2000"};
        var inputBillings = new String[]{"20230905 111111 D00001 COMPLETED 2000", "20230906 111111 D00001 COMPLETED 2000", "20230907 111111 D00001 CANCELLED 2000", "20230905 444444 D00003 CANCELLED 50000"};
        var queries = new String[]{"20230907", "20230906", "20230905"};

        assertThat(solution2.solution(inputDeliveries, inputBillings, queries))
                .isEqualTo(new String[][]{{"DIFFERENT 20230907 D00001"}, {}, {"BILLING_ONLY 20230905 D00003", "DELIVERY_ONLY 20230905 D00002", "DIFFERENT 20230905 D00001"}});
    }

    // 조금은 양이 많은 데이터?
}