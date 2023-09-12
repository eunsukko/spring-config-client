package com.github.eunsukko.codingtest;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Solution2 {

    public String[][] solution(String[] inputDeliveries, String[] inputBillings, String[] inputQueries) {
        var deliveries = readDeliveries(inputDeliveries);
        var billings = readBillings(inputBillings);

        var deliveriesForBilling = deliveries.stream()
                .filter(delivery -> delivery.isBillingRequired())
                .collect(Collectors.toList());


        return toAnswers(queryForAnswer(inputQueries, deliveriesForBilling, billings));
    }

    // 매개변수 등을 여기에 추가해도 좋습니다
    private List<List<String>> queryForAnswer(String[] inputQueries, List<Delivery> deliveries, List<Billing> billings) {
        var answers = new ArrayList<List<String>>();
        for (var comparedDay : inputQueries) {
            var answer = compareAt(comparedDay, deliveries, billings).stream()
                    .map(differentDelivery -> differentDelivery.toAnswerString())
                    .collect(Collectors.toList());
            answers.add(answer);
        }
        return answers;
    }

    private List<DifferentDelivery> compareAt(String comparedDay, List<Delivery> deliveries, List<Billing> billings) {
        var deliveriesAt = deliveries.stream()
                .filter(delivery -> delivery.isAt(comparedDay))
                .collect(Collectors.toList());
        var billingsAt = billings.stream()
                .filter(billing -> billing.isAt(comparedDay))
                .collect(Collectors.toList());
        return compare(deliveriesAt, billingsAt);
    }

    // comparedDay 에 해당하는 배달,빌링 건들만 입력으로 존재합니다
    private List<DifferentDelivery> compare(List<Delivery> deliveries, List<Billing> billings) {
        // 복합키 = workingDay + deliveryId
        var deliveryMap = deliveries.stream()
                .collect(Collectors.toMap(delivery -> delivery.toKey(), Function.identity()));
        var billingMap = billings.stream()
                .collect(Collectors.toMap(billing -> billing.toKey(), Function.identity()));

        var differentDeliveries = new ArrayList<DifferentDelivery>();

        // 배달팀에만 존재하는 경우
        for (var key : deliveryMap.keySet()) {
            if (!billingMap.containsKey(key)) {
                var delivery = deliveryMap.get(key);
                differentDeliveries.add(DifferentDelivery.deliveryOnly(delivery.getWorkingDay(), delivery.getDeliveryId()));
            }
        }

        // 빌링팀에만 존재하는 경우
        for (var key : billingMap.keySet()) {
            if (!deliveryMap.containsKey(key)) {
                var billing = billingMap.get(key);
                differentDeliveries.add(DifferentDelivery.billingOnly(billing.getWorkingDay(), billing.getDeliveryId()));
            }
        }

        // 모두 존재하지만 서로 다른 값을 가진 경우
        var bothMap = new HashSet<>(deliveryMap.keySet());
        bothMap.retainAll(billingMap.keySet());
        for (var key : bothMap) {
            var delivery = deliveryMap.get(key);
            var billing = billingMap.get(key);

            DifferentDelivery.compared(delivery, billing)
                    .ifPresent(differentDelivery -> differentDeliveries.add(differentDelivery));
        }

        // 문자열 순으로 정렬하기
        differentDeliveries.sort(Comparator.comparing(differentDelivery -> differentDelivery.toAnswerString()));

        return differentDeliveries;
    }

    public static class DifferentDelivery {
        private final String workingDay;
        private final String deliveryId;
        private final String differentType; // DELIVERY_ONLY, BILLING_ONLY, DIFFERENT

        public DifferentDelivery(String workingDay, String deliveryId, String differentType) {
            this.workingDay = workingDay;
            this.deliveryId = deliveryId;
            this.differentType = differentType;
        }

        public static DifferentDelivery deliveryOnly(String workingDay, String deliveryId) {
            return new DifferentDelivery(workingDay, deliveryId, "DELIVERY_ONLY");
        }

        public static DifferentDelivery billingOnly(String workingDay, String deliveryId) {
            return new DifferentDelivery(workingDay, deliveryId, "BILLING_ONLY");
        }

        public static Optional<DifferentDelivery> compared(Delivery delivery, Billing billing) {
            var workingDay = delivery.getWorkingDay();
            var deliveryId = delivery.getDeliveryId();

            if (!delivery.getRiderId().equals(billing.getRiderId())) {
                return Optional.of(new DifferentDelivery(workingDay, deliveryId, "DIFFERENT"));
            }

            if (!delivery.getStatus().equals(billing.getDeliveryStatus())) {
                return Optional.of(new DifferentDelivery(workingDay, deliveryId, "DIFFERENT"));
            }

            if (delivery.getDeliveryFee() != billing.getDeliveryFee()) {
                return Optional.of(new DifferentDelivery(workingDay, deliveryId, "DIFFERENT"));
            }

            return Optional.empty();
        }

        public String getWorkingDay() {
            return workingDay;
        }

        public String getDeliveryId() {
            return deliveryId;
        }

        public String getDifferentType() {
            return differentType;
        }

        public String toAnswerString() {
            return String.format("%s %s %s", differentType, workingDay, deliveryId);
        }
    }


    public static class Delivery {
        private final String workingDay;
        private final String riderId;
        private final String deliveryId;
        private final String status; // COMPLETED, CANCELLED
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

        public boolean isBillingRequired() {
            return "YES".equals(requiredBilling);
        }

        public boolean isAt(String workingDay) {
            return this.workingDay.equals(workingDay);
        }

        public String toKey() {
            return workingDay + deliveryId;
        }

        public String getWorkingDay() {
            return workingDay;
        }

        public String getDeliveryId() {
            return deliveryId;
        }

        public String getRiderId() {
            return riderId;
        }

        public String getStatus() {
            return status;
        }

        public int getDeliveryFee() {
            return deliveryFee;
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

        public boolean isAt(String workingDay) {
            return this.workingDay.equals(workingDay);
        }

        public String toKey() {
            return workingDay + deliveryId;
        }

        public String getWorkingDay() {
            return workingDay;
        }

        public String getDeliveryId() {
            return deliveryId;
        }

        public String getRiderId() {
            return riderId;
        }

        public String getDeliveryStatus() {
            return deliveryStatus;
        }

        public int getDeliveryFee() {
            return fee;
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

    private String[][] toAnswers(List<List<String>> answers) {
        var outputAnswer = new String[answers.size()][];
        for (int i = 0; i < answers.size(); i++) {
            var answer = answers.get(i);
            outputAnswer[i] = answer.toArray(new String[answer.size()]);
        }
        return outputAnswer;
    }
}
