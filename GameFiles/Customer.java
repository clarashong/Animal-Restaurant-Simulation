import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Customers that come into the store. Spawns in the store 
 * looking for a free table to sit at. Once they get to a table, 
 * they sit down, place an order, and pay afterwards.
 * 
 * @author Marco Luong, Joey Guan, Clara Hong
 */
public class Customer extends People
{
    private int actCounter; 

    private ArrayList<Table> tables;
    private Table targetTable = null;
    private Door exit;

    private Food targetFood = null;
    private TipJar tipJar;

    private int moneySpent;
    private int happiness; //happiness out of 100
    private int orderNumber; 
    private Emote emote; 
    private GreenfootImage skin; 

    private boolean seated = false; 
    private boolean thinking = false; 
    private boolean hasOrdered = false; 
    private boolean hasBeenServed = false;
    private boolean hasEaten = false;
    private boolean hasTipped = false;
    
    int speed = 2;

    /**
     * Main constructor
     * 
     * @param exit      Connects the customer with the spawner
     */
    public Customer(Door exit) 
    {
        this.exit = exit;
        happiness = Greenfoot.getRandomNumber(20) + 80; 
        skin = genRandomSkin(); 
        setImage(skin);
    }

    /**
     * Act - do whatever the Customer wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(targetTable == null && !hasEaten)
        {
            findFreeTable();
        }
        if(targetTable != null && !hasEaten)
        {
            targetFood = (Food) getOneIntersectingObject(Food.class);
            if (!seated && !hasOrdered && !hasEaten) {
                moveTowards(targetTable, speed, 0, -60);
                sit(targetTable);
            } else if (!thinking && !hasOrdered) {
                thinkOfOrder(); 
            } else if (hasOrdered && !hasEaten && targetFood != null) {
                eat();
            }
        }
        else
        {
            exit.areTablesOpen(false);
        }
        actCounter++; 
        if (hasEaten) {
            goToTipJar(); 
        } 
    }

    /**
     * Runs through an array of all tables and checks if any are free.
     */
    public void findFreeTable(){
        tables = (ArrayList) getWorld().getObjects(Table.class);
        for(Table t: tables){
            if(t.isFree()){
                targetTable = t;
            }
        }
        if(targetTable != null)
        {
            targetTable.setIsFree(false);
        }
    }

    /**
     * If the customer reaches the table, make it sit behind it.
     * 
     * @param f Targeted table
     */
    private void sit(Table t){
        if(isAt(t, speed, 0, -60))
        {
            seated = true;
        }
    }

    /**
     * Move towards and leave the restaurant through the exit.
     */
    private void leave(){
        turnTowards(exit.getSpawnX(), exit.getSpawnY());
        move(speed);
        setRotation(0);
        if(getY() <= exit.getSpawnY()){
            getWorld().removeObject(this);
        }
    }

    /**
     * A randomized animal customer.
     */
    public GreenfootImage genRandomSkin() {
        int random = Greenfoot.getRandomNumber(4) + 1; 
        GreenfootImage newSkin = new GreenfootImage("customer" + Integer.toString(random) + ".png"); 
        return newSkin; 
    }

    /**
     * A thought bubble that shows what the customer wants to order.
     */
    public void thinkOfOrder() {
        getWorld().removeObject(emote); 
        emote = new Emote("thinking"); 
        getWorld().addObject(emote, getX() - 70, getY() - 40); //thought bubble emote 
        thinking = true; 
    }

    //method can be called by the waiter when they reach this customer 
    public void order() {
        //generates random number for a random order
        int random = Greenfoot.getRandomNumber(5); 
        orderNumber = random;         
        //removes the previous emote 
        getWorld().removeObject(emote); 
        emote = new Emote(random); 
        getWorld().addObject(emote, getX() - 70, getY() - 40); 
        hasOrdered = true; 
        thinking = false; 
    }

    //creates a complaint 
    public void complain() {
        if (targetTable.getDirtiness() >= 1) {
            getWorld().removeObject(emote); 
            emote = new Emote("dirty"); 
            getWorld().addObject(emote, getX() - 70, getY() - 40); 
        } 
    }

    // Eats food
    public void eat()
    {
        if(targetFood.onTable())
        {
            MyWorld w = (MyWorld) getWorld();
            int price = targetFood.getPrice();
            targetFood.consume(); 
            if (targetFood.getConsumed() == true) {
                w.setFunds(price);
                w.newCustomer();
                w.updateStatBoxes();
                w.addObject(new PopUp(price), getX(), getY());

                w.removeObject(targetFood);
                hasEaten = true;
                targetTable.changeDirtiness(1);
            }
        }
    }

    /**
     * Moves towards the tip jar and leaves a tip.
     */
    public void goToTipJar() {
        //fill in 
        //location is at TipJar, leave a Tip then leave 
        MyWorld w = (MyWorld) getWorld();
        tipJar = w.getObjects(TipJar.class).get(0);
        if(hasTipped){
            leave();
        }
        else{
            seated = false;
            targetTable.setIsFree(true);
            if(!isAt(tipJar, speed, -30, 0))
            {
                moveTowards(tipJar, speed, -30, 0);
            }
            else
            {
                leaveTip(w);
            }
        }
    }

    /**
     * based on happiness, tip x amount and add to the funds.
     */
    public void leaveTip(MyWorld w) {
        int tip = happiness / 5; 
        if (happiness > 50) {
            //create popup with tip amount 
            //add to the world's balance 
            w.setFunds(tip);
            w.addObject(new PopUp(tip), getX(), getY());
            hasTipped = true;
        }
        leaveReview(w);
        w.updateStatBoxes();
    }

    /**
     * Leaves a review for the restaurant out of 5 based on happiness.
     */
    public void leaveReview(MyWorld w) {
        double review = happiness / 20; // happiness out of 100, reviews out of 5 
        w.newReview(review);
    }

    public int getOrderNumber() {
        return orderNumber; 
    }
    public boolean getIsThinking() {
        return thinking; 
    }
    public boolean getHasOrdered() {
        return hasOrdered; 
    }
    public boolean getHasBeenServed()
    {
        return hasBeenServed;
    }
    public void setHasBeenServed(boolean b)
    {
        hasBeenServed = b;
    }
    public Table getTargetTable()
    {
        return targetTable;
    }
    public boolean getIsSeated()
    {
        return seated;
    }
}
