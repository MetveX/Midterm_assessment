package Homework_11.repository;

import Homework_11.model.Car;
import java.util.List;

public interface CarsRepository {
    List<Car> getAllCars();
    void addCar(Car car);
    List<String> findNumbersByColorOrMileage(String colorToFind, long mileageToFind);
    int countUniqueModelsInPriceRange(long minPrice, long maxPrice);
    String findColorOfCheapestCar();
    double findAverageCostByModel(String modelToFind);
    void saveToFile(String filename);
    void loadFromFile(String filename);

}