package allGUI;


import javax.swing.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BoardWithBuyLandandHouse extends javax.swing.JFrame {
    boolean ckLand = true; //buy the land
    public BoardWithBuyLandandHouse() {
        initComponents();
    }


    private void initComponents() {
        text = new javax.swing.JLabel();
        board = new javax.swing.JLabel();
        popup = new javax.swing.JLabel();
        popup2 = new javax.swing.JLabel();
        btnGroup = new javax.swing.ButtonGroup();
        buyOneHouse = new javax.swing.JCheckBox();
        buyTwoHouse = new javax.swing.JCheckBox();
        buyThreeHouse = new javax.swing.JCheckBox();
        btnConfirm = new javax.swing.JButton();
        btnBuy = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(null);
        setResizable(false);
        if(ckLand){
            btnBuy.setVisible(false);
            btnBuy.setBounds(300,370,85,40);
            getContentPane().add(btnBuy);

            btnCancel.setVisible(false);
            btnCancel.setBounds(420,370,85,40);
            getContentPane().add(btnCancel);

            popup2.setIcon(new javax.swing.ImageIcon("C:\\Users\\us\\Desktop\\Y2S1\\JAVA\\monopolyTest\\src\\Asset 20.png"));
            getContentPane().add(popup2);
            popup2.setBounds(270,0,800,600);
            popup2.setVisible(false);

            btnBuy.setIcon(new javax.swing.ImageIcon("C:\\Users\\us\\Desktop\\Character\\Asset 21.png"));
            btnBuy.setToolTipText("");
            btnBuy.setBorder(null);
            btnBuy.setBorderPainted(false);
            btnBuy.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    //joinBtnActionPerformed(evt);
                }
            });

            btnCancel.setIcon(new javax.swing.ImageIcon("C:\\Users\\us\\Desktop\\Character\\Asset 22.png"));
            btnCancel.setToolTipText("");
            btnCancel.setBorder(null);
            btnCancel.setBorderPainted(false);
            btnCancel.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    //joinBtnActionPerformed(evt);
                }
            });

            board.setIcon(new javax.swing.ImageIcon("C:\\Users\\us\\Desktop\\Y2S1\\JAVA\\monopolyTest\\src\\board.png")); // NOI18N
            getContentPane().add(board);
            board.setBounds(0, 0, 800, 600);
            popup2.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    popupMousePressed2(evt);
                }
            });
            board.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    boardMousePressed2(evt);
                }
            });
        }
        else{
            btnConfirm.setVisible(false);
            btnConfirm.setBounds(505,370,85,40);
            getContentPane().add(btnConfirm);

            btnGroup.add(buyOneHouse);
            btnGroup.add(buyTwoHouse);
            btnGroup.add(buyThreeHouse);

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addGap(300, 300, 300)
                                    .addComponent(buyOneHouse)
                                    .addGap(90, 90, 90)
                                    .addComponent(buyTwoHouse)
                                    .addGap(93, 93, 93)
                                    .addComponent(buyThreeHouse)
                                    .addContainerGap(218, Short.MAX_VALUE))
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addContainerGap(368, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(buyThreeHouse)
                                            .addComponent(buyTwoHouse)
                                            .addComponent(buyOneHouse))
                                    .addGap(208, 208, 208))
            );
            buyOneHouse.setVisible(false);
            buyTwoHouse.setVisible(false);
            buyThreeHouse.setVisible(false);

            popup.setIcon(new javax.swing.ImageIcon("C:\\Users\\us\\Desktop\\Y2S1\\JAVA\\monopolyTest\\src\\Asset 18.png"));

            getContentPane().add(popup);
            popup.setBounds(230,0,800,600);
            popup.setVisible(false);
            popup.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    popupMousePressed(evt);
                }
            });

            board.setIcon(new javax.swing.ImageIcon("C:\\Users\\us\\Desktop\\Y2S1\\JAVA\\monopolyTest\\src\\board.png")); // NOI18N
            getContentPane().add(board);
            board.setBounds(0, 0, 800, 600);

            btnConfirm.setIcon(new javax.swing.ImageIcon("C:\\Users\\us\\Desktop\\Character\\Asset 19.png"));
            btnConfirm.setToolTipText("");
            btnConfirm.setBorder(null);
            btnConfirm.setBorderPainted(false);
            btnConfirm.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    //joinBtnActionPerformed(evt);
                }
            });

            board.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    boardMousePressed(evt);
                }
            });
        }
        pack();

    }// </editor-fold>

    private void popupMousePressed(java.awt.event.MouseEvent evt)
    {
        popup.setVisible(false);
        btnConfirm.setVisible(false);
        buyOneHouse.setVisible(false);
        buyTwoHouse.setVisible(false);
        buyThreeHouse.setVisible(false);
    }
    private void popupMousePressed2(java.awt.event.MouseEvent evt)
    {
        popup2.setVisible(false);
        btnBuy.setVisible(false);
        btnCancel.setVisible(false);
    }
    private void boardMousePressed(java.awt.event.MouseEvent evt) {
        double x = evt.getX();
        double y = evt.getY();
        //text.setText("X : "+x+"  Y : "+y);
        //text.setVisible(true);
        btnConfirm.setVisible(true);
        popup.setVisible(true);
        buyOneHouse.setVisible(true);
        buyTwoHouse.setVisible(true);
        buyThreeHouse.setVisible(true);
    }

    private void boardMousePressed2(java.awt.event.MouseEvent evt) {
        double x = evt.getX();
        double y = evt.getY();
        popup2.setVisible(true);
        btnBuy.setVisible(true);
        btnCancel.setVisible(true);
    }

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BoardWithBuyLandandHouse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BoardWithBuyLandandHouse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BoardWithBuyLandandHouse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BoardWithBuyLandandHouse().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JLabel board;
    private javax.swing.JLabel text;
    private javax.swing.JLabel popup;
    private javax.swing.JLabel popup2;
    private javax.swing.ButtonGroup btnGroup;
    private javax.swing.JCheckBox buyOneHouse;
    private javax.swing.JCheckBox buyTwoHouse;
    private javax.swing.JCheckBox buyThreeHouse;
    private javax.swing.JButton btnConfirm;
    private javax.swing.JButton btnBuy;
    private javax.swing.JButton btnCancel;
    // End of variables declaration
}