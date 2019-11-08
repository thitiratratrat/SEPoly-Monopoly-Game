package socketConnection;

import model.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

class ClientHandler extends Thread {
    private Socket socket;
    private Server server;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private int ID;

    public ClientHandler(Socket socket, ObjectInputStream inputStream, ObjectOutputStream outputStream, Server server, int ID) {
        this.socket = socket;
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.server = server;
        this.ID = ID;
    }

    @Override
    public void run() {
        try {
            while (true) {
                ServerMessage serverMessage = (ServerMessage) inputStream.readUnshared();
                String action = serverMessage.getAction();

                switch (action) {
                    case ("endTurn"): {
                        int playerID = (int) serverMessage.getData();
                        server.startNextPlayerTurn(playerID);
                        break;
                    }

                    case ("updatePlayer"): {
                        Player player = (Player) serverMessage.getData();
                        server.updatePlayer(player);
                        break;
                    }

                    case ("startAuction"): {
                        server.startAuction(serverMessage);
                        break;
                    }

                    case ("bid"): {
                        server.setNewHighestBid((BidObj) serverMessage.getData());
                        break;
                    }

                    case ("endAuction"): {
                        server.endAuction();
                        break;
                    }

                    case ("drawCard"): {
                        DrawCardObj drawCardObj = (DrawCardObj) serverMessage.getData();
                        server.drawCard(drawCardObj);
                        break;
                    }

                    default:
                        break;
                }
            }
        } catch (Exception e) {
            try {
                inputStream.close();
                outputStream.close();
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public ObjectOutputStream getOutputStream() {
        return outputStream;
    }

    public int getID() {
        return ID;
    }
}