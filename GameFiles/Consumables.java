import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Consumables is the superclass for Food and Drinks. Consumables are anything
 * that customers can order to eat/drink.
 * 
 * @author Clara Hong
 * @version November 2022
 */
public class Consumables extends Actor
{
    protected boolean consumed = false; 
    protected int frameNumber = -1; 
    protected GreenfootImage[] frames = new GreenfootImage[4]; 
    protected int index; 
    protected int actCounter = 0; 
    
    //Plays the eating animation on food
    public void consume() {
        if (!consumed) {
            if (actCounter % 60 == 0) {
                frameNumber++; 
                if (frameNumber >= 4) {
                    consumed = true; 
                } else {
                    setImage(frames[frameNumber]); 
                }
            }
        }
    }
        
    public boolean getConsumed() {
        return consumed; 
    }
}
