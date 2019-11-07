package model;

public interface Property {
    public int getPrice();

    public void soldTo(Player player);

    public Player getOwner();
}