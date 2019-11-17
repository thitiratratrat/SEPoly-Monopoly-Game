package model;

import java.io.Serializable;

public class DrawCardObj implements Serializable {
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

