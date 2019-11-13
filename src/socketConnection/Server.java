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
import java.util.Random;

public class Server {
    private List<ClientHandler> clients;
    private ServerSocket serverSocket;
    private ArrayList<Player> players;
    private ArrayList<Space> map;
    private ArrayList<Card> communityDeck;
    private ArrayList<Card> chanceDeck;
    private Integer highestPlayerIDBidder;
    private Integer highestBiddingMoney;
    private PropertySpace auctionProperty;
    final private int STARTINGMONEY = 1500;
    final private int CARDCOUNT = 10;

    Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        clients = Collections.synchronizedList(new ArrayList<>());
        players = new ArrayList<>();
        map = new ArrayList<>();
        communityDeck = new ArrayList<>();
        chanceDeck = new ArrayList<>();
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
        initCardData();
        sendMapData();
        sendInitPlayerData();
        sendInitOpponentData();
        sendStartGame();
        startNextPlayerTurn(-1);
    }


    public void sendToAllClients(ServerMessage serverMessage) throws IOException {
        for (ClientHandler client : clients) {
            client.getOutputStream().writeUnshared(serverMessage);
            client.getOutputStream().reset();
        }
    }

    public void startNextPlayerTurn(int playerID) throws IOException {
        int nextPlayerIDTurn = 0;

        while (true) {
            nextPlayerIDTurn = playerID == players.size() - 1 ? 0 : playerID + 1;

            if (players.get(nextPlayerIDTurn) != null) {
                break;
            }
        }

        ClientHandler firstPlayerClient = clients.get(nextPlayerIDTurn);
        ServerMessage serverMessage = new ServerMessage("startTurn", "");

        firstPlayerClient.getOutputStream().writeUnshared(serverMessage);
        firstPlayerClient.getOutputStream().reset();
    }

    public void updatePlayer(Player player) throws IOException {
        players.set(player.getID(), player);
        PlayerObj playerObj = new PlayerObj(player.getX(), player.getY(), player.getMoney(), player.getID());
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
        ServerMessage serverMessage = new ServerMessage("endAuction", "");
        sendToAllClients(serverMessage);

        if (highestPlayerIDBidder != null) {
            Player player = players.get(highestPlayerIDBidder);
            player.pay(highestBiddingMoney - auctionProperty.getPrice());
            player.buy(auctionProperty);
            auctionProperty.soldTo(player);

            //update player to player client
            serverMessage = new ServerMessage("updatePlayer", player);
            sendToPlayer(serverMessage, player.getID());

            //update player to opponents
            updatePlayer(player);

            //update map to every player
            serverMessage = new ServerMessage("updateMap", auctionProperty);
            sendToAllClients(serverMessage);
        }

        highestPlayerIDBidder = null;
        highestBiddingMoney = null;
    }

    public void drawCard(DrawCardObj drawCardObj) throws IOException {
        Random randomGenerator = new Random();
        int cardNumber = randomGenerator.nextInt(CARDCOUNT);
        Card card = drawCardObj.getdeckType().equalsIgnoreCase("community") ? communityDeck.get(cardNumber) : chanceDeck.get(cardNumber);
        String effect = card.getEffect();
        int effectAmount = card.getEffectAmount();
        Player player = players.get(drawCardObj.getPlayerID());

        switch (effect) {
            case ("getPaid"): {
                player.getPaid(effectAmount);
                break;
            }

            case ("pay"): {
//                if (isBankrupt(effectAmount)) {
//                    player.pay(effectAmount);
//                }
                checkBankrupt(player);
                break;
            }

            case ("breakJail"): {
                player.drawBreakJailCard();
                break;
            }

            case ("getJailed"): {
                player.jailed();
                ServerMessage serverMessage = new ServerMessage("goToJail", "");
                sendToPlayer(serverMessage, player.getID());
                break;
            }

            case ("moveForward"): {
                ServerMessage serverMessage = new ServerMessage("moveForward", effectAmount);
                sendToPlayer(serverMessage, player.getID());
                break;
            }

            default:
                break;
        }

        //send player data to all clients
        ServerMessage serverMessage = new ServerMessage("updatePlayer", player);
        sendToPlayer(serverMessage, player.getID());
        updatePlayer(player);
    }

    public void payRentPlayer(GetPaidObj getPaidObj) throws IOException {
        Player player = players.get(getPaidObj.getPlayerID());
        player.getPaid(getPaidObj.getRent());
        ServerMessage serverMessage = new ServerMessage("updatePlayer", player);
        sendToPlayer(serverMessage, player.getID());
        updatePlayer(player);
    }

    public void sendToAllExcept(int ID, ServerMessage serverMessage) throws IOException {
        for (ClientHandler client : clients) {
            if (client.getID() == ID) {
                continue;
            }
            client.getOutputStream().writeUnshared(serverMessage);
            client.getOutputStream().reset();
        }
    }

    public void playerBankrupt(int playerID) throws IOException {
        Player player = players.get(playerID);
        ServerMessage mapMessage = new ServerMessage("updateMap", "");
        ArrayList<PropertySpace> properties = player.getAllProperty();

        for (PropertySpace property: properties) {
            property.soldBack();
            mapMessage.setData(property);
            sendToAllClients(mapMessage);
        }

        players.set(playerID, null);
        ServerMessage serverMessage = new ServerMessage("bankrupt", playerID);
        sendToAllClients(serverMessage);
    }

    private void initMapData() {
        //TODO: init map queried from database here
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

        for (ClientHandler client : clients) {
            for (Player player : players) {
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

    private void sendStartGame() throws IOException {
        ServerMessage serverMessage = new ServerMessage("startGame", "");
        sendToAllClients(serverMessage);
    }


    private void sendToPlayer(ServerMessage serverMessage, int ID) throws IOException {
        ClientHandler client = clients.get(ID);
        client.getOutputStream().writeUnshared(serverMessage);
        client.getOutputStream().reset();
    }

    private void checkBankrupt(Player player) {
        //TODO: check bankrupt
    }

    private void initCardData() {
        initCommunityCardData();
        initChanceCardData();
    }

    private void initCommunityCardData() {
        //TODO: query community card data from database
    }

    private void initChanceCardData() {
        //TODO: query chance card data from database
    }

//    private boolean isBankrupt(int payingAmount) {
//
//        return true;
//    }
}
