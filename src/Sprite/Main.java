package Sprite;

/*
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Random;

public class Main extends JFrame {
    BufferedImage sprite, sprite2 , sprite3, map, landmark;


    Animator rat, rat2 ,ratJail , pdamian ,pdamian2 , pdamianJail , pup , pup2,pUpJail, maid,maid2,maidJail;
    String nameSprite = "ratSprite.png" , name  ;
    int die1 = 0, die2 =0 ;

    public Main(){
        setSize(800,600);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        name = "/"+ nameSprite;
        init(name);
    }

    private void init(String name) {
        BufferedImageLoader loader = new BufferedImageLoader() ;
        BufferedImage spriteSheet= null ;
        BufferedImage diceSpriteSheet= null ;
        BufferedImage mapBG = null ;
        BufferedImage landmarkSheet = null ;
        try {
            spriteSheet = loader.loadImage(name);
            diceSpriteSheet = loader.loadImage("/dice.png" );
            mapBG = loader.loadImage("/board.png");
            landmarkSheet = loader.loadImage("/1.png");

        }catch (IOException ex){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,ex);
        }
        SpriteSheet ss = new SpriteSheet(spriteSheet);
        SpriteSheet diceSS = new SpriteSheet(diceSpriteSheet);
        SpriteSheet diceSS2 = new SpriteSheet(diceSpriteSheet);
        SpriteSheet mapSS = new SpriteSheet(mapBG);
        SpriteSheet land1 = new SpriteSheet(landmarkSheet);

        ArrayList<BufferedImage> sprites =new ArrayList<BufferedImage>();
        ArrayList<BufferedImage> spritesRight =new ArrayList<BufferedImage>();

        Random random = new Random();
        die1  = random.nextInt(6) + 1;
        die2  = random.nextInt(6) + 1;

        map = mapSS.grabSprite(0,0, 800,600);
        landmark = land1.grabSprite(0,0,800,600);



        if(nameSprite == "ratSprite.png")
        {
            sprites.add(ss.grabSprite(0, 0, 110,120)); //เอาสไปร์ทที่ตนแรกของทุกอัน เดินซ้าย
            //sprites.add(ss.grabSprite(108, 0, 110,120));
            //sprites.add(ss.grabSprite(220, 0, 109,120));
            spritesRight.add(ss.grabSprite(0, 123, 110,120)); //เดินขวา
            //spritesRight.add(ss.grabSprite(108, 123, 110,120));
            //spritesRight.add(ss.grabSprite(218, 123, 110,120));

            rat = new Animator(sprites);
            rat.setSpeed(200);
            rat.start();

            rat2 = new Animator(spritesRight);
            rat2.setSpeed(200);
            rat2.start();
        }
        else if(nameSprite == "PDAMIAN.png"){
            sprites.add(ss.grabSprite(0, 0, 127,230));//เอาสไปร์ทที่ตนแรกของทุกอัน เดินซ้าย
            sprites.add(ss.grabSprite(127, 0, 127,230));
            sprites.add(ss.grabSprite(254, 0, 127,230));
            spritesRight.add(ss.grabSprite(0, 230, 127,230));//เอาสไปร์ทที่ตนแรกของทุกอัน เดินซ้าย
            spritesRight.add(ss.grabSprite(127, 230, 127,230));
            spritesRight.add(ss.grabSprite(254, 232, 127,230));

            pdamian = new Animator(sprites);
            pdamian.setSpeed(200);
            pdamian.start();

            pdamian2 = new Animator(spritesRight);
            pdamian2.setSpeed(200);
            pdamian2.start();
        }
        else if(nameSprite == "pupSprite.png"){
            sprites.add(ss.grabSprite(0, 243, 130,243));//เอาสไปร์ทที่ตนแรกของทุกอัน เดินซ้าย
            sprites.add(ss.grabSprite(132, 243, 130,243));
            sprites.add(ss.grabSprite(265, 243, 130,243));
            spritesRight.add(ss.grabSprite(0, 0, 130,243));//เอาสไปร์ทที่ตนแรกของทุกอัน เดินซ้าย
            spritesRight.add(ss.grabSprite(132, 0, 130,243));
            spritesRight.add(ss.grabSprite(265, 0, 130,243));

            pup = new Animator(sprites);
            pup.setSpeed(200);
            pup.start();

            pup2 = new Animator(spritesRight);
            pup2.setSpeed(200);
            pup2.start();
        }
        else if(nameSprite == "maidSprite.png"){
            sprites.add(ss.grabSprite(0, 0, 120,195));//เอาสไปร์ทที่ตนแรกของทุกอัน เดินซ้าย
            sprites.add(ss.grabSprite(115, 0, 118,195));
            sprites.add(ss.grabSprite(216, 0, 124,194));
            spritesRight.add(ss.grabSprite(0, 198, 114,190));//เอาสไปร์ทที่ตนแรกของทุกอัน เดินซ้าย
            spritesRight.add(ss.grabSprite(114, 198, 117,190));
            spritesRight.add(ss.grabSprite(231, 198, 114,190));
            maid = new Animator(sprites);
            maid.setSpeed(200);
            maid.start();
            maid2 = new Animator(spritesRight);
            maid2.setSpeed(200);
            maid2.start();
        }
        else if(nameSprite == "ratSprite22.png"){
            sprites.add(ss.grabSprite(0, 0, 110,120)); //rat warp
            sprites.add(ss.grabSprite(108, 0, 110,120));
            sprites.add(ss.grabSprite(220, 0, 109,120));
            sprites.add(ss.grabSprite(0, 0, 1,1));
            ratJail = new Animator(sprites);
            ratJail.setSpeed(200);
            ratJail.start();
        }
        else if(nameSprite == "pupSpriteJail.png"){
            sprites.add(ss.grabSprite(0, 0, 130,242));
            sprites.add(ss.grabSprite(132, 0, 130,242));
            sprites.add(ss.grabSprite(261, 0, 129,242));
            sprites.add(ss.grabSprite(0, 0, 1,1));
            pUpJail = new Animator(sprites);
            pUpJail.setSpeed(200);
            pUpJail.start();
        }
        else if(nameSprite == "PDamianJail.png"){
            sprites.add(ss.grabSprite(0, 0, 127,229));
            sprites.add(ss.grabSprite(127, 0, 127,229));
            sprites.add(ss.grabSprite(255, 0, 126,229));
            sprites.add(ss.grabSprite(0, 0, 1,1));
            pdamianJail = new Animator(sprites);
            pdamianJail.setSpeed(200);
            pdamianJail.start();
        }
        else if(nameSprite == "maidSpriteJail.png"){
            sprites.add(ss.grabSprite(0, 0, 120,195));
            sprites.add(ss.grabSprite(124, 0, 120,195));
            sprites.add(ss.grabSprite(248, 0, 120,195));
            sprites.add(ss.grabSprite(0, 0, 1,1));
            maidJail = new Animator(sprites);
            maidJail.setSpeed(200);
            maidJail.start();
        }
    }
    Image dbImage;
    Graphics dbg ;
    public void paint(Graphics g)
    {
        dbImage = createImage(getWidth(),getHeight());
        dbg = dbImage.getGraphics();
        paintComponents(dbg);
        g.drawImage(dbImage,0,0,null);
    }

    public void paintComponents(Graphics g ){
        g.drawImage(map,0 ,0, 800,600,null);
        g.drawImage(sprite2,215 ,300, 30,30,null); //1
        g.drawImage(sprite3,250 ,300, 30,30,null); //2
        g.drawImage(landmark,0 ,0, 800,600,null); //2

        if(nameSprite == "ratSprite.png"){

            if(rat != null ){
                rat.update(System.currentTimeMillis());
                g.drawImage(rat.sprite,380,460,50,50,null); //show position in the frame
            }
            /*if(rat2 != null ){
                rat2.update(System.currentTimeMillis());
                g.drawImage(rat2.sprite,200,100,50,50,null);
            }*/
       /* }
        else if(nameSprite == "PDAMIAN.png"){
            if(pdamian != null ){
                pdamian.update(System.currentTimeMillis());
                g.drawImage(pdamian.sprite,100,100,50,90,null);
            }
            if(pdamian2 != null ){
                pdamian2.update(System.currentTimeMillis());
                g.drawImage(pdamian2.sprite,200,100,50,90,null);
            }
        }
        else if(nameSprite == "pupSprite.png"){
            if(pup != null ){
                pup.update(System.currentTimeMillis());
                g.drawImage(pup.sprite,100,100,50,70,null);
            }
            if(pup2 != null ){
                pup2.update(System.currentTimeMillis());
                g.drawImage(pup2.sprite,200,100,50,70,null);
            }
        }
        else if(nameSprite == "maidSprite.png"){
            if(maid != null ){
                maid.update(System.currentTimeMillis());
                g.drawImage(maid.sprite,100,100,48,70,null);
            }
            if(maid2 != null ){
                maid2.update(System.currentTimeMillis());
                g.drawImage(maid2.sprite,200,100,48,70,null);
            }
        }
        else if(nameSprite == "ratSprite22.png"){
            if(ratJail != null ){
                ratJail.update(System.currentTimeMillis());
                g.drawImage(ratJail.sprite,100,100,50,50,null);
            }
        }
        else if(nameSprite == "pupSpriteJail.png"){
            if(pUpJail != null ){
                pUpJail.update(System.currentTimeMillis());
                g.drawImage(pUpJail.sprite,100,100,50,70,null);
            }
        }
        else if(nameSprite == "PDamianJail.png"){
            if(pdamianJail != null ){
                pdamianJail.update(System.currentTimeMillis());
                g.drawImage(pdamianJail.sprite,100,100,50,90,null);
            }
        }
        else if(nameSprite == "maidSpriteJail.png"){
            if(maidJail != null ){
                maidJail.update(System.currentTimeMillis());
                g.drawImage(maidJail.sprite,100,100,48,70,null);
            }
        }
        repaint();
    }



}

*/