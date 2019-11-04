package socketConnection;

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
                String newMessage = (String) inputStream.readObject();
                server.setMessage(newMessage);
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