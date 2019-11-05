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
        initMapData();
        sendMapData();
        sendInitPlayerData();
        startNextPlayerTurn(-1);
    }

    private void sendInitPlayerData() throws IOException {
        ServerMessage serverMessage = new ServerMessage("initPlayer", "");

        for (ClientHandler client: clients) {
            Player player = players.get(client.getID());
            serverMessage.setData(player);
            client.getOutputStream().writeObject(serverMessage);
        }
    }

   private void sendMapData() throws IOException {
        ServerMessage serverMessage = new ServerMessage("initMap", map);
        sendDataToAllClients(serverMessage);
   }

    public void sendDataToAllClients(ServerMessage serverMessage) throws IOException {
        for (ClientHandler client : clients) {
            client.getOutputStream().writeObject(serverMessage);
        }
    }

    public void startNextPlayerTurn(int playerID) throws IOException {
        int nextPlayerIDTurn = playerID == players.size() -1 ? 0 : playerID + 1;
        ClientHandler firstPlayerClient = clients.get(nextPlayerIDTurn);
        ServerMessage serverMessage = new ServerMessage("startTurn", "");

        firstPlayerClient.getOutputStream().writeObject(serverMessage);
    }

    public void updatePlayer(PlayerObj playerObj) throws IOException {
       for (ClientHandler client: clients) {
           if (client.getID() == playerObj.getID()) {
               continue;
           }
           ServerMessage serverMessage = new ServerMessage("updatePlayer", playerObj);
           client.getOutputStream().writeObject(serverMessage);
       }
    }

    public void close() throws IOException {
        serverSocket.close();
    }

    private void initMapData() {
        //init map queried from database here
    }
}


//when player's data is sent here to update, send it to other player