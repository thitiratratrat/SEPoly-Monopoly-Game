package model;

public class UtilitySpace extends PropertySpace {
    private String type;

    public UtilitySpace(int number, String name, int price, String type, double[] positions) {
        super(number, name, positions, price);
    }

    public String getType() {
        return type;
    }
}
