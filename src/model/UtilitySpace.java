package model;

public class UtilitySpace extends PropertySpace {
    public UtilitySpace(int number, String name, int price, double[] positions, byte[] image) {
        super(number, name, positions, image, price);
    }

    public int getRentPrice() {
        int multiplier = owner.getUtilityCount() > 1 ? 10 : 4;

        return multiplier;
    }
}
