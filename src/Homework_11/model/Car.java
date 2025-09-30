package Homework_11.model;

import java.util.Objects;

public class Car {
    private String number;
    private String model;
    private String color;
    private long mileage;
    private long cost;

    public Car(String number, String model, String color, long mileage, long cost) {
        this.number = number;
        this.model = model;
        this.color = color;
        this.mileage = mileage;
        this.cost = cost;
    }

    public String getNumber() { return number; }
    public String getModel() { return model; }
    public String getColor() { return color; }
    public long getMileage() { return mileage; }
    public long getCost() { return cost; }

    public void setNumber(String number) {
        if (number == null || number.trim().isEmpty()) {
            throw new IllegalArgumentException("Номер не может быть пустым");
        }
        this.number = number;
    }

    public void setModel(String model) {
        if (model == null || model.trim().isEmpty()) {
            throw new IllegalArgumentException("Модель не может быть пустой");
        }
        this.model = model;
    }

    public void setColor(String color) {
        if (color == null || color.trim().isEmpty()) {
            throw new IllegalArgumentException("Цвет не может быть пустым");
        }
        this.color = color;
    }

    public void setMileage(long mileage) {
        if (mileage < 0) {
            throw new IllegalArgumentException("Пробег не может быть отрицательным");
        }
        this.mileage = mileage;
    }

    public void setCost(long cost) {
        if (cost < 0) {
            throw new IllegalArgumentException("Стоимость не может быть отрицательной");
        }
        this.cost = cost;
    }

    @Override
    public String toString() {
        return String.format("%s|%s|%s|%d|%d", number, model, color, mileage, cost);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(number, car.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}