package Homework_8;

import java.util.Arrays;
import java.util.Scanner;

public class AnagramChecker {

    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        char[] sArray = s.toLowerCase().toCharArray();
        char[] tArray = t.toLowerCase().toCharArray();

        Arrays.sort(sArray);
        Arrays.sort(tArray);

        return Arrays.equals(sArray, tArray);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите первую строку:");
        String s = scanner.nextLine();

        System.out.println("Введите вторую строку:");
        String t = scanner.nextLine();

        boolean result = isAnagram(s, t);
        System.out.println("Результат: " + result);

        // Тестовые примеры из задания
        System.out.println("\nТестовые примеры:");
        System.out.println("Бейсбол - бобслей: " + isAnagram("Бейсбол", "бобслей"));
        System.out.println("Героин - регион: " + isAnagram("Героин", "регион"));
        System.out.println("Клоака - околка: " + isAnagram("Клоака", "околка"));

        scanner.close();
    }
}