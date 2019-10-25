package model;

public class UtilitySpace extends Space implements Property{
    private double price;
    private Player owner;

    UtilitySpace(int number, String name, double price) {
        super(number, name);
        action = "property";
        this.price = price;
        owner = null;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void soldTo(Player player) {
        owner = player;
    }

    public Player getOwner() {
        return owner;
    }

}
