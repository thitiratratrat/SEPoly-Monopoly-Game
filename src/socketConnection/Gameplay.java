package socketConnection;

import DiceAnimate.Dice;
import allSpritePlayer.BufferedImageLoader;
import allSpritePlayer.Play;
import allSpritePlayer.SpriteSheet;
import model.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Timer;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Movable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gameplay extends javax.swing.JFrame {
    private String name;
    private Client client;
    private Player player;
    private ArrayList<PlayerObj> opponents;
    private ArrayList<Space> map;
    //private String address = "25.30.143.112";
    private String address = "127.0.0.1";
    //private String address = "161.246.144.120";
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
    final private int JAIL_FEE = 200000;
    private final int MAX_PLAYERS = 4;
    private ArrayList<CharacterSprite> displayPlayer = new ArrayList<>(MAX_PLAYERS);

    //character

    //-----------------------------------------------------------------
    //------------------ F O R U I ------------------------------------
    //-----------------------------------------------------------------
    private int price;

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
    public static javax.swing.JPanel gameplay;
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

    //for landmark buying
    private javax.swing.JLabel landmarkBuying;
    private javax.swing.JLabel buyBtn2;
    private javax.swing.JLabel cancelBtn2;
    private javax.swing.JLabel title;
    private javax.swing.JLabel text3;
    private javax.swing.JLabel landmarkImg;

    //land buying
    private javax.swing.JLabel landTitle;
    private javax.swing.JLabel text4;
    private javax.swing.JLabel cancelBtn3;
    private javax.swing.JLabel buyBtn3;
    private javax.swing.JLabel landBuying;

    //dice
    private Dice dice;

    //jail  break
    private javax.swing.JLabel stayBtn;
    private javax.swing.JLabel useBtn;
    private javax.swing.JLabel payBtn;
    private javax.swing.JLabel jailBreak;

    //auction
    private javax.swing.JLabel title2;
    private javax.swing.JLabel image2;
    private javax.swing.JLabel highestBid;
    private javax.swing.JLabel timeLeft;
    private javax.swing.JLabel bidBtn;
    private javax.swing.JLabel passBtn;
    private javax.swing.JTextField bidPlace;
    private javax.swing.JLabel auction;

    //house-landmark
    private javax.swing.JLabel icBuilding;
    private javax.swing.JLabel lCanteen;
    private javax.swing.JLabel yellowBin;
    private javax.swing.JLabel blueBin;
    private javax.swing.JLabel redBin;
    private javax.swing.JLabel floorEight;
    private javax.swing.JLabel ic16;
    private javax.swing.JLabel ic01;
    private javax.swing.JLabel ic02;
    private javax.swing.JLabel ic03;
    private javax.swing.JLabel market;
    private javax.swing.JLabel seven;
    private javax.swing.JLabel ptec;
    private javax.swing.JLabel alumniRes;
    private javax.swing.JLabel alumniCafe;
    private javax.swing.JLabel ic06;
    private javax.swing.JLabel ic04;
    private javax.swing.JLabel fireEscape;
    private javax.swing.JLabel waterCooler;
    private javax.swing.JLabel floorSix;

    private ArrayList<JLabel> estate;


    public Gameplay() {
        initUI();
    }

    public void connectToServer() throws IOException {
        this.startClientConnection();
    }

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

        // L A N D    B U Y I N G
        landTitle = new javax.swing.JLabel("", SwingConstants.CENTER);
        landTitle.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 15));
        landTitle.setBounds(308, 160, 187, 34);
        text4 = new javax.swing.JLabel("", SwingConstants.CENTER);
        text4.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 15));
        text4.setBounds(308, 210, 187, 30);
        buyBtn3 = new javax.swing.JLabel(new javax.swing.ImageIcon("src\\allImage\\Asset 21.png"));
        buyBtn3.setBounds(416, 248, 100, 39);
        cancelBtn3 = new javax.swing.JLabel(new javax.swing.ImageIcon("src\\allImage\\Asset 22.png"));
        cancelBtn3.setBounds(295, 248, 100, 39);
        landBuying = new javax.swing.JLabel(new javax.swing.ImageIcon("src\\allImage\\landBuying.png"));
        landBuying.setBounds(0, 0, 800, 600);

        landBuying.add(landTitle);
        landBuying.add(text4);
        landBuying.add(buyBtn3);
        landBuying.add(cancelBtn3);
        landBuying.setVisible(false);

        // H O U S E  B U Y I N G  P O P U P
        cancelBtn = new javax.swing.JLabel(new javax.swing.ImageIcon("src\\allImage\\Asset 22.png"));
        cancelBtn.setBounds(290, 390, 90, 39);
        totalPrice = new javax.swing.JLabel();
        totalPrice.setFont(new java.awt.Font("Tahoma", 1, 16));
        totalPrice.setBounds(350, 354, 210, 20);
        buyBtn = new javax.swing.JLabel(new javax.swing.ImageIcon("src\\allImage\\Asset 21.png"));
        buyBtn.setBounds(430, 390, 100, 39);
        threeHouseCheck = new javax.swing.JCheckBox();
        threeHouseCheck.setBounds(500, 310, 25, 25);
        twoHouseCheck = new javax.swing.JCheckBox();
        twoHouseCheck.setBounds(390, 310, 25, 25);
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

        // L A N D  M A R K  B U Y I N G
        title = new javax.swing.JLabel("", SwingConstants.CENTER);
        title.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 15));
        title.setBounds(308, 160, 187, 34);
        landmarkImg = new javax.swing.JLabel();
        text3 = new javax.swing.JLabel("", SwingConstants.CENTER);
        text3.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 15));
        text3.setBounds(305, 320, 200, 100);
        cancelBtn2 = new javax.swing.JLabel(new javax.swing.ImageIcon("src\\allImage\\Asset 22.png"));
        cancelBtn2.setBounds(302, 357, 100, 39);
        buyBtn2 = new javax.swing.JLabel(new javax.swing.ImageIcon("src\\allImage\\Asset 21.png"));
        buyBtn2.setBounds(407, 357, 100, 39);
        landmarkBuying = new javax.swing.JLabel(new javax.swing.ImageIcon("src\\allImage\\landmarkBuying.png"));
        landmarkBuying.setBounds(0, 0, 800, 600);

        landmarkBuying.add(title);
        landmarkBuying.add(landmarkImg);
        landmarkBuying.add(text3);
        landmarkBuying.add(buyBtn2);
        landmarkBuying.add(cancelBtn2);
        landmarkBuying.setVisible(false);


        // S H O W  C A R D
        card = new javax.swing.JLabel();
        card.setBounds(0, 0, 800, 600);
        card.setVisible(false);

        // J A I L  B R E A K
        stayBtn = new javax.swing.JLabel(new javax.swing.ImageIcon("src\\allImage\\stayBtn.png"));
        stayBtn.setBounds(471, 238, 36, 16);
        useBtn = new javax.swing.JLabel(new javax.swing.ImageIcon("src\\allImage\\useBtn.png"));
        useBtn.setBounds(471, 261, 36, 16);
        payBtn = new javax.swing.JLabel(new javax.swing.ImageIcon("src\\allImage\\payBtn.png"));
        payBtn.setBounds(471, 212, 36, 16);
        jailBreak = new javax.swing.JLabel(new javax.swing.ImageIcon("src\\allImage\\jailBreak.png"));
        jailBreak.add(stayBtn);
        jailBreak.add(useBtn);
        jailBreak.add(payBtn);
        jailBreak.setVisible(false);

        // A U C T I O N
        title2 = new javax.swing.JLabel("", SwingConstants.CENTER);
        title2.setFont(new java.awt.Font("Microsoft PhagsPa", 0, 15));
        title2.setBounds(308, 160, 187, 34);
        image2 = new javax.swing.JLabel();
        highestBid = new javax.swing.JLabel();
        highestBid.setBounds(291, 314, 107, 28);
        timeLeft = new javax.swing.JLabel();
        timeLeft.setBounds(402, 314, 107, 28);
        bidBtn = new javax.swing.JLabel();
        bidBtn.setBounds(299, 423, 82, 32);
        passBtn = new javax.swing.JLabel();
        passBtn.setBounds(417, 423, 82, 32);
        bidPlace = new javax.swing.JTextField();
        bidPlace.setBounds(303, 369, 198, 27);
        bidPlace.setFont(new java.awt.Font("Microsoft PhagsPa", 2, 15));
        bidPlace.setText("Place your bid here");
        auction = new javax.swing.JLabel(new javax.swing.ImageIcon("src\\allImage\\auction.png"));
        auction.setBounds(0, 0, 800, 600);
        auction.setVisible(false);

        //Dice
        dice = new Dice();
        dice.setVisible(false);

        //house-landmark
        icBuilding = new javax.swing.JLabel();
        lCanteen = new javax.swing.JLabel();
        yellowBin = new javax.swing.JLabel();
        blueBin = new javax.swing.JLabel();
        redBin = new javax.swing.JLabel();
        floorEight = new javax.swing.JLabel();
        ic16 = new javax.swing.JLabel();
        ic01 = new javax.swing.JLabel();
        ic02 = new javax.swing.JLabel();
        ic03 = new javax.swing.JLabel();
        market = new javax.swing.JLabel();
        seven = new javax.swing.JLabel();
        ptec = new javax.swing.JLabel();
        alumniRes = new javax.swing.JLabel();
        alumniCafe = new javax.swing.JLabel();
        ic06 = new javax.swing.JLabel();
        ic04 = new javax.swing.JLabel();
        fireEscape = new javax.swing.JLabel();
        waterCooler = new javax.swing.JLabel();
        floorSix = new javax.swing.JLabel();

