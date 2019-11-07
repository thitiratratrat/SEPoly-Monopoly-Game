package socketConnection;

import model.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server {
    private List<ClientHandler> clients;
    private ServerSocket serverSocket;
    private ArrayList<Player> players;
    private ArrayList<Space> map;
    private Integer highestPlayerIDBidder;
    private Integer highestBiddingMoney;
    private PropertySpace auctionProperty;
    final private double STARTINGMONEY = 1000;

    Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        clients = Collections.synchronizedList(new ArrayList<>());
        players = new ArrayList<>();
        map = new ArrayList<>();
    }

    public void connect() throws IOException {
        try {
            Socket socket = serverSocket.accept();
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            int ID = players.size();
            ClientHandler clientHandler = new ClientHandler(socket, inputStream, outputStream, this, ID);
            Player player = new Player(STARTINGMONEY, ID);

            clients.add(clientHandler);
            players.add(player);

            clientHandler.start();
        } catch (Exception e) {
            close();
        }
    }

    public void start() throws IOException {
        highestPlayerIDBidder = null;
        highestBiddingMoney = null;
        initMapData();
        sendMapData();
        sendInitPlayerData();
        sendInitOpponentData();
        startNextPlayerTurn(-1);
    }


    public void sendToAllClients(ServerMessage serverMessage) throws IOException {
        for (ClientHandler client : clients) {
            client.getOutputStream().writeUnshared(serverMessage);
            client.getOutputStream().reset();
        }
    }

    public void startNextPlayerTurn(int playerID) throws IOException {
        int nextPlayerIDTurn = playerID == players.size() - 1 ? 0 : playerID + 1;
        ClientHandler firstPlayerClient = clients.get(nextPlayerIDTurn);
        ServerMessage serverMessage = new ServerMessage("startTurn", "");

        firstPlayerClient.getOutputStream().writeUnshared(serverMessage);
        firstPlayerClient.getOutputStream().reset();
    }

    public void updatePlayer(PlayerObj playerObj) throws IOException {
        Player player = players.get(playerObj.getID());
        player.setMoney(playerObj.getMoney());
        player.setX(playerObj.getX());
        player.setY(playerObj.getY());

        ServerMessage serverMessage = new ServerMessage("updateOpponent", playerObj);
        sendToAllExcept(player.getID(), serverMessage);
    }

    public void close() throws IOException {
        serverSocket.close();
    }

    public void setNewHighestBid(BidObj bidObj) throws IOException {
        highestBiddingMoney = bidObj.getBidMoney();
        highestPlayerIDBidder = bidObj.getPlayerID();

        ServerMessage serverMessage = new ServerMessage("updateHighestBidPrice", bidObj);
        sendToAllClients(serverMessage);
    }

    public void startAuction(ServerMessage serverMessage) throws IOException {
        auctionProperty = (PropertySpace) serverMessage.getData();
        sendToAllClients(serverMessage);
    }

    public void endAuction() throws IOException {
        System.out.println("end auction in server");
        ServerMessage serverMessage = new ServerMessage("endAuction", "");
        sendToAllClients(serverMessage);

        if (highestPlayerIDBidder != null) {
            Player player = players.get(highestPlayerIDBidder);
            player.pay(highestBiddingMoney);
            player.buy(auctionProperty);
            auctionProperty.soldTo(player);
            System.out.println("player's money: " + player.getMoney());

            //update player to player client
            serverMessage = new ServerMessage("updatePlayer", player);
            ClientHandler playerClient = clients.get(player.getID());
            playerClient.getOutputStream().writeUnshared(serverMessage);
            playerClient.getOutputStream().reset();

            //update player to opponents
            PlayerObj playerObj = new PlayerObj(player.getX(), player.getY(), player.getMoney(), player.getID());
            serverMessage = new ServerMessage("updateOpponent", playerObj);
            sendToAllExcept(player.getID(), serverMessage);

            //update map to every player
            //test
            auctionProperty.setPrice(150);
            System.out.println("auction price: " + auctionProperty.getPrice());
            serverMessage = new ServerMessage("updateMap", auctionProperty);
            sendToAllClients(serverMessage);
        }

        highestPlayerIDBidder = null;
        highestBiddingMoney = null;
    }

    private void initMapData() {
        //init map queried from database here
    }

    private void sendInitPlayerData() throws IOException {
        ServerMessage serverMessage = new ServerMessage("initPlayer", "");

        for (ClientHandler client : clients) {
            Player player = players.get(client.getID());
            serverMessage.setData(player);
            client.getOutputStream().writeUnshared(serverMessage);
            client.getOutputStream().reset();
        }
    }

    private void sendInitOpponentData() throws IOException {
        ServerMessage serverMessage = new ServerMessage("initOpponents", "");
        ArrayList<PlayerObj> opponents = new ArrayList<>();

        for (ClientHandler client: clients) {
            for (Player player: players) {
                if (player.getID() == client.getID()) {
                    continue;
                }
                PlayerObj opponentObj = new PlayerObj(player.getX(), player.getY(), player.getMoney(), player.getID());
                opponents.add(opponentObj);
            }
            serverMessage.setData(opponents);
            client.getOutputStream().writeUnshared(serverMessage);
            client.getOutputStream().reset();
        }
    }

    private void sendMapData() throws IOException {
        ServerMessage serverMessage = new ServerMessage("initMap", map);
        sendToAllClients(serverMessage);
    }

    private void sendToAllExcept(int ID, ServerMessage serverMessage) throws IOException {
        for (ClientHandler client : clients) {
            if (client.getID() == ID) {
                continue;
            }
            client.getOutputStream().writeUnshared(serverMessage);
            client.getOutputStream().reset();
        }
    }
}

//TODO: init Card Decks
//TODO: trigger to start game

//when player's data is sent here to update, send it to other player
