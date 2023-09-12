package com.github.eunsukko.codingtest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution3 {

    public String[] solution(String[] inputCommands) {
        // tx begin
        // tx end
        // tx rollback
        // a 100
        var commands = readCommands(inputCommands);

        var answers = new ArrayList<String>();
        return toAnswers(answers);
    }

    private List<String> readCommands(String[] inputCommands) {
        return Arrays.stream(inputCommands)
                .collect(Collectors.toList());
    }

    private String[] toAnswers(List<String> answers) {
        return answers.toArray(new String[answers.size()]);
    }
}
