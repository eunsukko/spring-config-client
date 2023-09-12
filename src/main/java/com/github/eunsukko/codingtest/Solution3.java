package com.github.eunsukko.codingtest;

import java.util.*;
import java.util.stream.Collectors;

public class Solution3 {

    public String[] solution(String[] inputCommands) {
        // tx begin
        // tx end
        // tx rollback
        // a 100
        var commands = readCommands(inputCommands);

        var variableMap = new HashMap<String, Integer>();

        var txContext = TxContext.empty();
        for (var command : commands) {
            var splited = command.split(" ");
            if (!"tx".equals(splited[0])) {
                var variable = splited[0];
                var value = Integer.parseInt(splited[1]);

                if (txContext.isNotBegin()) {
                    variableMap.put(variable, value);
                    continue;
                }

                // tx
                txContext.saveVariable(variable, value);
                continue;
            }

            // tx
            var txCommand = splited[1];
            if (txCommand.equals("begin")) {
                txContext.beginTx();
            }
            if (txCommand.equals("end")) {
                txContext.endTx();
                // 트랜잭션이 끝날 때 저장을 시도
                if (txContext.canSaveVariables()) {
                    variableMap.putAll(txContext.getVariableMap());
                }
                if (txContext.isAllEnded()) {
                    txContext.clear();
                }
            }
            if (txCommand.equals("rollback")) {
                txContext.markToRollback();
            }
        }

        var answers = new ArrayList<String>();
        for (var variable : variableMap.keySet()) {
            answers.add(String.format("%s %d", variable, variableMap.get(variable)));
        }

        return toAnswers(answers);
    }

    static class TxContext {
        private int txCount;
        private boolean rollback;
        private Map<String, Integer> variableMap;

        public static TxContext empty() {
            return new TxContext(0, false, new HashMap<String, Integer>());
        }

        public TxContext(int txCount, boolean rollback, Map<String, Integer> variableMap) {
            this.txCount = txCount;
            this.variableMap = variableMap;
            this.rollback = rollback;
        }

        public void markToRollback() {
            rollback = true;
        }

        public void beginTx() {
            txCount += 1;
        }

        public void endTx() {
            txCount -= 1;
        }

        public boolean canSaveVariables() {
            if (rollback) {
                return false;
            }
            return txCount == 0;
        }

        public Map<String, Integer> getVariableMap() {
            return variableMap;
        }

        public void clear() {
            variableMap.clear();
            rollback = false;
        }

        public void saveVariable(String variable, int value) {
            variableMap.put(variable, value);
        }

        public boolean isNotBegin() {
            return txCount <= 0;
        }

        public boolean isAllEnded() {
            return txCount == 0;
        }
    }

    private List<String> readCommands(String[] inputCommands) {
        return Arrays.stream(inputCommands)
                .collect(Collectors.toList());
    }

    private String[] toAnswers(List<String> answers) {
        return answers.toArray(new String[answers.size()]);
    }
}
