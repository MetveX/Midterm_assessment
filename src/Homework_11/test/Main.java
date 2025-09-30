package Homework_11.test;

import Homework_11.model.Car;
import Homework_11.repository.CarsRepository;
import Homework_11.repository.CarsRepositoryImpl;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        File dataDir = new File("src/Homework_11/data");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }

        CarsRepository repository = new CarsRepositoryImpl();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Ввод данных об автомобилях ===");
        System.out.println("Вводите данные в формате: НОМЕР|МОДЕЛЬ|ЦВЕТ|ПРОБЕГ|ЦЕНА");
        System.out.println("Пример: a123me|Mercedes|White|0|8300000");
        System.out.println("Для завершения ввода введите 'END':");

        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equals("END")) {
                break;
            }

            try {
                String[] parts = input.split("\\|");
                if (parts.length == 5) {
                    String number = parts[0].trim();
                    String model = parts[1].trim();
                    String color = parts[2].trim();
                    long mileage = Long.parseLong(parts[3].trim());
                    long cost = Long.parseLong(parts[4].trim());

                    Car car = new Car(number, model, color, mileage, cost);
                    repository.addCar(car);
                    System.out.println("Добавлен автомобиль: " + number);
                } else {
                    System.out.println("Ошибка: неверный формат. Нужно 5 полей через |");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: пробег и цена должны быть числами");
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }

        System.out.println("\n=== Ввод параметров для поиска ===");

        System.out.print("Цвет для поиска (например, Black): ");
        String colorToFind = scanner.nextLine().trim();

        System.out.print("Пробег для поиска (например, 0): ");
        long mileageToFind = Long.parseLong(scanner.nextLine().trim());

        System.out.print("Минимальная цена (например, 700000): ");
        long minPrice = Long.parseLong(scanner.nextLine().trim());

        System.out.print("Максимальная цена (например, 800000): ");
        long maxPrice = Long.parseLong(scanner.nextLine().trim());

        System.out.print("Первая модель для средней стоимости (например, Toyota): ");
        String model1 = scanner.nextLine().trim();

        System.out.print("Вторая модель для средней стоимости (например, Volvo): ");
        String model2 = scanner.nextLine().trim();

        repository.saveToFile("src/Homework_11/data/cars.txt");

        printAllCars(repository);
        executeStreamTasks(repository, colorToFind, mileageToFind, minPrice, maxPrice, model1, model2);
        saveResultsToFile(repository, colorToFind, mileageToFind, minPrice, maxPrice, model1, model2);

        scanner.close();
    }

    private static void printAllCars(CarsRepository repository) {
        System.out.println("\n=== Все автомобили в базе ===");
        System.out.printf("%-8s %-8s %-8s %-8s %s%n", "Number", "Model", "Color", "Mileage", "Cost");
        repository.getAllCars().forEach(car -> System.out.printf("%-8s %-8s %-8s %-8d %d%n",
                car.getNumber(), car.getModel(), car.getColor(), car.getMileage(), car.getCost()));
        System.out.println();
    }

    private static void executeStreamTasks(CarsRepository repository, String colorToFind,
                                           long mileageToFind, long minPrice, long maxPrice,
                                           String model1, String model2) {
        System.out.println("=== Результаты Stream API операций ===");

        List<String> numbers = repository.findNumbersByColorOrMileage(colorToFind, mileageToFind);
        System.out.println("1. Номера автомобилей цвета '" + colorToFind + "' или с пробегом " + mileageToFind + ": " + String.join(" ", numbers));

        int uniqueModels = repository.countUniqueModelsInPriceRange(minPrice, maxPrice);
        System.out.println("2. Уникальные модели в диапазоне " + minPrice + "-" + maxPrice + ": " + uniqueModels + " шт.");

        String cheapestColor = repository.findColorOfCheapestCar();
        System.out.println("3. Цвет автомобиля с минимальной стоимостью: " + cheapestColor);

        double avgCost1 = repository.findAverageCostByModel(model1);
        double avgCost2 = repository.findAverageCostByModel(model2);
        System.out.printf("4. Средняя стоимость модели %s: %.2f%n", model1, avgCost1);
        System.out.printf("5. Средняя стоимость модели %s: %.2f%n", model2, avgCost2);
    }

    private static void saveResultsToFile(CarsRepository repository, String colorToFind,
                                          long mileageToFind, long minPrice, long maxPrice,
                                          String model1, String model2) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("src/Homework_11/data/cars.txt"))) {
            writer.println("=== Результаты анализа автомобилей ===");
            writer.println();
            writer.println("Все автомобили в базе:");
            writer.printf("%-8s %-8s %-8s %-8s %s%n", "Number", "Model", "Color", "Mileage", "Cost");
            repository.getAllCars().forEach(car -> writer.printf("%-8s %-8s %-8s %-8d %d%n",
                    car.getNumber(), car.getModel(), car.getColor(), car.getMileage(), car.getCost()));
            writer.println();

            List<String> numbers = repository.findNumbersByColorOrMileage(colorToFind, mileageToFind);
            writer.println("1. Номера автомобилей цвета '" + colorToFind + "' или с пробегом " + mileageToFind + ": " + String.join(" ", numbers));

            int uniqueModels = repository.countUniqueModelsInPriceRange(minPrice, maxPrice);
            writer.println("2. Уникальные модели в диапазоне " + minPrice + "-" + maxPrice + ": " + uniqueModels + " шт.");

            String cheapestColor = repository.findColorOfCheapestCar();
            writer.println("3. Цвет автомобиля с минимальной стоимостью: " + cheapestColor);

            double avgCost1 = repository.findAverageCostByModel(model1);
            double avgCost2 = repository.findAverageCostByModel(model2);
            writer.printf("4. Средняя стоимость модели %s: %.2f%n", model1, avgCost1);
            writer.printf("5. Средняя стоимость модели %s: %.2f%n", model2, avgCost2);

            System.out.println("\nРезультаты сохранены в файл: src/Homework_11/data/cars.txt");

        } catch (Exception e) {
            System.err.println("Ошибка при записи результатов: " + e.getMessage());
        }
    }
}