/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rock.effect;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class Animation {
    
    private String name;
    private boolean isRepeated;
    
    private ArrayList<FrameImage> frameImages;
    private int currentFrame ;
    
    private ArrayList<Boolean> ignoreFrames;
    
    private ArrayList<Double> delayFrame;// Mảng chứa thời gian delay giữa các Frame
    
    private long beginTime;
    
    private boolean drawRectFrame;
    
    
    public Animation(){
        delayFrame =new ArrayList<Double>();
        beginTime =0;
        currentFrame=0;
        
        ignoreFrames = new ArrayList<Boolean>();
        frameImages = new ArrayList<FrameImage>();
        
        drawRectFrame= false;
        
        isRepeated= true;
        
    }
    public Animation (Animation animation){
        beginTime = animation.beginTime;
        currentFrame= animation.currentFrame;
        drawRectFrame= animation.drawRectFrame;
        isRepeated= animation.isRepeated;
        
        delayFrame = new ArrayList<Double>();
        for( Double d: animation.delayFrame){
            delayFrame.add(d);
        }
        
        ignoreFrames = new ArrayList<Boolean>();
        for (Boolean b : animation.ignoreFrames){
            ignoreFrames.add(b);
            
        }
        frameImages = new ArrayList<FrameImage>();
        for (FrameImage f : animation.frameImages){
            frameImages.add(f);
            
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsRepeated() {
        return isRepeated;
    }

    public void setIsRepeated(boolean isRepeated) {
        this.isRepeated = isRepeated;
    }

    public ArrayList<FrameImage> getFrameImages() {
        return frameImages;
    }

    public void setFrameImages(ArrayList<FrameImage> frameImages) {
        this.frameImages = frameImages;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(int currentFrame) {
        if (currentFrame >=0 && currentFrame < frameImages.size()){
            this.currentFrame = currentFrame;
        }else{
            this.currentFrame = 0;
        }
    }

    public ArrayList<Boolean> getIgnoreFrames() {
        return ignoreFrames;
    }

    public void setInogreFrames(ArrayList<Boolean> ignoreFrames) {
        this.ignoreFrames = ignoreFrames;
    }

    public ArrayList<Double> getDelayFrame() {
        return delayFrame;
    }

    public void setDelayFrame(ArrayList<Double> delayFrame) {
        this.delayFrame = delayFrame;
    }

    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public boolean getDrawRectFrame() {
        return drawRectFrame;
    }

    public void setDrawRectFrame(boolean drawRectFrame) {
        this.drawRectFrame = drawRectFrame;
    }
    
    //Get a Frame
    public boolean getIgnoreFrame(int id){
            return ignoreFrames.get(id);
    }
    //Set a Frame ignore 
    public void  setIgnoreFrame(int id){
        if( id >=0 && id < ignoreFrames.size()){
            ignoreFrames.set(id, true);
        }
    }
    //unIgnore a Frame
    public void  unIgnoreFrame(int id){
        if( id >=0 && id < ignoreFrames.size()){
            ignoreFrames.set(id, false);
        }
    }
    
    public void reset(){ // reset lai khi nhan vat dung hanh dong 
         currentFrame =0;
         beginTime =0;
         for( int i =0; i< ignoreFrames.size();i++){
             ignoreFrames.set(i, false);
         }
    }
    
    public void add (FrameImage frameImage, double timeToNextFrame){
        ignoreFrames.add(false);
        frameImages.add(frameImage);
        delayFrame.add(timeToNextFrame); 
    }
    
    public BufferedImage getCurentImage(){
        return  frameImages.get(currentFrame).getImage();
    }
    
    public void Update (long currenttiem){
        if (beginTime ==0 ){
            beginTime = currenttiem;
            
        }else {
            if (currenttiem - beginTime > delayFrame.get(currentFrame)){
                nextFrame();
                beginTime = currenttiem;
                
            }
            
        }
    }
    
    
    public void nextFrame(){
        if (currentFrame>= frameImages.size()-1){
            if(isRepeated){
                currentFrame=0;
            }
        }else{
            currentFrame++;
        }
        if (ignoreFrames.get(currentFrame)){
            nextFrame();
        }
    }
    
    public boolean isLastFrame (){// kiểm tra coi đã hết 1 animation chưa
        if(currentFrame ==frameImages.size()-1){
            return true;
        }else{
            return false;
        }
    } 
    
    public void flipAllImage(){ // lật các tấm hình khi nhân vật chạy ngược lại 
        for (int i=0; i < frameImages.size();i++){
            BufferedImage image = frameImages.get(i).getImage();
            
            AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
            tx.translate(-image.getWidth(), 0);
            
            AffineTransformOp op = new AffineTransformOp(tx ,AffineTransformOp.TYPE_BILINEAR);
            image = op.filter(image, null);
            
            frameImages.get(i).setImage(image);
                
        }
    }
    
    public void draw (Graphics2D g2,int x, int y  ){
        BufferedImage  image = getCurentImage();
        g2.drawImage(image, x- image.getWidth()/2,y- image.getHeight()/2,null);
        if (drawRectFrame){
            g2.drawRect(x-image.getWidth()/2, y- image.getHeight()/2, image.getWidth(), image.getHeight());
        }
    }


}
