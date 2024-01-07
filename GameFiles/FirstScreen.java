import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * First Screen is the first world the player will see when the simulation starts. 
 * It has a button, that when pressed, initiates the story sequence. 
 * 
 * @author Clara Hong
 * @version November 2022
 */
public class FirstScreen extends World
{
    private GreenfootImage bg = new GreenfootImage("startScreen.png"); 
    ChoiceButton startGame; 
    /**
     * Constructor for FirstScreen that sets a background
     * and creates a button that will switch Worlds when clicked
     */
    public FirstScreen()
    {    
        // Create a new world with 1024x640 cells with a cell size of 1x1 pixels.
        super(1024, 640, 1); 
        setBackground(bg); 
        startGame = new ChoiceButton(75, "Start", 16, new Color(232, 232, 232), new Color(232, 232, 232), new Color(196, 204, 135));
        addObject(startGame, getWidth()/2, 4 * (getHeight()/5));
    }
    
    /**
     * Detects if a button has been clicked, which will
     * switch Worlds to the StoryScreen World
     */
    public void act() {
        if(startGame.getIsClicked()){
            Greenfoot.setWorld(new StoryScreen());
        }
    }
}

