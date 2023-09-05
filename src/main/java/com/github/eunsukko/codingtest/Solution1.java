package com.github.eunsukko.codingtest;

import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Solution1 {
    public String[][] solution(int[][] inputEvents, int m, String[] inputPayments) {
        var events = readEvents(inputEvents);

        // 적절한 용어가 생각이 안나네 ㅠㅎ
        var threshHold = m;
        var payments = readPayments(inputPayments);

        var ans = new ArrayList<String[]>();
        for (var event : events) {
            // threshHold 개 만큼 채우기
            ans.add(findWinners(event, threshHold, payments));
        }

        return ans.toArray(new String[events.size()][threshHold]);
    }

    private String[] findWinners(Event event, int threshHold, List<Payment> payments) {
        var winners = new ArrayList<>();
        // winners 를 채우기
        // - threshHold 를 채우면 끝
        // -
        var totalPayMap = payments.stream()
                .map(Payment::getCustomerName)
                .collect(Collectors.toSet()).stream()
                .collect(Collectors.toMap(name -> name, name -> 0));
        for (var payment : payments) {
            if (winners.size() >= threshHold) {
                break;
            }

            if (event.isNotEventPeriod(payment.getPayDay())) {
                continue;
            }

            var customer = payment.customerName;
            var currentTotal = totalPayMap.get(customer) + payment.getPayMoney();

            totalPayMap.put(customer, currentTotal);

            if (currentTotal >= event.getEventMoney()
                    && !winners.contains(customer)) {
                winners.add(customer);
            }
        }

        // 사전순으로 정렬 필요
        var sortedWinners = winners.stream()
                .sorted()
                .collect(Collectors.toList());

        final var emptyWinner = "";
        if (sortedWinners.size() < threshHold) {
            for (int i = sortedWinners.size(); i < threshHold; i++) {
                sortedWinners.add(emptyWinner);
            }
        }

        return sortedWinners.toArray(new String[threshHold]);
    }


    private List<Event> readEvents(int[][] inputEvents) {
        var events = new ArrayList<Event>();
        for (int[] inputEvent : inputEvents) {
            events.add(new Event(inputEvent[0], inputEvent[1], inputEvent[2]));
        }
        return events;
    }

    private List<Payment> readPayments(String[] inputPayments) {
        var payments = new ArrayList<Payment>();
        for (String inputPayment : inputPayments) {
            var splited = inputPayment.split(" ");
            payments.add(new Payment(
                    Integer.parseInt(splited[1]),
                    splited[0],
                    Integer.parseInt(splited[2])));
        }
        return payments;
    }

    static class Event {
        // [from, to]
        int from;
        int to;
        int eventMoney;

        public Event(int from, int to, int eventMoney) {
            this.from = from;
            this.to = to;
            this.eventMoney = eventMoney;
        }

        public boolean isNotEventPeriod(int payDay) {
            return !isEventPeriod(payDay);
        }

        public boolean isEventPeriod(int payDay) {
            return (from <= payDay) && (payDay <= to);
        }

        public int getEventMoney() {
            return eventMoney;
        }
    }

    static class Payment {
        int payDay;
        String customerName;
        int payMoney;

        public Payment(int payDay, String customerName, int payMoney) {
            this.payDay = payDay;
            this.customerName = customerName;
            this.payMoney = payMoney;
        }

        public int getPayDay() {
            return payDay;
        }

        public String getCustomerName() {
            return customerName;
        }

        public int getPayMoney() {
            return payMoney;
        }
    }
}
