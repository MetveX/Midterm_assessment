package Homework_7;

class Product {
    protected String name;
    protected double cost;

    public Product(String name, double cost) {
        setName(name);
        setCost(cost);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Название продукта не может быть пустой строкой");
        }
        if (name.length() < 3) {
            throw new IllegalArgumentException("Название продукта должно быть не короче 3 символов");
        }
        if (name.matches("\\d+")) {
            throw new IllegalArgumentException("Название продукта не должно содержать только цифры");
        }
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        if (cost <= 0) {
            throw new IllegalArgumentException("Стоимость продукта должна быть положительным числом");
        }
        this.cost = cost;
    }

    public double getPrice() {
        return cost;
    }

    @Override
    public String toString() {
        return String.format("%s - %.2f руб.", name, getPrice());
    }
}