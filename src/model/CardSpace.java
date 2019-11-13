package model;

public class CardSpace extends Space {
    private String type;

    public CardSpace(int number, String name, String type, double[] positions) {
        super(number, name, positions );
        this.type = type;
        action = "draw card";
    }

    public String getType() {
        return type;
    }
}
