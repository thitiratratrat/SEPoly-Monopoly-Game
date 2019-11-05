package model;

public class PlayerObj {
    private int x;
    private int y;
    private double money;

    PlayerObj(int x, int y, double money) {
        this.x = x;
        this.y = y;
        this.money = money;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getMoney() {
        return money;
    }
}
