import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * <h1> Animal Restaurant Simulation </h1>
 * <h3> by: Marco Luong, Joey Guan, Clara Hong, Jaylan Howden </h3>
 * <p>
 * The Animal Restaurant Simulation, as the name implies, is a simulated restaurant<br>
 * run by the animals, for the animals. The simulation starts with the Door class, which<br>
 * is the whole wall, and spawns Customer objects at the doorway. The Customers will <br>
 * pick a table that is unoccupied and think of a order, randomly picked from the <br>
 * menu. Timmy the waiter will come up to Customers that have not ordered, get their order,<br>
 * and repeat them back to chef Gumi. Gumi will receive the orders, cook the foods, and <br>
 * put them on the counter, which will be taken by Timmy to the Customers. The Customers<br>
 * will then eat, dirty the table (probably unintentionally), pay, tip, and leave.<br>
 * <p>
 * <h4> Things to watch out for: </h4>
 * <ul>
 * <li>Staff level up as they do tasks </li> 
 *      <ul>
 *          <li>Timmy can hold more orders in his head</li>
 *          <li>Gumi reduces the cooking time of foods</li>
 *          <li>Apricat can clean more and rest less</li>
 *          <li>They all get 1 more speed at max level (and cute little hats! except for Gumi, who gets a cute bigger hat)</li>
 *      </ul>
 * <li>Foods have have different prices and cooking times, which are correlated.<br>
 *       The higher the price, the longer it takes to cook.</li> 
 *   
 * <li> Customer happiness decides the amount of their tip and review.<br>
 *        happiness is assigned randomly out of 100 with a lower bound of 80</li>
 * <li>Upgrades will prioritize lower level facilities</li> 
 * <li>There is an statistics screen at the end (forgot to show in presentation)</li> 
 * <li>The jazzy Kevin Macleod background music </li> 
 * <li>Our metaphorical blood, sweat, and tears.</li> 
 * </ul>
 * <p>
 * <h3> Credits: </h3> 
 * <p>
 * <h4> Code: </h4>
 * SuperStatBar, SuperTextBox, and bits of SuperSmoothMover <br>
 * by our wonderful teacher mr. Cohen (totally not buttering you up or anything) <br>
 * <p>
 * <h4> Background music: </h4>
 * Night in Venice by Kevin MacLeod | https://incompetech.com/ <br>
 * Music promoted by https://www.chosic.com/free-music/all/ <br>
 * Creative Commons Creative Commons: By Attribution 3.0 License <br>
 * http://creativecommons.org/licenses/by/3.0/ <br>
 * <p>
 * <h4> Graphics: </h4>
 * All characters, food items, facilities, and icons are existing parts of the original game <br>
 * Animal Restaurant and the intellectual property of Wei Wang (also known as DH Games).  <br>
 * All images/assets are versions or edited versions of images from the Animal Restaurant Wiki, a page on fandom.com. <br>
 * Link: https://animalrestaurant.fandom.com/wiki/Animal_Restaurant_Wiki. <br>
 * <p>
 * <h5> From our amazing artist: </h5>
 * Graphics: background for the main game world was drawn by Clara Hong. <br>
 * Dirt: The dirt was drawn by Clara Hong. <br>
 * Emotes: all emotes were drawn by Clara Hong excluding the food icons that were taken from “Recipes” on the Animal Restaurant Wiki. <br>
 * Popup: The popups were created by editing the cod icon from the original game. <br>
 * Gumi’s hat was drawn by Clara Hong. <br>
 * <p>
 * When the characters reach their maximum level, they get their own hat. <br>
 * Graphics for the hats come from “Wearable Mementos” in the Animal Restaurant Wiki. <br>
 * The hats used were: Brown Top Hat, and Antler Knitted Beanie. <br>
 * Link: https://animalrestaurant.fandom.com/wiki/Chef_Gumi#Wearable_Mementos <br>
 * <p>
 * FirstScreen: The main image is an edited version of the tile screen that can be <br>
 * found in Random Game Guides Wiki - Animal Restaurant. <br>
 * Link: https://random-game-guides.fandom.com/wiki/Animal_Restaurant <br>
 * <p>
 * Food and Drinks: all images for the food and drinks class were found on the Animal RestaurantWiki under "Recipes." <br>
 * This includes: Americano, Espresso, and Tea, Taiyaki, Seaweed Rice Ball, Purple Sweet Potato Bun, Adzuki Bean Dumpling, and Bagel. <br>
 * Link: https://animalrestaurant.fandom.com/wiki/Recipes <br>
 * <p>
 * CoffeeBar: The graphics for the Coffee Bar were taken from the Animal Restaurant Wiki's page for "Coffee Bar." <br>
 * These bars include: Casual log, Fine Wooden Table, Elegant Cafe, and VIP Treatment. <br>
 * Link: https://animalrestaurant.fandom.com/wiki/Facilities/Restaurant#Coffee_Bar <br>
 * <p>
 * ServingCounter: The image for the serving counter is a modified version of the <br>
 * Casual Log's image from the Animal Restaurant Wiki. The extra items on the original <br>
 * counter's image have been digitally removed and painted over to fit the simulation's need. <br>
 * Link to the original image: https://animalrestaurant.fandom.com/wiki/Facilities/Restaurant#Coffee_Bar <br>
 * <p>
 * Stove: The images for the stoves were taken from the Animal Restaurant  Wiki in the entry for "Stove." <br>
 * This includes: Small Stone Stove, Fine Stone Stove, Modern Stove, and Advanced Stove. <br>
 * Link: https://animalrestaurant.fandom.com/wiki/Facilities/Kitchen#Stove <br>
 * <p>
 * Table: The Table images are from the Animal restaurant wiki under the names: Small Stump, Small table, Elegant Table, and VIP Table. <br>
 * Link: https://animalrestaurant.fandom.com/wiki/Facilities/Restaurant#Table <br>
 * <p>
 * TipJar: The images for the tip jar were taken from the Animal Restaurant Wiki under the section "Tip Desk." <br>
 * The following tip jar skins were used for the simulation: Stone Bowl, Savings Jar, Piggy Bank, Cash Register. <br>
 * Link: https://animalrestaurant.fandom.com/wiki/Facilities/Restaurant#Tip_Desk <br>
 * <p>
 * Apricat: Apricat's image was taken from the Animal Restaurant Wiki in a section titled "Apricat the Sweeper." <br>
 * Link: https://animalrestaurant.fandom.com/wiki/Apricat_the_Sweeper <br>
 * <p>
 * Gumi: Gumi's image was taken from the Animal Restaurant Wiki in a section titled "Chef Gumi" <br>
 * Link: https://animalrestaurant.fandom.com/wiki/Chef_Gumi <br>
 * <p>
 * Timmy: Timmy’s image was taken from the Animal Restaurant Wiki, in a section titled "Timmy the Server." <br>
 * Link: https://animalrestaurant.fandom.com/wiki/Timmy_the_Server <br>
 * <p>
 * @author Marco Luong, Joey Guan, Clara Hong
 */
