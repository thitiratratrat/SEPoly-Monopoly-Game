package allSpritePlayer;

import model.Movable;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CharacterWarp extends JPanel {
    BufferedImage spriteIdleL,spriteIdleR;
    DiceAnimate.Animator rollDice ,rollDice2 , animateWarp;
    Movable player;
    int posX , posY ;
    int spaceNumber ;
    CharacterWarp(Movable player, int spaceNumber){
        this.player =player;
        this.spaceNumber = spaceNumber;
        this.posX = player.getX();
        this.posY = player.getY();
        BufferedImageLoader loader = new BufferedImageLoader();
        BufferedImage spriteSheet = null , spriteSheetIdle = null;
        try{
            switch (player.getID()){
                case 0 :
                    spriteSheet = loader.loadImage("/allImage/ratSprite22.png");
                    spriteSheetIdle = loader.loadImage("/allImage/ratSprite.png");
                    break;
                case 1 :
                    spriteSheet = loader.loadImage("/allImage//pupSpriteJail.png");
                    spriteSheetIdle = loader.loadImage("/allImage/pupSprite.png");
                    break;
                case 2 :
                    spriteSheet = loader.loadImage("/allImage/PDamianJail.png");
                    spriteSheetIdle = loader.loadImage("/allImage/PDAMIAN.png");
                    break;
                case 3 :
                    spriteSheet = loader.loadImage("/allImage/maidSpriteJail.png");
                    spriteSheetIdle = loader.loadImage("/allImage/maidSprite.png");
                    break;
            }
        }
        catch (IOException ex) {
                Logger.getLogger(CharacterWarp.class.getName()).log(Level.SEVERE, null, ex);
        }

        SpriteSheet playerSS = new SpriteSheet(spriteSheet);
        SpriteSheet playerSSIdle = new SpriteSheet(spriteSheetIdle);
        ArrayList<BufferedImage> spritesJail =new ArrayList<BufferedImage>();
        switch (player.getID()){
            case 0:
                spritesJail.add(playerSS.grabSprite(0, 0, 110,120));
                spritesJail.add(playerSS.grabSprite(108, 0, 110,120));
                spritesJail.add(playerSS.grabSprite(220, 0, 109,120));
                spritesJail.add(playerSS.grabSprite(0, 0, 1,1));
                animateWarp = new DiceAnimate.Animator(spritesJail);
                animateWarp.setSpeed(200);
                animateWarp.start();
                spriteIdleL = playerSSIdle.grabSprite(0, 0, 110, 120);
                spriteIdleR = playerSSIdle.grabSprite(0, 123, 110, 120);
                break;
            case 1:
                spritesJail.add(playerSS.grabSprite(0, 0, 130,242));
                spritesJail.add(playerSS.grabSprite(132, 0, 130,242));
                spritesJail.add(playerSS.grabSprite(261, 0, 129,242));
                spritesJail.add(playerSS.grabSprite(0, 0, 1,1));
                animateWarp = new DiceAnimate.Animator(spritesJail);
                animateWarp.setSpeed(200);
                animateWarp.start();
                spriteIdleL = playerSSIdle.grabSprite(0, 243, 130, 243);
                spriteIdleR = playerSSIdle.grabSprite(0, 0, 130, 243);
                break;

            case 2:
                spritesJail.add(playerSS.grabSprite(0, 0, 127,229));
                spritesJail.add(playerSS.grabSprite(127, 0, 127,229));
                spritesJail.add(playerSS.grabSprite(255, 0, 126,229));
                spritesJail.add(playerSS.grabSprite(0, 0, 1,1));
                animateWarp = new DiceAnimate.Animator(spritesJail);
                animateWarp.setSpeed(200);
                animateWarp.start();
                spriteIdleL = playerSSIdle.grabSprite(0, 0, 127, 230);
                spriteIdleR = playerSSIdle.grabSprite(0, 230, 127, 230);
                break;

            case 3:
                spritesJail.add(playerSS.grabSprite(0, 0, 120,195));
                spritesJail.add(playerSS.grabSprite(124, 0, 120,195));
                spritesJail.add(playerSS.grabSprite(248, 0, 120,195));
                spritesJail.add(playerSS.grabSprite(0, 0, 1,1));
                animateWarp = new DiceAnimate.Animator(spritesJail);
                animateWarp.setSpeed(200);
                animateWarp.start();
                spriteIdleL = playerSSIdle.grabSprite(0, 0, 120, 195);
                spriteIdleR = playerSSIdle.grabSprite(0, 198, 114, 190);
                break;
        }
    }

    public int MoveForwardTo()
    {
        int side ,spaceNum ;
        double sideDouble;
        int posX = 0 , posY = 0 ;
        sideDouble = (double) spaceNumber /8 ;
        side = (int) Math.ceil(sideDouble);
        spaceNum = spaceNumber %8;
        switch (side){
            case 1:
                posX = 360 ;
                posY = 460 ;
                posX -= 35*spaceNum; //side1
                posY -= 23*spaceNum; //side1
                break;
            case 2:
                posX = 80 ;
                posY = 276 ;
                posX += 37*spaceNum; //side2
                posY -= 27*spaceNum; //side2
                break;
            case 3:
                posX = 376 ;
                posY = 60 ;
                posX += 37*spaceNum; //side3
                posY += 26*spaceNum; //side3
                break;
            case 4:
                posX = 672 ;
                posY = 268 ;
                posX -= 35*spaceNum; //side4
                posY += 25*spaceNum; //side4
                break;
        }
        player.setX(posX);
        player.setY(posY);
        return side;
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
        int side = 0 ;
        if(animateWarp != null ){
            if(animateWarp.getCurrentFrame() != 4){
                animateWarp.update(System.currentTimeMillis());
                g.drawImage(animateWarp.sprite,this.posX,this.posY,50,50,null);
            }
            else {
                side = MoveForwardTo();
                if(side==1 || side ==4){
                    g.drawImage(spriteIdleL,getX(),getY(),50,50,null);
                }
                else{
                    g.drawImage(spriteIdleR,getX(),getY(),50,50,null);
                }
            }
        }
        repaint();
    }
}

