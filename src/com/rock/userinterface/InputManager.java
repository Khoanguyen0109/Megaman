/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rock.userinterface;

import com.rock.gameobjects.Megaman;
import java.awt.event.KeyEvent;

/**
 *
 * @author admin
 */
public class InputManager {
    // quản lý dữ liệu nhập từ bàn phím
    
    private  GamePanel gamePanel;
    
    public InputManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        
    }
    
    
    // Nhấn phím
    public void processKeyPressed(int keyCode){
        switch (keyCode) {
            case KeyEvent.VK_UP :
                gamePanel.megaman.setSpeedY(-5);
                break;
            case KeyEvent.VK_DOWN: 
                gamePanel.megaman.setSpeedY(5);
                break;
            case KeyEvent.VK_LEFT: 
//                System.out.println("Go back");
                gamePanel.megaman.setDerection(Megaman.DIR_LEFT);
                gamePanel.megaman.setSpeedX(-5);
                break;
            case KeyEvent.VK_RIGHT: 
                gamePanel.megaman.setDerection(Megaman.DIR_RIGHT);
                gamePanel.megaman.setSpeedX(5);
                break;
            case KeyEvent.VK_ENTER:
               
                
                break;    
            case KeyEvent.VK_SPACE: 
                gamePanel.megaman.setSpeedY(-3);
                gamePanel.megaman.setPosY(gamePanel.megaman.getPosY() -3);
                break;
            case KeyEvent.VK_C: 
                
                break;
          
        }
    }
    //Thả Phím 
    public void processKeyReleased(int keyCode){
        switch (keyCode) {
            case KeyEvent.VK_UP :
                gamePanel.megaman.setSpeedY(0);
                break;
            case KeyEvent.VK_DOWN: 
                gamePanel.megaman.setSpeedY(0);
                break;
            case KeyEvent.VK_LEFT: 
                gamePanel.megaman.setSpeedX(0);
                break;
            case KeyEvent.VK_RIGHT: 
                gamePanel.megaman.setSpeedX(0);
                break;
            case KeyEvent.VK_ENTER:
               
                
                break;    
            case KeyEvent.VK_SPACE: 
                gamePanel.megaman.setSpeedY(0); 
                break;
            case KeyEvent.VK_C: 
                
                break;
            
        }
    }
}
