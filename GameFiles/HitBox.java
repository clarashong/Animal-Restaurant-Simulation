import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class creates square actors used for detection.
 * They have a customizable width and height, and can return 
 * whether or not it is intersecting another Actor.
 * 
 * @author Joey Guan
 * @version November 2022
 */
public class HitBox extends Actor
{
    /**
     * Creates a simple square Actor for collision detection
     * 
     * @param width   The width of the HitBox square
     * @param height  The height of the HitBox square
     */
    public HitBox(int width, int height)
    {
        GreenfootImage image = getImage();
        image.scale(width, height);
        setImage(image);
    }
    
    public boolean isIntersecting(Actor a)
    {
        return intersects(a);
    }
}
