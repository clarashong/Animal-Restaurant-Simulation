import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Timmy is the server and a subclass of the Staff class. He takes orders from customers who want to order food. 
 * He then communicates the orders to Chef Gumi, then delivers the food to 
 * the corresponding customer. 
 * <p>
 * Timmy's level/experience as a staff member correlates to the number of orders
 * that he can handle at a time. 
 * <p>
 * 
 * @author Clara Hong, Joey Guan
 * @version November 2022 
 */
public class Timmy extends Staff
{
    private ServingCounter logCounter; 
    private ArrayList<Customer> customers;
    private Customer targetCustomer = null;

    private ArrayList<Food> foods;
    private Food targetFood;

    private ArrayList<Customer> customersToBeServed;
    private boolean carryingFood;

    private GreenfootImage timmyLeft = new GreenfootImage("Timmy.png"); 
    private GreenfootImage timmyRight = new GreenfootImage("Timmy.png"); 

    private int maxOrderCapacity = 2; 
    private int ordersInProgress = 0; 
    private ArrayList<Integer> orderList = new ArrayList<Integer>(); 

    private boolean atCounter; 
    private int speakingCooldown = 40; 
    private int speakingOrderNum = 0; 
    private int ordersSpoken = 0; 
    private boolean doneSpeaking = true; 

    public Timmy(){
        timmyLeft.scale(100,120);
        timmyRight.scale(100,120); 
        timmyRight.mirrorHorizontally(); 
        setImage(timmyLeft); 

        //serving Counter related variables 
    }

    /**
     * Act - do whatever the People wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {        
        if(!carryingFood)
        {
            findFoodOnCounter(); //Looks for food that's on the servingCounter
        }

        //Takes food to customer if there's food on the counter
        if(targetFood != null && doneSpeaking) //doneSpeaking so that moving food doesn't interrupt speaking orders
        {
            takeFoodToCustomer();
        } 
        else if (ordersInProgress < maxOrderCapacity) { // gets orders from customers if there's no food to transport
            findOccupiedTable();
            goToCustomersAndTakeOrders();
            customers = (ArrayList) getWorld().getObjects(Customer.class);
            if(customers.size() != 0 && customers.size() < maxOrderCapacity && customers.size() == ordersInProgress) // For when there aren't enough customers to get to maxOrderCapacity
            {
                goToCounterAndRelayOrders();
            }
        } else { // tells orders to the chef after getting orders
            goToCounterAndRelayOrders();
        } 
    }

    //waiter tries to find a table with someone sitting at it in order to take their order 
    public void findOccupiedTable() {
        customers = (ArrayList) getWorld().getObjects(Customer.class);
        targetCustomer = null;

        for(Customer c: customers){
            if(c.getIsThinking()  && !c.getHasOrdered()) {
                targetCustomer = c;
            }
        }
    }

    public void goToCustomersAndTakeOrders()
    {
        if (targetCustomer != null) {
            //animation
            if (targetCustomer.getX() > this.getX()) { 
                setImage(timmyRight); 
            }  else {
                setImage(timmyLeft); 
            }
            if (isAt(targetCustomer, speed, -50, 0) && !targetCustomer.getHasOrdered()) {
                takeOrder(); 
                expUp();
                if(exp >= maxExp && lvl < maxLvl){
                    lvlUp();
                }
            }
            else
            {
                moveTowards(targetCustomer, speed, -50, 0); 
            }
        } 
    }

    public void takeOrder() {
        targetCustomer.order(); 
        int order = targetCustomer.getOrderNumber(); 
        Integer x = Integer.valueOf(order); 
        orderList.add(x); 
        ordersInProgress++; 
    }

    public boolean checkIfAtCounter() {
        if (isAt(logCounter, speed, 100, 0)) {
            if (ordersInProgress == 0) {
                return false; 
            }
            return true; 
        } else {
            return false; 
        }
    }

    public void goToCounterAndRelayOrders()
    {
        doneSpeaking = false; 
        logCounter = getWorld().getObjects(ServingCounter.class).get(0); 
        if (!checkIfAtCounter()) {
            moveTowards(logCounter, speed, 100, 0);  
            setImage(timmyLeft);
        } else if (!doneSpeaking){
            sayOrders();
        }
    }

    public void sayOrders() {
        if (!doneSpeaking) {
            //get food number from order list
            int a = orderList.get(speakingOrderNum); 
            Emote emote = new Emote(a); 
            //use emote to state order to the chef, wait for time in between orders
            if (speakingCooldown == 40) {
                getWorld().addObject(emote, getX() - 70, getY() - 40); 
                ordersSpoken++; 
            } else if (speakingCooldown == 0) {
                speakingOrderNum++; 
                speakingCooldown = 100; 
            }
        }
        if (speakingOrderNum == ordersInProgress) { 
            //number of orders in progress should reset when the orders are stated
            ordersInProgress = 0;
            speakingOrderNum = 0; 

            Gumi gumi = getWorld().getObjects(Gumi.class).get(0);
            gumi.receiveOrders(orderList); // tells chef orders before clearing

            orderList.clear();
            ordersSpoken = 0; 
            doneSpeaking = true; 
        }  
        speakingCooldown--; 
    }

    public void findFoodOnCounter()
    {
        foods = (ArrayList) getWorld().getObjects(Food.class);
        targetFood = null;

        for(Food f: foods){
            if(f.onServingCounter() && !f.onTable()){
                targetFood = f;
            }
        }
    }

    public void findCustomerWithOrder(int i)
    {
        customersToBeServed = (ArrayList) getWorld().getObjects(Customer.class);
        targetCustomer = null;

        for(Customer c: customers){
            if(c.getOrderNumber() == i && c.getHasOrdered() && !c.getHasBeenServed() && c.getIsSeated())
            {
                targetCustomer = c;
            }
        }
    }

    public void takeFoodToCustomer()
    {
        if(isAt(targetFood, speed, 50, 0))
        {
            targetFood.setLocation(getX()-20, getY());
            carryingFood = true;
        }
        if(carryingFood)
        {
            findCustomerWithOrder(targetFood.getIndex());
            serveCustomer(); //method below
        }
        else
        {
            moveTowards(targetFood, speed, 50, 0);
            setImage(timmyLeft);
        }
    }

    public void serveCustomer()
    {
        if(targetCustomer != null && isAt(targetCustomer, speed, -50, 0))
        {
            Table table = (Table) targetCustomer.getTargetTable();
            targetFood.setLocation(table.getX(), table.getY()-30);
            targetFood.setOnTable(true);
            targetCustomer.setHasBeenServed(true);
            carryingFood = false;

            logCounter.emptySlot();//minus one to occupied slots on the servingCounter
        }
        else if(targetCustomer != null)
        {
            moveTowards(targetCustomer, speed, -50, 0);
            //Animation
            if (this.getX() < targetCustomer.getX()) {
                setImage(timmyRight); 
                targetFood.setLocation(getX()+20, getY());
            }  else {
                setImage(timmyLeft); 
                targetFood.setLocation(getX()-20, getY());
            }
        }
    }

    public void lvlUp(){
        lvl++;
        if(lvl == maxLvl){
            timmyLeft = new GreenfootImage("TimmyMax.png");
            timmyRight = new GreenfootImage("TimmyMax.png");

            timmyLeft.scale(100,120);
            timmyRight.scale(100,120); 
            timmyRight.mirrorHorizontally();

            speed++;
        }

        maxOrderCapacity++;
        exp = 0;
        maxExp += changeExpBy;
        expBar.update(exp);
    }
}