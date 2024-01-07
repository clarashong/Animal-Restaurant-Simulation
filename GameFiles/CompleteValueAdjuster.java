import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Creates a bar with two arrows at the side. 
 * The centre bar will display the value to the user, 
 * while the left arrow will decrease the value in the centre 
 * by a given value and the right arrow will increase it by the 
 * same value.
 * <p>
 * The values that the value is increased/decreased by can be seen
 * displayed on the arrows.
 */
public class CompleteValueAdjuster extends Button
{
    private String valName;
    private int originalValue;
    SuperTextBox value;
    ValueAdjuster incr, decr;
    /**
     * @param w World to add actors in
     * @param x Centre of the entire object as a system on the x axis
     * @param y Centre of the entire object as a system on the y axis
     * @param valName The name of what the centre value represents
     * @param f Font that will be used
     * @param max The maximum value you can set it to
     * @param min The minimum value you can set it to
     * @param buttonW The width of the arrows
     * @param buttonH The height of the arrows
     * @param valInt The original centre value.
     * @param changeBy The amount to change the centre value by
     */
    public CompleteValueAdjuster(World w, int x, int y, String valName, Font f, int max, int min, int buttonW, int buttonH, int valInt, int changeBy){
        super(buttonH, buttonW, " ");
        this.valName = valName;
        originalValue = valInt;
        
        value = new SuperTextBox(valName + ": " + valInt, new Color(101, 53, 15), Color.WHITE, f, true, buttonW * 10, 0, new Color(101, 53, 15));
        incr = new ValueAdjuster(buttonW, buttonH, "+" + changeBy, valName, max, min, valInt, changeBy, false, value);
        decr = new ValueAdjuster(buttonW, buttonH, "" + -changeBy, valName, max, min, valInt, -changeBy, true, value);
        
        // Linking the two arrows to one another
        incr.setCorrespondingAdjuster(decr);
        decr.setCorrespondingAdjuster(incr);
        w.addObject(value, x, y);
        w.addObject(incr, x + value.getImage().getWidth() / 2, y);
        w.addObject(decr, x - value.getImage().getWidth() / 2, y);
    }
    
    public void clicked(){}
    
    public int getUpdatedValue(){
        return incr.getNewValue();
    }
}