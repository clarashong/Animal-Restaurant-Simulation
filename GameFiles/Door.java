import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The 'spawner' for customers. Runs on a random number generator 
 * by a 'spawn rate' that is adjusted by how high the restaurant 
 * review is.
 * 
 * @author Clara Hong, Marco Luong 
 * @version November 21, 2022
 */
public class Door extends Facilities
{
    private GreenfootImage wall; 
    private int doorX = 845, doorY = 90; //x and y of where the door is (for spawning) 
    private int spawnRate = 250; //should be made to change based on restaurant rating/reputation
    private int spawnQueue = 1, queueDelay = 0;
    private boolean closed = false, tablesOpen = true;

    public Door() {
        setImage(new GreenfootImage("wall.png")); 
    }

    public void act()
    {
        spawn(); 
    }

    public void spawn() {
        if(spawnQueue > 0 && tablesOpen && queueDelay >= 300){
            getWorld().addObject(new Customer(this), doorX, doorY);
            spawnQueue--;
            queueDelay = 0;
        }
        else if (Greenfoot.getRandomNumber(spawnRate) == 0 && !closed) {
            if(tablesOpen){
                getWorld().addObject(new Customer(this), doorX, doorY);
            }
            else{
                spawnQueue++;
            }
        }
        if(tablesOpen && !closed){
            queueDelay++;
        }
        else{
            queueDelay = 0;
        }
    }

    /**
     * Closes the store by stopping spawns and clearing the queue.
     */
    public void storeClosed(){
        closed = true;
        spawnQueue = 0;
        queueDelay = 0;
    }

    /**
     * Reopens the store by resuming spawns and changing the 
     * spawnRate by the review of the store (the higher, the faster). 
     */
    public void storeOpen(int review){
        closed = false;
        tablesOpen = true;
        spawnQueue = 1;
        queueDelay = 0;
        spawnRate = 250 / review;
    }

    public void areTablesOpen(boolean x){
        tablesOpen = x;
    }

    public void setSpawnRate(int newRate) {
        spawnRate = newRate;
    }

    public int getSpawnX(){
        return doorX;
    }

    public int getSpawnY(){
        return doorY;
    }
}
