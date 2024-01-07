import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Gumi is a subclass of Staff, the chef of the restaurant.
 * He will receive food orders from Timmy, cook them, and
 * place them on the counter for pickup and serving.
 * 
 * @author Joey Guan
 * @version November, 2022
 */
public class Gumi extends Staff
{
    private int actCounter = 0;

    private GreenfootImage img = new GreenfootImage("Gumi.png"); // 
    private ArrayList<Stove> stoves;
    private Stove targetStove;
    private ArrayList<Food> foods;
    private Food targetFood;

    private ArrayList<Integer> orderNumbers = new ArrayList<Integer>();    

    private ServingCounter servingCounter;
    private int reduceCookTimeBy = 0;
    private boolean carryingFood = false;
    public Gumi()
    {
        GreenfootImage image = getImage();
        image.scale(110,110);
        setImage(image);
    }

    public void act()
    {
        servingCounter = getWorld().getObjects(ServingCounter.class).get(0);
        findFreeStove(); //Looks for a stove that isn't cooking food already
        if(!carryingFood) 
        {
            findCookedFood(); //Looks for food that's finished cooking and not on the counter yet
        }
        if(targetFood != null && servingCounter.getFilledSlots() != servingCounter.getNumSlots()) //There's food to be moved, and servingCounter isn't full
        {
            moveTowardsAndTakeFood();//Picks up a food
            moveTowardsCounter();//Moves and places food at the counter
        }
        else if(targetStove != null && orderNumbers.size() != 0)//There's a stove that's free and not all orders are done cooking
        {
            moveTowards(targetStove, speed, 100, 0);
            if(orderNumbers.size() != 0 && isAt(targetStove, speed, 100, 0)) //If next to stove
            {
                targetStove.cookFood(new Food(orderNumbers.get(0), reduceCookTimeBy));
                orderNumbers.remove(0);
                expUp();
                if(exp == maxExp && lvl < maxLvl){
                    lvlUp();
                }
            }
        }
        actCounter++;
    }

    public void findFreeStove()
    {
        stoves = (ArrayList) getWorld().getObjects(Stove.class);
        targetStove = null;

        for(Stove s: stoves){
            if(!s.isCooking()){
                targetStove = s;
            }
        }
    }

    public void findCookedFood()
    {
        foods = (ArrayList) getWorld().getObjects(Food.class);
        targetFood = null;

        for(Food f: foods){
            if(f.isCooked() && !f.onServingCounter()){
                targetFood = f;
            }
        }
    }

    public void receiveOrders(ArrayList<Integer> ordersFromTimmy)
    {
        for(int i = 0; i < ordersFromTimmy.size(); i++)
        {
            orderNumbers.add(ordersFromTimmy.get(i));
        }
    }
    
    public void moveTowardsAndTakeFood()
    {
        if(isAt(targetFood, speed, 100, 0))
        {
            targetFood.setLocation(getX(), getY());
            targetFood.getStove().setIsCooking(false);
            carryingFood = true;
        }
        if(carryingFood)
        {
            targetFood.setLocation(getX(), getY());
        }
        else
        {
            moveTowards(targetFood, speed, 100, 0);
        }
    }

    public void moveTowardsCounter()
    {
        if(carryingFood && !isAt(servingCounter, speed, -50, 0))
        {
            moveTowards(servingCounter, speed, -50, 0);
        }
        else if(carryingFood && isAt(servingCounter, speed, -50, 0))
        {
            targetFood.setLocation(servingCounter.getX()-20, servingCounter.genFreeSlotLocation());
            targetFood.setOnCounter(true);
            servingCounter.fillSlot();
            carryingFood = false;
        }
    }
    
    public void lvlUp(){
        lvl++;
        if(lvl == maxLvl){
            img = new GreenfootImage("GumiMax.png");
            img.scale(110,110);
            setImage(img);
            speed++;
        }
        
        reduceCookTimeBy += 50;
        exp = 0;
        maxExp += changeExpBy;
        expBar.update(exp);
    }
}
