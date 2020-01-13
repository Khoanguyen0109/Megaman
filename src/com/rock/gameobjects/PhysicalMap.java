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
public class PhysicalMap extends GameObject {
    
    
    public int[][] phys_map;
    private  int tileSize;
    
 
    
    public PhysicalMap (float x , float y ,GameWorld gameWorld){
        super(x, y, gameWorld);

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
                    g2.fillRect((int) getPosX() + j*tileSize , 
                        (int) getPosY() + i*tileSize , tileSize, tileSize);
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
                    Rectangle r= new Rectangle((int) getPosX() + x*tileSize ,(int) getPosY() +y*tileSize,tileSize,tileSize);
                    if(rect.intersects(r)) {
                        return r;
                    }
                }
            }
        }
        return  null;
    }
    
    public Rectangle haveCollisionWithTop (Rectangle rect){ // va cham với trần 
        
        int posX1 = rect.x/tileSize;
        posX1 -=2;
        int posX2 = (rect.x+ rect.width)/tileSize;
        posX2 +=2;
        
        int posY = rect.y/tileSize;
        
        if (posX1 <0)
            posX1 = 0;
        if (posX2 >= phys_map[0].length )
            posX2 =phys_map[0].length-1;
        
        for ( int y = posY; y>=0; y-- ){
            for ( int x =posX1 ; x <= posX2; x++){
                if ( phys_map[y][x] == 1){
                    Rectangle r = new Rectangle((int)getPosX() + x *tileSize, (int) getPosY() +y *tileSize,tileSize,tileSize);
                    if (rect.intersects(r)){
                        return  r;
                    }
                }
            }
        }return null;
    }
    
    public Rectangle haveCollisionWithRightWall (Rectangle rect ){ // va cham voi tuong ben phai 
        int posY1= rect.y/tileSize; // tọa độ y điểm góc trên bên trái 
        posY1-=2;
        int posY2 = (rect.y + rect.height)/tileSize; //tọa đô y điểm góc dưới bên trái
        posY2+=2;
        
        int posX1= (rect.x+rect.width) /tileSize ;// tọa độ x  điểm góc trên bên phải 
        int posX2= posX1+3 ; 
        
        if ( posX2 > phys_map[0].length) 
            posX2 = phys_map[0].length- 1;
        
        if ( posY1 <0)
            posY1 = 0;
        if ( posY2>= phys_map.length )
            posY2 = phys_map.length - 1;
        
        for ( int x = posX1 ; x<= posX2; x ++){
            for ( int y= posY1; y <= posY2; y ++){
                if(phys_map[y][x] == 1){
                    Rectangle r = new Rectangle ((int) getPosX()+ x*tileSize , (int) getPosX()+ x*tileSize, tileSize,tileSize);
                    if ( rect.intersects(r) && r.y < rect.y + rect.height-1){
                        return r;
                    }
                }
            }
        }
        return  null;
    }
    public Rectangle haveCollisionWithLeftWall(Rectangle rect){ // va cham voi tuong ben trai
        
        int posY1 = rect.y/tileSize;
        posY1-=2;
        int posY2 = (rect.y + rect.height)/tileSize;
        posY2+=2;
        
        int posX1 = (rect.x + rect.width)/tileSize;
        int posX2 = posX1 - 3;
        if(posX2 < 0) posX2 = 0;
        
        if(posY1 < 0) posY1 = 0;
        if(posY2 >= phys_map.length) posY2 = phys_map.length - 1;
        
        
        for(int x = posX1; x >= posX2; x--){
            for(int y = posY1; y <= posY2;y++){
                if(phys_map[y][x] == 1){
                    Rectangle r = new Rectangle((int) getPosX() + x * tileSize, (int) getPosY() + y * tileSize, tileSize, tileSize);
                    if(r.y < rect.y + rect.height - 1 && rect.intersects(r))
                        return r;
                }
            }
        }
        return null;
        
    }

    @Override
    public void Update() {
    }
}
