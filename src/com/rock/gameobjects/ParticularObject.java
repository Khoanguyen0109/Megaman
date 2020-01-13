/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rock.gameobjects;

import com.rock.effect.Animation;
import java.awt.Rectangle;

/**
 *
 * @author admin
 */
public abstract  class ParticularObject extends GameObject {
    public static  final int LEAGUE_TEAM =1;
    public static final  int ENEMY_TEAM = 2;
    
    public static  final int LEFT_DIR =0;
    public static final  int RIGHT_DIR = 1;
    
    public static final int ALIVE = 0;
    public static final int BEHURT = 1;
    public static final int FEY = 2;
    public static final int DEATH = 3;
    public static final int NOBEHURT = 4;
    
    private int state = ALIVE;
    
    private float width;
    private float height;
    private float mass;
    private float speedX;
    private float speedY;
    private int blood;
    private int teamType;
    private int damage;
    
    private int direction;
    
    protected Animation behurtForwardAnim, behurtBackAnim;
    
    
    private long startTimeNoBeHurt;
    private long timeForNoBeHurt;

    public ParticularObject(float x, float y, float width, float height, float mass,  int blood, GameWorld gameWorld) {
        
        super(x, y, gameWorld);
        this.width = width;
        this.height = height;
        this.mass = mass;

        this.blood = blood;
        
        direction = RIGHT_DIR;
 
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getMass() {
        return mass;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }

    public float getSpeedX() {
        return speedX;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    public int getTeamType() {
        return teamType;
    }

    public void setTeamType(int teamType) {
        this.teamType = teamType;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public long getStartTimeNoBeHurt() {
        return startTimeNoBeHurt;
    }

    public void setStartTimeNoBeHurt(long startTimeNoBeHurt) {
        this.startTimeNoBeHurt = startTimeNoBeHurt;
    }

    public long getTimeForNoBeHurt() {
        return timeForNoBeHurt;
    }

    public void setTimeForNoBeHurt(long timeForNoBeHurt) {
        this.timeForNoBeHurt = timeForNoBeHurt;
    }
    
    @Override
    public void Update() {
        switch (state) {
            case ALIVE:
                break;
            
            case DEATH:
                break;
            
            case FEY:
                break;
            
            case BEHURT:
                if ( behurtBackAnim== null){
                    state = NOBEHURT;
                    startTimeNoBeHurt = System.nanoTime();
                    if(getBlood()==0){
                        state =FEY;
                    }
                }else {
                    behurtBackAnim.Update(System.nanoTime());
                    if (behurtBackAnim.isLastFrame()){
                        behurtBackAnim.reset();
                        state = NOBEHURT;
                        if ( getBlood() == 0 ){
                            state = FEY;
                        }
                        startTimeNoBeHurt = System.nanoTime();
                    }
                }
                break;
            case NOBEHURT:
                if (System.nanoTime()- startTimeNoBeHurt > timeForNoBeHurt )
                    state = ALIVE ;
                break;
            
       

        }
    }
    
    public Rectangle getBoundForCollisiowWithMap(){
        Rectangle bound = new Rectangle();
        bound.x = (int) (getPosX() -(getWidth()/2));
        bound.y = (int) (getPosY()-(getWidth()/2));
        bound.width = (int) getWidth();
        bound.height = (int) getHeight();
        return  bound;
    }
    public void beHurt (int damage){
        setBlood(getBlood() - damage);
        state= BEHURT;
        hurtingCallback(); 
    }
    
    public abstract Rectangle getBoundForCollíionWithEnemy();
    
    public abstract  void actack();
    
    public void hurtingCallback(){
        
    }
    
    
}
