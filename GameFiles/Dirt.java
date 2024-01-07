import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * The Dirt Class is used as a visual effect that is placed on top of tables
 * to display how dirty the table is. There are 3 levels of dirtiness, 
 * as the levels increase, there are more dirt particles present in its image. 
 * <p>
 * Graphics: All dirt images were drawn by Clara Hong. 
 * 
 * @author Clara Hong
 * @version November 2022
 */
public class Dirt extends Actor
{
    private static ArrayList<GreenfootImage> images = new ArrayList<GreenfootImage>(); 
    private Table owner;
    private int level = 1; 
    
    /**
     * Constructor for dirt class
     * 
     * @param owner The table that the dirt exists on
     */    
    public Dirt(Table owner) {
        this.owner = owner; 
        level = owner.getDirtiness();
        init();
        setImage(images.get(level)); 
    }
    
    /**
     * Act - do whatever the Dirt wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        checkOwnerDirtiness(); 
    }
    
    //initializes dirt images
    public static void init() {
        for(int i = 0; i < 4; i++) {
            GreenfootImage image = new GreenfootImage("dirt" + Integer.toString(i) + ".png");
            image.scale(65,65); 
            images.add(image);
        }
    }
    
    //Updates the image to reflect dirt amount level
    public void checkOwnerDirtiness() { 
        if (level != owner.getDirtiness()) {
            level = owner.getDirtiness(); 
            setImage(images.get(level)); 
        }
    }
}
