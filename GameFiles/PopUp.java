import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 *  PopUps are created by Customers when they finish eating, 
 *  indicating payment and the amount of money earned.
 *  An additional constructor allows them to be created
 *  with a custom image for various different purposes.
 *  They will decrease in transparency and move upwards 
 *  and are removed when transparency gets low enough.
 *  
 *  
 * @author Joey Guan, Marco Luong
 * @version November 21, 2022
 */
public class PopUp extends Actor
{
    private int num = 255, numChange;
    private boolean slow;
    
    /**
     * Constructor for payment PopUps
     * 
     * @param price The amount of money paid by a customer
     */
    public PopUp(int price)
    {
        slow = false;
        numChange = 10;
        Font font = new Font(false, false, 20);
        GreenfootImage image = new GreenfootImage("codPopup.png");
        
        image.setFont(font);
        image.setColor(new Color(235, 234, 218));
        image.drawString(Integer.toString(price), 70, 21);
        setImage(image);
    }
    
    /**
     * Constructor for custom image PopUps with a specified
     * String of text layered on it.
     * 
     * @param img        A specified GreenfootImage
     * @param scaleW     Width of img
     * @param scaleL     Length of img
     * @param fontSize   Size of text
     * @param c          Color of text
     * @param line       Text that you want to show
     * 
     */
    public PopUp(GreenfootImage img, int scaleW, int scaleL, int fontSize, Color c, String line)
    {
        slow = true;
        numChange = 1;
        Font font = new Font("Calibri", true, false, fontSize);
        
        img.scale(scaleW, scaleL);
        setImage(img);
        img.setFont(font);
        img.setColor(c);
        img.drawString(line, getImage().getWidth() / 2 - (line.length() * fontSize / 4), getImage().getHeight() / 2);
    }
    
    public void act()
    {
        num -= numChange;
        setLocation(getX(), getY()-1);
        if(num > 0)
        {
            getImage().setTransparency(num);
        }
        else
        {
            getWorld().removeObject(this);
        }
    }
}

