package model;

public class RailroadSpace extends PropertySpace {
    final private int  BASE_RENT_PRICE = 25;

    public RailroadSpace(int number, String name, int price) {
        super(number, name, price);
    }

    public int getRentPrice() {
        return owner.getRailroadCount() * BASE_RENT_PRICE;
    }
}
