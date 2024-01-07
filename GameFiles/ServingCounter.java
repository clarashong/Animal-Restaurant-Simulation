import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Serving Counter is a subclass of the Facilities class. 
 * <p>
 * The counter is a stationary object where Chef Gumi will place down finished 
 * food orders so that the server can pick them up and deliver them 
 * to customers. The counter has a certain amount of slots that can fit food, 
 * and the amount of slots increases with upgrades. The counter is also the 
 * spot where Timmy will pass the orders to Gumi to cook. 
 * <p>
 * Graphics: The image for the serving counter is a modified version of the 
 * Casual Log's image from the Animal Restaurant Wiki. The extra items on the 
 * original counter's image have been digitally removed and painted over to fit
 * the simulation's need. Link to the original image: 
 * https://animalrestaurant.fandom.com/wiki/Facilities/Restaurant#Coffee_Bar
 * 
 * @author Clara Hong 
 * @version November 2022
 */
public class ServingCounter extends Facilities
{
    private int numSlots; //number of slots 
    private int filledSlots = 0;
    
    private int[] slotYs; 
    private int level = 1; 
    private int maxLevel = 3;
    
    public ServingCounter() {
        setImage(new GreenfootImage("logCounter.png")); 
        numSlots = level + 4; //number of food slots increases with level
        slotYs = new int[numSlots];   
    }
    
    public void act() {
        genSlotYs();
    }
    
    /**
     * This method generates an array of all the yOffsets for each spot on the counter
     * as wells as the y-position of each slot 
     */
    public void genSlotYs() {
        //the height of each slot
        int slotHeight = (getImage().getHeight())/numSlots;  
        int[] yOffsets = new int[numSlots]; 
        //sets the y-offset of the farthest down slot
        yOffsets[yOffsets.length-1] = slotHeight * (numSlots/2); 
        //for loop that fills the array with all the yOffsets 
        for (int i = yOffsets.length - 2; i >= 0; i--) {
            yOffsets[i] = yOffsets[i + 1] - slotHeight; 
        }
        //for loop that fills the array with the y-position of each slot 
        for (int i = 0; i < slotYs.length; i++ ) {
            //uses the offset to calculate it
            slotYs[i] = getY() + yOffsets[i]; 
        }
    }
    
    /**
     * Generates the y-coordinate of where the food should go (based on slot availability)
     * This helps the waiter/cook know where to put down the dish/cup/food
     */
    public int genFreeSlotLocation(){
        int yOffset; 
        for (int i = 0; i < slotYs.length; i++) {
            yOffset = slotYs[i] - getY(); 
            Food f = (Food)getOneObjectAtOffset(0, yOffset, Food.class);
            if (f == null) {
                return slotYs[i]; 
            }
        }
        //There is no free slot
        return -1; 
    }
    
    public void fillSlot()
    {
        filledSlots++;
    }
    public void emptySlot()
    {
        filledSlots--;
    }
    public int getFilledSlots()
    {
        return filledSlots;
    }
    public int getNumSlots()
    {
        return numSlots;
    }
}

