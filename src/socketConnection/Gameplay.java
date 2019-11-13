package socketConnection;

import model.*;

import javax.swing.*;
import java.io.IOException;
import java.util.*;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class Gameplay {
    private Client client;
    private Player player;
    private ArrayList<PlayerObj> opponents;
    private ArrayList<Space> map;
    private String address = "127.0.0.1";
    private int port = 5056;
    private Timer biddingTimer = new Timer();
    private Timer sendPlayerDataTimer, getGameDataTimer;
    private Integer highestBidMoney = null;
    private boolean isTurn = false;
    private boolean isMoving = false;
    private int jailTurnCount = 0;
    private int spaceNumber = 0;
    final private int TIMER_DELAY = 10;
    final private int SPACE_COUNT = 32;
    final private int BIDTIMER_DELAY = 100000;
    final private int JAIL_SPACE_NUMBER = 8;
    final private int JAIL_FEE = 50;

    public Gameplay() {
        initMapUI();
        initUI();
    }

    public void display() throws IOException {
        this.startClientConnection();
        this.start();
    }

    /*public void run() throws IOException {
        Gameplay gameplay = new Gameplay();
        gameplay.startClientConnection();
        gameplay.start();
    }*/

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPort(int port) {
        this.port = port;
    }

    private void startClientConnection() throws IOException {
        client = new Client(address, port);
    }

    private void initUI() {

    }

    private void initMapUI() {

    }

    private void start() {
        //startSendPlayerPositionTimer();
        startGetGameDataTimer();
    }

    //send player position update
    /*
    private void startSendPlayerPositionTimer() {
        sendPlayerDataTimer = new Timer();
        sendPlayerDataTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!isMoving) {
                    try {
                        sendPlayerToUpdate();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, 0, TIMERDELAY);
    }*/

    //for update with no request from gameplay client side
    private void startGetGameDataTimer() {
        getGameDataTimer = new Timer();
        getGameDataTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    ServerMessage serverMessage = client.getData();
                    if (serverMessage == null) {
                        return;
                    }

                    String action = serverMessage.getAction();
                    switch (action) {
                        case ("startGame"): {
                            //TODO: start main game UI
                            break;
                        }

                        case ("startTurn"): {
                            isTurn = true;
                            break;
                        }

                        case ("initMap"): {
                            map = (ArrayList<Space>) serverMessage.getData();
                            break;
                        }

                        case ("updatePlayer"):
                        case ("initPlayer"): {
                            player = (Player) serverMessage.getData();
                            //TODO: animate player if changes in x y
                            break;
                        }

                        case ("initOpponents"): {
                            opponents = (ArrayList<PlayerObj>) serverMessage.getData();
                            break;
                        }

                        case ("updateOpponent"): {
                            PlayerObj playerObj = (PlayerObj) serverMessage.getData();
                            updateOpponent(playerObj);
                            break;
                        }
                        case ("startAuction"): {

                            PropertySpace auctionProperty = (PropertySpace) serverMessage.getData();
                            highestBidMoney = auctionProperty.getPrice();
                            //TODO: auction UI visible
                            biddingTimer.cancel();
                            startBidTimer();
                            break;
                        }

                        case ("updateHighestBidPrice"): {
                            BidObj bidObj = (BidObj) serverMessage.getData();
                            highestBidMoney = bidObj.getBidMoney();
                            biddingTimer.cancel();
                            startBidTimer();
                            break;
                        }

                        case ("endAuction"): {
                            biddingTimer.cancel();
                            break;
                        }

                        case ("updateMap"): {
                            PropertySpace propertySpace = (PropertySpace) serverMessage.getData();
                            updateMap(propertySpace);
                            break;
                        }

                        case ("moveForward"): {
                            int spaceNumber = (int) serverMessage.getData();
                            movePlayerForward(spaceNumber);
                            break;
                        }

                        case ("goToJail"): {
                            //TODO: animation go to jail
                            spaceNumber = JAIL_SPACE_NUMBER;
                            break;
                        }

                        case ("updateDice"): {
                            int[] diceNumbers = (int[]) serverMessage.getData();
                            //TODO: display UI dice roll
                            break;
                        }

                        default:
                            break;
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 0, TIMER_DELAY);
    }

    private void endTurn() throws IOException {
        isTurn = false;
        ServerMessage serverMessage = new ServerMessage("endTurn", player.getID());
        client.sendData(serverMessage);
    }

    private void updateOpponent(PlayerObj playerObj) {
        for (PlayerObj opponent : opponents) {
            if (opponent.getID() == playerObj.getID()) {
                opponent = playerObj;
                break;
            }
        }

        //TODO: animate opponent to move to that position
    }

    private void buy(PropertySpace propertySpace) throws IOException {
        propertySpace.soldTo(player);
        player.buy(propertySpace);
        sendPlayerToUpdate();
        sendMapToUpdate(propertySpace);

        if (propertySpace instanceof EstateSpace) {
            //TODO: UI option to buy house
        }
    }

    private void buyHouse(EstateSpace estateSpace) throws IOException {
        player.pay(estateSpace.getHousePrice());
        estateSpace.buildHouse(1);
        sendMapToUpdate(estateSpace);
        sendPlayerToUpdate();
        //TODO: animation build house
    }

    private void buyLandmark(EstateSpace estateSpace) throws IOException {
        //TODO: UI is not enable if player does not have 4 houses
        if (estateSpace.getHouseCount() != 4) {
            return;
        }

        player.pay(estateSpace.getLandmarkCount());
        estateSpace.buildLandmark();
        sendMapToUpdate(estateSpace);
        sendPlayerToUpdate();
        //TODO: animation remove houses
        //TODO: animation build landmark
    }

    private void sell(PropertySpace propertySpace) throws IOException {
        if (propertySpace instanceof EstateSpace) {
            if (((EstateSpace) propertySpace).getHouseCount() != 0) {
                return;
            }
        }
        propertySpace.soldBack();
        player.sell(propertySpace);
        player.getPaid(propertySpace.getPrice() / 2);
        sendMapToUpdate(propertySpace);
        sendPlayerToUpdate();
        //TODO: remove color on property space
    }

    private void sellHouse(EstateSpace estateSpace) throws IOException {
        if (estateSpace.getHouseCount() == 0) {
            return;
        }

        estateSpace.sellHouse(1);
        player.getPaid(estateSpace.getHousePrice() / 2);
        sendMapToUpdate(estateSpace);
        sendPlayerToUpdate();
        //TODO: animation remove house
    }

    private void sellLandmark(EstateSpace estateSpace) throws IOException {
        if (estateSpace.getLandmarkCount() == 0) {
            return;
        }

        estateSpace.sellLandmark();
        player.getPaid(estateSpace.getLandmarkPrice() / 2);
        sendMapToUpdate(estateSpace);
        sendPlayerToUpdate();
        //TODO: animation remove landmark
    }

    private void sendPlayerToUpdate() throws IOException {
        ServerMessage serverMessage = new ServerMessage("updatePlayer", player);
        client.sendData(serverMessage);
    }

    private void startAuction(PropertySpace property) throws IOException {
        ServerMessage serverMessage = new ServerMessage("startAuction", property);
        client.sendData(serverMessage);
    }

    private void bid(int bidMoney) throws IOException {
        if (bidMoney <= highestBidMoney || player.getMoney() < bidMoney) {
            return;
        }

        BidObj bidObj = new BidObj(player.getID(), bidMoney);
        ServerMessage serverMessage = new ServerMessage("bid", bidObj);
        client.sendData(serverMessage);
    }

    private void rollDice() throws IOException {
        int[] diceNumbers = new int[2];
        int totalMoveCount = 0;
        Random randomGenerator = new Random();

        for (int i = 0; i < diceNumbers.length; i++) {
            int diceNumber = randomGenerator.nextInt(6) + 1;
            diceNumbers[i] = diceNumber;
            totalMoveCount += diceNumber;
        }

        ServerMessage serverMessage = new ServerMessage("updateDice", diceNumbers);
        client.sendData(serverMessage);
        //TODO: display UI dice roll

        if (player.isJailed()) {
            checkBreakJail(diceNumbers, totalMoveCount);
        } else {
            movePlayerForward(totalMoveCount);
        }
    }

    private void movePlayerForward(int moveCount) throws IOException {
        isMoving = true;
        spaceNumber += moveCount;

        if (spaceNumber >= SPACE_COUNT) {
            spaceNumber %= SPACE_COUNT;
            if (!player.isJailed()) {
                int goMoney = ((StartSpace) map.get(0)).getGoMoney();
                player.getPaid(goMoney);
                sendPlayerToUpdate();
            }
        }
        //TODO: animation move player forward
        isMoving = false;
        doSpaceAction(spaceNumber, moveCount);
    }

    private void doSpaceAction(int spaceNumber, int diceNumber) throws IOException {
        Space space = map.get(spaceNumber);
        String action = space.getAction();

        switch (action) {
            case ("draw card"): {
                CardSpace cardSpace = (CardSpace) space;
                String deckType = cardSpace.getType();
                DrawCardObj drawCardObj = new DrawCardObj(player.getID(), deckType);
                ServerMessage serverMessage = new ServerMessage("drawCard", drawCardObj);
                client.sendData(serverMessage);
                break;
            }

            case ("start"): {
                StartSpace startSpace = (StartSpace) space;
                player.getPaid(startSpace.getGoMoney());
                sendPlayerToUpdate();
                break;
            }

            case ("go to jail"): {
                player.jailed();
                break;
            }

            case ("property"): {
                PropertySpace propertySpace = (PropertySpace) space;
                Player owner = propertySpace.getOwner();
                if (owner == null) {
                    //TODO: display UI to let player choose to buy or put up for auction
                } else if (owner.getID() == player.getID()) {
                    //TODO: display UI to let player choose to buy house
                } else {
                    int rent = propertySpace.getRentPrice();

                    if (propertySpace instanceof UtilitySpace) {
                        rent *= diceNumber;
                    }

                    player.pay(rent);
                    checkBankrupt();
                    sendPlayerToUpdate();
                    GetPaidObj getPaidObj = new GetPaidObj(propertySpace.getOwner().getID(), rent);
                    ServerMessage serverMessage = new ServerMessage("getPaid", getPaidObj);
                    client.sendData(serverMessage);
                }
                break;
            }

            case ("pay tax"): {
                TaxSpace taxSpace = (TaxSpace) space;
                player.pay(taxSpace.getTaxFee());
                checkBankrupt();
                sendPlayerToUpdate();
                break;
            }

            case ("free parking"):
            default:
                break;
        }
    }

    private void checkBreakJail(int[] diceNumbers, int moveCount) throws IOException {
        if (diceNumbers[0] == diceNumbers[1]) {
            jailTurnCount = 0;
            player.getOutOfJail();
            movePlayerForward(moveCount);
        } else if (jailTurnCount == 2) {
            payJailFine();
            movePlayerForward(moveCount);
        } else {
            jailTurnCount += 1;
        }
    }

    private void payJailFine() throws IOException {
        jailTurnCount = 0;
        player.pay(JAIL_FEE);
        checkBankrupt();
        player.getOutOfJail();
        sendPlayerToUpdate();
    }

    private void useBreakJailCard() throws IOException {
        player.useBreakJailCard();
        sendPlayerToUpdate();
        jailTurnCount = 0;
    }

    private void updateMap(PropertySpace propertySpace) {
        map.set(propertySpace.getNumber(), propertySpace);
    }

    private void sendMapToUpdate(PropertySpace propertySpace) throws IOException {
        ServerMessage serverMessage = new ServerMessage("updateMap", propertySpace);
        client.sendData(serverMessage);
    }

    private void startBidTimer() {
        biddingTimer = new Timer();

        biddingTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                ServerMessage serverMessage = new ServerMessage("endAuction", "");
                try {
                    client.sendData(serverMessage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                cancel();
            }
        }, BIDTIMER_DELAY, 100000);
    }

    private void checkBankrupt() {

    }

    public int check_board_is_clicked(double x, double y){
        for(Space s:map){
            if(check(s.getPositions(),x,y)){
                return  map.indexOf(s);
            }
        }
        return -1;
    }
    private boolean check(double[] pos,double x,double y){
        double x1 = pos[0];
        double y1 = pos[1];
        double x2 = pos[2];
        double y2 = pos[3];
        double x3 = pos[4];
        double y3 = pos[5];
        double x4 = pos[6];
        double y4 = pos[7];

        double slope_1to2 = (y2-y1)/(x2-x1);
        double slope_2to3 = (y3-y2)/(x3-x2);
        double slope_3to4 = (y4-y3)/(x4-x3);
        double slope_4to1 = (y1-y4)/(x1-x4);

        double c_1to2 = (-x1*slope_1to2)+y1;
        double c_2to3 = (-x2*slope_2to3)+y2;
        double c_3to4 = (-x3*slope_3to4)+y3;
        double c_4to1 = (-x4*slope_4to1)+y4;
    //upper Y
        double yUpper1 = (slope_2to3*x)+c_2to3; //xLeft2
        double yUpper2 = (slope_3to4*x)+c_3to4; //xRight2
        if (y < yUpper1 || y < yUpper2)
            return false;
    //lower Y
        double yLower1 = (slope_1to2*x)+c_1to2; //xLeft1
        double yLower2 = (slope_4to1*x)+c_4to1; //xRight1
        if (y > yLower1 || y > yLower2)
            return false;
    //Left X
        double xLeft1 = (y - c_1to2)/slope_1to2;
        double xLeft2 = (y - c_2to3)/slope_2to3;
        if (x < xLeft1 || x < xLeft2)
            return false;
    //Right X
        double xRight1 = (y - c_4to1)/slope_4to1;
        double xRight2 = (y - c_3to4)/slope_3to4;

        if (x > xRight1 || x > xRight2)
            return false;

        return true;

    }

    public ArrayList<Space> getMap() {
        return this.map;
    }
}