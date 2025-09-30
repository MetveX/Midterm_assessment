package Homework_7;

class DiscountProduct extends Product {
    private double discount;
    private boolean discountActive;

    public DiscountProduct(String name, double cost, double discount) {
        super(name, cost);
        setDiscount(discount);
        this.discountActive = true;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        if (discount < 0) {
            throw new IllegalArgumentException("Размер скидки не может быть отрицательным");
        }
        if (discount > cost) {
            throw new IllegalArgumentException("Размер скидки не может превышать цену продукта");
        }
        this.discount = discount;
    }

    public boolean isDiscountActive() {
        return discountActive;
    }

    public void setDiscountActive(boolean discountActive) {
        this.discountActive = discountActive;
    }

    @Override
    public double getPrice() {
        return discountActive ? cost - discount : cost;
    }

    @Override
    public String toString() {
        if (discountActive) {
            return String.format("%s - %.2f руб. (скидка %.2f руб.)", name, getPrice(), discount);
        } else {
            return String.format("%s - %.2f руб. (скидка не активна)", name, getPrice());
        }
    }
}