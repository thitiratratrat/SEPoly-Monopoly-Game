package socketConnection;

import java.io.IOException;
import java.net.InetAddress;
import java.sql.SQLException;

public class ServerMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Server server = new Server(5056);
        server.connect();
        try {
            server.start();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
