package model;

import java.util.ArrayList;

public class Player {
    ArrayList<UtilitySpace> utilities;
    ArrayList<EstateSpace> estates;
    double money;

    Player(double startingMoney) {
        money = startingMoney;
        utilities = new ArrayList<UtilitySpace>();
        estates = new ArrayList<EstateSpace>();
    }

    public void buy(Property property) {}
    public void drawCard(ArrayList<Card> cards) {};
    public int rollDice() {};
    public double pay() {};
    public void getMoney(double money) {};
    public void mortgage(Property property) {};
}
