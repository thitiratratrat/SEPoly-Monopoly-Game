package socketConnection;

import model.Player;
import model.PlayerObj;
import model.ServerMessage;
import model.Space;

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
    private boolean isMoving = false;
    final private int TIMERDELAY = 500;
    final private String[] messages = {"Yay", "Yo", "Hahah"};
    JFrame frame;
    JLabel lblText;

    Gameplay() {
        initMapUI();
        initUI();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
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
        //init map UI here
    }

    private void start() {
        getMapData();
        startSendPlayerPositionTimer();
        startGetGameDataTimer();
    }

    private void startSendPlayerPositionTimer() {
        sendPlayerDataTimer = new Timer();
        sendPlayerDataTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!isMoving) {
                    return;
                }
                try {
                    Random randomGenerator = new Random();
                    int randomInt = randomGenerator.nextInt(messages.length);
                    client.sendData(messages[randomInt]);
                } catch (
                        IOException e) {
                    e.printStackTrace();
                }
            }
        }, 0, TIMERDELAY);
    }

    private void startGetGameDataTimer() {
        getGameDataTimer = new Timer();
        getGameDataTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    String message = (String) client.getData();
                    if (message != null) {
                        System.out.println(message);
                        lblText.setText(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 0, TIMERDELAY);
    }

    private void getMapData() {
        map = client.getMapData();
    }

    private void sendData(ServerMessage serverMessage) throws IOException {
        client.sendData(serverMessage);
    }

}

//set timer to update player's position bcos it is automatically moved

