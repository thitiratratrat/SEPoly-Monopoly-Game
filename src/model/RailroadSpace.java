package model;

public class RailroadSpace extends PropertySpace {
    final private int  BASE_RENT_PRICE = 25;

    public RailroadSpace(int number, String name, int price, double[] positions) {
        super(number, name, positions, price);
    }

    public int getRentPrice() {
        return owner.getRailroadCount() * BASE_RENT_PRICE;
    }
}
