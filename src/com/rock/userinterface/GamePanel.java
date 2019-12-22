package com.rock.userinterface;
import com.rock.effect.Animation;
import com.rock.effect.CacheDataLoader;
import com.rock.effect.FrameImage;
import com.rock.gameobjects.Megaman;
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

    private BufferedImage bufferedImage ;
    private Graphics2D bufGraphics2D;
    
    Megaman megaman = new Megaman(300,300,100,100, 0.1f);

    
//    FrameImage frame1, frame2 , frame3;
//    Animation animation ; 

    public GamePanel(){

        inputManager = new InputManager(this);
        bufferedImage = new BufferedImage(GameFrame.SCREEN_WITH, GameFrame.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
//        try {
////            BufferedImage image = ImageIO.read(new File("data/megasprite.png"));
////            BufferedImage image1 = image.getSubimage(800, 177, 81, 100);
//        frame1=  CacheDataLoader.getInstance().getFrameImage("idle1" );
//        animation = CacheDataLoader.getInstance().getAnimation("run");
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

        g.drawImage(bufferedImage, 0,0 , this);
        
    }
    public void UpdateGame(){
        megaman.update();
    }
    
    public void RenderGame(){
        if (bufferedImage == null){
             bufferedImage = new BufferedImage (GameFrame.SCREEN_WITH, GameFrame.SCREEN_HEIGHT,BufferedImage.TYPE_INT_ARGB);
             
        }
        
        if ( bufferedImage!= null){
            bufGraphics2D =(Graphics2D) bufferedImage.getGraphics();
        
        }
        if (bufGraphics2D != null ){
             
            bufGraphics2D.setColor(Color.white);
            bufGraphics2D.fillRect(0, 0, GameFrame.SCREEN_WITH, GameFrame.SCREEN_HEIGHT);
            
//            bufGraphics2D.setColor(Color.red);
//            bufGraphics2D.fillRect(40, 50, 100, 100);
            megaman.draw(bufGraphics2D);

        }
    }

    @Override
    public void run () {

        long previousTime = System.nanoTime();
        long currentTime;
        long sleepTime;

        long period = 1000000000/80;

        while(isRunning){
            
            UpdateGame();
            RenderGame();
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