package model;

public interface Property {
    public double getPrice();
    public void soldTo(Player player);
    public Player getOwner();
}
