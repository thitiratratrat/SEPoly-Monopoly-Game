package socketConnection;

import java.io.IOException;
import java.net.InetAddress;

public class ServerMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Server server = new Server(5056);
        server.start();
    }
}
