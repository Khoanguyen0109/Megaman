/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rock.userinterface;

import com.rock.gameobjects.GameWorld;
import com.rock.gameobjects.Megaman;
import java.awt.event.KeyEvent;

/**
 *
 * @author admin
 */
public class InputManager {
    // quản lý dữ liệu nhập từ bàn phím
    

    private  GameWorld gameWorld;
    public InputManager(GameWorld gameWorld){
        this.gameWorld = gameWorld;
        
    }
    
    
    // Nhấn phím
    public void processKeyPressed(int keyCode){
        switch (keyCode) {
            case KeyEvent.VK_UP :
                gameWorld.megaman.setSpeedY(-5);
                break;
            case KeyEvent.VK_DOWN: 
                gameWorld.megaman.setSpeedY(5);
                break;
            case KeyEvent.VK_LEFT: 
//                System.out.println("Go back");
                gameWorld.megaman.setDerection(Megaman.DIR_LEFT);
                gameWorld.megaman.setSpeedX(-5);
                break;
            case KeyEvent.VK_RIGHT: 
                gameWorld.megaman.setDerection(Megaman.DIR_RIGHT);
                gameWorld.megaman.setSpeedX(5);
                break;
            case KeyEvent.VK_ENTER:
               
                
                break;    
            case KeyEvent.VK_SPACE: 
                gameWorld.megaman.setSpeedY(-3);
                gameWorld.megaman.setPosY(gameWorld.megaman.getPosY() -3);
                break;
            case KeyEvent.VK_C: 
                
                break;
          
        }
    }
    //Thả Phím 
    public void processKeyReleased(int keyCode){
        switch (keyCode) {
            case KeyEvent.VK_UP :
                gameWorld.megaman.setSpeedY(0);
                break;
            case KeyEvent.VK_DOWN: 
                gameWorld.megaman.setSpeedY(0);
                break;
            case KeyEvent.VK_LEFT: 
                gameWorld.megaman.setSpeedX(0);
                break;
            case KeyEvent.VK_RIGHT: 
                gameWorld.megaman.setSpeedX(0);
                break;
            case KeyEvent.VK_ENTER:
               
                
                break;    
            case KeyEvent.VK_SPACE: 
                gameWorld.megaman.setSpeedY(0); 
                break;
            case KeyEvent.VK_C: 
                
                break;
            
        }
    }
}
