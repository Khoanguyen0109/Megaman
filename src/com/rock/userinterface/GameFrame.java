/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rock.userinterface;

import com.rock.effect.CacheDataLoader;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author admin
 */
public class GameFrame extends JFrame {
    
    public static final int SCREEN_WITH =1000;
    public static final int SCREEN_HEIGHT =600;
    
    GamePanel gamePanel;
    
    public GameFrame(){
        Toolkit toolkit = this.getToolkit();
        Dimension dimension = toolkit.getScreenSize();
        
//        
        try {
            CacheDataLoader.getInstance().LoadData();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.setBounds((dimension.width- SCREEN_WITH)/2,(dimension.height- SCREEN_HEIGHT)/2, SCREEN_WITH, SCREEN_HEIGHT);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        gamePanel = new GamePanel();
        add(gamePanel); 
        this.addKeyListener(gamePanel);
    }
    
    ///
    public  void startGame(){
        gamePanel.startGame();
        
    }
    
    public static void main(String[] args) {
        
        GameFrame gameFrame = new GameFrame();
        gameFrame.setVisible(true);
        gameFrame.startGame();
        
    }
    
}
