package socketConnection;

import model.*;

import javax.swing.*;
import java.io.IOException;
import java.util.*;
import java.util.Timer;

public class Gameplay {
    private Client client;
    private Player player;
    private ArrayList<PlayerObj> opponents;
    private ArrayList<Space> map;
    private String address = "127.0.0.1";
    private int port = 5056;
    private Timer sendPlayerDataTimer, getGameDataTimer;
    private boolean isTurn = false;
    private boolean isMoving = false;
    final private int TIMERDELAY = 500;

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
    }

    private void initMapUI() {
        GUI.NewJFrame2 board  = new GUI.NewJFrame2();
        board.display();
    }

    private void start() {
        startSendPlayerPositionTimer();
        startGetGameDataTimer();
    }

    //send player position update
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
                        case ("startTurn"): {
                            isTurn = true;
                            break;
                        }

                        case ("initMap"): {
                            map = (ArrayList<Space>) serverMessage.getData();
                            break;
                        }

                        case ("initPlayer"): {
                            player = (Player) serverMessage.getData();
                            break;
                        }

                        case ("updatePlayer"): {
                            PlayerObj playerObj = (PlayerObj) serverMessage.getData();
                            updatePlayer(playerObj);
                            break;
                        }

                        case ("startAuction"): {
                            //TODO: auction UI visible
                            break;
                        }

                        default:
                            break;
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 0, TIMERDELAY);
    }

    private void endTurn() throws IOException {
        isTurn = false;
        ServerMessage serverMessage = new ServerMessage("endTurn", player.getID());
        client.sendData(serverMessage);
    }

    private void updatePlayer(PlayerObj playerObj) {
        for (PlayerObj opponent : opponents) {
            if (opponent.getID() == playerObj.getID()) {
                opponent.setX(playerObj.getX());
                opponent.setY(playerObj.getY());
                opponent.setMoney(playerObj.getMoney());
            }
        }
    }

    private void buy() throws IOException {
        //TODO: check money before buying

        //TODO: Player buys house/estate/utility logic here

        //update player's money
        sendPlayerToUpdate();
    }

    private void sendPlayerToUpdate() throws IOException {
        //TODO: get x and y position of player
        int xPosition = 0;
        int yPosition = 0;
        PlayerObj playerObj = new PlayerObj(xPosition, yPosition, player.getMoney(), player.getID());
        ServerMessage serverMessage = new ServerMessage("updatePlayer", playerObj);
        client.sendData(serverMessage);
    }

    private void startAuction(PropertySpace property) throws IOException {
        ServerMessage serverMessage = new ServerMessage("startAuction", property);
        client.sendData(serverMessage);
    }

    //TODO: bidding
    //TODO: end auction
}


