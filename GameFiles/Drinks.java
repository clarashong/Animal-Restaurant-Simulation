import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Drinks is a subclass of Foods. Planned, but not 
 * implemented. Would've resembled Food class, except 
 * that they are drinks.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Drinks extends Consumables
{
    private String[] names = {"Espresso", ""};
    private int[] prices = {5, 10, 15, 20, 25};//placeholder prices
    private static int[] cookTimes = {300, 400, 500}; //cook time in terms of acts 
    private ArrayList<GreenfootImage> images = new ArrayList<GreenfootImage>(); 
  
    public Drinks() 
    {
        
    }
    public void act() 
    {
 
    }
     public void init()
    {
        for(int i = 1; i < prices.length+1; i++)
        {
            GreenfootImage image = new GreenfootImage("drinks" + Integer.toString(i) + ".png");
            images.add(image);
        }
    }
    
    public String getDrinksName(int index)
    {
        return names[index];
    }
    
    public int getDrinksPrice(int index)
    {
        return prices[index];
    }
    
    public GreenfootImage getDrinksImage(int index)
    {
        return images.get(index);
    }
    
}
