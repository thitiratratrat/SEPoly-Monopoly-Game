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
    private int spaceNumber = 0;
    final private int TIMER_DELAY = 10;
    final private int SPACE_COUNT = 32;
    final private int BIDTIMER_DELAY = 100000;
    final private int JAIL_SPACE_NUMBER = 8;

    Gameplay() {
        initMapUI();
        initUI();
    }

    public static void main(String[] args) throws IOException {
        Gameplay gameplay = new Gameplay();
        gameplay.startClientConnection();
        gameplay.start();
    }

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
        //TODO: init UI code here
    }

    private void initMapUI() {
        //TODO: init map UI here
    }

    private void start() throws IOException {
        startSendPlayerPositionTimer();
        startGetGameDataTimer();
    }

    //send player position update
    private void startSendPlayerPositionTimer() {
        sendPlayerDataTimer = new Timer();
        sendPlayerDataTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (isMoving) {
                    try {
                        sendPlayerToUpdate();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, 0, TIMER_DELAY);
    }

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

    private void buy() throws IOException {
        //TODO: check money before buying

        //TODO: Player buys house/estate/utility logic here

        //update player's money
        sendPlayerToUpdate();
        //TODO: update map
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
        //TODO: display UI dice roll
        movePlayerForward(totalMoveCount);
    }

    private void movePlayerForward(int moveCount) throws IOException {
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
        doSpaceAction(spaceNumber);
    }

    private void doSpaceAction(int spaceNumber) throws IOException {
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



            default:
                break;
        }
    }

    private void updateMap(PropertySpace propertySpace) {
        map.set(propertySpace.getNumber(), propertySpace);
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
}