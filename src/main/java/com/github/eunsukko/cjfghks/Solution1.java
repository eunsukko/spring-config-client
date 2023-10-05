package com.github.eunsukko.cjfghks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Solution1 {
    public int solution(int[] inputFishes, int[] inputFeeds) {
        var fishes = toList(inputFishes).stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        var feeds = toList(inputFeeds);

        // 가장 작은애가 맨 앞
        // 큰애가 맨 앞으로 오도록?
        // - 그러면 순서대로 루프를 돌리면 되고
        // - 제거는 맨 마지막만 하면 됨

        for (var feed : feeds) {
            var livedFishes = collectLived(fishes, feed);

            fishes = livedFishes;
        }

        return fishes.size();
    }

    private List<Integer> collectLived(List<Integer> fishes, Integer feed) {
        // 큰 물고기가 앞에 옴
        var livedFishes = new ArrayList<Integer>();

        for (int i = 0; i < fishes.size(); i++) {
            var fish = fishes.get(i);
            // 추가
            if (fish <= feed) {
                livedFishes.add(fish);
                feed -= fish;
                continue;
            }
            // 잡아먹기
            // - 자신이 마지막이 아닌 경우
            if (i + 1 != fishes.size()) {
                fishes.remove(fishes.size() - 1);
                livedFishes.add(fish);
                continue;
            }
            // 본인제거
        }

        return livedFishes;
    }

    private List<Integer> toList(int[] array) {
        return Arrays.stream(array)
                .boxed()
                .collect(Collectors.toList());
    }

}
