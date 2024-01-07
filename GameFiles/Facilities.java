import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Facilities here.
 * 
 * @author Joey Guan, Clara Hong, Marco Luong
 */
public abstract class Facilities extends Actor
{
    protected int level = 1, maxLevel = 3, upgradeCost = 50;
    protected double taskSpeedModifier = 1;
    protected boolean upgraded = false;
    protected MyWorld w;
    private ArrayList<GreenfootImage> images = new ArrayList<GreenfootImage>();
    
    protected void upgrade(){}
    
    /**
     * Animation played to indicate that the facility is upgraded
     */
    protected void upgradeAni(){
        MyWorld w = (MyWorld) getWorld();
        w.addObject(new PopUp(new GreenfootImage("upgrade.png"), 200, 75, 25, new Color(135, 119, 99), "Upgrade"), getX(), getY());
    }
    
    /**
     * Checks if this was recently upgraded 'at night'
     */
    public boolean isUpgraded(){
        if(upgraded){
            upgraded = false;
            return true;
        }return false;
    }
    
    public int getUpgradeCost(){
        return upgradeCost;
    }
    
    public int getLevel(){
        return level;
    }
    
    public boolean atMaxLevel(){
        if(level < maxLevel){
            return false;
        }return true;
    }
}
