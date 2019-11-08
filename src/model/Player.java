package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {
    private ArrayList<UtilitySpace> utilities;
    private ArrayList<EstateSpace> estates;
    private int breakJailCards;
    private boolean isJailed;
    private double money;
    private int ID;
    private int x;
    private int y;

    public Player(double startingMoney, int ID) {
        money = startingMoney;
        utilities = new ArrayList<UtilitySpace>();
        estates = new ArrayList<EstateSpace>();
        this.ID = ID;
        breakJailCards = 0;
        isJailed = false;
    }

    public void buy(Property property) {
        if (property instanceof UtilitySpace) {
            utilities.add((UtilitySpace) property);
        } else {
            estates.add((EstateSpace) property);
        }
    }

    public void pay(double amount) {
        money -= amount;
    }

    public void getPaid(double amount) {
        money += amount;
    }

    public double getMoney() {
        return money;
    }

    public int getID() {
        return ID;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getBreakJailCards() {
        return breakJailCards;
    }

    public void jailed() {
        isJailed = true;
    }

    public void getOutOfJail() {
        isJailed = false;
    }

    public boolean isJailed() {
        return isJailed;
    }

    public void useBreakJailCard() {
        breakJailCards -= 1;
    }

    public void drawBreakJailCard() {
        breakJailCards += 1;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

//    public void mortgage(Property property) {};
}
