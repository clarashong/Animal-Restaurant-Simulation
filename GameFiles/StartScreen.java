import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Start screen for the user to adjust starting variables. 
 * Press "Start" to move onto the main game world.
 * 
 * @author Marco Luong
 * @version November 2022
 */
public class StartScreen extends World
{
    // Starting variables
    private Font classic;
    private int daysToRun, startingFunds;
    CompleteValueAdjuster setDays, setFunds;
    ChoiceButton startGame, resetValues;
    GreenfootImage bg = new GreenfootImage("settingBg.png");
    
    /**
     * Constructor of StartScreen. Adds starting variables as parameters that are set at the start of the sim
     */
    
    public StartScreen()
    {    
        super(1024, 640, 1); // call super constructor
        setBackground(bg);
        
        // Setting variable values
        classic = new Font ("Times New Roman", true, false, 18);
        daysToRun = 1;
        startingFunds = 0;
        
        // Adding buttons and value adjusters for users
        startGame = new ChoiceButton(75, "Start", 16, new Color(232, 232, 232), new Color(232, 232, 232), new Color(196, 204, 135));
        setDays = new CompleteValueAdjuster(this, getWidth() / 2, 2 * (getHeight() / 5), "Max days", classic, 10, 1, 40, 75, daysToRun, 1);
        setFunds = new CompleteValueAdjuster(this, getWidth() / 2, 3 * (getHeight() / 5), "Starting funds", classic, 1500, 0, 40, 75, startingFunds, 100);
        
        // Adding start button
        addObject(startGame, getWidth()/2, 4 * (getHeight()/5));
    }
    
    public void act()
    {
        // Check the new value of the adjusted values
        daysToRun = setDays.getUpdatedValue();
        startingFunds = setFunds.getUpdatedValue();
        
        // If start button is clicked, start game
        if(startGame.getIsClicked()){
            Greenfoot.setWorld(new MyWorld(daysToRun, startingFunds));
        }
    }
}
