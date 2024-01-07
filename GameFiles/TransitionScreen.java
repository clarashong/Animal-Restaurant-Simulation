import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Blinds that go down to partially block the screen to 
 * indicate that a day passed.
 * 
 * @author Marco Luong
 * @version November 2022
 */
public class TransitionScreen extends Actor
{
    //Staring variables
    private int moveSpeed;
    private boolean finished, midpointReached, stayOnScrn;
    GreenfootImage blinds = new GreenfootImage("blinds.png");
    
    /**
     * @param stayOnScrn If true, the blinds won't go back up after coming down
     */
    public TransitionScreen(boolean stayOnScrn){
        this.stayOnScrn = stayOnScrn;
        
        setImage(blinds);
        moveSpeed = 3;
        finished = false;
        midpointReached = false;
    }
    
    /**
     * Act - do whatever the DailyUpgrade wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        MyWorld w = (MyWorld) getWorld();
        if(getY() <= getWorld().getHeight() / 2 && !midpointReached){
            setLocation(getX(), getY() + moveSpeed);
        }
        
        if(getY() >= getWorld().getHeight() / 2){
            midpointReached = true;
            moveSpeed = -moveSpeed;
            if(stayOnScrn){
                finished = true;
            }
        }
        
        if(!stayOnScrn && midpointReached){
            if(getY() > 0){
                setLocation(getX(), getY() + moveSpeed);
            }
            else{
                finished = true;
            }
        }
    }
    
    public int getSpeed(){
        return moveSpeed;
    }
    
    public boolean doneTransition(){
        return finished;
    }
}
