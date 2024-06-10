package BuckShot2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Scanner;

public class PlayerEntity extends Entity {

    GamePanel gp;
    MouseHandler mouseH;
    BufferedImage tableShotgun;

    public PlayerEntity (GamePanel gp, MouseHandler mouseH, Shotgun shotgun) {
        super(gp, mouseH, shotgun);
        this.gp = gp;
        this.mouseH = this.gp.mouseH;

        setPlayerValues();
        getPlayerImages();
    }

    public void setPlayerValues(){
        setHealth(6);
        setName("Player");
    }

    public void getPlayerImages() {
        try {
            tableShotgun = ImageIO.read(getClass().getResourceAsStream("/tablegun.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void takeAction(){
        if (isDead) {
            inAction = false;
            System.out.println("turn ended cause you died");
        } else {inAction = true;}
        if (inAction) {
            update();
            if (opponent.isDead) {
                inAction = false;
                System.out.println("turn ended cause dealer died");
            } else {
                System.out.println("Current health: " + getHealth());
                System.out.println("Do something:");
                Button yourTurn = new Button(gp, mouseH, "Your Turn!", 500, 500, 200, 100, 10, 20);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (this.mouseH.leftClicked) {
                        shootSelf();
                    } else if (this.mouseH.rightClicked) {
                        shootOpp();
                    }
            }
        }
        opponent.inAction = true;
        update();
    }

    public void update() {

    }

    public void draw(Graphics2D g2) {
        if (inAction) {
            g2.drawImage(tableShotgun,410, 410, 359,380, null);
        } else {
        }
    }
}
