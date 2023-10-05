package com.github.eunsukko.cjfghks;

import java.util.ArrayList;
import java.util.List;

public class Solution3 {
    public int solution(int n, int[] cars, int[][] links) {
        //
        var edges = generateEdges(links, n);

        // rootSum
        var rootSums = new int[n];
        var visited = new int[n];
        for (int i = 0; i < rootSums.length; i++) {
            rootSums[i] = 0;
            visited[i] = 0;
        }

        var totalSum = fillRootSum(0, edges, rootSums, visited, cars);

        int minDiff = 123456789;
        for (var rootSum : rootSums) {
            var remainder = totalSum - rootSum;
            var currentDiff = abs(remainder - rootSum);
            minDiff = Integer.min(minDiff, currentDiff);
        }

        return minDiff;
    }

    private int abs(int a) {
        if (a < 0) {
            return -a;
        }
        return a;
    }

    private int fillRootSum(int node, List<List<Integer>> edges, int[] rootSums, int[] visited, int [] cars) {
//        System.out.println(String.format("node: %d", node + 1));
        if (visited[node] == 1) {
            return 0;
        }
        visited[node] = 1;

        int totalSum = cars[node];
        for (var toNode : edges.get(node)) {
            totalSum += fillRootSum(toNode, edges, rootSums, visited, cars);
        }

//        System.out.println(String.format("totalSum[%d] = %d", node + 1, totalSum));

        rootSums[node] = totalSum;

        return totalSum;
    }

    private List<List<Integer>> generateEdges(int [][] links, int n) {
        var edges = new ArrayList<List<Integer>>();
        for (int i = 0;i < n; i++) {
            edges.add(new ArrayList<Integer>());
        }

        // fill edges
        for (var link : links) {
            var n1 = link[0] - 1;
            var n2 = link[1] - 1;

            edges.get(n1).add(n2);
            edges.get(n2).add(n1);
        }
        return edges;
    }
}