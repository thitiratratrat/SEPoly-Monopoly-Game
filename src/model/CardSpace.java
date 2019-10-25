package model;

public class CardSpace extends Space {
    private String type;

    CardSpace(int number, String name, String type) {
        this.number = number;
        this.name = name;
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
