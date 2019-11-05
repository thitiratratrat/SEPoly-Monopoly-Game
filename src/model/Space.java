package model;

import java.io.Serializable;

public abstract class Space implements Serializable {
    protected int number;
    protected String name;
    protected String action;

    public Space(int number, String name) {
        this.number = number;
        this.name = name;
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
}
