package com.github.eunsukko.cjfghks;

public class Solution2 {
    public int[][] solution(int r, int c) {
        int[][] boards = new int[r][];
        for (int i = 0; i < r; i++) {
            boards[i] = new int[c];
        }

        int currentNumber = 0;
        int layerCount = (r - 1) / 2;

        // 껍질 채우기
        // r -1 / 2개
        for (int layer = 0; layer < layerCount; layer++) {
            // 시작 위치 구하기
            var startR = (layer % 2 == 0) ? layer : (r - 1) - layer;
            // dr, dc 구하기 - 그리기에 필요한 길이
            currentNumber = draw(boards, startR, c - 1, r - (2 * layer), c - layer, (layer % 2 == 0) ? 1 : -1, currentNumber + 1);
        }

        // 마지막 라인 채우기
        // 1 개
        // 시작위치 구하기
        // dc 그리기 - 그리기에 필요한 길이
        drawLine(boards, layerCount, c - 1, c - layerCount, currentNumber + 1);


        return boards;
    }

    private void drawLine(int[][] boards, int startR, int startC, int dC, int number) {

        // <----------
        var currentR = startR;
        var currentC = startC;
        boards[currentR][currentC] = number;

        for (int i = 0; i < dC- 1; i++) {
            number += 1;
            currentC -= 1; // <-
            boards[currentR][currentC] = number;
        }
    }

    // rDirection -> +1 /-1
    private int draw(int[][] boards, int startR, int startC, int dR, int dC, int rDirection, int number) {
//        System.out.println(String.format("start (%d, %d)", startR, startC));

        var currentR = startR;
        var currentC = startC;
        boards[currentR][currentC] = number;

        // <----------
        for (int i = 0; i < dC - 1; i++) {
            number += 1;
            currentC -= 1; // <-
            boards[currentR][currentC] = number;
        }

        // |
        for (int i = 0; i < dR - 1; i++) {
            number += 1;
            currentR += rDirection; // |
            boards[currentR][currentC] = number;
        }

        // ----------->
        for (int i = 0; i < dC - 1; i++) {
            number += 1;
            currentC += 1; // ->
            boards[currentR][currentC] = number;
        }

        return number;
    }
}
