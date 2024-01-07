import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * CoffeeBar is a subclass of Facilities, and like most facilities can be 
 * upgraded to gain more sophisticated graphics. Drinks are made/brewed at the 
 * coffee bar. 
 * 
 * @author Joey Guan
 * @version November 2022
 */
public class CoffeeBar extends Facilities
{
    private int actCounter = 0;
    int upgradeCost = 1000; // actual cost tbd

    private GreenfootImage[] images = {new GreenfootImage("CoffeeBar1.png"), new GreenfootImage("CoffeeBar2.png"), 
                                       new GreenfootImage("CoffeeBar3.png"), new GreenfootImage("CoffeeBar4.png")}; 

    public CoffeeBar() 
    {
        GreenfootImage image = getImage();
        image.scale(210,160);
        setImage(image);
    }

    public void act()
    {
        actCounter++;
    }

    //Makes drinks
    public void brew(Food food)
    {
        actCounter = 0;
        if(actCounter == 120)
        {
            getWorld().addObject(food, getX(), getY()+10);
        }
    }

    //Upgrades
    public void upgrade()
    {
        upgraded = true;
        level ++;
        upgradeCost *= 1.3; 
        taskSpeedModifier *= 1.5; // will be balanced
        if(level >= 3 && level<=4)
        {
            GreenfootImage image = images[level-1];
            image.scale(210,250);
            setImage(image);
        }
        else
        {
            GreenfootImage image = images[level-1];
            image.scale(210,160);
            setImage(image);
        }
    }
}
