package GUI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Asus
 */
public class NewJFrame2 extends javax.swing.JFrame {

    public NewJFrame2() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    private void initComponents() {

        text = new javax.swing.JLabel();
        board = new javax.swing.JLabel();
        popup = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        //setPreferredSize(new java.awt.Dimension(800, 600));
        //getContentPane().setLayout(null);
        setResizable(false);

        //text.setText("sxdcfgvhbjnrxsdcfgvhbjnkmxesdcfvghbjnkmezsxdrcfvghbjnkm");
        getContentPane().add(text);
        text.setBounds(0, 0, 100, 100);
        text.setVisible(false);

        popup.setIcon(new javax.swing.ImageIcon("C://Users/Asus/Desktop/Monopoly/popup.png"));
        getContentPane().add(popup);
        popup.setBounds(0,0,800,600);
        popup.setVisible(false);
        popup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                popupMousePressed(evt);
            }
        });

        board.setIcon(new javax.swing.ImageIcon("C://Users/Asus/Desktop/Monopoly/final_board.png")); // NOI18N
        getContentPane().add(board);
        board.setBounds(0, 0, 800, 600);
        board.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                boardMousePressed(evt);
            }
        });
        pack();
    }// </editor-fold>

    private void popupMousePressed(java.awt.event.MouseEvent evt) {
        popup.setVisible(false);
    }
    private void boardMousePressed(java.awt.event.MouseEvent evt) {
        double x = evt.getX();
        double y = evt.getY();
        text.setText("X : "+x+"  Y : "+y);
        text.setVisible(true);
        /*send x y to server and then weather the position is on a space
        * if true show the popup ui*/
    }
    /**
     * @param args the command line arguments
     */
    public void display() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JLabel board;
    private javax.swing.JLabel text;
    private javax.swing.JLabel popup;
    // End of variables declaration
}