/*        estate.add(null);
        estate.add(icBuilding);
        estate.add(null);
        estate.add(lCanteen);
        estate.add(null);
        estate.add(yellowBin);
        estate.add(blueBin);
        estate.add(redBin);
        estate.add(null);
        estate.add(null);
        estate.add(floorEight);
        estate.add(ic16);
        estate.add(null);
        estate.add(ic01);*/


        gameplay.setLayout(null);

        gameplay.add(spaceInfo);
        gameplay.add(card);
        gameplay.add(houseBuying);
        gameplay.add(titleDeedInfo);
        gameplay.add(landmarkBuying);
        gameplay.add(landBuying);
        gameplay.add(jailBreak);
        gameplay.add(auction);

        auction.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        landBuying.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        spaceInfo.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        card.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        houseBuying.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        titleDeedInfo.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        landmarkBuying.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jailBreak.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        gameplay.add(rollBtn);
        rollBtn.setVerticalAlignment(SwingConstants.CENTER);


        gameplay.add(namePlayer1);
        gameplay.add(namePlayer2);
        gameplay.add(namePlayer3);
        gameplay.add(namePlayer4);
        gameplay.add(moneyPlayer1);
        gameplay.add(moneyPlayer2);
        gameplay.add(moneyPlayer3);
        gameplay.add(moneyPlayer4);
        gameplay.add(dice);

        dice.setVerticalAlignment(SwingConstants.BOTTOM);
        //dice.setIcon(new ImageIcon("\\src\\allImage\\finaljingjing_board.png"));
        dice.setBackground(new Color(223, 234, 184));
        dice.setBounds(343, 185, 100, 50);
        //dice.setBackground(new Color(0x0));
        board.setVerticalAlignment(SwingConstants.BOTTOM);


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
                hideLabel(evt, titleDeedInfo);
            }
        });

        board.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                boardMousePressed(evt);
            }
        });

        spaceInfo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                hideLabel(evt, spaceInfo);
            }
        });

        card.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cardMousePressed(evt);
            }
        });

        // house buy
        cancelBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                hideLabel(evt, houseBuying);
            }
        });

        buyBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                try {
                    buyHouseMousePressed(evt);
                } catch (IOException e) {
                    e.printStackTrace();
                }
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


        //land buy
        buyBtn3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                try {
                    buyLandMousePressed(evt);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        cancelBtn3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                hideLabel(evt, landBuying);
            }
        });

        // landmark buy
        cancelBtn2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                hideLabel(evt, landmarkBuying);
            }
        });

        buyBtn2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                try {
                    buyLandmarkMousePressed(evt);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        // dice
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
        }
        connectToServer();
        startBtn.setEnabled(false);
        name = username.getText();
        start();
        sendNameToServer();
    }


    //******************************************************************************
    //******************************************************************************
    //---------------------G A M E P L A Y------------------------------------------

    // hide Label
    private void hideLabel(java.awt.event.MouseEvent evt, JLabel label) {
        label.setVisible(false);
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
                setImage(image, index);
                titleDeedInfo.setVisible(true);
                dice.setVisible(false);
            } else {
                spaceInfo.setIcon(new ImageIcon((map.get(index)).getImage()));
                spaceInfo.setVisible(true);
            }
        }
    }

    //set img for show
    private void setImage(JLabel image, int index) {
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
    }

    // land buying
    private void showLandBuying(PropertySpace space) {
        landTitle.setText(space.getName());
        text4.setText("Price : " + intToString(space.getPrice()));
        landBuying.setVisible(true);
    }

    private void buyLandMousePressed(java.awt.event.MouseEvent evt) throws IOException {
        PropertySpace space = (PropertySpace) map.get(spaceNumber);
        buy(space);
        landBuying.setVisible(false);
    }

    // house buying
    private void showHouseBuying(EstateSpace space) {
        int houseCount = space.getHouseCount();
        int money = player.getMoney();
        price = space.getHousePrice();
        int temp = price;
        oneHouseCheck.setEnabled(false);
        twoHouseCheck.setEnabled(false);
        threeHouseCheck.setEnabled(false);
        oneHouseCheck.setSelected(false);
        twoHouseCheck.setSelected(false);
        threeHouseCheck.setSelected(false);
        for (int i = houseCount; i <= 2; i++) {
            if (i == 0 && money >= temp) {
                oneHouseCheck.setEnabled(true);
                temp += price;
            }
            if (i == 1 && money >= temp) {
                twoHouseCheck.setEnabled(true);
                temp += price;
            }
            if (i == 2 && money >= temp) {
                threeHouseCheck.setEnabled(true);
                temp += price;
            }
        }
        if (oneHouseCheck.isEnabled() || twoHouseCheck.isEnabled() || threeHouseCheck.isEnabled()) {
            houseBuying.setVisible(true);
        }

    }

    private void oneHouseCheckActionPerformed(java.awt.event.ActionEvent evt) {
        if (oneHouseCheck.isSelected())
            totalPrice.setText(intToString(((EstateSpace) map.get(spaceNumber)).getHousePrice()));
        else {
            twoHouseCheck.setSelected(false);
            threeHouseCheck.setSelected(false);
            totalPrice.setText("");
        }
    }

    private void twoHouseCheckActionPerformed(java.awt.event.ActionEvent evt) {
        price = ((EstateSpace) map.get(spaceNumber)).getHousePrice();
        if (twoHouseCheck.isSelected() && !oneHouseCheck.isEnabled())
            totalPrice.setText(intToString(price));
        else if (twoHouseCheck.isSelected() && oneHouseCheck.isEnabled()) {
            oneHouseCheck.setSelected(true);
            totalPrice.setText(intToString(price * 2));
        } else {
            threeHouseCheck.setSelected(false);
            if (oneHouseCheck.isSelected())
                totalPrice.setText(intToString(price));
            else
                totalPrice.setText("");
        }

    }

    private void threeHouseCheckActionPerformed(java.awt.event.ActionEvent evt) {
        price = ((EstateSpace) map.get(spaceNumber)).getHousePrice();
        if (threeHouseCheck.isSelected() && !oneHouseCheck.isEnabled() && !twoHouseCheck.isEnabled())
            totalPrice.setText(intToString(price));
        else if (threeHouseCheck.isSelected() && !oneHouseCheck.isEnabled() && twoHouseCheck.isEnabled()) {
            twoHouseCheck.setSelected(true);
            totalPrice.setText(intToString(price * 2));
        } else if (threeHouseCheck.isSelected() && oneHouseCheck.isEnabled() && twoHouseCheck.isEnabled()) {
            oneHouseCheck.setSelected(true);
            twoHouseCheck.setSelected(true);
            totalPrice.setText(intToString(price * 3));
        } else {
            if (oneHouseCheck.isSelected() && twoHouseCheck.isSelected())
                totalPrice.setText(intToString(price * 2));
            else if (oneHouseCheck.isSelected())
                totalPrice.setText(intToString(price));
            else if (!oneHouseCheck.isEnabled() && twoHouseCheck.isSelected())
                totalPrice.setText(intToString(price));
            else
                totalPrice.setText("");
        }
    }

    private void buyHouseMousePressed(java.awt.event.MouseEvent evt) throws IOException {
        buyHouse((EstateSpace) map.get(spaceNumber));
        houseBuying.setVisible(false);
    }


    //landmark buying
    private void showLandmarkBuying(EstateSpace space) {
        title.setText(space.getName());
        setImage(landmarkImg, map.indexOf(space));
        text3.setText("Landmark Price : " + intToString(space.getLandmarkPrice()));
        landmarkBuying.setVisible(true);
    }

    private void buyLandmarkMousePressed(java.awt.event.MouseEvent evt) throws IOException {
        buyLandmark((EstateSpace) map.get(spaceNumber));
        landBuying.setVisible(false);
    }

    private void cardMousePressed(java.awt.event.MouseEvent evt) {
        card.setVisible(false);
    }

    private void rollBtnMousePressed(java.awt.event.MouseEvent evt) throws IOException {
        if (!isTurn) {
            return;
        }
        rollDice();
        rollBtn.setEnabled(false);
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
                            //start();
                            break;
                        }

                        case ("startTurn"): {
                            isTurn = true;
                            rollBtn.setEnabled(true);
                            //walk
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

                        case ("updatePlayer"): {
                            Player temp = (Player) serverMessage.getData();
                            player.setMoney(temp.getMoney());
                            System.out.println("player ID from update: " + player.getID());
                            setMoney(player.getID(), player.getMoney());
                            System.out.println("THISONE : " + player.getID());
                            System.out.println("player ID: " + player.getID());
//                            javax.swing.Timer t = new javax.swing.Timer(300, new MoveForward(displayPlayer.get(player.getID()), player, diceNum));
//                            t.start();
                            break;
                        }

                        case ("initPlayer"): {

                            player = (Player) serverMessage.getData();
                            setMoney(player.getID(), player.getMoney());
                            setMoney(player.getID(), player.getMoney());
                            break;
                        }

                        case ("initOpponents"): {
                            opponents = (ArrayList<PlayerObj>) serverMessage.getData();
                            initCharacterSprites();
                            break;
                        }

                        case ("updateOpponent"): {
                            PlayerObj playerObj = (PlayerObj) serverMessage.getData();
                            updateOpponent(playerObj);
                            setMoney(playerObj.getID(), playerObj.getMoney());
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
                            //System.out.println("print Map: "+ map.get(spaceNumber));
                            movePlayerTo(JAIL_SPACE_NUMBER);
                            break;
                        }

                        case ("updateDice"): {
                            int[] diceNumbers = (int[]) serverMessage.getData();
                            dice.roll(diceNumbers[0], diceNumbers[1]);
                            dice.setVisible(true);
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
                            Movable data = moveObj.getPlayer();
                            int moveCount = moveObj.getMoveNumber();
                            PlayerObj opponent = getOpponent(data.getID());
                            //TODO: animate opponent id forward
                            //System.out.print("opponent : ");
                            //System.out.println(opponent.getX() + "    " + opponent.getY());
                            javax.swing.Timer t2 = new javax.swing.Timer(300, new MoveForward(displayPlayer.get(opponent.getID()), opponent , moveCount
                            ));
                            t2.start();
                            System.out.println(opponent.getX() + "    " + opponent.getY());
//                            sendPlayerToUpdate();

                            container.repaint();
                            break;
                        }

                        case ("moveOpponentTo"): {
                            MoveAnimateObj moveObj = (MoveAnimateObj) serverMessage.getData();
                            //TODO: animate player opponent to specific position

                            movePlayerTo(moveObj.getMoveNumber());
                            container.repaint();
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
        rollBtn.setEnabled(false);
        dice.setVisible(false);
        ServerMessage serverMessage = new ServerMessage("endTurn", player.getID());
        client.sendData(serverMessage);

    }

    private void updateOpponent(PlayerObj playerObj) {
        for (PlayerObj opponent : opponents) {
            if (opponent.getID() == playerObj.getID()) {
                opponent.setX(playerObj.getX());
                opponent.setY(playerObj.getY());
                opponent.setMoney(playerObj.getMoney());
                break;
            }
        }

        setMoney(playerObj.getID(), playerObj.getMoney());

        //TODO: animate opponent to move to that position
        //888888
        gameplay.repaint();


    }

    private void buy(PropertySpace propertySpace) throws IOException {
        player.buy(propertySpace);
        sendPlayerToUpdate();
        sendMapToUpdate(propertySpace);

        if (propertySpace instanceof EstateSpace) {
            if (player.getMoney() >= ((EstateSpace) propertySpace).getHousePrice()) {
                showHouseBuying(((EstateSpace) propertySpace));
            }
        }
    }

    private void buyHouse(EstateSpace estateSpace) throws IOException {
        player.buyHouse(estateSpace, price / estateSpace.getHousePrice());
        sendMapToUpdate(estateSpace);
        sendPlayerToUpdate();
        //TODO: animation build house
    }

    private void buyLandmark(EstateSpace estateSpace) throws IOException {
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

    private PlayerObj getOpponent(int playerID) {
        for (PlayerObj opponent : opponents) {
            if (opponent.getID() == playerID) {
                return opponent;
            }
        }
        return null;
    }

    private void sendPlayerToUpdate() throws IOException {
        ServerMessage serverMessage = new ServerMessage("updatePlayer", player);
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
        System.out.println("dice number: " + totalMoveCount);
        //TODO: display UI dice roll
        if (player.isJailed()) {
            checkBreakJail(diceNumbers, totalMoveCount);
        } else {
            System.out.println("moving");
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

        sendPlayerToMoveForward(moveCount);
        //TODO: animation move player forward
       // System.out.println("move animation");
        javax.swing.Timer t = new javax.swing.Timer(300, new MoveForward(displayPlayer.get(player.getID()), player, moveCount));
        //System.out.println("move count from rolling: " + moveCount);
        t.start();

        isMoving = false;
        doSpaceAction(spaceNumber, moveCount);
    }

    private void movePlayerTo(int number) throws IOException {
        isMoving = true;
        spaceNumber = number;

        sendPlayerToMoveTo(number);
        //TODO: animation warp player to space number
        MoveForwardTo(spaceNumber);

        //System.out.println("Player move: "+ spaceNumber);
        //new MoveForwardTo(displayPlayer.get(player.getID()),player,number);

        isMoving = false;
        doSpaceAction(spaceNumber, 1);
    }

    private void doSpaceAction(int spaceNumber, int diceNumber) throws IOException {
        Space space = map.get(spaceNumber);
        String action = space.getAction();

        switch (action) {
            case ("draw card"): {
                endTurn();
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
                    if (player.getMoney() >= propertySpace.getPrice())
                        showLandBuying(propertySpace);
                    else
                        endTurn();
                } else if (owner.getID() == player.getID()) {
                    if (propertySpace instanceof EstateSpace) {
                        if (((EstateSpace) propertySpace).getHouseCount() < 3) {
                            if (player.getMoney() >= ((EstateSpace) propertySpace).getHousePrice()) {
                                showHouseBuying((EstateSpace) propertySpace);
                            }
                        } else {
                            if (player.getMoney() >= ((EstateSpace) propertySpace).getLandmarkPrice()) {
                                showLandmarkBuying((EstateSpace) propertySpace);
                            }
                        }
                    }
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
                    endTurn();
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
                endTurn();
                break;
            }

            case ("go"): {
                int moveNumber = ((MoveSpace) space).getAmount();
                movePlayerTo(moveNumber);
                sendPlayerToMoveTo(moveNumber);

                endTurn();
                break;
            }

            case ("stop"): {
                player.jailed();
                endTurn();
                break;
            }

            case ("free parking"):
                endTurn();
                break;
            default:
                break;
        }
        endTurn();

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
        endTurn();
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

    private void initCharacterSprites() {
        for (int i = 0; i < MAX_PLAYERS; i++) {
            System.out.print("i");
            displayPlayer.add(new CharacterSprite(player));
        }
        System.out.println("");
        System.out.println("display player length: " + displayPlayer.size());
        CharacterSprite sprite = new CharacterSprite(player);
        sprite.setBounds(360, 460, 50, 50);
        sprite.setVisible(true);
        gameplay.add(sprite);
        setMoney(player.getID(), player.getMoney());
        displayPlayer.set(player.getID(), sprite);

        for (PlayerObj opponent : opponents) {
            //System.out.println("test");
            sprite = new CharacterSprite(opponent);
            sprite.setBounds(360, 460, 50, 50);
            sprite.setVisible(true);
            gameplay.add(sprite);
            setMoney(opponent.getID(), opponent.getMoney());
            displayPlayer.set(opponent.getID(), sprite);
        }
        // System.out.println("test2");
        gameplay.add(board);
        //board.setVisible(false);
    }

    public void MoveForwardTo(int spaceNumber)
    {
        int side, spaceNum;
        double sideDouble;
        int posX = 0, posY = 0;
        System.out.println("space: "+ spaceNumber);
        sideDouble = (double) spaceNumber / 8;
        side = (int) Math.ceil(sideDouble);
        spaceNum = spaceNumber % 8;
        if(spaceNum == 0 ){
            spaceNum= 8 ;
        }
        switch (side)
        {
            case 1:
                posX = 360;
                posY = 460;
                posX -= 35 * spaceNum; //side1
                posY -= 23 * spaceNum; //side1

                break;
            case 2:
                posX = 80;
                posY = 276;
                posX += 37 * spaceNum; //side2
                posY -= 27 * spaceNum; //side2

                break;
            case 3:
                posX = 376;
                posY = 60;
                posX += 37 * spaceNum; //side3
                posY += 26 * spaceNum; //side3
                break;

            case 4:
                posX = 672;
                posY = 268;
                posX -= 35 * spaceNum; //side4
                posY += 25 * spaceNum; //side4

                break;
        }
        // System.out.println("change the position");
        System.out.println("PosX : "+ posX + "POSY: "+ posY);
        player.setX(posX);
        player.setY(posY);
        System.out.println("Testttt : " + player.getX() + "    " + player.getY());
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


}


class CharacterSprite extends JLabel
{
    BufferedImage spriteIdleL, spriteIdleR;
    Movable player;

    public CharacterSprite(Movable player) {
        this.player = player;
        BufferedImageLoader loader = new BufferedImageLoader();
        BufferedImage spriteSheet = null;

        setVisible(true);
        try {
            System.out.println("Player sprite: " + player.getID());
            switch (player.getID()) {
                case 0:
                    spriteSheet = loader.loadImage("/allImage/ratSprite.png");
                    break;
                case 1:
                    spriteSheet = loader.loadImage("/allImage/pupSprite.png");
                    break;
                case 2:
                    spriteSheet = loader.loadImage("/allImage/PDAMIAN.png");
                    break;
                case 3:
                    spriteSheet = loader.loadImage("/allImage/maidSprite.png");
                    break;
            }
        } catch (IOException ex) {
            Logger.getLogger(socketConnection.CharacterSprite.class.getName()).log(Level.SEVERE, null, ex);
        }
        SpriteSheet playerSS = new SpriteSheet(spriteSheet);
        switch (player.getID()) {
            case 0:
                spriteIdleL = playerSS.grabSprite(0, 0, 110, 120);
                spriteIdleR = playerSS.grabSprite(0, 123, 110, 120);
                break;
            case 1:
                spriteIdleL = playerSS.grabSprite(0, 243, 130, 243);
                spriteIdleR = playerSS.grabSprite(0, 0, 130, 243);
                break;
            case 2:
                spriteIdleL = playerSS.grabSprite(0, 0, 127, 230);
                spriteIdleR = playerSS.grabSprite(0, 230, 127, 230);
                break;
            case 3:
                spriteIdleL = playerSS.grabSprite(0, 0, 120, 195);
                spriteIdleR = playerSS.grabSprite(0, 198, 114, 190);
                break;
        }

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        /*if(){
            g.drawImage(spriteIdleL, 360, 460, 50, 50, null);
        }*/
        // System.out.println("isus");
        if ((player.getX() >= 80 && player.getX() < 672 && player.getY() <= 276) ||
                (player.getX() >= 60 && player.getY() <= 268)) {
            g.drawImage(spriteIdleR, 0, 0, 50, 50, null);
        } else {
            g.drawImage(spriteIdleL, 0, 0, 50, 50, null);
        }
        setBounds(player.getX(), player.getY(), 50, 50);
    }
}

class MoveForward implements ActionListener {
    socketConnection.CharacterSprite allSprite;
    static int diceNumber;
    static int posX, posY;
    Movable player;
    int count = 0;

    public MoveForward(socketConnection.CharacterSprite allSprite, Movable player, int diceNumber) {
        this.allSprite = allSprite;
        this.player = player;
        this.diceNumber = diceNumber;

    }

    public int ckSide() {
        if ((player.getX() <= 360 && player.getX() > 80) && (player.getY() > 276 && player.getY() <= 460)) {
            return 1;
        } else if ((player.getX() < 376 && player.getX() >= 80) && (player.getY() > 60 && player.getY() <= 276)) {
            return 2;
        } else if ((player.getX() < 672 && player.getX() >= 376) && (player.getY() < 268 && player.getY() >= 60)) {
            return 3;
        } else {
            return 4;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int side = ckSide();
        count++;
        //System.out.println(player.getX() + "     " + player.getY());
        posX = player.getX();
        posY = player.getY();
        if (diceNumber == 0) {
            //allSprite.repaint();
            posX += 0;
            posY += 0;
            count = 0;
        }

        switch (side) {
            case 1:
                posX -= 35; //side1
                posY -= 23; //side1
                break;
            case 2:
                posX += 37; //side2
                posY -= 27; //side2
                break;
            case 3:
                posX += 37; //side3
                posY += 26; //side3
                break;
            case 4:
                if (posX == 427 && posY == 443) {
                    posX -= 67;
                    posY += 17;
                } else {
                    posX -= 35; //side4
                    posY += 25; //side4
                }
                break;
        }
        //System.out.println("dice num in action performed: " + diceNumber);

        player.setX(posX);
        player.setY(posY);
        //System.out.println(player.getX() + "    " + player.getY());
        allSprite.validate();
        allSprite.repaint();
        (allSprite.getParent().getParent()).validate();
        (allSprite.getParent().getParent()).repaint();

        if (count == diceNumber) { //จนครั้งที่เดิน
            //System.out.println("stop timer!");
            ((javax.swing.Timer) e.getSource()).stop();
            count = 0;
        }
    }
}
