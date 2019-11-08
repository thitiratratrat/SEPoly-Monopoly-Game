package allSpritePlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Random;

public class MainNoAnimate extends JFrame {

    // Animator rat, rat2 ,ratJail ;
    // have only ratSprite

    public MainNoAnimate( ){

        allObjectinPage p1 = new allObjectinPage();
        add(p1);

        Timer t = new Timer(300, new ReDraw(p1));
        t.start();

    }

    public static void main(String[] args) {
        MainNoAnimate main = new MainNoAnimate();
        main.setSize(800,600);
        main.setVisible(true);
        main.setResizable(false);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setLocationRelativeTo(null);

    }
}
class ReDraw implements ActionListener {
    static int count = 0;
    static int posX = 369;
    static int posY = 60;
    static int velX = 35;
    static int velY = 25;
    static int box = 0;
    allObjectinPage main;

    ReDraw(allObjectinPage gameScreen ) {
        this.main = gameScreen;
    }

    public void actionPerformed(ActionEvent e) {
        count++;
        System.out.println(box);
        //if(box >=1 && box < 9 ){
        posX += velX;
        posY += velY;
        //}
        if(box >= 9 && box <= 16)
        {
            posX -= velX;
            posY += velY;
        }
        else if(box >16 && box <=24){
            posX += velX;
            posY += velY;
        }
        else if(box > 24 ){
            posX -= velX;
            posY += velY;
        }
        if(box == 33){
            box =1;
        }
        System.out.println("Flag 1: " + posX + " " + posY);
        main.repaint();

        if (count ==8) {
            ((Timer) e.getSource()).stop();
            //box += count;
        }
    }
}
class allObjectinPage extends JPanel{

    BufferedImage sprite, sprite2 , sprite3, map, landmark,spriteR;

    int die1 = 0, die2 =0 ;
    static int resultDice =0 ;

    int positionX = 360  , positionY  = 460 ;

    public allObjectinPage(){
        BufferedImageLoader loader = new BufferedImageLoader() ;
        BufferedImage spriteSheet= null ;
        BufferedImage diceSpriteSheet= null ;
        BufferedImage mapBG = null ;
        BufferedImage landmarkSheet = null ;

        try {
            spriteSheet = loader.loadImage("/ratSprite.png");
            diceSpriteSheet = loader.loadImage("/dice.png" );
            mapBG = loader.loadImage("/board.png");
            landmarkSheet = loader.loadImage("/1.png");

        }catch (IOException ex){
            Logger.getLogger(MainNoAnimate.class.getName()).log(Level.SEVERE,null,ex);
        }
        SpriteSheet ss = new SpriteSheet(spriteSheet);
        SpriteSheet diceSS = new SpriteSheet(diceSpriteSheet);
        SpriteSheet diceSS2 = new SpriteSheet(diceSpriteSheet);
        SpriteSheet mapSS = new SpriteSheet(mapBG);
        SpriteSheet land1 = new SpriteSheet(landmarkSheet);

        Random random = new Random();
        die1  = random.nextInt(6) + 1;
        die2  = random.nextInt(6) + 1;

        resultDice = die1 + die2;

        map = mapSS.grabSprite(0,0, 800,600);
        landmark = land1.grabSprite(0,0,800,600);

        if (die1 == 6) {sprite2 = diceSS.grabSprite(0,0,69,69); }
        else if (die1 == 5) { sprite2 = diceSS.grabSprite(0,69,69,69); }
        else if ( die1 ==1)  { sprite2 = diceSS.grabSprite(0,138,69,69); }
        else if (die1 == 3) { sprite2 = diceSS.grabSprite(69,0,69,69); }
        else if (die1 == 2) { sprite2 = diceSS.grabSprite(69,69,69,69); }
        else if (die1 == 4) { sprite2 = diceSS.grabSprite(69,138,69,69); }

        if (die2 == 6) {sprite3 = diceSS2.grabSprite(0,0,69,69); }
        else if (die2 == 5) { sprite3 = diceSS2.grabSprite(0,69,69,69); }
        else if ( die2 ==1)  { sprite3 = diceSS2.grabSprite(0,138,69,69); }
        else if (die2 == 3) { sprite3 = diceSS2.grabSprite(69,0,69,69); }
        else if (die2 == 2) { sprite3 = diceSS2.grabSprite(69,69,69,69); }
        else if (die2 == 4) { sprite3 = diceSS2.grabSprite(69,138,69,69); }

        spriteR = ss.grabSprite(0, 123, 110,120); //right
        sprite = ss.grabSprite(0, 0, 110,120);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(map,0 ,0, 800,600,null);
        g.drawImage(sprite2,215 ,300, 30,30,null); //1
        g.drawImage(sprite3,250 ,300, 30,30,null); //2
        g.drawImage(landmark,0 ,0, 800,600,null); //2
        //g.drawImage(sprite,360, 460,50,50,null); //sprite's location

        if(ReDraw.posX >= 80 && ReDraw.posY <= 260){
            g.drawImage(spriteR, ReDraw.posX , ReDraw.posY,  50,50 ,null ) ;
        }
        else{
            g.drawImage(sprite, ReDraw.posX , ReDraw.posY,  50,50 ,null ) ;
        }
    }
}
