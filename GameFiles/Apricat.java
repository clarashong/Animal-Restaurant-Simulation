import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Apricat is the cleaner for the restaurant and a subclass
 * of the Staff class. To be used, she just needs to be 
 * placed in the world. 
 * <p>
 * She targets dirty tables and goes to clean them. She has a maximum amount of 
 * tables that she can clean at a time and needs to take breaks in between. 
 * <p>
 * 
 * @author Clara Hong
 * @version November 2022
 */
public class Apricat extends Staff
{
    private ArrayList<Table> tables;
    private Table targetTable = null;
    private boolean nearTargetTable; 
    GreenfootImage image, image1; 
    
    private int tablesCleaned = 0; 
    private int maxTables = 3; //max numbers of tables at a time that she can clean
    private int breakCooldown = 300; 
    private boolean atBreakSpot = false; 
    
    private int actCounter = 0; 
    
    public Apricat()
    {
        //set graphics
        image = new GreenfootImage("Apricat.png"); 
        image1 = new GreenfootImage("Apricat2.png"); 
        image.scale(100,100);
        image1.scale(100,100); 
        setImage(image);
        
        nearTargetTable = false; 
    }
    
    public void act()
    {
        //2-frame animation
        if(actCounter % 40 >= 0 && actCounter % 40 <=20 && !atBreakSpot) {
            setImage(image); 
        } else {
            setImage(image1); 
        }
        
        //if she hasn't cleaned her max amount of tables yet 
        if (tablesCleaned < maxTables) {
            if (!nearTargetTable) {
                //looks for a table and moves towards it 
                findDirtyTable();
                if (targetTable != null) {
                    moveTowardsTargetTable(speed);
                }
                if (targetTable == null) {
                    takeBreak(); //takes break if there's no tables left to clean
                }
            } else if (targetTable != null) {
                cleanTable(); 
            }
        } else {
            takeBreak(); 
        }
        actCounter++; 
    }
    
    public void findDirtyTable()
    {
        tables = (ArrayList) getWorld().getObjects(Table.class);
        for(Table t: tables){
            if(t.getDirtiness() > 0){
                if (targetTable == null) {
                    targetTable = t;
                }
            }
        }
    }
    
    public void cleanTable() {
        targetTable.changeDirtiness(-1); 
        if (targetTable.getDirtiness() == 0) {
            expUp();
            if(exp >= maxExp && lvl < maxLvl){
                lvlUp();
            }
            targetTable = null; 
            nearTargetTable = false;
            tablesCleaned++; 
        }
    }
    
    public void moveTowardsTargetTable(int speed){
        int xx = targetTable.getX(); 
        int yy = targetTable.getY(); 
        
        turnTowards(xx - 70, yy); 
        move(speed);
        setRotation(0);
        //getX() > xx - 80 && getY() == yy
        if (intersects(targetTable)) {
            nearTargetTable = true; 
        }
    }
    
    /** 
     * Method for Apricat, the cleaner, to take breaks. 
     * She has a specified coordinate where she would take a break - and she 
     * will go to that spot. Once at the location, she will start a cooldown 
     * and stand there until the break's over. 
     */
    public void takeBreak() {
        //break location
        int breakX = 380; 
        int breakY = 570; 
        
        //senses if she's at the spot
        if (getX() <= breakX && getY() >= breakY) {
            atBreakSpot = true; 
        } else {
            atBreakSpot = false; 
        }
        if (!atBreakSpot) {
            //if she's not at the spot, she will try to move closer to it
            turnTowards(breakX, breakY); 
            move(speed);
            setRotation(0);
        } else {
            //cooldown decreases with each act act 
            breakCooldown--; 
            if (breakCooldown <= 0) {
                //her break will last for 300 acts 
                breakCooldown = 300; 
                //the amount of tables she has cleaned resets 
                tablesCleaned = 0; 
            }
        }
    }
    
    public void lvlUp(){
        lvl++;
        if(lvl == maxLvl){
            image = new GreenfootImage("ApricatMax.png");
            image1 = image;
            image1.mirrorHorizontally();
            image.scale(100,100);
            image1.scale(100,100); 
            
            speed++;
        }
        
        maxTables++;
        exp = 0;
        maxExp += changeExpBy;
        expBar.update(exp);
    }
}
