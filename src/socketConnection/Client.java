package socketConnection;

import java.io.*;
import java.net.*;

public class Client {
    private Socket socket = null;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    final private int SOCKETINPUTTIMEOUT = 10;

    Client(String address, int port) throws IOException {
        socket = new Socket(address, port);
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());
    }

    public void sendData(Object data) throws IOException {
        outputStream.writeObject(data);
        outputStream.flush();
    }

    public Object getData() throws SocketException {
        try {
            socket.setSoTimeout(SOCKETINPUTTIMEOUT);
            return inputStream.readObject();
        } catch (Exception e) {
            return null;
        }
    }
}

//override action perform and send update client info