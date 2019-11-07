package model;

import java.util.ArrayList;

public class EstateSpace extends PropertySpace {
    private int housePrice;
    private int landmarkPrice;
    private int houseCount;
    private int landmarkCount;
    private ArrayList<Integer> rentPrices;

    public EstateSpace(int number, String name, int price, int rent, int oneHouseRent,
                int twoHouseRent, int threeHouseRent, int fourHouseRent,
                int landmarkRent, int housePrice, int landmarkPrice) {
        super(number, name, price);
        this.housePrice = housePrice;
        this.landmarkPrice = landmarkPrice;
        houseCount = 0;
        landmarkCount = 0;
        rentPrices = new ArrayList<>();

        rentPrices.add(rent);
        rentPrices.add(oneHouseRent);
        rentPrices.add(twoHouseRent);
        rentPrices.add(threeHouseRent);
        rentPrices.add(fourHouseRent);
        rentPrices.add(landmarkRent);
    }

    public int getHousePrice() {
        return housePrice;
    }

    public int getLandmarkPrice() {
        return landmarkPrice;
    }

    public int getHouseCount() {
        return houseCount;
    }

    public int getLandmarkCount() {
        return landmarkCount;
    }

    public int getRentPrice() {
        return rentPrices.get(houseCount);
    }

    public void sellHouse(int sellHouseCount) {
        houseCount -= sellHouseCount;
    }

    public void sellLandmark() {
        landmarkCount = 0;
    }
}
