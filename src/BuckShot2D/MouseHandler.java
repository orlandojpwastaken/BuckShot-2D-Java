package BuckShot2D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseListener, MouseMotionListener {

    public boolean leftClicked;
    public boolean rightClicked;
    private int mouseX;
    private int mouseY;

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(SwingUtilities.isLeftMouseButton(e)){
            if (!leftClicked || rightClicked) {
                leftClicked = true;
                rightClicked = false;
                System.out.println("leftClicked");
            } else {
                leftClicked = false;
                rightClicked = true;
                System.out.println("not leftClicked");
            }

        } if (SwingUtilities.isRightMouseButton(e)){
            if (!rightClicked || leftClicked
            ) {
                rightClicked = true;
                leftClicked = false;
                System.out.println("rightClicked");
            } else {
                rightClicked = false;
                leftClicked = true;
                System.out.println("notRightClicked");
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();

        System.out.println("("+ mouseX +", " + mouseY +")" );
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }
}
