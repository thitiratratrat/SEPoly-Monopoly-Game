package model;

import java.io.Serializable;
public abstract class Space implements Serializable {
    protected int number;
    protected String name;
    protected String action;
    protected double[] positions;
    protected byte[] image;

    public Space(int number, String name, double[] positions, byte[] image) {
        this.number = number;
        this.name = name;
        this.positions = positions;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public String getAction() {
        return action;
    }

    public double[] getPositions() {
        return positions;
    }

    public byte[] getImage() {
        return image;
    }
}
