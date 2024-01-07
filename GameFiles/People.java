import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Superclass of all 'people' in the simulation.
 * 
 * @author Marco Luong, Joey Guan, Clara Hong
 */
public abstract class People extends Actor
{
    protected int speed, exp;
    
    /**
     * Used to move an Actor towards a point relative to another Actor,
     * while not showing any visible rotation
     * 
     * @param a        The Actor to move towards
     * @param speed    Speed that it should move at
     * @param xOffset  xOffset from the specified Actor's center x-Coordinate
     * @param yOffset  yOffset from the specified Actor's center y-Coordinate
     */
    protected void moveTowards(Actor a, int speed, int xOffset, int yOffset){
        if(!isAt(a, speed, xOffset, yOffset))
        {
            turnTowards(a.getX() + xOffset, a.getY() + yOffset);
            move(speed);
            setRotation(0);
        }
    }

    /**
     * Returns whether or not the Actor calling the method is located 
     * at a specific point relative to a specified Actor. To do this 
     * the method creates destinationPoint HitBox actor at the specific point
     * and a moverPoint HitBox actor that follows the calling Actor around.
     * If the moverPoint intersects the destinationPoint; returns true; 
     * else returns false.
     *  
     * @param a        A specified Actor
     * @param speed    Speed of the caller Actor; used to decide how large the destinationPoint has to be to avoid bugs
     * @param xOffset  xOffset from the specified Actor's center x-Coordinate
     * @param yOffset  yOffset from the specified Actor's center y-Coordinate
     * 
     */
    public boolean isAt(Actor a, int speed, int xOffset, int yOffset)
    {
        MyWorld w = (MyWorld) getWorld();
        HitBox destinationPoint = new HitBox(speed, speed);
        w.addObject(destinationPoint, a.getX() + xOffset, a.getY() + yOffset);
        
        HitBox moverPoint = new HitBox(1,1);
        w.addObject(moverPoint, getX(), getY());
        if(moverPoint.isIntersecting(destinationPoint))
        {
            w.removeObject(destinationPoint);
            w.removeObject(moverPoint);
            return true;
        }
        else
        {
            w.removeObject(destinationPoint);
            w.removeObject(moverPoint);
            return false;
        }
    }
}
