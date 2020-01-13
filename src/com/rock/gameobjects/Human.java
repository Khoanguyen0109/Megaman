/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rock.gameobjects;

import java.awt.Rectangle;

/**
 *
 * @author admin
 */
public abstract class Human extends  ParticularObject{
    private boolean isJumping;
    private boolean  iDicking;
    private boolean  isLanding;
    
    public Human ( float x, float y , float width, float height, float mass, int blood, GameWorld gameWorld ){
        super(x, y, width, height, mass, blood, gameWorld);
        setState(ALIVE);
        
    }
    public abstract void run ();
    
    public abstract  void  jump();
    
    public abstract void dick();
    
    public abstract void standUP();
    
    public abstract void  stopSrun();

    public boolean isIsJumping() {
        return isJumping;
    }

    public void setIsJumping(boolean isJumping) {
        this.isJumping = isJumping;
    }

    public boolean isiDicking() {
        return iDicking;
    }

    public void setiDicking(boolean iDicking) {
        this.iDicking = iDicking;
    }

    public boolean isIsLanding() {
        return isLanding;
    }

    public void setIsLanding(boolean isLanding) {
        this.isLanding = isLanding;
    }
    
     
    @Override
    public Rectangle getBoundForCollíionWithEnemy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actack() {
    }
    
    @Override 
    public void Update(){
        super.Update();
        if (getState() == ALIVE || getState() == NOBEHURT){
            if(!isLanding){
                setPosX(getPosX()+getSpeedX());
                /// kiểm tra nếu đụng tường trái
                if (getDirection()== LEFT_DIR && getGameWorld().physicalMap.haveCollisionWithLeftWall(getBoundForCollisiowWithMap()) != null){
                    Rectangle rectLeftWall = getGameWorld().physicalMap.haveCollisionWithLeftWall(getBoundForCollisiowWithMap());
                    setPosX(rectLeftWall.x + rectLeftWall.width + getWidth()/2); /// điểm x của ô bức tường + độ dày + 1/2 ô của nhân vật ( do điểm x,y nằm ở tâm   
                                     
                }
                // kiểm tra nếu đụng tường phải thì set lại PosX
                if ( getDirection()== RIGHT_DIR && getGameWorld().physicalMap.haveCollisionWithRightWall(getBoundForCollisiowWithMap())!= null){
                    Rectangle rectRightWall = getGameWorld().physicalMap.haveCollisionWithRightWall(getBoundForCollisiowWithMap());
                    setPosX(rectRightWall.x - getWidth()/2);// set x sát bức tường 
                }
                
                Rectangle boudforCollisionWithMapFuture = getBoundForCollisiowWithMap(); // get đối tượng
                boudforCollisionWithMapFuture.y += (getSpeedY()!=0?getSpeedY():2); // kiểm tra nếu speedY !0, =  getSpeedY ngược lại  =2 
                
                Rectangle rectLand = getGameWorld().physicalMap.haveCollisionWithLand(boudforCollisionWithMapFuture);
                Rectangle rectTop = getGameWorld().physicalMap.haveCollisionWithTop(boudforCollisionWithMapFuture);
                
                if (rectTop!= null){
                    setSpeedY(0);
                    setPosY(rectTop.y+ getGameWorld().physicalMap.getTileSize() + getHeight()/2);
                }else if (rectLand != null){
                    setIsJumping(false);
                    if (getSpeedY() > 0 ){
                        setIsLanding(true);
                    }
                    setSpeedY(0);
                    setPosY(rectLand.y - getHeight()/2 -1 );
                    
                }else {
                    setIsJumping(true);
                    setSpeedY(getPosY() + getMass());
                    setPosY(getPosY() + getSpeedY());
                    
                }
            }
        }
    }
}
