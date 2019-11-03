package model;

public abstract class PropertySpace extends Space implements Property {
    protected double price;
    protected Player owner;

    public PropertySpace(int number, String name, double price) {
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
