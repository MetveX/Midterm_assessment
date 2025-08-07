package Midterm_assessment;

import java.util.ArrayList;

public class Person {

    String name;
    double money;
    ArrayList<String> products = new ArrayList<>();

    public Person(String name, double money) {
        this.name = name;
        this.money = money;
    }
}