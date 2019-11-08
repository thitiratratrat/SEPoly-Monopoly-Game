package socketConnection;

import model.BidObj;
import model.PlayerObj;
import model.ServerMessage;

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
                        PlayerObj playerObj = (PlayerObj) serverMessage.getData();
                        server.updatePlayer(playerObj);
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

                    case("drawCard"): {
                        String deckType = (String) serverMessage.getData();
                        server.drawCard(deckType);
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