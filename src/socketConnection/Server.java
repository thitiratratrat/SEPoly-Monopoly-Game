package socketConnection;

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
    final private double STARTINGMONEY = 1000;
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
        try {
            initMapData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        //************************************************************************
        //************************************************************************
        //update db
        /*
        Connection connection = DriverManager.getConnection("jdbc:sqlite://C:/Users/Asus/Desktop/Monopoly/src/Estate.db");
        //Statement statement = connection.createStatement();
        double x1,x2,x3,x4,y1,y2,y3,y4;



            try {
                String sql = "UPDATE Estate SET x1 = ? , "
                        + "y1 = ? "
                        + "x2 = ? "
                        + "y2 = ? "
                        + "y3 = ? "
                        + "y3 = ? "
                        + "y4 = ? "
                        + "WHERE y4 = ? ";
                    Connection conn = connection;
                    PreparedStatement pstmt = conn.prepareStatement(sql)
                    pstmt.setDouble(13, x1);
                    pstmt.setDouble(14, y1);
                    pstmt.setDouble(15, x2);
                    pstmt.setDouble(16, y2);
                    pstmt.setDouble(17, x3);
                    pstmt.setDouble(18, y3);
                    pstmt.setDouble(19, x4);
                    pstmt.setDouble(20, y4);
                    pstmt.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        /**
         * @param args the command line arguments

        public static void main(String[] args) {

            UpdateApp app = new UpdateApp();
            // update the warehouse with id 3
            app.update(3, "Finished Products", 5500);
        }*/

        Connection c = null;
        try{
            Connection connection = DriverManager.getConnection("jdbc:sqlite://C:/Users/Asus/Desktop/Monopoly/src/Estate.db");
            Statement statement = connection.createStatement();
            ResultSet estate = statement.executeQuery("select * from Estate");
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

                switch(estate.getString(3)) {
                    case "estate":
                        temp = new EstateSpace(estate.getInt(1),estate.getString(2),estate.getInt(4),
                                estate.getDouble(7),estate.getDouble(8),estate.getDouble(9),
                                estate.getDouble(10),estate.getDouble(11),estate.getDouble(5),
                                estate.getDouble(6),estate.getBytes(12),pos);
                        map.add(temp);
                        break;
                    case "utility":
                        temp = new UtilitySpace(estate.getInt(1),estate.getString(2),
                                estate.getInt(4),estate.getString(2),pos);
                        map.add(temp);
                        break;
                    case "start":
                        temp = new StartSpace(estate.getInt(1),estate.getString(2),
                                estate.getInt(4),pos);
                        map.add(temp);
                        break;
                    case "card":
                        temp = new CardSpace(estate.getInt(1),estate.getString(2),
                                estate.getString(2),pos);
                        map.add(temp);
                        break;
                    case "free parking":
                        temp = new FreeParkingSpace(estate.getInt(1),estate.getString(2),pos);
                        map.add(temp);
                        break;
                    case "tax":
                        temp = new TaxSpace(estate.getInt(1),estate.getString(2),pos);
                        map.add(temp);
                        break;
                    case "jail":
                        temp = new JailSpace(estate.getInt(1),estate.getString(2),pos);
                        map.add(temp);
                        break;
                    case "go to jail":
                        temp = new MoveSpace(estate.getInt(1),estate.getString(2),pos);
                        map.add(temp);
                        break;

                }
            }
            connection.close();
        }catch (Exception e){
            System.out.println(e);
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

    private void sendToAllExcept(int ID, ServerMessage serverMessage) throws IOException {
        for (ClientHandler client : clients) {
            if (client.getID() == ID) {
                continue;
            }
            client.getOutputStream().writeUnshared(serverMessage);
            client.getOutputStream().reset();
        }
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
}