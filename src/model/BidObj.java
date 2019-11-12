package model;

import java.io.Serializable;

public class BidObj implements Serializable {
    private int playerID;
    private int bidMoney;

    public BidObj(int playerID, int bidMoney) {
        this.playerID = playerID;
        this.bidMoney = bidMoney;
    }

    public int getPlayerID() {
        return playerID;
    }

    public int getBidMoney() {
        return bidMoney;
    }
}
