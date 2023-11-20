package org.example;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    static StringBuilder results = new StringBuilder();
    static int length = 500;

    public static void swapping(int first, int second, List<Integer> numbers) {
        int a = numbers.get(first);
        numbers.set(first, numbers.get(second));
        numbers.set(second, a);
    }

    public static void main(String[] args) {
        while (length <= 45000) {
            List<Integer> numbers = new ArrayList<>();
            results.append(length).append(";");
            Random value = new Random();
            for (int i = 0; i < length; i++) numbers.add(value.nextInt());
            long startTime = System.nanoTime();
            for (int out = length - 1; out >= 1; out--) {
                for (int in = 0; in < out; in++) {
                    if (numbers.get(in) > numbers.get(in + 1))
                        swapping(in, in + 1, numbers);
                }
            }
            long endTime = System.nanoTime();
            long spent_time = (endTime - startTime);  //divide by 1000000 to get milliseconds.
            results.append(spent_time / 1000000).append(",").append((spent_time / 10000) % 100).append("\n");
            length += 250;
            File outputFile = new File("Results.txt");
            try (OutputStream outputStream = new FileOutputStream(outputFile)) {
                outputStream.write(results.toString().getBytes(StandardCharsets.UTF_8));
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}