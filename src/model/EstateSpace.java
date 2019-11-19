package model;

import java.util.ArrayList;

public class EstateSpace extends PropertySpace {
    private int housePrice;
    private int landmarkPrice;
    private int houseCount;
    private int landmarkCount;
    private ArrayList<Integer> rentPrices;
    final private int MAX_LANDMARK = 1;
    final private int MAX_HOUSE_COUNT = 3;
    private double[] positions;
    public EstateSpace(int number, String name, int price, int rent, int oneHouseRent,
                int twoHouseRent, int threeHouseRent, int landmarkRent, int housePrice,
                       int landmarkPrice, byte[] image, double[] positions) {
        super(number, name, positions, image, price);
        this.housePrice = housePrice;
        this.landmarkPrice = landmarkPrice;
        this.positions = positions;
        houseCount = 0;
        landmarkCount = 0;
        rentPrices = new ArrayList<>();

        rentPrices.add(rent);
        rentPrices.add(oneHouseRent);
        rentPrices.add(twoHouseRent);
        rentPrices.add(threeHouseRent);
        rentPrices.add(landmarkRent);
    }
    public ArrayList<Integer> getRentPrices(){ return rentPrices; }

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
        //change 5 to 4 has only 3 house
        int rentPrice = landmarkCount == MAX_LANDMARK ? rentPrices.get(4) : rentPrices.get(houseCount);
        return rentPrice;
    }

    public void sellHouse(int sellHouseCount) {
        houseCount -= sellHouseCount;
    }

    public void buildHouse(int houseCount) {
        this.houseCount += houseCount;

        if (this.houseCount > MAX_HOUSE_COUNT) {
            houseCount = MAX_HOUSE_COUNT;
        }
    }

    public void buildLandmark() {
        landmarkCount = MAX_LANDMARK;
    }

    public void sellLandmark() {
        landmarkCount = 0;
        houseCount = 0;
    }

    public void soldBack() {
        sellLandmark();
        super.soldBack();
    }

    public int getDisplayXPos(){
        return (int)positions[2];
    }

    public int getDisplayYPos(){
        return (int)positions[3];
    }


}
