package com.github.eunsukko.cjfghks;

public class Solution5 {
    public int solution(int n, int[] premium, int[] normal) {
        // 한칸씩 옆으로 밀자
        int[] nextPremium = new int[n];
        int[] nextNormal = new int[n];
        for (int i = 0; i < n - 1; i++) {
            nextPremium[i] = premium[i + 1];
            nextNormal[i] = normal[i + 1];
        }
        nextPremium[n - 1] = premium[0];
        nextNormal[n - 1] = normal[0];

        return Integer.min(solve(n, premium, normal), solve(n, nextPremium, nextNormal));
    }

    public int solve(int n, int[] premium, int[] normal) {
        long[] dp = new long[n + 1];
        for (int i = 0; i < n + 1; i++) {
            dp[i] = 0;
        }

        //
        dp[0] = 0;
        dp[1] = Integer.min(normal[0], premium[0]);

        for (int i = 2; i <= n; i++) {
            var node = i - 1;
            var currentDp = 12345678900000L;

            //
            currentDp = Long.min(currentDp, dp[i - 2] + premium[node - 1]);

            currentDp = Long.min(currentDp, dp[i - 1] + normal[node]);

            // 혹 일반 배달이 더 싼 경우도 있으니
            // - 마지막 쪽을 위함
            currentDp = Long.min(currentDp, dp[i - 1] + premium[node]);

            dp[i] = currentDp;
//            System.out.println(String.format("dp[%d] = %d", node, dp[i]));
        }

        return (int) dp[n];
    }
}