package DiceAnimate;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Random;

public class Main extends JFrame {
    BufferedImage spritesIdle ,  spritesIdle2 ;
    Animator rollDice ,rollDice2;
    boolean ckA=true;

    int die1 = 0, die2 =0 ;




    public Main(){
        setSize(800,600);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        init();
    }

    private void init() {
        BufferedImageLoader loader = new BufferedImageLoader() ;
        BufferedImage diceSpriteSheet= null ;

        try {
            diceSpriteSheet = loader.loadImage("/allImage/newDice.png" );
        }catch (IOException ex){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,ex);
        }

        SpriteSheet diceSS = new SpriteSheet(diceSpriteSheet);
        SpriteSheet diceSS2 = new SpriteSheet(diceSpriteSheet);
        SpriteSheet dice = new SpriteSheet(diceSpriteSheet);

        ArrayList<BufferedImage> sprites =new ArrayList<BufferedImage>();
        ArrayList<BufferedImage> sprites2 =new ArrayList<BufferedImage>();



        Random random = new Random();
        die1  = random.nextInt(6) + 1;
        die2  = random.nextInt(6) + 1;
        System.out.println(die1 + " " + die2);

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

      /*  switch (die1) {
            case 1: spritesIdle = diceSS.grabSprite(184, 92, 92,92);break;
            case 2: spritesIdle = diceSS.grabSprite(276, 92, 92,92);break;
            case 3:  spritesIdle = diceSS.grabSprite(0, 184, 92,92);break;
            case 4: spritesIdle = diceSS.grabSprite(92, 184, 92,92);break;
            case 5: spritesIdle = diceSS.grabSprite(184, 184, 92,92);break;
            case 6: spritesIdle = diceSS.grabSprite(276, 184, 92,92);break;
        }
*/
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
       /* switch (die2) {
            case 1: spritesIdle2 = diceSS.grabSprite(184, 92, 92,92);break;
            case 2: spritesIdle2 = diceSS.grabSprite(276, 92, 92,92);break;
            case 3:  spritesIdle2 = diceSS.grabSprite(0, 184, 92,92);break;
            case 4: spritesIdle2 = diceSS.grabSprite(92, 184, 92,92);break;
            case 5: spritesIdle2 = diceSS.grabSprite(184, 184, 92,92);break;
            case 6: spritesIdle2 = diceSS.grabSprite(276, 184, 92,92);break;
        }
*/
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
        dbg = dbImage.getGraphics();
        paintComponents(dbg);
        g.drawImage(dbImage,0,0,null);
    }

    public void paintComponents(Graphics g ){
        if(rollDice != null ){
            if(rollDice.getCurrentFrame() != 6) {
                rollDice.update(System.currentTimeMillis());
                g.drawImage(rollDice.sprite, 380, 460, 50, 50, null);
            }
            else{
                rollDice.stop();
            }
        }
        if(rollDice2 != null ){
            if(rollDice2.getCurrentFrame() != 6) {
                rollDice2.update(System.currentTimeMillis());
                g.drawImage(rollDice2.sprite,460,460,50,50,null);
            }
            else{
                rollDice2.stop();
            }

        }
        repaint();
    }

    Graphics g ;

    public void paintNewDice() {

        g.drawImage(spritesIdle,460, 460, 50,50 ,null);
        g.drawImage(spritesIdle2,460, 460, 50,50 ,null);
        //repaint();
    }

    public static void main(String[] args) {
        Main main = new Main();
        // write your code here
    }
}
