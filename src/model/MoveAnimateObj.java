package model;

import java.io.Serializable;

public class MoveAnimateObj implements Serializable {
    private Movable player;
    private int moveNumber;

    public MoveAnimateObj(Movable player, int number) {
        this.player = player;
        moveNumber = number;
    }

    public Movable getPlayer() {
        return player;
    }

    public int getMoveNumber() {
        return moveNumber;
    }
}
