package socketConnection;

import model.Player;
import model.Space;
import model.UtilitySpace;

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
            ClientHandler clientHandler = new ClientHandler(socket, inputStream, outputStream, this);
            int playerID = players.size();
            Player player = new Player(STARTINGMONEY, playerID);

            clients.add(clientHandler);
            players.add(player);

            clientHandler.start();
        } catch (Exception e) {
            close();
        }
    }

    public void start() {
        initMapData();
    }

    public ArrayList<Space> getMapData() {
        return map;
    }

    public void sendData() throws IOException {
        for(ClientHandler client: clients) {
            client.getOutputStream().writeObject("");
        }
    }

    public void close() throws IOException {
        serverSocket.close();
    }

    private void initMapData() {
        //init map queried from database here

        map.add(new UtilitySpace(1, "Rat's rail", 100, "railroad"));
        map.add(new UtilitySpace(2, "Waterpark", 200, "water"));
    }
}


//when player's data is sent here to update, send it to other player