import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The StoryScreen class is a World that is used to play out the backstory 
 * for the simulation. It displays the backstory by cycling through pre-made
 * slides. 
 * <p>
 * Shortcut: press the enter key to skip the backstory and go straight to
 * the settings/start screen. 
 * <p>
 * Graphics: The text was added by Clara Hong. Slide 1's image is from the 
 * Animal Restaurant Wiki under "The story of Gumi and Cici" - Old Journal 
 * Page 1. Slide 2's image was cut out from a screenshot under the article 
 * "Animal Restaurant: Run your own adorable Restaurant." Slide 3's image is 
 * from the Animal Restaurant Wiki's entry for "Chef Gumi." Lastly, Slide 4's 
 * image incorporates the image used for Gumi, Apricat, and Timmy's characters. 
 * The background for this slide is an edited version of a screenshot 
 * of the title screen of for the official Animal Restaurant game 
 * that can be found on a TV tropes page called "Video Game/ Animal Restaurant." 
 * <p>
 * Links: Slide 1 (https://animalrestaurant.fandom.com/wiki/The_story_of_Gumi_and_Cici), 
 * Slide 2 (https://gamingonphone.com/reviews/animal-restaurant-review/),  
 * Slide 3 (https://animalrestaurant.fandom.com/wiki/Chef_Gumi),  
 * Slide 4 (https://tvtropes.org/pmwiki/pmwiki.php/VideoGame/AnimalRestaurant).
 * 
 * @author Clara Hong
 * @version Nov 2022
 */
public class StoryScreen extends World
{
    private GreenfootImage[] slides = new GreenfootImage[4]; 
    private GreenfootImage currentSlide; 
    private int slideNum = 1; // the number slide that's on
    private int actCounter = 0; 


    //constructor for StoryScreen
    public StoryScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 640, 1); 
        init(); //initialise graphics
        setBackground(slides[0]); 
    }
    
    public void act() {
        //the slides will change every 240 acts
        if (actCounter > 0 && actCounter % 240 == 0) {
            slideNum++; 
            if (slideNum < 5) {
                setBackground(slides[slideNum - 1]); 
            } else if (slideNum >= 5) {
                Greenfoot.setWorld(new StartScreen()); 
            }
        }
        if (Greenfoot.isKeyDown("enter")) {
            Greenfoot.setWorld(new StartScreen()); 
        }
        actCounter++; //counting the acts
    }
    
    //this method initialises all the slides needed for the game's backstory
    public void init() {
        //for loop fills the array with all the needed slide images
        for (int i = 0; i < slides.length; i++ ) {
            //all the slide images are named slide#.png (ex. slide2.png) 
            slides[i] = new GreenfootImage("slide" +Integer.toString(i + 1) + ".png"); 
        }
    }
}

