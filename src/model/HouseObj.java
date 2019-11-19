package model;

import java.io.Serializable;

public class HouseObj implements Serializable {
    private String path;
    private int spaceNumber;
    private int ID;

    public HouseObj(String path, int spaceNumber, int ID) {
        this.path = path;
        this.spaceNumber = spaceNumber;
        this.ID = ID;
    }

    public String getPath() {
        return path;
    }

    public int getSpaceNumber() {
        return spaceNumber;
    }

    public int getID() {
        return ID;
    }
}
