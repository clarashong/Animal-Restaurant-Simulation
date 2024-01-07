import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Table is a subclass of the Facilities superclass. It is where the customers
 * sit and consume their orders. The tables are upgradable, and by upgrading them
 * their images change to a more sophisticated appearance. Each table also has their
 * own dirtiness level, and when the table's dirty, it will lower the happiness
 * of customers. 
 * 
 * @author Clara Hong
 * @version November 2022
 */
public class Table extends Facilities
{
    private boolean isFree = true; //has a customer at this table
    private int dirtiness = 0; //dirtiness from 0-3 (0 = clean, 3 = very dirty)
    private Dirt dirt;
    
    private GreenfootImage[] images = {new GreenfootImage("Table1.png"), new GreenfootImage("Table2.png"), 
                                       new GreenfootImage("Table3.png"), new GreenfootImage("Table4.png")}; 
    
    public Table() {
        GreenfootImage image = getImage();
        image.scale(125,100);
        setImage(image);
    }
    
    public void addedToWorld(World w) {
        dirt = new Dirt(this);
        getWorld().addObject(dirt, getX(), getY() - 15); 
    }
    
    //Method to upgrade tables
    public void upgrade()
    {
        upgraded = true;
        level ++;
        upgradeCost *= 1.3;
        taskSpeedModifier *= 1.5;
        if(level<=4)
        {
            GreenfootImage image = images[level-1];
            image.scale(125,100);
            setImage(image);
        }
    }
    
    /**
     * method to change the dirtiness 
     * 
     */
    public void changeDirtiness(int change) {
        dirtiness += change; 
        //make sure the level does not go over bounds
        if (dirtiness <= 0) {
            dirtiness = 0; 
        } else if (dirtiness >= 3) {
            dirtiness = 3; 
        }
    }
    
    public int getDirtiness(){
        return dirtiness;
    }
    
    public int getLevel() {
        return level; 
    }
    
    public boolean isFree() {
        return isFree; 
    }
    public void setIsFree(boolean b)
    {
        isFree = b;
    }
}