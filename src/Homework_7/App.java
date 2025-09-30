package Homework_7;

import java.util.ArrayList;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Person> persons = new ArrayList<>();
        ArrayList<Product> allProducts = new ArrayList<>();
        ArrayList<String> output = new ArrayList<>();

        System.out.println("Введите данные: ");
        String customersLine = scanner.nextLine();
        String[] customersData = customersLine.split(";");
        for (String customer : customersData) {
            String[] parts = customer.split("=");
            if (parts.length < 2) continue;

            String name = parts[0].trim();
            double money = Double.parseDouble(parts[1].trim());

            if (name.length() < 3) {
                System.out.println("Имя не может быть короче 3 символов");
                return;
            }
            if (money < 0) {
                System.out.println("Деньги не могут быть отрицательными");
                return;
            }

            persons.add(new Person(name, money));
        }

        String productsLine = scanner.nextLine();
        String[] productsData = productsLine.split(";");
        for (String product : productsData) {
            String[] parts = product.split("=");
            if (parts.length < 2) continue;

            String name = parts[0].trim();
            double cost = Double.parseDouble(parts[1].trim());

            if (allProducts.size() % 2 == 0) {
                double discount = cost * 0.1; // 10% скидка
                allProducts.add(new DiscountProduct(name, cost, discount));
            } else {
                allProducts.add(new Product(name, cost));
            }
        }

        System.out.println("\n=== Информация о всех продуктах ===");
        for (Product p : allProducts) {
            System.out.println(p.toString());
        }

        while (true) {
            String line = scanner.nextLine();
            if (line.equals("END")) break;

            String[] parts = line.split(" - ");
            if (parts.length < 2) continue;

            String personName = parts[0].trim();
            String productName = parts[1].trim();

            Person currentPerson = null;
            for (Person p : persons) {
                if (p.name.equals(personName)) {
                    currentPerson = p;
                    break;
                }
            }

            Product currentProduct = null;
            for (Product p : allProducts) {
                if (p.getName().equals(productName)) {
                    currentProduct = p;
                    break;
                }
            }

            if (currentPerson != null && currentProduct != null) {
                if (currentPerson.money >= currentProduct.getPrice()) {
                    currentPerson.money -= currentProduct.getPrice();
                    currentPerson.products.add(currentProduct.getName());
                    output.add(personName + " купил " + productName);
                } else {
                    output.add(personName + " не может позволить себе " + productName);
                }
            }
        }

        output.add("");

        for (Person p : persons) {
            if (p.products.isEmpty()) {
                output.add(p.name + " - Ничего не куплено");
            } else {
                StringBuilder sb = new StringBuilder(p.name + " - ");
                for (int i = 0; i < p.products.size(); i++) {
                    if (i > 0) sb.append(", ");
                    sb.append(p.products.get(i));
                }
                output.add(sb.toString());
            }
        }

        for (String line : output) {
            System.out.println(line);
        }

        scanner.close();
    }
}