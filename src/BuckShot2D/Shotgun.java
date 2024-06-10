package BuckShot2D;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

public class Shotgun {

    GamePanel gp;
    // Attributes
    public Integer numOfBullets;
    private Integer nextBullet;

    // Shotgun Things
    public Stack<Integer> loadedBullets = new Stack<>();
    // States
    public boolean isLoaded = false;
    public boolean isReloading = true;

    // Random Number Generator for Shotgun
    Random random= new Random();

    // Constructor
    Shotgun(GamePanel gp){
        this.gp = gp;
    }

    // Getter
    public Integer getNumOfBullets() {return numOfBullets;
    }

    // Custom Methods
    private ArrayList<Integer> generateBullets() {
        /*
        Generate a random set of live and dud bullets.
        Randomly generated bullets will be shown to the players.
         */
        ArrayList<Integer> bulletsToLoad = new ArrayList<>();

        // Insurance so that at least 1 live and 1 dud bullet.
        bulletsToLoad.add(0);
        bulletsToLoad.add(1);

        int numExtraBullets = random.nextInt(6); // How many bullets are generated in a single reload. (2-8)
        for (int i = 0 ; i < numExtraBullets ; i++) {
            // Randomly generates 0 or 1 and adds it to the list of bullets,
            bulletsToLoad.add(random.nextInt(2));
        }
        // Shows the order to the player.
        Collections.sort(bulletsToLoad);
        System.out.println(bulletsToLoad + "\n");
        return bulletsToLoad;
    }

    private void loadBullets(ArrayList<Integer> bullets) {
        /*
        Loads the bullets in a random order.
         */
        System.out.println("Loading shells in a random order... \n");
        while (!bullets.isEmpty()) {
            int randomIndex = random.nextInt(bullets.size());
            int bullet = bullets.remove(randomIndex);
            loadedBullets.push(bullet);
            numOfBullets = loadedBullets.size();
        }
    }

    public void reloadShotgun() {
        /*
        Combines the generateBullets and loadBullets method
        in a single method for code efficiency.
         */
        isReloading = true;
        loadBullets(generateBullets());
        System.out.println("Gun is loaded \n");
        System.out.println(loadedBullets);
        isReloading = false;
    }

    public Integer peekBarrel(){
        // Returns the current bullet in the chamber
        nextBullet = loadedBullets.peek();
        return nextBullet;
    }

    public void updateBullets() {
        /*
        After shooting, removes the bullet from the top of stack
        and subtracts the number of the bullets ian the gun by one.
         */

        loadedBullets.pop();
        numOfBullets--;
        if (getNumOfBullets() == 0){
            isLoaded = false;
        }

        // Debug Print
//        System.out.println("Current arrangement: " + loadedBullets.toString());
//        System.out.println("Number of bullets left in gun: " + loadedBullets.size());
//        System.out.println();
    }

    public boolean shoot() {
        if (peekBarrel() == 1) {
            updateBullets();
            gp.playSoundEffect(1);
            return true; // Shoots live
        } else {
            updateBullets();
            gp.playSoundEffect(0);
            return false; // Shoots blank
        }
    }
}