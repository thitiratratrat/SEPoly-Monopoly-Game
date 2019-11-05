package model;

public class EstateSpaceObj {
    private Player owner;
    private int houseCount;
    private int landmarkCount;

    EstateSpaceObj(Player owner, int houseCount, int landmarkCount) {
        this.owner = owner;
        this.houseCount = houseCount;
        this.landmarkCount = landmarkCount;
    }

    public Player getOwner() {
        return owner;
    }

    public int getHouseCount() {
        return houseCount;
    }

    public int getLandmarkCount() {
        return landmarkCount;
    }
}