public class MyWorld extends World
{
    /* Variables
     * - Set by user before simulation starts:
     * days, funds
     * 
     * - For the top status bar:
     * daysToRun, funds, customersServed, review
     * 
     * - For the end screen:
     * days, funds, customersServed, review, moneyGained, moneySpent
     */
    private static int days, daysToRun, funds, customersServed, moneyGained, moneySpent;
    private static double review, totalReview;

    // General variables
    private int dayTimer, dayLength, transitionTimer;
    private TipJar tJar;
    private Door d;
    private TransitionScreen blinds = new TransitionScreen(false);
    private Font classic;
    private SuperTextBox infoBoxL, infoBoxR;

    private GreenfootSound bgMusic = new GreenfootSound("bg_music.mp3");
    GreenfootImage background = new GreenfootImage("bg.png");

    /**
     * Constructor for objects of class MyWorld.
     * @param daysToRun Value from start screen to determine how long the sim 
     *                  should run for
     * @param funds     Value from start screen that determines how much money 
     *                  the restaurant starts with
     */
    public MyWorld(int daysToRun, int funds)
    {   
        super(1024, 640, 1);
        dayTimer = 0; // counts acts
        dayLength = 3000; // how long each day is
        transitionTimer = 0; // timer for transition between days
        this.daysToRun = daysToRun; // total days to run
        this.funds = funds; // starting money
        started(); // Start up music when world is switched over
        prepare();
    }

    /**
     * Act loop just runs the dayCycle Method.
     */
    public void act(){
        dayCycle();
    }

    /**
     * Starts music world is run
     */
    public void started(){
        bgMusic.setVolume(30);
        bgMusic.playLoop();
    }

