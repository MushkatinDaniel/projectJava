package ru.mushkatin;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {

    private static final int TEXTS_COUNT = 10_000;
    private static final int TEXT_LENGTH = 100_000;
    private static final int QUEUE_CAPACITY = 100;


    private static final BlockingQueue<String> queueA = new ArrayBlockingQueue<>(QUEUE_CAPACITY);
    private static final BlockingQueue<String> queueB = new ArrayBlockingQueue<>(QUEUE_CAPACITY);
    private static final BlockingQueue<String> queueC = new ArrayBlockingQueue<>(QUEUE_CAPACITY);

    public static void main(String[] args) throws InterruptedException {

        Thread generator = new Thread(() -> {
            try {
                for (int i = 0; i < TEXTS_COUNT; i++) {
                    String text = generateText("abc", TEXT_LENGTH);
                    queueA.put(text);
                    queueB.put(text);
                    queueC.put(text);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread analyzerA = createAnalyzer(queueA, 'a');
        Thread analyzerB = createAnalyzer(queueB, 'b');
        Thread analyzerC = createAnalyzer(queueC, 'c');

        generator.start();
        analyzerA.start();
        analyzerB.start();
        analyzerC.start();

        generator.join();
        analyzerA.join();
        analyzerB.join();
        analyzerC.join();
    }

    private static Thread createAnalyzer(BlockingQueue<String> queue, char symbol) {
        return new Thread(() -> {
            int maxCount = 0;
            try {

                for (int i = 0; i < TEXTS_COUNT; i++) {
                    String text = queue.take();
                    int count = countChar(text, symbol);
                    if (count > maxCount) {
                        maxCount = count;
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            System.out.println("Максимальное количество символов '" + symbol + "': " + maxCount);
        });
    }

    private static int countChar(String text, char symbol) {
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == symbol) {
                count++;
            }
        }
        return count;
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }
}