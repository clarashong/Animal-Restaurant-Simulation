import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * An actor that the user interacts with through a mouse click. 
 * The default clicked() method is empty and is meant to be filled 
 * out with the action meant to be carried out when the actor is clicked. 
 * 
 * @author Marco Luong
 */
public abstract class Button extends Actor
{
    protected MyWorld w;
    protected String text;
    protected int height, width;
    
    /**
     * Main constructor for the actor. Gives an indicator of how 
     * big the button will be and what text it stores.
     * 
     * @param w     Width of button
     * @param h     Height of button
     * @param txt   Text displayed
     */
    public Button(int w, int h, String txt){
        text = txt;
        width = w;
        height = h;
    }
    
    public void act(){
        if(Greenfoot.mouseClicked(this)){ // Checks if clicked
            clicked();
        }
    }
    
    protected abstract void clicked();
}
