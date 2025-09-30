package Homework_8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Тест Задания 1 ===");
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 2, 4, 1, 5));
        ArrayList<String> strings = new ArrayList<>(Arrays.asList("a", "b", "c", "a", "b"));

        System.out.println("Исходный список чисел: " + numbers);
        System.out.println("Уникальные числа: " + CollectionUtils.getUniqueElements(numbers));

        System.out.println("Исходный список строк: " + strings);
        System.out.println("Уникальные строки: " + CollectionUtils.getUniqueElements(strings));

        System.out.println("\n=== Тест Задания 2 ===");
        System.out.println("Бейсбол - бобслей: " + AnagramChecker.isAnagram("Бейсбол", "бобслей"));
        System.out.println("Героин - регион: " + AnagramChecker.isAnagram("Героин", "регион"));
        System.out.println("Клоака - околка: " + AnagramChecker.isAnagram("Клоака", "околка"));
        System.out.println("Тест - тсее: " + AnagramChecker.isAnagram("Тест", "тсее"));

        System.out.println("\n=== Тест Задания 3 ===");
        PowerfulSet powerfulSet = new PowerfulSet();
        Set<Integer> setA = Set.of(1, 2, 3, 4, 5);
        Set<Integer> setB = Set.of(3, 4, 5, 6, 7);

        System.out.println("Set A: " + setA);
        System.out.println("Set B: " + setB);
        System.out.println("Пересечение: " + powerfulSet.intersection(setA, setB));
        System.out.println("Объединение: " + powerfulSet.union(setA, setB));
        System.out.println("A \\ B: " + powerfulSet.relativeComplement(setA, setB));
        System.out.println("B \\ A: " + powerfulSet.relativeComplement(setB, setA));
    }
}