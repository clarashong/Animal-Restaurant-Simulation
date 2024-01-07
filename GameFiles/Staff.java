import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Superclass for the staff members of the restuarant.
 * 
 * @author Joey Guan, Clara Hong, Marco Luong
 */
public abstract class Staff extends People
{
    // Starting variables
    private Color barBackground = new Color(135, 119, 99), barColor = new Color(204, 184, 159);
    protected int lvl, maxLvl, exp, maxExp, changeExpBy;
    protected SuperStatBar expBar;
    int speed = 2;

    public Staff(){
        lvl = 1;
        maxLvl = 3;
        exp = 0; // starting exp
        maxExp = 25; // how much exp needed to level up
        changeExpBy = 5; // exp to gain each time
        expBar = new SuperStatBar (maxExp, exp, this, getImage().getWidth() / 5, 10, -getImage().getHeight() / 5, barColor, barBackground, false, barBackground, 3);
    }
    
    // Add the experience bar
    public void addedToWorld(World w){
        w.addObject(expBar, getX(), getY());
        expBar.update(exp);
    }

    protected void lvlUp(){}; // method to be run when the exp bar is full
    
    protected void expUp(){ // gain exp once
        exp += changeExpBy;
        expBar.update(exp);
    }
}

