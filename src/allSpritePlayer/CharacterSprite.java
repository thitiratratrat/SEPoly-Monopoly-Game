package allSpritePlayer;

import model.Movable;

import javax.management.ObjectName;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.PortUnreachableException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CharacterSprite extends JPanel{
    BufferedImage spriteIdleL, spriteIdleR;

    public CharacterSprite(Movable player){
        BufferedImageLoader loader = new BufferedImageLoader();
        BufferedImage spriteSheet = null;
        try{
            switch (player.getID()) {
                case 0: spriteSheet = loader.loadImage("/allImage/ratSprite.png");break;
                case 1: spriteSheet = loader.loadImage("/allImage/pupSprite.png");break;
                case 2: spriteSheet = loader.loadImage("/allImage/PDAMIAN.png");break;
                case 3: spriteSheet = loader.loadImage("/allImage/maidSprite.png");break; }
        }catch (IOException ex) {
            Logger.getLogger(CharacterSprite.class.getName()).log(Level.SEVERE, null, ex);
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
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        //g.drawImage(board, 0, 0, 800, 600, null);
        if ((MoveForward.posX >= 80 && MoveForward.posX < 672 && MoveForward.posY <= 276) ||
                (MoveForward.posY >= 60 && MoveForward.posY <= 268)) {
            g.drawImage(spriteIdleR, MoveForward.posX, MoveForward.posY, 50, 50, null);
        } else {
            g.drawImage(spriteIdleL, MoveForward.posX, MoveForward.posY, 50, 50, null);
        }

    }
}

class MoveForward implements ActionListener {
    CharacterSprite allSprite ;
    static int diceNumber ;
    static int posX , posY;
    int  count ;
    public MoveForward(CharacterSprite allSprite , model.Movable player, int diceNumber){
        this.allSprite = allSprite;
        this.diceNumber = diceNumber;
        this.posX = player.getX();
        this.posY = player.getY();
    }


    public int ckSide() {
        if((posX <= 360 && posX > 80)&&(posY > 276 && posY <= 460)){ return  1;}
        else if((posX < 376 && posX >= 80)&&(posY > 60 && posY <= 276)){ return 2;}
        else if((posX < 672 && posX >= 376)&&(posY < 268 && posY >= 60)){ return 3;}
        else{ return 4;}
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int side = ckSide();
        count++;
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
        System.out.println("Flag 1: " + posX + " " + posY);
        allSprite.repaint();

        if (count == diceNumber) { //จนครั้งที่เดิน
            ((Timer) e.getSource()).stop();
        }
    }
}
