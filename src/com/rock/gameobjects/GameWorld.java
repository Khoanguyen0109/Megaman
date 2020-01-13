/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rock.gameobjects;

import java.awt.Graphics2D;

/**
 *
 * @author admin
 */
public class GameWorld {
    
   public Megaman megaman;
   public PhysicalMap physicalMap;


    
    public GameWorld(){
        physicalMap = new PhysicalMap(0,0,this);
        megaman = new Megaman(300,300,100,100, 0.1f,this);
    }
    
    public void Update (){
         megaman.update();
    }
    
    public void Render (Graphics2D g2 ){
        
        physicalMap.draw(g2);
        megaman.draw(g2);
    }
    
    
}
