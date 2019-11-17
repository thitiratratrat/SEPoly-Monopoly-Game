package model;

public class MoveAnimateObj {
    private Movable player;
    private int moveNumber;

    public MoveAnimateObj(Movable player, int number) {
        this.player = player;
        moveNumber = number;
    }

    public Movable getPlayerID() {
        return player;
    }

    public int getMoveNumber() {
        return moveNumber;
    }
}
