package BuckShot2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class DealerEntity extends Entity {

    GamePanel gp;
    MouseHandler mouseH;
    Shotgun shotgun;
    Random random = new Random();
    public BufferedImage happy,angry,hands,gun;
    public String healthState;
    public String gunState;

    public DealerEntity(GamePanel gp, MouseHandler mouseH, Shotgun shotgun) {
        super(gp, mouseH, shotgun);

        setDealerValues();
        getDealerImage();
    }

    public void setDealerValues(){
        setName("Dealer");
        setHealth(6);
        healthState = "happy";
        gunState = "hands";
    }

    public void getDealerImage(){
        try {
            System.out.println("Trying to load assets");
            happy = ImageIO.read(getClass().getResourceAsStream("/dealer/dealerhappy.png"));
            angry = ImageIO.read(getClass().getResourceAsStream("/dealer/dealermad.png"));
            hands = ImageIO.read(getClass().getResourceAsStream("/dealer/dealerhands.png"));
            gun = ImageIO.read(getClass().getResourceAsStream("/dealer/dealergun.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void makeChoice() {
        if (isDead) {
            inAction = false;
            System.out.println("turn ended cus dealer died");
        } else {
            inAction = true;
        }
        update();
        if (inAction) {
            if (opponent.isDead) {
                inAction = false;
                System.out.println("turn ended cus player died");
            } else {
                System.out.println("Dealer Health: " + getHealth());
                System.out.println("It's Dealer's Turn");
                    /*
                    If the dealer can move, it will go through
                    the AI thinking algorithm which is described as the following:
                    1. Will use as many items as they can
                    2. Will act accordingly if they know what the current shell is.
                    3. Otherwise, will flip a coin to randomly decide who to shoot.
                     */// If dealer doesn't know what the next shot is, dealer will flip a coin to decide
                    System.out.println("Dealer is flipping a coin");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                int decision = random.nextInt(2); // Coin flip to make decision to either shoot self or player.
                    if (decision == 0) {
                        shootSelf();
                    }
                    if (decision == 1) {
                        shootOpp();
                    }
                // Check on whether Dealer can move again after action.
                if (shotSelfWithDud) {
                    inAction = true;
                    shotSelfWithDud = false;
                }
            }
        }
        opponent.inAction = true;
        update();
    }

    public void update() {
        if (getHealth() == 6) {
            healthState = "happy";
        } else if (getHealth() <= 5) {
            healthState = "angry";
        }

        if (inAction) {
            gunState = "shotgun";
        } else {
            gunState = "hands";
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage imageDealer = null;
        BufferedImage imageHands = null;

        switch (healthState) {
            case "happy" :
                imageDealer = happy;
                break;
            case "angry":
                imageDealer = angry;
                break;
        }

        switch (gunState) {
            case "hands" :
                imageHands = hands;
                break;
            case "shotgun":
                imageHands = gun;
                break;
        }

        g2.drawImage(imageDealer, 0,70, 1280, 720, null);
        g2.drawImage(imageHands, 0,70,1280,720,null);


    }
}