    /**
     * Stops music when world is stopped
     */ 
    public void stopped(){
        bgMusic.stop();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        days = 0;
        customersServed = 0;
        moneyGained = 0;
        moneySpent = 0;
        totalReview = 0;

        setBackground(background);
        setPaintOrder(PopUp.class, TransitionScreen.class, Emote.class, Dirt.class, Food.class, SuperStatBar.class, Facilities.class, People.class, SuperTextBox.class,  Door.class);

        // Setting up info boxes at the top of the screen
        classic = new Font ("Times New Roman", true, false, 18);
        String[] infoL = {"~~~~~~", "Days run: " + days, "Cod: " + funds, "~~~~~~"};
        String[] infoR = {"~~~~~~", "Customers served: " + customersServed, "Stars: " + totalReview + " / 5", "~~~~~~"};

        infoBoxL = new SuperTextBox(infoL, Color.BLACK, Color.WHITE, classic, true, 250, 10, new Color(101, 53, 15));
        infoBoxR = new SuperTextBox(infoR, Color.BLACK, Color.WHITE, classic, true, 250, 10, new Color(101, 53, 15));

        infoBoxL.update();
        infoBoxR.update();
        addObject(infoBoxL, 2 * (infoBoxL.getImage().getWidth() / 3), 2 * (infoBoxL.getImage().getHeight() / 3));
        addObject(infoBoxR, 2 * infoBoxR.getImage().getWidth(), 2 * (infoBoxR.getImage().getHeight() / 3));

        //Tables
        addObject(new Table(),500,300);
        addObject(new Table(),700,300);
        addObject(new Table(),900,300);
        addObject(new Table(),500,500);
        addObject(new Table(),700,500);
        addObject(new Table(),900,500);

        //Door
        d = new Door();
        addObject(d, 512, 41); 

        //Tip jar
        addObject(new TipJar(), 950, 150);

        //Stoves
        addObject(new Stove(), 60, 400);
        addObject(new Stove(), 60, 300);
        addObject(new Stove(), 60, 200);

        //CoffeeBar
        addObject(new CoffeeBar(), 200, 530); 

        //Serving Counter
        addObject(new ServingCounter(), 290, 275); 

        //Staff
        addObject(new Gumi(), 200, 200);
        addObject(new Timmy(), 370, 540);  
        addObject(new Apricat(), 300, 530);
    }

    /**
     * Changes the world as the day goes on. 
     * Background changes as it reaches nighttime. 
     * Once each day ends, blinds will partially cover the screen 
     * to upgrade facilities while the store is "closed".
     * 
     * @author Marco Luong
     */
    private void dayCycle(){
        if(dayTimer == dayLength / 2){
            setBackground(new GreenfootImage("bgNight.png")); 
        }
        else if(dayTimer == dayLength / 3){ 
            setBackground(new GreenfootImage("bgSunset.png")); 
        }
        else if(dayTimer >= dayLength){
            d.storeClosed(); // Stop spawns
            if(getObjects(Customer.class).isEmpty()){ // Only trigger if all customers leave
                if(days< daysToRun){
                    if(transitionTimer == 0){
                        blinds = new TransitionScreen(false); // Doesn't stay on the screen
                        addObject(blinds, getWidth() / 2, 0);
                    }

                    // Run while the blinds are up
                    if(!getObjects(TransitionScreen.class).isEmpty()){
                        if(blinds.getY() == getHeight() / 3){ // Upgrade behind blinds
                            days++;
                            dailyUpgrade();
                            days--; // Just to trigger the upgrades, but will mess with the blinds on the last day if not called
                        }
                        if(blinds.doneTransition()){ 
                            days++;
                            updateStatBoxes(); // Day changed

                            removeObject(blinds);
                            PopUp p = new PopUp(new GreenfootImage("board.png"), 200, 125, 30, new Color(204, 184, 159), "Day " + (days + 1));
                            addObject(p, getWidth() / 2, 0 + p.getImage().getHeight() / 2);

                            showNewlyUpgraded(); // Show upgraded facilities
                            d.storeOpen((int) review); // Start spawns
                            dayTimer = 0;
                            transitionTimer = -1;
                        }
                    }
                }
                else{
                    if(transitionTimer == 0){
                        blinds = new TransitionScreen(true);
                        addObject(blinds, getWidth() / 2, 0);
                    }

                    if(blinds.doneTransition()){
                        // trigger end screen
                        removeObject(infoBoxL);
                        removeObject(infoBoxR);
                        endResultDisplay();
                    }
                }
                transitionTimer++;
            }
        }
        dayTimer++;
    }

