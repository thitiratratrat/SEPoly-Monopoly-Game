package model;

public class CardSpace extends Space {
    private String type;

    public CardSpace(int number, String name, String type) {
        super(number, name);
        this.type = type;
        action = "draw card";
    }

    public String getType() {
        return type;
    }
}
