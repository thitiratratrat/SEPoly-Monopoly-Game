package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {
    private ArrayList<UtilitySpace> utilities;
    private ArrayList<EstateSpace> estates;
    private double money;
    private int ID;

    public Player(double startingMoney, int ID) {
        money = startingMoney;
        utilities = new ArrayList<UtilitySpace>();
        estates = new ArrayList<EstateSpace>();
        this.ID = ID;
    }

    //    public void buy(Property property) {}
//    public void drawCard(ArrayList<Card> cards) {};
//    public int rollDice() {};
//    public double pay(Player player) {};

    public void getPaid(double amount) {
        money += amount;
    }

    public double getMoney() {
        return money;
    }

    public int getID() {
        return ID;
    }


    ;
//    public void mortgage(Property property) {};
}
