package model;

public class UtilitySpaceObj {
    private Player owner;

    UtilitySpaceObj(Player player) {
        owner = player;
    }

    public Player getOwner() {
        return owner;
    }
}
