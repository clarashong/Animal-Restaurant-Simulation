import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Food is a subclass of Consumables. They are given 
 * characteristics from arrays that hold a "dictionary" of 
 * characteristics based on an index number. They will slowly
 * fade into the world, duration scaling with cookTime.
 * 
 * @author Joey Guan, Clara Hong, Jaylan Howden
 * @version November 2022
 */
public class Food extends Consumables
{
    //Arrays to hold food characteristics
    private String[] names = {"Taiyaki", "Riceball", "Purple Sweet Potato Bun"};
    private int[] prices = {50, 60, 70, 80, 90}; // placeholder prices
    private int[] cookTimes = {300, 400, 500, 600, 700}; //cook time in terms of acts 
    private GreenfootImage[] images = {new GreenfootImage("food1.png"), new GreenfootImage("food2.png"), new GreenfootImage("food3.png"),  
            new GreenfootImage("food4.png"), new GreenfootImage("food5.png")}; 

    //Charateristics 
    private int price;
    private int cookTime; 
    private int foodIndex; 
    private int reducedCTime;
    
    //State of matter booleans
    private boolean cooked = false; 
    private boolean onServingCounter = false;
    private boolean onTable = false;
    
    private Stove stove;
    /**
     *  Constructor of Food
     *  
     *  @param index Index number that identifies which food it is, assigning characteristics respectfully
     *  @param reducedCTime The amount of time that is cut from the cook time by other simulation effects
     */
    public Food(int index, int reducedCTime) 
    {
        price = prices[index];
        cookTime = cookTimes[index];
        foodIndex = index;
        this.index = index;
        this.reducedCTime = reducedCTime;
        
        GreenfootImage image = images[index];
        image.setTransparency(0);
        setImage(image);
        
        initFrames();
    }

    //Fades in until cooked
    public void act()
    {
        stove = (Stove) getOneIntersectingObject(Stove.class);
        if (!cooked) {
            fadeIn();
        }
        actCounter++; 
    }
    
    //Starts at 0 transparency, fades to 255, scaling with cookTime
    public void fadeIn() {
        double rate = 255 / (double) (cookTime - reducedCTime);
        int futureTransparency = (int)(rate * actCounter); 
        if (futureTransparency >= 255) {
            futureTransparency = 255;
            cooked = true;
        }
        getImage().setTransparency(futureTransparency); 
    }
   
    //Initializes food animation images
    public void initFrames() {
        for (int i = 0; i < frames.length; i++) {
            GreenfootImage image = new GreenfootImage("food" + Integer.toString(index + 1) + Integer.toString(i) + ".png"); 
            frames[i] = image; 
        }
    }
    
    public int getIndex() {
        return foodIndex; 
    }
    public int getCookTime()
    {
        return cookTime;
    }
    public int getPrice()
    {
        return price;
    }
    
    public boolean isCooked()
    {
        return cooked;
    }
    
    public boolean onServingCounter()
    {
        return onServingCounter;
    }
    public void setOnCounter(boolean b)
    {
        onServingCounter = b;
    }
    
    public boolean onTable()
    {
        return onTable;
    }
    public void setOnTable(boolean b)
    {
        onTable = b;
    }
    
    public Stove getStove()
    {
        return stove;
    }
}
