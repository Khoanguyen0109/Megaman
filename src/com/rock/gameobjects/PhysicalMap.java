/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rock.gameobjects;

import com.rock.effect.CacheDataLoader;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author admin
 */
public class PhysicalMap  {
    
    
    public int[][] phys_map;
    private  int tileSize;
    
    public float r1,r2;
    
    public PhysicalMap (float x , float y ){
        
        this.r1 = x;  
        this.r2 = y;
        this.tileSize =30;
        phys_map = CacheDataLoader.getInstance().getPhysicalMap();
           
    }

    public int getTileSize() {
        return tileSize;
    }

    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }
    
    public void draw (Graphics2D g2){
        
        g2.setColor(Color.gray);
         for(int i = 0;i< phys_map.length;i++)
            for(int j = 0;j<phys_map[0].length;j++)
                if(phys_map[i][j]!=0) {
                    g2.fillRect((int) r1 + j*tileSize , 
                        (int) r2 + i*tileSize , tileSize, tileSize);
                }
    }
    
    public Rectangle haveCollisionWithLand (Rectangle rect){
    /// kiem tra khi hình chữ nhật của nhân vật va cham với mặt đất 
        int posX1 = rect.x/tileSize; /// index cua hien tai cua nhan vat tren mảng  
                                     /// chổ bắt đầu để duyệt mảng va chạm 
                                     /// vị trí góc trên bên trái của nhân vật
        posX1 -=2;
        int posX2 = (rect.x + rect.width )/ tileSize;  // vị trí x gốc trên bên phải của nhân vật 
        posX2 +=2;
        
        int posY2 =(rect.y + rect.height)/ tileSize;
        
        if(posX1 < 0){
            posX1 =0 ;
        }
        if( posX2 >= phys_map[0].length){
            posX2 = phys_map[0].length-1;
        }
        
        for (int y = posY2;y<phys_map.length;y++){
            for (int x = posX1; x<= posX2;x++){
                if ( phys_map[y][x] ==1 ){
                    Rectangle r= new Rectangle((int) r1 + x*tileSize ,(int) r2 +y*tileSize,tileSize,tileSize);
                    if(rect.intersects(r)) {
                        return r;
                    }
                }
            }
        }
        return  null;
    }
}
