package model;

import java.io.Serializable;

public class PlayerObj implements Serializable {
    private int x;
    private int y;
    private int ID;
    private double money;

    public PlayerObj(int x, int y, double money, int ID) {
        this.x = x;
        this.y = y;
        this.money = money;
        this.ID = ID;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setMoney(double money) {
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

    public int getID() {
        return ID;
    }
}
