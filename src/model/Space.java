package model;

import java.io.Serializable;
// add posiiton
public abstract class Space implements Serializable {
    protected int number;
    protected String name;
    protected String action;
    protected double[] positions;

    public Space(int number, String name, double[] positions) {
        this.number = number;
        this.name = name;
        this.positions = positions;
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
}
