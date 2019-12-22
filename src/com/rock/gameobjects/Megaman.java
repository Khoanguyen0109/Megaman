/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rock.gameobjects;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author admin
 */
public class Megaman {
    
    private float posX;
    private  float posY;
    
    private float width;
    private  float height;
    private float  mass;
    
    private float speedX;
    private  float speedY;
    
    public static  int  DIR_LEFT;
    public static int DIR_RIGHT ;
    private  int derection;
    

    public Megaman(float posX, float posY, float width, float height, float mass) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.mass = mass;
    }
    public void update (){
        setPosX(getPosX()+ speedX);
        if ( getPosY() >=400){
            setPosY(400);
        }else{
            setPosY(getPosY() + speedY);
            setSpeedY(getSpeedY() + mass);
        }
        
        
       
    }
    
    public void draw (Graphics2D g2){
        g2.setColor( Color.red);
        g2.fillRect((int) posX, (int) posY  , (int) width, (int) height);
        
    }
    
    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
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

    public int getDerection() {
        return derection;
    }

    public void setDerection(int derection) {
        this.derection = derection;
    }
    
    
    
}
