package socketConnection;

import java.io.*;
import java.net.*;

public class Client {
    private Socket socket = null;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    Client(String address, int port) throws IOException, ClassNotFoundException {
        socket = new Socket(address, port);
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());

        while(true) {
            String message = (String) inputStream.readObject();
            System.out.println(message);
            break;
        }
    }

}
