package BuckShot2D;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    final int screenWidth = 1280;
    final int screenHeight = 790;

    // FPS
    int FPS = 60;
    MouseHandler mouseH = new MouseHandler();
    Sound sound = new Sound();

    BattleHandler bh = new BattleHandler(this);
    Background background = new Background(this);
    Button selfButton = new Button(this, mouseH, "Your Health: " + bh.player.getHealth(), 825, 650, 300, 100, 50, 60);
    Button oppButton = new Button(this, mouseH, "Dealer Health: "+ bh.dealer.getHealth(), 150, 650, 300, 100,60, 60);
    Thread gameThread;



    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addMouseListener(mouseH);
        this.addMouseMotionListener(mouseH);
        this.setFocusable(true);

    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null){

            update();

            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        oppButton.updateButton("Dealer Health: " + bh.dealer.getHealth());
        selfButton.updateButton("Your Health: " + bh.player.getHealth());
        bh.update();
        repaint();
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        // Draw Background
        background.draw(g2);

        // Draw Dealer
        bh.dealer.draw(g2);

        // Draw Player
        bh.player.draw(g2);

        // Draw Button
        selfButton.draw(g2);
        oppButton.draw(g2);

        g2.dispose();
    }

    public void playSoundEffect(int i) {
        sound.setFile(i);
        sound.play();
    }

}
