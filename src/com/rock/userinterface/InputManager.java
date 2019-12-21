/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rock.userinterface;

import java.awt.event.KeyEvent;

/**
 *
 * @author admin
 */
public class InputManager {
    // quản lý dữ liệu nhập từ bàn phím
    
    // Nhấn phím
    public void processKeyPressed(int keyCode){
        switch (keyCode) {
            case KeyEvent.VK_UP :
                
                break;
            case KeyEvent.VK_DOWN: 
                
                break;
            case KeyEvent.VK_LEFT: 
                System.out.println("Go back");
                break;
            case KeyEvent.VK_RIGHT: 
                
                break;
            case KeyEvent.VK_ENTER:
               
                
                break;    
            case KeyEvent.VK_SPACE: 
                
                break;
            case KeyEvent.VK_C: 
                
                break;
          
        }
    }
    //Thả Phím 
    public void processKeyReleased(int keyCode){
        switch (keyCode) {
            case KeyEvent.VK_UP :
                
                break;
            case KeyEvent.VK_DOWN: 
                
                break;
            case KeyEvent.VK_LEFT: 
                System.out.println("Release  back");
                break;
            case KeyEvent.VK_RIGHT: 
                
                break;
            case KeyEvent.VK_ENTER:
               
                
                break;    
            case KeyEvent.VK_SPACE: 
                
                break;
            case KeyEvent.VK_C: 
                
                break;
            
        }
    }
}
