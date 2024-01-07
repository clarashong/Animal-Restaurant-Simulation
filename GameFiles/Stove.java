import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Stove is a subclass of Facilities. Chef Gumi uses the stove in order to 
 * cook the food. Like most facilities, the stove can be upgraded, and with 
 * each upgrade, its appearance becomes more modern and advanced. 
 * 
 * @author Joey Guan
 * @version November 2022
 */
public class Stove extends Facilities
{
    private GreenfootImage[] images = {new GreenfootImage("Stove1.png"), new GreenfootImage("Stove2.png"), 
                                       new GreenfootImage("Stove3.png"), new GreenfootImage("Stove4.png")}; 
    private boolean isCooking = false;
    
    public Stove() 
    {
        GreenfootImage image = getImage();
        image.scale(100,90);
        setImage(image);
    }
    
    public void cookFood(Food food)
    {
        isCooking = true;
        getWorld().addObject(food, getX(), getY()-40);
    }
    
    public void upgrade()
    {
        upgraded = true;
        level ++;
        upgradeCost *= 1.3;
        taskSpeedModifier *= 1.5;
        if(level<=4)
        {
            GreenfootImage image = images[level-1];
            image.scale(100,90);
            setImage(image);
        }
    }
    
    public boolean isCooking()
    {
        return isCooking;
    }
    public void setIsCooking(boolean b)
    {
        isCooking = b;
    }
}
