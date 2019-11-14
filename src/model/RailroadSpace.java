package model;

public class RailroadSpace extends PropertySpace {
    final private int  BASE_RENT_PRICE = 25;

    public RailroadSpace(int number, String name, int price, double[] positions, byte[] image) {
        super(number, name, positions, image, price);
    }

    public int getRentPrice() {
        return owner.getRailroadCount() * BASE_RENT_PRICE;
    }

}
