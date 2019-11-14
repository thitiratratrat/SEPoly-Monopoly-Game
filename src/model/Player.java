package model;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Player implements Serializable {
    private ArrayList<UtilitySpace> utilities;
    private ArrayList<EstateSpace> estates;
    private ArrayList<RailroadSpace> railroads;
    private int asset;
    private int breakJailCards;
    private boolean isJailed;
    private int money;
    private int ID;
    private int x;
    private int y;

    public Player(int startingMoney, int ID) {
        money = startingMoney;
        asset = startingMoney;
        utilities = new ArrayList<>();
        estates = new ArrayList<>();
        railroads = new ArrayList<>();
        this.ID = ID;
        breakJailCards = 0;
        isJailed = false;
    }

    public void buy(Property property) {
        if (property instanceof UtilitySpace) {
            utilities.add((UtilitySpace) property);
        } else if (property instanceof RailroadSpace) {
            railroads.add((RailroadSpace) property);
        } else {
            estates.add((EstateSpace) property);
        }

        property.soldTo(this);
        pay(property.getPrice());
        asset += property.getPrice() / 2;
    }

    public void buyHouse(EstateSpace estateSpace) {
        int price = estateSpace.getHousePrice();

        pay(price);
        estateSpace.buildHouse(1);
        asset += price / 2;
    }

    public void buyLandmark(EstateSpace estateSpace) {
        int price = estateSpace.getLandmarkPrice();

        pay(price);
        estateSpace.buildLandmark();
        asset += price / 2;
    }

    public void sell(PropertySpace property) {
        if (property instanceof UtilitySpace) {
            utilities.removeIf(utility -> property.getNumber() == utility.getNumber());
        } else if (property instanceof RailroadSpace) {
            railroads.removeIf(railroad -> property.getNumber() == railroad.getNumber());
        } else {
            estates.removeIf(estate -> property.getNumber() == estate.getNumber());
        }

        int propertySellingPrice = property.getPrice() / 2;
        property.soldBack();
        asset -= propertySellingPrice;
        getPaid(propertySellingPrice);
    }

    public void sellHouse(EstateSpace estateSpace) {
        estateSpace.sellHouse(1);
        int houseSellingPrice = estateSpace.getHousePrice() / 2;
        asset -= houseSellingPrice;
        getPaid(houseSellingPrice);
    }

    public void sellLandmark(EstateSpace estateSpace) {
        estateSpace.sellLandmark();
        int landmarkSellingPrice = estateSpace.getLandmarkPrice() / 2;
        asset -= landmarkSellingPrice;
        getPaid(landmarkSellingPrice);
    }

    public void pay(double amount) {
        money -= amount;
        asset -= amount;
    }

    public void getPaid(double amount) {
        money += amount;
        asset += amount;
    }

    public int getMoney() {
        return money;
    }

    public int getAsset() {
        return asset;
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
        getOutOfJail();
    }

    public int getUtilityCount() {
        return utilities.size();
    }

    public int getRailroadCount() {
        return railroads.size();
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

    public ArrayList<PropertySpace> getAllProperty() {
        ArrayList<PropertySpace> property = new ArrayList<>();
        property.addAll(estates);
        property.addAll(utilities);
        property.addAll(railroads);

        return property;
    }

    //TODO: buy house add asset
}
