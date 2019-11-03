package model;

import java.util.ArrayList;

public class EstateSpace extends PropertySpace {
    private double housePrice;
    private double landmarkPrice;
    private int houseCount;
    private int landmarkCount;
    private ArrayList<Double> rentPrices;

    public EstateSpace(int number, String name, double price, double rent, double oneHouseRent,
                double twoHouseRent, double threeHouseRent, double fourHouseRent,
                double landmarkRent, double housePrice, double landmarkPrice) {
        super(number, name, price);
        this.housePrice = housePrice;
        this.landmarkPrice = landmarkPrice;
        houseCount = 0;
        landmarkCount = 0;
        rentPrices = new ArrayList<Double>();

        rentPrices.add(rent);
        rentPrices.add(oneHouseRent);
        rentPrices.add(twoHouseRent);
        rentPrices.add(threeHouseRent);
        rentPrices.add(fourHouseRent);
        rentPrices.add(landmarkRent);
    }

    public double getHousePrice() {
        return housePrice;
    }

    public double getLandmarkPrice() {
        return landmarkPrice;
    }

    public int getHouseCount() {
        return houseCount;
    }

    public int getLandmarkCount() {
        return landmarkCount;
    }

    public double getRentPrice() {
        return rentPrices.get(houseCount);
    }

    public void sellHouse(int sellHouseCount) {
        houseCount -= sellHouseCount;
    }

    public void sellLandmark() {
        landmarkCount = 0;
    }
}
