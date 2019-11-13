import SEPoly.SEPoly;
import socketConnection.ServerMain;

import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                ServerMain main2 = new ServerMain();
                try {
                    main2.start();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        SEPoly.start();
    }
}
