package DiceAnimate;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animator {
    ArrayList<BufferedImage> frames ;
    BufferedImage sprite;
    private volatile boolean running =false;
    private long previosTime,speed ;
    private int frameAtPause ,currentFrame;

    public Animator(ArrayList<BufferedImage> frames){
        this.frames = frames;
    }

    public void setSpeed(long speed){
        this.speed = speed;
    }

    public void setFrames(){
        //stop();
        running = false;
        previosTime = 0;
        frameAtPause = 7 ;
        currentFrame = 6 ;
    }
    public void update(long time){
        if (running){
            if(time - previosTime >= speed){
                currentFrame++;
                try{
                    sprite = frames.get(currentFrame);
                }catch (IndexOutOfBoundsException e){
                    currentFrame = 0;
                    sprite = frames.get(currentFrame);
                }
                previosTime = time;
            }
        }

    }

    public void start(){
        running = true;
        previosTime = 0;
        frameAtPause = 0;
        currentFrame = 0 ;
    }

    public void stop( ){
        running = false;
        previosTime = 0;
        frameAtPause = 0;
        currentFrame = 0 ;
    }
    public void pause( ){
        frameAtPause = currentFrame ;
        running = false;
    }
    public void resume( ){
        currentFrame = frameAtPause;
        running =true;
    }
}
