import SEPoly.SEPoly;
import socketConnection.Gameplay;
import socketConnection.Server;
import socketConnection.ServerMain;

import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                SEPoly.start();
            }
        });
        thread.start();
    }
}
