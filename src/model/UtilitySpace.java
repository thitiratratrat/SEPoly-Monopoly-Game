package model;

public class UtilitySpace extends PropertySpace {
    private String type;

    public UtilitySpace(int number, String name, double price, String type, double[] positions) {
        super(number, name, positions, price);
        this.type = type;
    }
}
