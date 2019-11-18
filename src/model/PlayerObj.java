package model;

import java.io.Serializable;

public class PlayerObj implements Serializable, Movable {
    private int x = 360;
    private int y = 460;
    private int ID;
    private int money;

    public PlayerObj(int x, int y, int money, int ID) {
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

    public void setMoney(int money) {
        this.money = money;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getMoney() {
        return money;
    }

    public int getID() {
        return ID;
    }
}