    /**
     * Displays variables for the end screen: 
     * days, funds, customersServed, review, moneyGained, moneySpent.
     */
    private void endResultDisplay(){
        setPaintOrder(SuperTextBox.class, TransitionScreen.class);

        Color bgC = new Color(38, 38, 38), txtC = new Color(222, 222, 222), borderC = new Color(101, 53, 15);
        classic = new Font ("Times New Roman", true, false, 30);
        String[] info = {"   ", "~~~~~~", "   ", "   ", "Total Days Run: " + days, 
                "Profit: " + funds + " cod", "Total money gained: " + moneyGained, 
                "Total money spent: " + moneySpent, "Total customers served: " + customersServed,
                "Restaurant Review: " + review + " / 5", "   ", "   ", "~~~~~~", "   "};

        SuperTextBox endInfoSummary = new SuperTextBox(info, bgC, txtC, classic, true, 600, 10, borderC);

        endInfoSummary.update();

        addObject(endInfoSummary, getWidth() / 2, getHeight() / 2);
    }

    /**
     * Upgrades any facilities with money in the bank. Prioritizes facilities 
     * of a lower level and makes sure they can't upgrade past the current day.
     */
    private void dailyUpgrade(){
        ArrayList<Facilities> fList = (ArrayList) getObjects(Facilities.class);
        ArrayList<Facilities> priorityFList = new ArrayList<Facilities>();
        int highestLvl = fList.get(0).getLevel(), nextLvl = fList.get(0).getLevel();

        // While there's still x factor of money, continue to upgrade
        // Fill the array 'priorityFList' with facilities of a lower level

        for(Facilities f: fList){
            nextLvl = f.getLevel();
            if(nextLvl > highestLvl){
                highestLvl = nextLvl;
            }
        }

        if(highestLvl != nextLvl){
            for(Facilities f: fList){
                if(f.getLevel() < highestLvl){
                    priorityFList.add(f);
                }
            }
        }

        if(priorityFList.size() > 0){
            for(Facilities f: priorityFList){
                if(!f.atMaxLevel() && f.getLevel() < days + 1 && funds -  f.getUpgradeCost() > 0){
                    f.upgrade();
                    setFunds(-f.getUpgradeCost());
                    updateStatBoxes();
                }
            }
        }

        for(Facilities f: fList){
            if(!f.atMaxLevel() && f.getLevel() < days + 1 && funds -  f.getUpgradeCost() > 0){
                f.upgrade();
                setFunds(-f.getUpgradeCost());
                updateStatBoxes();
            }
        }
    }

    /**
     * An animation when the day starts again to indicate to the 
     * viewer which facilities have been upgraded during the night.
     */
    private void showNewlyUpgraded(){
        ArrayList<Facilities> fList = (ArrayList) getObjects(Facilities.class);

        for(Facilities f: fList){
            if(f.isUpgraded()){
                f.upgradeAni();
            }
        }
    }

    /**
     * Update the two boxes at the top with new information.
     * Used whenever things like a new customer enters the shop happen.
     */
    public void updateStatBoxes(){
        String[] infoL = {"~~~~~~", "Days run: " + days, "Wallet: " + funds, "~~~~~~"};infoBoxL.update();
        String[] infoR = {"~~~~~~", "Customers served: " + customersServed, "Stars: " + review + " / 5", "~~~~~~"};
        infoBoxL.update(infoL);
        infoBoxR.update(infoR);
    }

    /**
     * Checks the funds available.
     */
    public int getFunds(){
        return funds;
    }

    /**
     * Adds or deducts away from the funds.
     * 
     * @param money Amount to add / deduct; positive to add, negative to deduct
     */
    public void setFunds(int money){
        funds += money;

        if(money < 0){
            moneySpent += Math.abs(money);
        }
        else{
            moneyGained += money;
        }
    }

    /**
     * Add one to this counter every time a customer walks in.
     */
    public void newCustomer(){
        customersServed++;
    }

    /**
     * Each time a customer leaves, add to a variable that totals the 
     * review to be divided at the end of the simulation.
     */
    public void newReview(double review){
        totalReview += review;
        this.review = Math.round((totalReview / customersServed * 1.0) * 10.0) / 10.0;
    }

    /**
     * Getter method of daysToRun
     */
    public int getDaysToRun(){
        return daysToRun;
    }

    /**
     * Setter method of daysToRun
     */
    public void setDaysToRun(int numDays){
        daysToRun += numDays;
    }

}
