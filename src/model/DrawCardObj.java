package model;

public class DrawCardObj {
    private int playerID;
    private String deckType;

    public DrawCardObj(int playerID, String deckType) {
        this.playerID = playerID;
        this.deckType = deckType;
    }

    public int getPlayerID() {
        return playerID;
    }

    public String getdeckType() {
        return deckType;
    }

}
