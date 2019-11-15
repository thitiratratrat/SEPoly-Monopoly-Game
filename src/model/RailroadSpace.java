package model;

public class RailroadSpace extends PropertySpace {
    final private int BASE_RENT_PRICE = 100000;

    public RailroadSpace(int number, String name, int price, double[] positions, byte[] image) {
        super(number, name, positions, image, price);
    }

    public int getRentPrice() {
        int rentPrice = owner.getRailroadCount() < 2 ? BASE_RENT_PRICE : 3 * BASE_RENT_PRICE;

        return rentPrice;
    }

}
