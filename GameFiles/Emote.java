import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Emotes are made by customers or staff to express themselves. 
 * Customers have a "thinking" emote to show that they're thinking about 
 * what to order.
 * Customers also have a "complaint" emote to complain about a dirty table. 
 * Both Timmy and Customers use the ordering emote to order foor or tell the 
 * chef about the order. 
 * 
 * @author Clara Hong
 * @version November 2022
 */
public class Emote extends Actor
{
    //Array to store order emotes
    private GreenfootImage[] foodImages = {new GreenfootImage("emoteFood1.png"), new GreenfootImage("emoteFood2.png"), new GreenfootImage("emoteFood3.png"), 
                                           new GreenfootImage("emoteFood4.png"), new GreenfootImage("emoteFood5.png")};

    private GreenfootImage image; //image for expression emotes
    private GreenfootImage orderEmote; //image for food orders

    private boolean order; //whether the emote is for ordering

    /**
     * Constructor for other bubbles
     * 
     * @param reason The kind of emote needed
     */
    public Emote (String reason) {
        if (reason.equals("thinking")) {
            image = new GreenfootImage("thoughtBubble.png"); 
        } else if (reason.equals("dirty")) {
            image = new GreenfootImage("dirtEmote.png");
        }
        setImage(image); 
        order = false; 
    }

    /**
     * Contructor for food order speech bubbles
     * 
     * @param index The food item it is emoting for
     */
    public Emote(int index) {
        orderEmote = foodImages[index]; 
        setImage(orderEmote); 
        order = true; 
    }

    /**
     * Act - do whatever the Emote wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (order) {
            if (getImage().getTransparency() < 15) {
                getWorld().removeObject(this); 
            } else {
                getImage().setTransparency(getImage().getTransparency() - 5); 
            }
        } 
    }
}