import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * The Tipjar is a subclass to the facilities class, and customers go 
 * towards the tip jar to tip the restaurant based on their happiness. 
 * 
 * @author Joey Guan
 * @version November 2022
 */
public class TipJar extends Facilities
{
    private GreenfootImage[] images = {new GreenfootImage("TipJar1.png"), new GreenfootImage("TipJar2.png"), 
                                       new GreenfootImage("TipJar3.png"), new GreenfootImage("TipJar4.png")}; 
    
    public TipJar()
    {
        GreenfootImage image = getImage();
        image.scale(60,100);
        setImage(image);
    }
    
    public void upgrade()
    {
        upgraded = true;
        level ++;
        upgradeCost *= 1.3;
        if(level >= 3 && level<=4)
        {
            GreenfootImage image = images[level-1];
            image.scale(80,100);
            setImage(image);
        }
        else
        {
            GreenfootImage image = images[level-1];
            image.scale(60,100);
            setImage(image);
        }
    }
}
