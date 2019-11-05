package socketConnection;

import model.ServerMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

class ClientHandler extends Thread {
    private Socket socket;
    private Server server;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public ClientHandler(Socket socket, ObjectInputStream inputStream, ObjectOutputStream outputStream, Server server) {
        this.socket = socket;
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            while(true) {
                ServerMessage serverMessage = (ServerMessage) inputStream.readObject();
                String action = serverMessage.getAction();

                switch(action) {
                    case("getMap"): {
                        outputStream.writeObject(server.getMapData());
                        break;
                    }

                    default: break;
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
}