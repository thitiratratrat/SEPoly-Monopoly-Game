import socketConnection.Server;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.io.IOException;
import java.sql.SQLException;

public class ServerMain {
    private final static int MAX_PLAYERS = 4;

    public static void main(String[] args) throws IOException, SQLException {
        Server server = new Server(5056);

        int playerCount = 0;

        while (playerCount != MAX_PLAYERS) {
            server.connect();
            playerCount += 1;
        }
        System.out.println("start game");
        server.start();
    }
}
