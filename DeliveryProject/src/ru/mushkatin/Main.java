package ru.mushkatin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;

public class Main {

    public static final Map<Integer, Integer> sizeToFreq = new HashMap<>();

    public static void main(String[] args) throws InterruptedException {
        final int routesCount = 1000;
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < routesCount; i++) {
            Thread thread = new Thread(() -> {
                String route = generateRoute("RLRFR", 100);

                int count = 0;
                for (int j = 0; j < route.length(); j++) {
                    if (route.charAt(j) == 'R') {
                        count++;
                    }
                }

                synchronized (sizeToFreq) {
                    sizeToFreq.merge(count, 1, Integer::sum);
                }
            });
            threads.add(thread);
            thread.start();
        }

        // ждём завершения всех потоков
        for (Thread thread : threads) {
            thread.join();
        }

        // ищем самую частую частоту
        int maxKey = -1;
        int maxValue = 0;
        for (Map.Entry<Integer, Integer> entry : sizeToFreq.entrySet()) {
            if (entry.getValue() > maxValue) {
                maxValue = entry.getValue();
                maxKey = entry.getKey();
            }
        }

        System.out.println("Самое частое количество повторений " + maxKey
                + " (встретилось " + maxValue + " раз)");
        System.out.println("Другие размеры:");
        for (Map.Entry<Integer, Integer> entry : sizeToFreq.entrySet()) {
            if (entry.getKey() != maxKey) {
                System.out.println("- " + entry.getKey() + " (" + entry.getValue() + " раз)");
            }
        }
    }

    public static String generateRoute(String letters, int length) {
        Random random = new Random();
        StringBuilder route = new StringBuilder();
        for (int i = 0; i < length; i++) {
            route.append(letters.charAt(random.nextInt(letters.length())));
        }
        return route.toString();
    }
}