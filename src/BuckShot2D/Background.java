package BuckShot2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Background {
    GamePanel gp;
    BufferedImage bgImage;

    public Background(GamePanel gp){
        this.gp = gp;

        setBgImage();
    }
    public void setBgImage(){
        try {
            bgImage = ImageIO.read(getClass().getResourceAsStream("/background.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics2D g2){
        g2.drawImage(bgImage, 0, 70, 1280, 720, null);
    }

}
