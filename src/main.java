import socketConnection.Gameplay;
import socketConnection.Server;
import socketConnection.ServerMain;

import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //Gameplay gameplay = new Gameplay();
                try {
                    for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                        if ("Nimbus".equals(info.getName())) {
                            javax.swing.UIManager.setLookAndFeel(info.getClassName());
                            break;
                        }
                    }
                } catch (ClassNotFoundException ex) {
                    java.util.logging.Logger.getLogger(Gameplay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    java.util.logging.Logger.getLogger(Gameplay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    java.util.logging.Logger.getLogger(Gameplay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                    java.util.logging.Logger.getLogger(Gameplay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }

                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        new Gameplay().setVisible(true);
                    }
                });
            }
        });
        thread.start();

    }
}
