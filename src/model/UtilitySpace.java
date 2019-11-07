package model;

public class UtilitySpace extends PropertySpace {
    private String type;

    public UtilitySpace(int number, String name, int price, String type) {
        super(number, name, price);
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
