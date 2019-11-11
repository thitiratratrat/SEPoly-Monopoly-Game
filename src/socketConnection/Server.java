package socketConnection;
//query commu card and chance card, change starting money and card count
import model.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
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
    final private double STARTINGMONEY = 1500000;
    final private int CARDCOUNT = 8;

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

    public void start() throws IOException, SQLException {
        highestPlayerIDBidder = null;
        highestBiddingMoney = null;
        initMapData();
        initCardData();
        sendMapData();
        sendInitPlayerData();
        sendInitOpponentData();
        sendStartGame();
        startNextPlayerTurn(-1);
        //test
//        sendMapData();
//        sendInitPlayerData();
//        startNextPlayerTurn(-1);
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

    private void initMapData() throws SQLException {
        //init map queried from database here
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite://C:/Users/Asus/Desktop/Monopoly/src/SEpoly.db");
            Statement statement = connection.createStatement();
            ResultSet estate = statement.executeQuery("select * from Map");
            Space temp;
            double[] pos = new double[8];
            while (estate.next()) {
                //System.out.println(estate.getString(3));
                pos[0] = estate.getDouble(13);
                pos[1] = estate.getDouble(14);
                pos[2] = estate.getDouble(15);
                pos[3] = estate.getDouble(16);
                pos[4] = estate.getDouble(17);
                pos[5] = estate.getDouble(18);
                pos[6] = estate.getDouble(19);
                pos[7] = estate.getDouble(20);

                switch (estate.getString(3)) {
                    case "estate":
                        temp = new EstateSpace(estate.getInt(1), estate.getString(2), estate.getInt(4),
                                estate.getDouble(7), estate.getDouble(8), estate.getDouble(9),
                                estate.getDouble(10), estate.getDouble(11), estate.getDouble(5),
                                estate.getDouble(6), estate.getBytes(12), pos);
                        map.add(temp);
                        break;
                    case "utility":
                        temp = new UtilitySpace(estate.getInt(1), estate.getString(2),
                                estate.getInt(4), estate.getString(2), pos);
                        map.add(temp);
                        break;
                    case "start":
                        temp = new StartSpace(estate.getInt(1), estate.getString(2),
                                estate.getInt(4), pos);
                        map.add(temp);
                        break;
                    case "card":
                        temp = new CardSpace(estate.getInt(1), estate.getString(2),
                                estate.getString(2), pos);
                        map.add(temp);
                        break;
                    case "free parking":
                        temp = new FreeParkingSpace(estate.getInt(1), estate.getString(2), pos);
                        map.add(temp);
                        break;
                    case "tax":
                        temp = new TaxSpace(estate.getInt(1), estate.getString(2), pos);
                        map.add(temp);
                        break;
                    case "jail":
                        temp = new JailSpace(estate.getInt(1), estate.getString(2), pos);
                        map.add(temp);
                        break;
                    case "go to jail":
                        temp = new MoveSpace(estate.getInt(1), estate.getString(2), pos);
                        map.add(temp);
                        break;

                }
            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
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
            player.pay(highestBiddingMoney);
            player.buy(auctionProperty);
            auctionProperty.soldTo(player);

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
            serverMessage = new ServerMessage("updateMap", auctionProperty);
            sendToAllClients(serverMessage);
        }

        highestPlayerIDBidder = null;
        highestBiddingMoney = null;
    }

    public void drawCard(String deckType) {
        Random randomGenerator = new Random();
        int cardNumber = randomGenerator.nextInt(CARDCOUNT);
        Card card = deckType.equalsIgnoreCase("community") ? communityDeck.get(cardNumber) : chanceDeck.get(cardNumber);

        //TODO: player act on card effect
        //go = move imidately
        //move = move character
        //gain = gain money
        //pay = pay money
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

    private void sendToAllExcept(int ID, ServerMessage serverMessage) throws IOException {
        for (ClientHandler client : clients) {
            if (client.getID() == ID) {
                continue;
            }
            client.getOutputStream().writeUnshared(serverMessage);
            client.getOutputStream().reset();
        }
    }

    private void initCardData() throws SQLException{
        initCommunityCardData();
        initChanceCardData();
    }

    private void initCommunityCardData() throws SQLException {
            //TODO: query community card data from database
            try {
                Connection connection = DriverManager.getConnection("jdbc:sqlite://C:/Users/Asus/Desktop/Monopoly/src/SEpoly.db");
                Statement statement = connection.createStatement();
                ResultSet card = statement.executeQuery("select * from Community_cards");
                Card temp;
                while (card.next()) {
                        //System.out.println(card.getString(3) + card.getInt(4));
                        temp = new Card(card.getString(3),card.getInt(4),card.getBytes(5));
                        communityDeck.add(temp);
                    }
                connection.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    private void initChanceCardData() throws SQLException{
        //TODO: query chance card data from database
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite://C:/Users/Asus/Desktop/Monopoly/src/SEpoly.db");
            Statement statement = connection.createStatement();
            ResultSet card = statement.executeQuery("select * from Chance_cards");
            Card temp;
            while (card.next()) {
                //System.out.println(card.getString(3) + card.getInt(4));
                temp = new Card(card.getString(3),card.getInt(4),card.getBytes(5));
                communityDeck.add(temp);
            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
