package DiceAnimate;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Dice extends JLabel {
    BufferedImage spritesIdle ,  spritesIdle2 ;
    Animator rollDice ,rollDice2;
    boolean ckA=true;

    int die1 = 0, die2 =0 ;

    public Dice(){
        setBounds(350,200,100,50);
        setVisible(true);
//        setResizable(false);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
    }



    public void roll(int die1, int die2) {
        BufferedImageLoader loader = new BufferedImageLoader() ;
        BufferedImage diceSpriteSheet= null ;

        try {
            diceSpriteSheet = loader.loadImage("/allImage/newDice.png" );
        }catch (IOException ex){
            Logger.getLogger(Dice.class.getName()).log(Level.SEVERE,null,ex);
        }

        SpriteSheet diceSS = new SpriteSheet(diceSpriteSheet);
        SpriteSheet diceSS2 = new SpriteSheet(diceSpriteSheet);

        ArrayList<BufferedImage> sprites =new ArrayList<BufferedImage>();
        ArrayList<BufferedImage> sprites2 =new ArrayList<BufferedImage>();

        sprites.add(diceSS.grabSprite(0, 0, 92,92));//1
        sprites.add(diceSS.grabSprite(92, 0, 92,92));//2
        sprites.add(diceSS.grabSprite(184, 0, 92,92));//3
        sprites.add(diceSS.grabSprite(276, 0, 92,92));
        sprites.add(diceSS.grabSprite(0, 92, 92,92));
        sprites.add(diceSS.grabSprite(92, 92, 92,92));

        switch (die1) {
            case 1: sprites.add(diceSS.grabSprite(184, 92, 92,92));break;
            case 2: sprites.add(diceSS.grabSprite(276, 92, 92,92));break;
            case 3: sprites.add(diceSS.grabSprite(0, 184, 92,92));break;
            case 4: sprites.add(diceSS.grabSprite(92, 184, 92,92));break;
            case 5: sprites.add(diceSS.grabSprite(184, 184, 92,92));break;
            case 6: sprites.add(diceSS.grabSprite(276, 184, 92,92));break;
        }

        sprites2.add(diceSS2.grabSprite(0, 0, 92,92));//1
        sprites2.add(diceSS2.grabSprite(92, 0, 92,92));//2
        sprites2.add(diceSS2.grabSprite(184, 0, 92,92));//3
        sprites2.add(diceSS2.grabSprite(276, 0, 92,92));
        sprites2.add(diceSS2.grabSprite(0, 92, 92,92));
        sprites2.add(diceSS2.grabSprite(92, 92, 92,92));

        switch (die2) {
            case 1: sprites2.add(diceSS2.grabSprite(184, 92, 92,92));break;
            case 2: sprites2.add(diceSS2.grabSprite(276, 92, 92,92));break;
            case 3: sprites2.add(diceSS2.grabSprite(0, 184, 92,92));break;
            case 4: sprites2.add(diceSS2.grabSprite(92, 184, 92,92));break;
            case 5: sprites2.add(diceSS2.grabSprite(184, 184, 92,92));break;
            case 6: sprites2.add(diceSS2.grabSprite(276, 184, 92,92));break;
        }

        rollDice = new Animator(sprites);
        rollDice.setSpeed(100);
        rollDice2 = new Animator(sprites2);
        rollDice2.setSpeed(100);

        rollDice.start();
        rollDice2.start();

    }

    Image dbImage;
    Graphics dbg ;
    public void paint(Graphics g) {
        dbImage = createImage(getWidth(),getHeight());
        setBackground(new Color(223,234,184));
        dbg = dbImage.getGraphics();
        setOpaque(true);
        paintComponents(dbg);
        g.drawImage(dbImage,0,0,null);
    }

    public void paintComponents(Graphics g ){
        setOpaque(true);
        if(rollDice != null ){
            if(rollDice.getCurrentFrame() != 6) {
                rollDice.update(System.currentTimeMillis());
                g.drawImage(rollDice.sprite, 0, 0, 50, 50, null);
            }
            else{
                rollDice.stop();
            }
        }
        if(rollDice2 != null ){
            if(rollDice2.getCurrentFrame() != 6) {
                rollDice2.update(System.currentTimeMillis());
                g.drawImage(rollDice2.sprite,50,0,50,50,null);
            }
            else{
                rollDice2.stop();
            }

        }
        repaint();
    }

}
