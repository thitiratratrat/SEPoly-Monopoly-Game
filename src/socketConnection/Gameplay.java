package socketConnection;

import DiceAnimate.Dice;
import model.*;

import javax.sound.midi.Soundbank;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.Timer;

import java.util.ArrayList;


public class Gameplay extends javax.swing.JFrame {
    private String name;
    private Client client;
    private Player player;
    private ArrayList<PlayerObj> opponents;
    private ArrayList<Space> map;
    private String address = "127.0.0.1";
    private int port = 5056;
    private Timer biddingTimer = new Timer();
    private Timer sendPlayerDataTimer, getGameDataTimer;
    private Integer highestBidMoney = null;
    private boolean isTurn = false;
    private boolean isMoving = false;
    private int jailTurnCount = 0;
    private int spaceNumber = 0;
    final private int TIMER_DELAY = 5;
    final private int SPACE_COUNT = 32;
    final private int BIDTIMER_DELAY = 100000;
    final private int JAIL_SPACE_NUMBER = 8;
    final private int JAIL_FEE = 50;
    private final int MAX_PLAYERS = 4;

    //-----------------------------------------------------------------
    //------------------ F O R U I ------------------------------------
    //-----------------------------------------------------------------


    //start
    private javax.swing.JLabel n;
    private javax.swing.JLabel dot;
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
    private javax.swing.JLabel moneyPlayer1;
    private javax.swing.JLabel moneyPlayer2;
    private javax.swing.JLabel moneyPlayer3;
    private javax.swing.JLabel moneyPlayer4;
    private javax.swing.JLabel namePlayer1;
    private javax.swing.JLabel namePlayer2;
    private javax.swing.JLabel namePlayer3;
    private javax.swing.JLabel namePlayer4;


    //title deed popup
    private javax.swing.JLabel board;
    private javax.swing.JPanel lobby;
    private javax.swing.JPanel start;
    private javax.swing.JLabel text;
    private javax.swing.JLabel titleDeedInfo;
    private javax.swing.JLabel landName;
    private javax.swing.JLabel image;

    //other space info
    private javax.swing.JLabel spaceInfo;

    //card
    private javax.swing.JLabel card;

    //roll but
    private javax.swing.JLabel rollBtn;

    //for house buying
    private javax.swing.JLabel buyBtn;
    private javax.swing.JLabel cancelBtn;
    private javax.swing.JCheckBox oneHouseCheck;
    private javax.swing.JCheckBox twoHouseCheck;
    private javax.swing.JCheckBox threeHouseCheck;
    private javax.swing.JLabel totalPrice;
    private javax.swing.JLabel houseBuying;

    //dice
    private Dice dice;

    public Gameplay() {
        initUI();
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Gameplay().setVisible(true);
//            }
//        });
    }

    public void connectToServer() throws IOException {
        this.startClientConnection();
    }

    /*public void run() throws IOException {
        Gameplay gameplay = new Gameplay();
        gameplay.startClientConnection();
        GUI.NewJFrame2 board = new GUI.NewJFrame2();
        board.display();
        gameplay.start();
    }*/

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPort(int port) {
        this.port = port;
    }

    private void startClientConnection() throws IOException {
        client = new Client(address, port);
    }

