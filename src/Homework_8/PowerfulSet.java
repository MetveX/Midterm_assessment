package Homework_8;

import java.util.HashSet;
import java.util.Set;

public class PowerfulSet {

    public <T> Set<T> intersection(Set<T> set1, Set<T> set2) {
        Set<T> result = new HashSet<>(set1);
        result.retainAll(set2);
        return result;
    }

    public <T> Set<T> union(Set<T> set1, Set<T> set2) {
        Set<T> result = new HashSet<>(set1);
        result.addAll(set2);
        return result;
    }

    public <T> Set<T> relativeComplement(Set<T> set1, Set<T> set2) {
        Set<T> result = new HashSet<>(set1);
        result.removeAll(set2);
        return result;
    }

    public static void main(String[] args) {
        PowerfulSet powerfulSet = new PowerfulSet();

        // Примеры из задания
        Set<Integer> set1 = Set.of(1, 2, 3);
        Set<Integer> set2 = Set.of(0, 1, 2, 4);

        System.out.println("Set1: " + set1);
        System.out.println("Set2: " + set2);

        System.out.println("Пересечение: " + powerfulSet.intersection(set1, set2));
        System.out.println("Объединение: " + powerfulSet.union(set1, set2));
        System.out.println("Разность (set1 \\ set2): " + powerfulSet.relativeComplement(set1, set2));
    }
}