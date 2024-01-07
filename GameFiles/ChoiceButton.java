import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A button that responds with a true/false when clicked.
 * What the button does is not included and is written outside
 * of the actor.
 * 
 * @author Marco Luong
 */
public class ChoiceButton extends Button
{
    private boolean isClicked;
    GreenfootImage img;
    
    /**
     * @param h         Height of the button
     * @param txt       Centred text that can tell the user what the button does
     * @param fontSize  Size of the text
     * @param font      Colour of the text
     * @param border    Colour of the button border
     * @param fill      Colour of the centre of the button
     */
    public ChoiceButton(int h, String txt, int fontSize, Color font, Color border, Color fill){
        super(fontSize * txt.length() * 2, h, txt);
        isClicked = false;
        int width = fontSize * txt.length() * 2;
        int filledWidth = 4 * (width / 5);
        int filledHeight = 4 * (h / 5);
        
        // Button border
        img = new GreenfootImage(width, h);
        img.setColor(border);
        img.fillRect(0, 0, width, h);
        
        // Button shape
        img.setColor(fill);
        img.fillRect(width / 10, h / 10, filledWidth, filledHeight);
        setImage(img);
        
        // Button text
        getImage().setFont(new Font("Calibri", true, false, fontSize));
        getImage().setColor(font);
        getImage().drawString(txt, width / 2 - (txt.length() * fontSize / 4), h/2);
    }
    
    public void clicked(){
        isClicked = true;
    }
    
    //Lets the button get multiple inputs by resetting it each time it's checked
    public boolean getIsClicked(){
        if(isClicked){
            isClicked = !isClicked;
            return true;
        }
        return false;
    }
}