package model;

public class GetPaidObj {
    private int playerID;
    private int rent;

    public GetPaidObj(int playerID, int rent) {
        this.playerID = playerID;
        this.rent = rent;
    }

    public int getPlayerID() {
        return playerID;
    }

    public int getRent() {
        return rent;
    }
}
