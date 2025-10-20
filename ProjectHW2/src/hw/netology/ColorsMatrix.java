package com.scanner.example.forlesson;

import java.util.Scanner;
import java.util.Random;

public class ColorsMatrix {

    public static final int SIZE = 8;

    public static void toScreen(int[][] colors) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.format("%4d", colors[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        int[][] colors = new int[SIZE][SIZE];
        int[][] rotatedColors90 = new int[SIZE][SIZE];
        int[][] rotatedColors180 = new int[SIZE][SIZE];
        int[][] rotatedColors270 = new int[SIZE][SIZE];

        Random rand = new Random();

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                colors[i][j] = rand.nextInt(256);
            }
        }
        for (int k = 0; k < SIZE; k++) {
            for (int n = 0; n < SIZE; n++) {
                rotatedColors90[n][SIZE - 1 - k] = colors[k][n];
            }
        }
        for (int k = 0; k < SIZE; k++) {
            for (int n = 0; n < SIZE; n++) {
                rotatedColors180[n][SIZE - 1 - k] = rotatedColors90[k][n];
            }
        }
        for (int k = 0; k < SIZE; k++) {
            for (int n = 0; n < SIZE; n++) {
                rotatedColors270[n][SIZE - 1 - k] = rotatedColors180[k][n];
            }
        }

        System.out.println("Исходная матрица:");
        toScreen(colors);
        System.out.println("Выберите угол поворота: 90, 180, 270");
        int number = s.nextInt();
        if (number == 90) {
            System.out.println("Матрица после поворота на 90°:");
            toScreen(rotatedColors90);
        } else if (number == 180) {
            System.out.println("Матрица после поворота на 180°:");
            toScreen(rotatedColors180);
        } else if (number == 270) {
            System.out.println("Матрица после поворота на 270°:");
            toScreen(rotatedColors270);
        }
    }
}
