/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SEPoly;

import model.Space;
import model.EstateSpace;
import socketConnection.Gameplay;
import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;


public class SEPoly extends javax.swing.JFrame {
    public SEPoly() throws IOException{
        initComponents();
    }
    private void initComponents() throws IOException {
        checkTitledeed = 0;


        game = new Gameplay();
        game.display();
        start = new javax.swing.JPanel();
        lobby = new javax.swing.JPanel();
        gameplay = new javax.swing.JPanel();

        container = new javax.swing.JTabbedPane();

        //gameplay
        image  = new javax.swing.JLabel();
        text = new javax.swing.JLabel();
        landName = new javax.swing.JLabel("", SwingConstants.CENTER);
        popup = new javax.swing.JLabel();
        board = new javax.swing.JLabel(new javax.swing.ImageIcon("src\\allImage\\finaljingjing_board.png"));
        //house buying popup
        cancelBtn = new javax.swing.JLabel(new javax.swing.ImageIcon("src\\allImage\\Asset 22.png"));
        buyBtn = new javax.swing.JLabel(new javax.swing.ImageIcon("src\\Users\\Asus\\Desktop\\javaProject\\monopoly\\src\\allImage\\Asset 21.png"));
        totalPrice = new javax.swing.JLabel();
        threeHouseCheck= new javax.swing.JCheckBox();
        twoHouseCheck = new javax.swing.JCheckBox();
        oneHouseCheck = new javax.swing.JCheckBox();


        //start
        createBtn = new javax.swing.JLabel();
        joinBtn = new javax.swing.JLabel();
        n = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        dot = new javax.swing.JLabel();
        logoBg = new javax.swing.JLabel();

        //lobby
        startBtn = new javax.swing.JLabel(new javax.swing.ImageIcon("src\\allImage\\Asset 30.png"));
        text2 = new javax.swing.JLabel();
        roomNumber = new javax.swing.JLabel();
        player1 = new javax.swing.JLabel();
        player2 = new javax.swing.JLabel();
        player3 = new javax.swing.JLabel();
        player4 = new javax.swing.JLabel();
        lobbyBg = new javax.swing.JLabel(new javax.swing.ImageIcon("src\\allImage\\lobbyBg.png"));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SE POLY");
        setPreferredSize(new java.awt.Dimension(800, 638));
        setResizable(false);
        getContentPane().setLayout(null);


        container.setEnabled(false);
        container.setBounds(0,-29,800,629);

        //******************************************************************************
        //******************************************************************************
        //---------------------S T A R T -----------------------------------------------
        start.setBounds(0,0,800,600);
        start.setLayout(null);
        start.setBackground(new java.awt.Color(255, 204, 204));
        start.add(logoBg);
        start.add(createBtn);
        start.add(joinBtn);
        start.add(n);
        start.add(dot);
        start.add(username);


        createBtn.setBounds(140,420,195,95);
        joinBtn.setBounds(450,420,195,95);
        n.setBounds(183,270,100,100);
        username.setBounds(350,305,250,35);
        dot.setBounds(300,270,100,100);
        logoBg.setBounds(144,50,498,180);

        n.setFont(new java.awt.Font("Microsoft PhagsPa", 1, 32));
        n.setText("name ");

        username.setFont(new java.awt.Font("Microsoft PhagsPa", 1, 22));
        username.setText("Enter your name");

        logoBg.setIcon(new javax.swing.ImageIcon("src\\allImage\\Asset 11.png"));
        logoBg.setLabelFor(logoBg);

        createBtn.setIcon(new javax.swing.ImageIcon("src\\allImage\\Asset 13.png"));

        createBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                createBtnActionPerformed(evt);
            }
        });


        joinBtn.setIcon(new javax.swing.ImageIcon("src\\allImage\\Asset 14.png")); // NOI18N

        joinBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                joinBtnActionPerformed(evt);
            }
        });



        dot.setText("<html><b>:</b></html>");



        container.addTab("start", start);




        //******************************************************************************
        //******************************************************************************
        //---------------------L O B B Y -----------------------------------------------
        lobby.setAlignmentX(0.0F);
        lobby.setAlignmentY(0.0F);
        lobby.setBounds(0,0,800,600);
        lobby.setLayout(null);
        lobby.add(startBtn);
        lobby.add(text2);
        lobby.add(roomNumber);
        lobby.add(player1);
        lobby.add(player2);
        lobby.add(player3);
        lobby.add(player4);
        lobby.add(lobbyBg);

        lobbyBg.setBounds(0,0,800,600);

        startBtn.setVisible(true);
        startBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                startBtnActionPerformed(evt);
            }
        });


        text2.setFont(new java.awt.Font("Muller Demo ExtraBold", 1, 36));
        text2.setForeground(new java.awt.Color(0, 0, 51));
        text2.setText("<html><b>Room  number  : </b></html>");

        roomNumber.setFont(new java.awt.Font("Muller Demo ExtraBold", 0, 24)); // NOI18N
        roomNumber.setForeground(new java.awt.Color(153, 0, 0));
        roomNumber.setText("1234");


        player1.setOpaque(true);
        player1.setBackground(new java.awt.Color(255, 255, 255));
        player1.setFont(new java.awt.Font("supermarket", 0, 24)); // NOI18N

        player2.setOpaque(true);
        player2.setBackground(new java.awt.Color(255, 255, 255));
        player2.setFont(new java.awt.Font("supermarket", 0, 24)); // NOI18N

        player3.setOpaque(true);
        player3.setBackground(new java.awt.Color(255, 255, 255));
        player3.setFont(new java.awt.Font("supermarket", 0, 24));

        player4.setOpaque(true);
        player4.setBackground(new java.awt.Color(255, 255, 255));
        player4.setFont(new java.awt.Font("supermarket", 0, 24));

        text2.setBounds(93,50,300,100);
        startBtn.setBounds(420,446,196,100);
        roomNumber.setBounds(400, 57, 100,100);
        player1.setBounds(170,188,450,50);
        player2.setBounds(170,250,450,50);
        player3.setBounds(170,312,450,50);
        player4.setBounds(170,374,450,50);





        container.addTab("lobby", lobby);

        //******************************************************************************
        //******************************************************************************
        //---------------------G A M E P L A Y------------------------------------------

        //house buying popup
        gameplay.add(buyBtn);
        gameplay.add(cancelBtn);
        gameplay.add(totalPrice);
        gameplay.add(oneHouseCheck);
        gameplay.add(twoHouseCheck);
        gameplay.add(threeHouseCheck);

        buyBtn.setVisible(false);
        cancelBtn.setVisible(false);
        totalPrice.setVisible(false);
        oneHouseCheck.setVisible(false);
        twoHouseCheck.setVisible(false);
        threeHouseCheck.setVisible(false);:


        gameplay.setAlignmentX(0.0F);
        gameplay.setAlignmentY(0.0F);
        gameplay.setBounds(0,0,800,600);
        gameplay.setLayout(null);
        gameplay.add(image);
        gameplay.add(text);
        gameplay.add(landName);
        gameplay.add(popup);
        gameplay.add(board);



        image.setVisible(false);



        popup.setBounds(0,0,800,600);
        popup.setVisible(false);
        popup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                popupMousePressed(evt);
            }
        });

        board.setBounds(0, 0, 800, 600);
        board.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                boardMousePressed(evt);
            }
        });

        text.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 13));
        getContentPane().add(text);
        text.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        text.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        text.setBounds(293,304,300,200);
        text.setVisible(false);

        landName.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 15));
        landName.setBounds(308,160,187,34);
        landName.setVisible(false);

        cancelBtn.setBounds(290, 400, 90, 39);
        buyBtn.setBounds(430, 400, 100, 39);

        totalPrice.setFont(new java.awt.Font("Tahoma", 1, 16));
        totalPrice.setText("1400000000");
        totalPrice.setBounds(350, 365, 210, 20);


        threeHouseCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                threeHouseCheckActionPerformed(evt);
            }
        });
        threeHouseCheck.setBounds(390, 310, 25, 25);

        twoHouseCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                twoHouseCheckActionPerformed(evt);
            }
        });
        twoHouseCheck.setBounds(500, 310, 25, 25);

        oneHouseCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oneHouseCheckActionPerformed(evt);
            }
        });
        oneHouseCheck.setBounds(270, 310, 25, 25);

        container.addTab("gameplay", gameplay);

        getContentPane().add(container);
        container.setSelectedIndex(0);
        pack();
    }




    //******************************************************************************
    //******************************************************************************
    //---------------------S T A R T -----------------------------------------------

    private void createBtnActionPerformed(java.awt.event.MouseEvent evt) {
        if(username.getText().length()>0 && !username.getText().equals("Enter your name")) {
            container.setSelectedIndex(1);
        }

    }

    private void joinBtnActionPerformed(java.awt.event.MouseEvent evt) {
        if(username.getText().length()>0 && !username.getText().equals("Enter your name")) {
            String roomNumber;
            JOptionPane joinGame = new JOptionPane();
            //Shows a inputdialog
            roomNumber = joinGame.showInputDialog("Enter a number: ");
            //if OK is pushed then (if not strDialogResponse is null)
            if (roomNumber != null && roomNumber.length()>0) {
                container.setSelectedIndex(1);
            }
            else if (roomNumber != null){
                JOptionPane.showMessageDialog(null, "Plese Enter your room number");
            }
        }
    }


    //******************************************************************************
    //******************************************************************************
    //---------------------L O B B Y -----------------------------------------------

    private void startBtnActionPerformed(java.awt.event.MouseEvent evt) {
        //if num of player == 4
        container.setSelectedIndex(2);
        isOwnedEstate();
    }



    //******************************************************************************
    //******************************************************************************
    //---------------------G A M E P L A Y------------------------------------------

    //close estate detail by clicking anywhere
    private void popupMousePressed(java.awt.event.MouseEvent evt) {
        if(checkTitledeed == 1){
            image.setVisible(false);
            popup.setVisible(false);
            text.setVisible(false);
            landName.setVisible(false);
            checkTitledeed = 0;
        }
    }
    //show estate detail
    private void boardMousePressed(java.awt.event.MouseEvent evt) {
        checkTitledeed = 1;
        map = game.getMap();
        double x = evt.getX();
        double y = evt.getY();

        int c = game.check_board_is_clicked(x,y);
        if (c != -1) {
            if (map.get(c) instanceof EstateSpace) {
                EstateSpace temp = (EstateSpace)map.get(c);

                popup.setIcon(Land_popup);
                String s = "<html><body><pre><b>" +
                        "PRICE              "+temp.getPrice()+"<br>" +
                        "RENT               "+temp.getRentPrices().get(0)+"<br>" +
                        "RENT WITH 1 HOUSE  "+temp.getRentPrices().get(1)+"<br>" +
                        "RENT WITH 2 HOUSE  "+temp.getRentPrices().get(2)+"<br>" +
                        "RENT WITH 3 HOUSE  "+temp.getRentPrices().get(3)+"<br>" +
                        "RENT WITH LANDMARK "+temp.getRentPrices().get(4)+"<br>" +
                        "HOUSE PRICE        "+temp.getHousePrice()+"<br>" +
                        "LANDMARK PRICE     "+temp.getLandmarkPrice()+"</b></pre></body></html>";
                text.setText(s);
                landName.setText("<html><b>"+temp.getName()+"</b></html>");
                ImageIcon t = new ImageIcon(((EstateSpace) map.get(c)).getImage());

                if(c>0 && c<8) {
                    image.setBounds(0 - ((EstateSpace) map.get(c)).getDisplayXPos() + 335,
                            0 - ((EstateSpace) map.get(c)).getDisplayYPos() + 300,
                            800, 600);
                }
                else if (c < 16){
                    image.setBounds(0 - ((EstateSpace) map.get(c)).getDisplayXPos() + 340,
                            0 - ((EstateSpace) map.get(c)).getDisplayYPos() + 265,
                            800, 600);
                }
                else if (c < 24){
                    image.setBounds(0 - ((EstateSpace) map.get(c)).getDisplayXPos() + 380,
                            0 - ((EstateSpace) map.get(c)).getDisplayYPos() + 280,
                            800, 600);
                }
                else{
                    image.setBounds(0 - ((EstateSpace) map.get(c)).getDisplayXPos() + 380,
                            0 - ((EstateSpace) map.get(c)).getDisplayYPos() + 290,
                            800, 600);
                }


                image.setIcon(t);
                image.setVisible(true);
                landName.setVisible(true);
                popup.setVisible(true);
                text.setVisible(true);
            }
        }
    }


    private void isOwnedEstate(){
        popup.setIcon(houseBuying_popup);
        popup.setVisible(true);
        buyBtn.setVisible(true);
        cancelBtn.setVisible(true);
        oneHouseCheck.setVisible(true);
        twoHouseCheck.setVisible(true);
        threeHouseCheck.setVisible(true);
        totalPrice.setVisible(true);
    }

    public static void start() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SEPoly.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SEPoly.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SEPoly.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SEPoly.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new SEPoly().setVisible(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void oneHouseCheckActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void twoHouseCheckActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void threeHouseCheckActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private int checkTitledeed;
    //start
    private javax.swing.JLabel createBtn;
    private javax.swing.JLabel n;
    private javax.swing.JLabel dot;
    private javax.swing.JLabel joinBtn;
    private javax.swing.JLabel logoBg;
    private javax.swing.JTextField username;

    //lobby
    private javax.swing.JLabel text2;
    private javax.swing.JLabel player1;
    private javax.swing.JLabel player2;
    private javax.swing.JLabel player3;
    private javax.swing.JLabel player4;
    private javax.swing.JLabel startBtn;
    private javax.swing.JLabel roomNumber;
    private javax.swing.JLabel lobbyBg;

    //gameplay
    private javax.swing.JTabbedPane container;
    private javax.swing.JPanel gameplay;

    private javax.swing.JLabel board;
    private javax.swing.JPanel lobby;
    private javax.swing.JPanel start;
    private javax.swing.JLabel text;
    private javax.swing.JLabel popup;
    private javax.swing.JLabel landName;
    private javax.swing.JLabel image;
    private javax.swing.ImageIcon Land_popup = new javax.swing.ImageIcon("src\\allImage\\estate.png");

    //for house buying
    private javax.swing.JLabel buyBtn;
    private javax.swing.JLabel cancelBtn;
    private javax.swing.JCheckBox oneHouseCheck;
    private javax.swing.JCheckBox twoHouseCheck;
    private javax.swing.JCheckBox threeHouseCheck;
    private javax.swing.ImageIcon houseBuying_popup = new javax.swing.ImageIcon("src\\allImage\\houseBuying.png");;
    private javax.swing.JLabel totalPrice;

    private Gameplay game;
    private ArrayList<Space> map;
}
