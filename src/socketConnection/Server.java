package socketConnection;

import model.Player;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server {
    private List<ClientHandler> clients;
    private ServerSocket serverSocket;
    private List<Player> players;
    private double startingMoney = 1000;

    Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        clients = Collections.synchronizedList(new ArrayList<>());
        players = new ArrayList<>();
    }

    public void start() throws IOException {
        try {
            Socket socket = serverSocket.accept();
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ClientHandler clientHandler = new ClientHandler(socket, inputStream, outputStream, this);
            Player player = new Player(startingMoney);

            clients.add(clientHandler);
            players.add(player);

            clientHandler.start();
        } catch (Exception e) {
            close();
        }
    }

    public void sendHello() throws IOException {
        for (ClientHandler client: clients) {
            ObjectOutputStream outputStream = client.getOutputStream();
            outputStream.writeObject("Hello");
        }
    }

    public void close() throws IOException {
        serverSocket.close();
    }
}

