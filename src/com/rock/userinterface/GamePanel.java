package com.rock.userinterface;
import com.rock.effect.Animation;
import com.rock.effect.CacheDataLoader;
import com.rock.effect.FrameImage;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable, KeyListener{
    private InputManager inputManager;
    
    private Thread gameThread;

    private boolean isRunning = true;
    

    
    FrameImage frame1, frame2 , frame3;
    Animation animation ; 

    public GamePanel(){

        inputManager = new InputManager();
//        try {
////            BufferedImage image = ImageIO.read(new File("data/megasprite.png"));
////            BufferedImage image1 = image.getSubimage(800, 177, 81, 100);
        frame1=  CacheDataLoader.getInstance().getFrameImage("idle1" );
        animation = CacheDataLoader.getInstance().getAnimation("idleshoot");
////            BufferedImage image2 = image.getSubimage(881, 177, 90, 100);
////            frame2= new FrameImage("frame2", image2);
////            
////            
////            BufferedImage image3 = image.getSubimage(970, 177, 93, 100);
////            frame3= new FrameImage("frame3", image3);
////            
////            animation = new Animation();
////            animation.add(frame1, 500*1000000);
////            animation.add(frame2, 500*1000000);
////            animation.add(frame3, 500*1000000);
////        } catch (IOException ex) {
////            
////        }

    }
    
    // Load game
    public void startGame(){
        if ( gameThread == null ){
            isRunning= true;
            gameThread = new Thread(this);
            gameThread.start();
        }
    }
     
    
    //Vẽ giao diện
    public void paint(Graphics g){

        g.setColor(Color.white);
        g.fillRect(0, 0, GameFrame.SCREEN_WITH, GameFrame.SCREEN_HEIGHT);
        
//        animation.Update(System.nanoTime());
//        
        Graphics2D g2  = (Graphics2D) g ; 
//        animation.draw(g2, 130, 200);
//        frame1.draw(g2, 100, 150);
        animation.draw(g2, 100, 150);


    }

     @Override
    public void run() {

        long previousTime = System.nanoTime();
        long currentTime;
        long sleepTime;

        long period = 1000000000/80;

        while(isRunning){
            
            
            repaint();
            currentTime = System.nanoTime();
            sleepTime = period - (currentTime - previousTime);
            try{

                    if(sleepTime > 0)
                            Thread.sleep(sleepTime/1000000);
                    else Thread.sleep(period/2000000);

            }catch(Exception e){}

            previousTime = System.nanoTime();
        }
        

    }
     @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        inputManager.processKeyPressed(e.getKeyCode());/// Xử lý nhấn phím
    }

    @Override
    public void keyReleased(KeyEvent e) {
        inputManager.processKeyReleased(e.getKeyCode());// Xử lý thả phím
    }



}