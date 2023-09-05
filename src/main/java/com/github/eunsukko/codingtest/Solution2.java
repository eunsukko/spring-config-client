package com.github.eunsukko.codingtest;

import java.util.ArrayList;
import java.util.List;

public class Solution2 {

    private static final int MAX_NUMBER_OF_DELIVERIES = 100;

    public String[][] solution(String[] inputDeliveries, String[] inputBillings, String[] inputQueries) {
        var deliveries = readDeliveries(inputDeliveries);
        var billings = readBillings(inputBillings);

        //

        return toAnswers(queryForAnswer(inputQueries));
    }

    public static class Delivery {
        private final String workingDay;
        private final String riderId;
        private final String deliveryId;
        private final String status; // CRATED, COMPLETED, CANCELLED
        private final String requiredBilling; // YES, NO
        private final int deliveryFee;

        public Delivery(String workingDay, String riderId, String deliveryId, String status, String requiredBilling, int deliveryFee) {
            this.workingDay = workingDay;
            this.riderId = riderId;
            this.deliveryId = deliveryId;
            this.status = status;
            this.requiredBilling = requiredBilling;
            this.deliveryFee = deliveryFee;
        }
    }

    public static class Billing {
        private final String workingDay;
        private final String riderId;
        private final String deliveryId;
        private final String deliveryStatus; // CRATED, COMPLETED, CANCELLED
        private final int fee;

        public Billing(String workingDay, String riderId, String deliveryId, String deliveryStatus, int fee) {
            this.workingDay = workingDay;
            this.riderId = riderId;
            this.deliveryId = deliveryId;
            this.deliveryStatus = deliveryStatus;
            this.fee = fee;
        }
    }

    private List<Delivery> readDeliveries(String[] inputDeliveries) {
        var deliveries = new ArrayList<Delivery>();
        for (var inputDelivery : inputDeliveries) {
            var splited = inputDelivery.split(" ");
            deliveries.add(new Delivery(splited[0], splited[1], splited[2], splited[3], splited[4], Integer.parseInt(splited[5])));
        }
        return deliveries;
    }

    private List<Billing> readBillings(String[] inputBillings) {
        var bilings = new ArrayList<Billing>();
        for (var inputBilling : inputBillings) {
            var splited = inputBilling.split(" ");
            bilings.add(new Billing(splited[0], splited[1], splited[2], splited[3], Integer.parseInt(splited[4])));
        }
        return bilings;
    }

    private List<List<String>> queryForAnswer(String[] inputQueries) {
        var answer = new ArrayList<List<String>>();
        for (var workingDay : inputQueries) {
            //
            // answer.add();
        }
        return answer;
    }

    private String[][] toAnswers(List<List<String>> answers) {
        var outputAnswer = new String[answers.size()][];
        for (int i = 0; i < answers.size(); i++) {
            var answer = answers.get(i);
            outputAnswer[i] = answer.toArray(new String[answer.size()]);
        }
        return outputAnswer;
    }
}