    private void initUI() {
        //TODO: init UI code here
        //Dice
        dice = new Dice();
        dice.setOpaque(false);
        dice.setBackground(new Color(255, 0, 0, 20));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SE POLY");
        setPreferredSize(new java.awt.Dimension(800, 638));
        setResizable(false);
        getContentPane().setLayout(null);


        start = new javax.swing.JPanel();
        start.setBounds(0, 0, 800, 600);
        start.setLayout(null);
        start.setBackground(new java.awt.Color(255, 204, 204));

        lobby = new javax.swing.JPanel();
        lobby.setBounds(0, 0, 800, 600);

        gameplay = new javax.swing.JPanel();
        gameplay.setBounds(0, 0, 800, 600);
        gameplay.add(dice);

        container = new javax.swing.JTabbedPane();
        container.addTab("Start", start);
        container.addTab("Lobby", lobby);
        container.addTab("Gameplay", gameplay);
        container.setEnabled(false);
        container.setBounds(0, -29, 800, 629);

        getContentPane().add(container);
        container.setSelectedIndex(0);

        // -------------------------------------------------------------
        // ----------------------- S T A R T ---------------------------
        // -------------------------------------------------------------
        startBtn = new javax.swing.JLabel(new javax.swing.ImageIcon("src\\allImage\\Asset 30.png"));
        startBtn.setBounds(300, 400, 196, 100);
        n = new javax.swing.JLabel();
        n.setBounds(183, 270, 100, 100);
        n.setFont(new java.awt.Font("Microsoft PhagsPa", 1, 32));
        n.setText("name ");
        username = new javax.swing.JTextField();
        username.setBounds(350, 305, 250, 35);
        username.setFont(new java.awt.Font("Microsoft PhagsPa", 1, 22));
        username.setText("Enter your name");
        dot = new javax.swing.JLabel();
        dot.setBounds(300, 270, 100, 100);
        dot.setText("<html><b>:</b></html>");
        logoBg = new javax.swing.JLabel();
        logoBg.setBounds(144, 50, 498, 180);
        logoBg.setIcon(new javax.swing.ImageIcon("src\\allImage\\Asset 11.png"));

        start.add(logoBg);
        start.add(startBtn);
        start.add(n);
        start.add(dot);
        start.add(username);


        // -------------------------------------------------------------
        // ----------------------- L O B B Y ---------------------------
        // -------------------------------------------------------------
        text2 = new javax.swing.JLabel();
        text2.setFont(new java.awt.Font("Muller Demo ExtraBold", 1, 36));
        text2.setForeground(new java.awt.Color(0, 0, 51));
        text2.setText("<html><b>Room  number  : </b></html>");
        roomNumber = new javax.swing.JLabel();
        roomNumber.setBounds(400, 57, 100, 100);
        roomNumber.setFont(new java.awt.Font("Muller Demo ExtraBold", 0, 24)); // NOI18N
        roomNumber.setForeground(new java.awt.Color(153, 0, 0));
        roomNumber.setText("2958");
        player1 = new javax.swing.JLabel();
        player1.setOpaque(true);
        player1.setBackground(new java.awt.Color(255, 255, 255));
        player1.setFont(new java.awt.Font("supermarket", 0, 24));
        player1.setBounds(170, 188, 450, 50);
        text2.setBounds(93, 50, 300, 100);
        player2 = new javax.swing.JLabel();
        player2.setOpaque(true);
        player2.setBackground(new java.awt.Color(255, 255, 255));
        player2.setFont(new java.awt.Font("supermarket", 0, 24));
        player2.setBounds(170, 250, 450, 50);
        player3 = new javax.swing.JLabel();
        player3.setOpaque(true);
        player3.setBackground(new java.awt.Color(255, 255, 255));
        player3.setFont(new java.awt.Font("supermarket", 0, 24));
        player3.setBounds(170, 312, 450, 50);
        player4 = new javax.swing.JLabel();
        player4.setOpaque(true);
        player4.setBackground(new java.awt.Color(255, 255, 255));
        player4.setFont(new java.awt.Font("supermarket", 0, 24));
        player4.setBounds(170, 374, 450, 50);
        lobbyBg = new javax.swing.JLabel(new javax.swing.ImageIcon("src\\allImage\\lobbyBg.png"));
        lobbyBg.setBounds(0, 0, 800, 600);

        lobby.setLayout(null);
        lobby.add(text2);
        lobby.add(roomNumber);
        lobby.add(player1);
        lobby.add(player2);
        lobby.add(player3);
        lobby.add(player4);
        lobby.add(lobbyBg);


        // -------------------------------------------------------------
        // -------------------G A M E   P L A Y ------------------------
        // -------------------------------------------------------------
        rollBtn = new javax.swing.JLabel(new javax.swing.ImageIcon("src\\allImage\\Asset 31.png"));
        rollBtn.setBounds(369, 381, 59, 50);
        rollBtn.setEnabled(false);


        board = new javax.swing.JLabel(new javax.swing.ImageIcon("src\\allImage\\finaljingjing_board.png"));
        board.setBounds(0, 0, 800, 600);
        moneyPlayer1 = new javax.swing.JLabel();
        moneyPlayer1.setBounds(653, 563, 126, 20);
        moneyPlayer2 = new javax.swing.JLabel();
        moneyPlayer2.setBounds(22, 563, 126, 20);
        moneyPlayer2.setHorizontalAlignment(SwingConstants.RIGHT);
        moneyPlayer3 = new javax.swing.JLabel();
        moneyPlayer3.setBounds(22, 52, 126, 20);
        moneyPlayer3.setHorizontalAlignment(SwingConstants.RIGHT);

        moneyPlayer4 = new javax.swing.JLabel();
        moneyPlayer4.setBounds(653, 52, 126, 20);
        namePlayer1 = new javax.swing.JLabel();
        namePlayer1.setBounds(653, 535, 126, 20);
        namePlayer2 = new javax.swing.JLabel();
        namePlayer2.setBounds(22, 535, 126, 20);
        namePlayer2.setHorizontalAlignment(SwingConstants.RIGHT);
        namePlayer3 = new javax.swing.JLabel();
        namePlayer3.setBounds(22, 24, 126, 20);
        namePlayer3.setHorizontalAlignment(SwingConstants.RIGHT);
        namePlayer4 = new javax.swing.JLabel();
        namePlayer4.setBounds(653, 24, 126, 20);


        // T I T L E  D E E D  P O P  U P
        image = new javax.swing.JLabel();
        text = new javax.swing.JLabel();
        text.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 13));
        text.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        text.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        text.setBounds(293, 304, 300, 200);
        landName = new javax.swing.JLabel("", SwingConstants.CENTER);
        landName.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 15));
        landName.setBounds(308, 160, 187, 34);
        titleDeedInfo = new javax.swing.JLabel(new javax.swing.ImageIcon("src\\allImage\\estate.png"));
        titleDeedInfo.setBounds(0, 0, 800, 600);

        titleDeedInfo.add(image);
        titleDeedInfo.add(text);
        titleDeedInfo.add(landName);
        titleDeedInfo.setVisible(false);

        // O T H E R   S P A C E
        spaceInfo = new javax.swing.JLabel();
        spaceInfo.setBounds(0, 0, 800, 600);
        spaceInfo.setVisible(false);

        // H O U S E  B U Y I N G  P O P U P
        cancelBtn = new javax.swing.JLabel(new javax.swing.ImageIcon("src\\allImage\\Asset 22.png"));
        cancelBtn.setBounds(290, 390, 90, 39);
        totalPrice = new javax.swing.JLabel();
        totalPrice.setFont(new java.awt.Font("Tahoma", 1, 16));
        totalPrice.setBounds(350, 354, 210, 20);
        buyBtn = new javax.swing.JLabel(new javax.swing.ImageIcon("src\\allImage\\Asset 21.png"));
        buyBtn.setBounds(430, 390, 100, 39);
        threeHouseCheck = new javax.swing.JCheckBox();
        threeHouseCheck.setBounds(390, 310, 25, 25);
        twoHouseCheck = new javax.swing.JCheckBox();
        twoHouseCheck.setBounds(500, 310, 25, 25);
        oneHouseCheck = new javax.swing.JCheckBox();
        oneHouseCheck.setBounds(270, 310, 25, 25);

        houseBuying = new javax.swing.JLabel(new javax.swing.ImageIcon("src\\allImage\\houseBuying.png"));
        houseBuying.setBounds(0, 0, 800, 600);
        houseBuying.add(cancelBtn);
        houseBuying.add(buyBtn);
        houseBuying.add(totalPrice);
        houseBuying.add(oneHouseCheck);
        houseBuying.add(twoHouseCheck);
        houseBuying.add(threeHouseCheck);
        houseBuying.setVisible(false);

        // S H O W  C A R D
        card = new javax.swing.JLabel();
        card.setBounds(0, 0, 800, 600);
        card.setVisible(false);

        gameplay.setLayout(null);
        gameplay.add(rollBtn);

        gameplay.add(spaceInfo);
        gameplay.add(card);
        gameplay.add(houseBuying);
        gameplay.add(titleDeedInfo);
        gameplay.add(namePlayer1);
        gameplay.add(namePlayer2);
        gameplay.add(namePlayer3);
        gameplay.add(namePlayer4);
        gameplay.add(moneyPlayer1);
        gameplay.add(moneyPlayer2);
        gameplay.add(moneyPlayer3);
        gameplay.add(moneyPlayer4);
        gameplay.add(board);


        // --------------------------------------------------------------------
        // ----------------- A C T I O N    L I S T E N E R -------------------
        //---------------------------------------------------------------------

        //start
        startBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                try {
                    startBtnActionPerformed(evt);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //gameplay
        titleDeedInfo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                titleDeedMousePressed(evt);
            }
        });
        houseBuying.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                houseBuyingMousePressed(evt);
            }
        });

        board.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                boardMousePressed(evt);
            }
        });

        spaceInfo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                spaceInfoMousePressed(evt);
            }
        });

        card.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cardMousePressed(evt);
            }
        });

        oneHouseCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oneHouseCheckActionPerformed(evt);
            }
        });
        twoHouseCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                twoHouseCheckActionPerformed(evt);
            }
        });
        threeHouseCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                threeHouseCheckActionPerformed(evt);
            }
        });

        rollBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                try {
                    rollBtnMousePressed(evt);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        pack();
    }

    //******************************************************************************
    //******************************************************************************
    //---------------------S T A R T -----------------------------------------------

    private void startBtnActionPerformed(java.awt.event.MouseEvent evt) throws IOException {
        if (username.getText().length() > 0 && !username.getText().equals("Enter your name")) {
            connectToServer();
            startBtn.setEnabled(false);
            name = username.getText();
            start();
            sendNameToServer();
        }
    }


    //******************************************************************************
    //******************************************************************************
    //---------------------G A M E P L A Y------------------------------------------

    //close title deed by clicking anywhere
    private void titleDeedMousePressed(java.awt.event.MouseEvent evt) {
        titleDeedInfo.setVisible(false);
    }

    //close other space info
    private void spaceInfoMousePressed(java.awt.event.MouseEvent evt) {
        spaceInfo.setVisible(false);
    }

    //show estate detail
    private void boardMousePressed(java.awt.event.MouseEvent evt) {
        double x = evt.getX();
        double y = evt.getY();

        int index = this.board_is_clicked(x, y);
        if (index != -1) {
            if (map.get(index) instanceof EstateSpace) {
                EstateSpace temp = (EstateSpace) map.get(index);
                String s = "<html><body><pre><b>" +
                        "PRICE               " + intToString(temp.getPrice()) + "<br>" +
                        "RENT                " + intToString(temp.getRentPrices().get(0)) + "<br>" +
                        "RENT WITH 1 HOUSE   " + intToString(temp.getRentPrices().get(1)) + "<br>" +
                        "RENT WITH 2 HOUSE   " + intToString(temp.getRentPrices().get(2)) + "<br>" +
                        "RENT WITH 3 HOUSE   " + intToString(temp.getRentPrices().get(3)) + "<br>" +
                        "RENT WITH LANDMARK  " + intToString(temp.getRentPrices().get(4)) + "<br>" +
                        "HOUSE PRICE         " + intToString(temp.getHousePrice()) + "<br>" +
                        "LANDMARK PRICE      " + intToString(temp.getLandmarkPrice()) + "</b></pre></body></html>";
                text.setText(s);
                landName.setText("<html><b>" + temp.getName() + "</b></html>");
                image.setIcon(new ImageIcon((map.get(index)).getImage()));

                if (index > 0 && index < 8) {
                    image.setBounds(0 - ((EstateSpace) map.get(index)).getDisplayXPos() + 335,
                            0 - ((EstateSpace) map.get(index)).getDisplayYPos() + 300,
                            800, 600);
                } else if (index < 16) {
                    image.setBounds(0 - ((EstateSpace) map.get(index)).getDisplayXPos() + 340,
                            0 - ((EstateSpace) map.get(index)).getDisplayYPos() + 265,
                            800, 600);
                } else if (index < 24) {
                    image.setBounds(0 - ((EstateSpace) map.get(index)).getDisplayXPos() + 380,
                            0 - ((EstateSpace) map.get(index)).getDisplayYPos() + 280,
                            800, 600);
                } else {
                    image.setBounds(0 - ((EstateSpace) map.get(index)).getDisplayXPos() + 380,
                            0 - ((EstateSpace) map.get(index)).getDisplayYPos() + 290,
                            800, 600);
                }


                titleDeedInfo.setVisible(true);
            } else {
                spaceInfo.setIcon(new ImageIcon((map.get(index)).getImage()));
                spaceInfo.setVisible(true);
            }
        }
    }

    private void houseBuyingMousePressed(java.awt.event.MouseEvent evt) {
        houseBuying.setVisible(false);
    }

    private void oneHouseCheckActionPerformed(java.awt.event.ActionEvent evt) {
        if (oneHouseCheck.isSelected())
            totalPrice.setText("Ddddddd");
        else
            totalPrice.setText("GGGggg");
    }

    private void twoHouseCheckActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void threeHouseCheckActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void cardMousePressed(java.awt.event.MouseEvent evt) {
        card.setVisible(false);
    }

    private void rollBtnMousePressed(java.awt.event.MouseEvent evt) throws IOException {
        rollDice();
    }

    //call when player buy an estate / land on own estate
    private void showHouseBuying(int indexonboard) {
        houseBuying.setVisible(true);
        //check player money and num of house to enable check box
        //call server  to update all
    }

    //call when player land on community card / chance card

    private void showCard() {
        //send to massage to show
    }

    private void setName(ArrayList<String> names) {
        for (int i = 0; i < names.size(); i++) {
            switch (i) {
                case 0:
                    namePlayer1.setText("<html><b><p style = 'font-family:Microsoft PhagsPa; font-size: 20'>"
                            + names.get(i) + "</p></b></html>");
                    break;
                case 1:
                    namePlayer2.setText("<html><b><p style = 'font-family:Microsoft PhagsPa; font-size: 20'>"
                            + names.get(i) + "</p></b></html>");
                    break;
                case 2:
                    namePlayer3.setText("<html><b><p style = 'font-family:Microsoft PhagsPa; font-size: 20'>"
                            + names.get(i) + "</p></b></html>");
                    break;
                case 3:
                    namePlayer4.setText("<html><b><p style = 'font-family:Microsoft PhagsPa; font-size: 20'>"
                            + names.get(i) + "</p></b></html>");
                    break;
                default:
                    break;
            }
        }
    }

    private void setMoney(int playerID, int money) {
        String moneyText = intToString(money);

        switch (playerID) {
            case (0):
                moneyPlayer1.setText(moneyText);
                break;
            case (1):
                moneyPlayer2.setText(moneyText);
                break;
            case (2):
                moneyPlayer3.setText(moneyText);
                break;
            case (3):
                moneyPlayer4.setText(moneyText);
                break;
            default:
                break;
        }
    }


    private String intToString(int inp) {
        String temp = "";
        if (inp < 1000)
            return Integer.toString(inp);
        if (inp >= 1000000) {
            temp += Integer.toString(inp / 1000000) + "M ";
            inp %= 1000000;
        }
        if (inp >= 1000)
            temp += Integer.toString(inp / 1000) + "K";
        return temp;
    }

    public int board_is_clicked(double x, double y) {
        System.out.println("board is clicked");
        for (Space s : map) {
            if (checkIsPositionsOnSpace(s.getPositions(), x, y)) {
                return map.indexOf(s);
            }
        }
        return -1;
    }

    private boolean checkIsPositionsOnSpace(double[] pos, double x, double y) {
        double x1 = pos[0];
        double y1 = pos[1];
        double x2 = pos[2];
        double y2 = pos[3];
        double x3 = pos[4];
        double y3 = pos[5];
        double x4 = pos[6];
        double y4 = pos[7];

        double slope_1to2 = (y2 - y1) / (x2 - x1);
        double slope_2to3 = (y3 - y2) / (x3 - x2);
        double slope_3to4 = (y4 - y3) / (x4 - x3);
        double slope_4to1 = (y1 - y4) / (x1 - x4);

        double c_1to2 = (-x1 * slope_1to2) + y1;
        double c_2to3 = (-x2 * slope_2to3) + y2;
        double c_3to4 = (-x3 * slope_3to4) + y3;
        double c_4to1 = (-x4 * slope_4to1) + y4;
        //upper Y
        double yUpper1 = (slope_2to3 * x) + c_2to3; //xLeft2
        double yUpper2 = (slope_3to4 * x) + c_3to4; //xRight2
        if (y < yUpper1 || y < yUpper2)
            return false;
        //lower Y
        double yLower1 = (slope_1to2 * x) + c_1to2; //xLeft1
        double yLower2 = (slope_4to1 * x) + c_4to1; //xRight1
        if (y > yLower1 || y > yLower2)
            return false;
        //Left X
        double xLeft1 = (y - c_1to2) / slope_1to2;
        double xLeft2 = (y - c_2to3) / slope_2to3;
        if (x < xLeft1 || x < xLeft2)
            return false;
        //Right X
        double xRight1 = (y - c_4to1) / slope_4to1;
        double xRight2 = (y - c_3to4) / slope_3to4;

        if (x > xRight1 || x > xRight2)
            return false;

        return true;
    }


    //*********************************************************************
    //*********************************************************************

    //------------------- E N D   U I   P A R T ---------------------------

    public void start() throws IOException {
//        startSendPlayerPositionTimer();
        startGetGameDataTimer();
    }
    //send player position update

    private void startSendPlayerPositionTimer() {
        sendPlayerDataTimer = new Timer();
        sendPlayerDataTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (isMoving) {
                    try {
                        sendPlayerToUpdate();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, 0, TIMER_DELAY);
    }
    //for update with no request from gameplay client side

    private void startGetGameDataTimer() {
        getGameDataTimer = new Timer();
        getGameDataTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    ServerMessage serverMessage = client.getData();
                    if (serverMessage == null) {
                        return;
                    }

                    String action = serverMessage.getAction();
                    switch (action) {
                        case ("startGame"): {
                            container.setSelectedIndex(2);
                            break;
                        }

                        case ("startTurn"): {
                            isTurn = true;
                            rollBtn.setEnabled(true);
                            //roll dice -> walk
                            //enable roll button

                            break;
                        }

                        case ("initMap"): {
                            map = (ArrayList<Space>) serverMessage.getData();
                            break;
                        }

                        case ("initNames"): {
                            ArrayList<String> names = (ArrayList<String>) serverMessage.getData();
                            setName(names);
                            break;
                        }

                        case ("updatePlayer"):
                        case ("initPlayer"): {
                            player = (Player) serverMessage.getData();
                            setMoney(player.getID(), player.getMoney());
                            //TODO: animate player if changes in x y
                            break;
                        }

                        case ("initOpponents"): {
                            opponents = (ArrayList<PlayerObj>) serverMessage.getData();
                            for (PlayerObj opponent : opponents) {
                                setMoney(opponent.getID(), opponent.getMoney());
                            }
                            break;
                        }

                        case ("updateOpponent"): {
                            PlayerObj playerObj = (PlayerObj) serverMessage.getData();
                            updateOpponent(playerObj);
                            break;
                        }

                        case ("startAuction"): {
                            PropertySpace auctionProperty = (PropertySpace) serverMessage.getData();
                            highestBidMoney = auctionProperty.getPrice();
                            //TODO: auction UI visible
                            biddingTimer.cancel();
                            startBidTimer();
                            break;
                        }

                        case ("updateHighestBidPrice"): {
                            BidObj bidObj = (BidObj) serverMessage.getData();
                            highestBidMoney = bidObj.getBidMoney();
                            biddingTimer.cancel();
                            startBidTimer();
                            break;
                        }

                        case ("endAuction"): {
                            biddingTimer.cancel();
                            break;
                        }

                        case ("updateMap"): {
                            PropertySpace propertySpace = (PropertySpace) serverMessage.getData();
                            updateMap(propertySpace);
                            break;
                        }

                        case ("moveForward"): {
                            int spaceNumber = (int) serverMessage.getData();
                            movePlayerForward(spaceNumber);
                            break;
                        }

                        case ("goToJail"): {
                            //TODO: animation go to jail
                            movePlayerTo(JAIL_SPACE_NUMBER);
                            break;
                        }

                        case ("updateDice"): {
                            int[] diceNumbers = (int[]) serverMessage.getData();
                            dice.roll(diceNumbers[0], diceNumbers[1]);
                            //TODO: display UI dice roll
                            break;
                        }

                        case ("bankrupt"): {
                            int playerID = (int) serverMessage.getData();
                            playerBankrupt(playerID);
                            break;
                        }

                        case ("moveTo"): {
                            int number = (int) serverMessage.getData();
                            movePlayerTo(number);
                            break;
                        }

                        case ("moveOpponentForward"): {
                            MoveAnimateObj moveObj = (MoveAnimateObj) serverMessage.getData();
                            //TODO: animate opponent id forward
                            break;
                        }

                        case ("moveOpponentTo"): {
                            MoveAnimateObj moveObj = (MoveAnimateObj) serverMessage.getData();
                            //TODO: animate player opponent to specific position
                            break;
                        }

                        default:
                            break;
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 0, TIMER_DELAY);
    }

    private void endTurn() throws IOException {
        isTurn = false;
        ServerMessage serverMessage = new ServerMessage("endTurn", player.getID());
        client.sendData(serverMessage);
        rollBtn.setEnabled(false);
    }

    private void updateOpponent(PlayerObj playerObj) {
        for (PlayerObj opponent : opponents) {
            if (opponent.getID() == playerObj.getID()) {
                opponent = playerObj;
                break;
            }
        }

        setMoney(playerObj.getID(), playerObj.getMoney());

        //TODO: animate opponent to move to that position
    }

    private void buy(PropertySpace propertySpace) throws IOException {
        player.buy(propertySpace);
        sendPlayerToUpdate();
        sendMapToUpdate(propertySpace);

        if (propertySpace instanceof EstateSpace) {
            //TODO: UI option to buy house
            houseBuying.setVisible(true);
        }
    }

    private void buyHouse(EstateSpace estateSpace, int houseCount) throws IOException {
        //TODO: UI not enable if player does not have enough money
        player.buyHouse(estateSpace, houseCount);
        sendMapToUpdate(estateSpace);
        sendPlayerToUpdate();
        //TODO: animation build house
    }

    private void buyLandmark(EstateSpace estateSpace) throws IOException {
        //TODO: UI is not enable if player does not have 4 houses
        if (estateSpace.getHouseCount() != 4) {
            return;
        }

        player.buyLandmark(estateSpace);
        sendMapToUpdate(estateSpace);
        sendPlayerToUpdate();
        //TODO: animation remove houses
        //TODO: animation build landmark
    }

    private void sell(PropertySpace propertySpace) throws IOException {
        if (propertySpace instanceof EstateSpace) {
            if (((EstateSpace) propertySpace).getHouseCount() != 0) {
                return;
            }
        }
        player.sell(propertySpace);
        sendMapToUpdate(propertySpace);
        sendPlayerToUpdate();
        //TODO: remove color on property space
    }

    private void sellHouse(EstateSpace estateSpace) throws IOException {
        if (estateSpace.getHouseCount() == 0) {
            return;
        }

        player.sellHouse(estateSpace);
        sendMapToUpdate(estateSpace);
        sendPlayerToUpdate();
        //TODO: animation remove house
    }

    private void sellLandmark(EstateSpace estateSpace) throws IOException {
        if (estateSpace.getLandmarkCount() == 0) {
            return;
        }

        player.sellLandmark(estateSpace);
        sendMapToUpdate(estateSpace);
        sendPlayerToUpdate();
        //TODO: animation remove landmark
    }

    private void sendPlayerToUpdate() throws IOException {
        ServerMessage serverMessage = new ServerMessage("updatePlayer", player);
        client.sendData(serverMessage);
    }

    private void sendPlayerToMoveForward(int moveCount) throws IOException {
        MoveAnimateObj moveObj = new MoveAnimateObj(player, moveCount);
        ServerMessage serverMessage = new ServerMessage("moveOpponentForward", moveObj);
        client.sendData(serverMessage);
    }

    private void sendPlayerToMoveTo(int spaceNumber) throws IOException {
        MoveAnimateObj moveObj = new MoveAnimateObj(player, spaceNumber);
        ServerMessage serverMessage = new ServerMessage("moveOpponentTo", moveObj);
        client.sendData(serverMessage);
    }

    private void startAuction(PropertySpace property) throws IOException {
        ServerMessage serverMessage = new ServerMessage("startAuction", property);
        client.sendData(serverMessage);
    }

    private void bid(int bidMoney) throws IOException {
        if (bidMoney <= highestBidMoney || player.getMoney() < bidMoney) {
            return;
        }

        BidObj bidObj = new BidObj(player.getID(), bidMoney);
        ServerMessage serverMessage = new ServerMessage("bid", bidObj);
        client.sendData(serverMessage);
    }

    private void rollDice() throws IOException {
        rollBtn.setEnabled(false);
        int[] diceNumbers = new int[2];
        int totalMoveCount = 0;
        Random randomGenerator = new Random();
        for (int i = 0; i < diceNumbers.length; i++) {
            int diceNumber = randomGenerator.nextInt(6) + 1;
            diceNumbers[i] = diceNumber;
            totalMoveCount += diceNumber;
        }

        ServerMessage serverMessage = new ServerMessage("updateDice", diceNumbers);
        client.sendData(serverMessage);
//        dice.roll(diceNumbers[0], diceNumbers[1]);
        //TODO: display UI dice roll
        //ddd(diceNumbers[0],diceNumbers[1]);
        if (player.isJailed()) {
            checkBreakJail(diceNumbers, totalMoveCount);
        } else {
            movePlayerForward(totalMoveCount);
        }
    }

    private void movePlayerForward(int moveCount) throws IOException {
        isMoving = true;
        spaceNumber += moveCount;

        if (spaceNumber >= SPACE_COUNT) {
            spaceNumber %= SPACE_COUNT;
            if (!player.isJailed()) {
                int goMoney = ((StartSpace) map.get(0)).getGoMoney();
                player.getPaid(goMoney);
                sendPlayerToUpdate();
            }
        }
        //TODO: animation move player forward
        sendPlayerToMoveForward(moveCount);
        isMoving = false;
        doSpaceAction(spaceNumber, moveCount);
    }

    private void movePlayerTo(int number) throws IOException {
        isMoving = true;
        spaceNumber = number;
        //TODO: animation warp player to space number
        sendPlayerToMoveTo(number);
        isMoving = false;
        doSpaceAction(spaceNumber, 1);
    }

    private void doSpaceAction(int spaceNumber, int diceNumber) throws IOException {
        Space space = map.get(spaceNumber);
        String action = space.getAction();

        switch (action) {
            case ("draw card"): {
                CardSpace cardSpace = (CardSpace) space;
                String deckType = cardSpace.getType();
                DrawCardObj drawCardObj = new DrawCardObj(player.getID(), deckType);
                ServerMessage serverMessage = new ServerMessage("drawCard", drawCardObj);
                client.sendData(serverMessage);
                break;
            }

            case ("start"): {
                StartSpace startSpace = (StartSpace) space;
                player.getPaid(startSpace.getGoMoney());
                sendPlayerToUpdate();
                break;
            }

            case ("go to jail"): {
                player.jailed();
                break;
            }

            case ("property"): {
                PropertySpace propertySpace = (PropertySpace) space;
                Player owner = propertySpace.getOwner();
                if (owner == null) {
                    //TODO: display UI to let player choose to buy or put up for auction
                    houseBuying.setVisible(true);
                } else if (owner.getID() == player.getID()) {
                    //TODO: display UI to let player choose to buy house if player has enough money
                } else {
                    int rent = propertySpace.getRentPrice();

                    if (propertySpace instanceof UtilitySpace) {
                        rent *= diceNumber;
                    }

//                    if (isBankrupt(rent)) {
//                        sendPlayerIsBankrupt();
//                        break;
//                    }

                    player.pay(rent);
                    sendPlayerToUpdate();
                    GetPaidObj getPaidObj = new GetPaidObj(propertySpace.getOwner().getID(), rent);
                    ServerMessage serverMessage = new ServerMessage("getPaid", getPaidObj);
                    client.sendData(serverMessage);
                }
                break;
            }

            case ("pay tax"): {
                TaxSpace taxSpace = (TaxSpace) space;
                double taxFee = taxSpace.getTaxFee();

//                if (isBankrupt(taxFee)) {
//                    sendPlayerIsBankrupt();
//                    break;
//                }

                player.pay(taxSpace.getTaxFee() * player.getMoney());
                sendPlayerToUpdate();
                break;
            }

            case ("go"): {
                movePlayerTo(((MoveSpace) space).getAmount());
                sendPlayerToUpdate();
                break;
            }

            case ("free parking"):
            default:
                break;
        }
    }

    private void checkBreakJail(int[] diceNumbers, int moveCount) throws IOException {
        if (diceNumbers[0] == diceNumbers[1]) {
            jailTurnCount = 0;
            player.getOutOfJail();
            movePlayerForward(moveCount);
        } else if (jailTurnCount == 2) {
            payJailFine();
            movePlayerForward(moveCount);
        } else {
            jailTurnCount += 1;
        }
    }

    private void payJailFine() throws IOException {
        jailTurnCount = 0;

//        if (isBankrupt(JAIL_FEE)) {
//            sendPlayerIsBankrupt();
//            return;
//        }

        player.pay(JAIL_FEE);
        player.getOutOfJail();
        sendPlayerToUpdate();
    }

    private void useBreakJailCard() throws IOException {
        player.useBreakJailCard();
        sendPlayerToUpdate();
        jailTurnCount = 0;
    }

    private void updateMap(PropertySpace propertySpace) {
        map.set(propertySpace.getNumber(), propertySpace);
    }

    private void sendMapToUpdate(PropertySpace propertySpace) throws IOException {
        ServerMessage serverMessage = new ServerMessage("updateMap", propertySpace);
        client.sendData(serverMessage);
    }

    private void sendPlayerIsBankrupt() throws IOException {
        ServerMessage serverMessage = new ServerMessage("bankrupt", player.getID());
        client.sendData(serverMessage);
    }

    private void startBidTimer() {
        biddingTimer = new Timer();

        biddingTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                ServerMessage serverMessage = new ServerMessage("endAuction", "");
                try {
                    client.sendData(serverMessage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                cancel();
            }
        }, BIDTIMER_DELAY, 100000);
    }

    private boolean isBankrupt(int payingAmount) {
        if (player.getMoney() >= payingAmount) {
            return false;
        }

        if (player.getAsset() < payingAmount) {
            return true;
        }

        return showSellPropertyUI();
    }


    private boolean showSellPropertyUI() {
        //TODO: returns boolean if player decides to bankrupt or did not sell enough property
        return false;
    }


    private void playerBankrupt(int playerID) throws IOException {
        if (playerID == player.getID()) {
            endTurn();
            return;
        }

        for (PlayerObj opponent : opponents) {
            if (playerID == opponent.getID()) {
                opponent = null;
                break;
            }
        }
    }

    private void sendNameToServer() throws IOException {
        ServerMessage serverMessage = new ServerMessage("addName", name);
        client.sendData(serverMessage);
    }

    public ArrayList<Space> getMap() {
        return this.map;
    }
}
