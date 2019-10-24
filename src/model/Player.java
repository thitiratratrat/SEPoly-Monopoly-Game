package model;

import java.util.ArrayList;

public class Player {
    ArrayList<Utility> utilities;
    ArrayList<Estate> estates;
    double money;

    Player(double startingMoney) {
        money = startingMoney;
        utilities = new ArrayList<Utility>();
        estates = new ArayList<Estate>();
    }

    public void buy(Property property) {}
    public void drawCard(ArrayList<Card> cards) {};
    public int rollDice() {};
    public double pay() {};
    public void getMoney(double money) {};
    public void mortgage(Property property) {};
}
