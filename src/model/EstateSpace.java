package model;

import java.util.ArrayList;

public class EstateSpace extends PropertySpace {
    private double housePrice;
    private double landmarkPrice;
    private int houseCount;
    private int landmarkCount;
    private ArrayList<Double> rentPrices;
    private byte[] image;

    public EstateSpace(int number, String name, double price, double rent, double oneHouseRent,
                double twoHouseRent, double threeHouseRent, double landmarkRent, double housePrice,
                       double landmarkPrice, byte[] image, double[] positions) {
        super(number, name, positions, price);
        this.housePrice = housePrice;
        this.landmarkPrice = landmarkPrice;
        this.image = image;
        houseCount = 0;
        landmarkCount = 0;
        rentPrices = new ArrayList<Double>();

        rentPrices.add(rent);
        rentPrices.add(oneHouseRent);
        rentPrices.add(twoHouseRent);
        rentPrices.add(threeHouseRent);
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

    public byte[] getImage(){ return image; }
}
