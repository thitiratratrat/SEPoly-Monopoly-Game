package model;

import jdk.jshell.execution.Util;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {
    private ArrayList<UtilitySpace> utilities;
    private ArrayList<EstateSpace> estates;
    private double money;
    private int ID;
    private int x;
    private int y;

    public Player(double startingMoney, int ID) {
        money = startingMoney;
        utilities = new ArrayList<UtilitySpace>();
        estates = new ArrayList<EstateSpace>();
        this.ID = ID;
    }

    public void buy(Property property) {
        if (property instanceof UtilitySpace) {
            utilities.add((UtilitySpace) property);
        }

        else {
            estates.add((EstateSpace) property);
        }
    }

//    public void drawCard(ArrayList<Card> cards) {};
//    public int rollDice() {};

    public void setMoney(double amount) {
        money = amount;
    }

    public void pay(double amount) {
        money -= amount;
    };

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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    ;
//    public void mortgage(Property property) {};
}
