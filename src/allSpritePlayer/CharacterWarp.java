package allSpritePlayer;

import model.Movable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CharacterWarp extends JPanel {
    DiceAnimate.Animator rollDice ,rollDice2 , ratJail, pUpJail, pDaminJail, maidJail;
}

class MoveForwardTo {
    CharacterWarp allSprite;
    static int side , spaceNum;
    static int posX, posY;

    public MoveForwardTo(Movable player , int spaceNumber ) {
        this.side = spaceNumber /8;
        this.spaceNum = spaceNumber  %8;

        switch (side) {
            case 1:
                posX = 360;
                posY = 460 ;
                posX -= 35*spaceNum; //side1
                posY -= 23*spaceNum; //side1
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
                posX -= 35; //side4
                posY += 25; //side4
                break;

         }

    /*
    public int ckSide() {
        if((posX <= 360 && posX > 80)&&(posY > 276 && posY <= 460)){ return  1;}
        else if((posX < 376 && posX >= 80)&&(posY > 60 && posY <= 276)){ return 2;}
        else if((posX < 672 && posX >= 376)&&(posY < 268 && posY >= 60)){ return 3;}
        else{ return 4;}
    }*/
    }
}
