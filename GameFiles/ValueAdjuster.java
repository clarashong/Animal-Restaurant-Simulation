import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The arrows on the side of the complete value adjuster. Two value adjusters 
 * are linked together to share a "linked value" and exchange information 
 * based on that. One arrow will add to the value by a certain amount, while 
 * the other subtracts by the same amount.
 * 
 * @author Marco Luong 
 * @version November 2022
 */
public class ValueAdjuster extends Button
{
    private SuperTextBox linkedValue;
    private ValueAdjuster va2;
    private String valName;
    private int max, min, newValue, changeBy;
    private boolean valueChanged;
    GreenfootImage arrow = new GreenfootImage("arrow.png");
    
    /**
     * @param w                 Width of the arrow
     * @param h                 Height of the button
     * @param txt               Used to display how much the value changes by
     * @param valName           Name of the value being changed
     * @param max               Maximum value the user can adjust to
     * @param min               Minimum value the user can adjust to
     * @param originalValue     Inital value of the value being adjusted
     * @param changeBy          How much to adjust by each time it's clicked
     * @param reflected         Facing left/right
     * @param linkedValue       The textbox that displays the value
     */
    public ValueAdjuster(int w, int h, String txt, String valName, int max, int min, int originalValue, int changeBy, boolean reflected, SuperTextBox linkedValue){
        super(w, h, txt);
        setImage(arrow);
        getImage().scale(w, h);
        
        this.linkedValue = linkedValue;
        this.max = max;
        this.min = min;
        this.changeBy = changeBy;
        this.valName = valName;
        newValue = originalValue;
        valueChanged = false;
        
        if(reflected){
            getImage().mirrorHorizontally();
        }
        
        getImage().setFont(new Font("Times New Roman", true, false, 16));
        getImage().setColor(new Color(101, 53, 15));
        getImage().drawString(txt, w/8, 3*(h/5));
    }
    
    // What the arrow does when clicked by the mouse
    public void clicked(){
        newValue += changeBy;
        if(newValue > max || newValue < min){
            if(newValue > max){
                linkedValue.update(valName + " can be set to a maximum of " + max);
            }
            else{
                linkedValue.update(valName + " can be set to a minimum of " + min);
            }
            newValue -= changeBy;
        }
        else{
            linkedValue.update(valName + ": " + newValue);
        }
        va2.setUpdatedValue(newValue);
    }
    
    public void setCorrespondingAdjuster(ValueAdjuster va2){
        this.va2 = va2;
    }
    
    public void setUpdatedValue(int val){
        newValue = val;
    }
    
    public int getNewValue(){
        return newValue;
    }
}

