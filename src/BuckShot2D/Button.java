package BuckShot2D;

import java.awt.*;

public class Button {
    private String text;
    private int xPos;
    private int yPos;
    private int width;
    private int height;
    private int xPadding;
    private int yPadding;

    GamePanel gp;
    MouseHandler mouseH;
    Color buttonColor = new Color(0,0,0,90);

    public Button(GamePanel gp, MouseHandler mouseH, String text, int xPos, int yPos, int width, int height, int xPadding, int yPadding) {
        this.gp = gp;
        this.mouseH = mouseH;
        setText(text);
        setXPos(xPos);
        setYPos(yPos);
        setWidth(width);
        setHeight(height);
        setXPadding(xPadding);
        setYPadding(yPadding);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getXPos() {return xPos;}

    public void setXPos(int xPos) {this.xPos = xPos;}

    public int getYPos() {
        return yPos;
    }

    public void setYPos(int yPos) {
        this.yPos = yPos;
    }

    public int getWidth() {return width;}

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getXPadding() {
        return xPadding;
    }

    public void setXPadding(int xPadding) {
        this.xPadding = xPadding;
    }

    public int getYPadding() {
        return yPadding;
    }

    public void setYPadding(int yPadding) {
        this.yPadding = yPadding;
    }

    public void updateButton(String Text){
        setText(Text);
    }

    public void draw(Graphics2D g2){
        drawButtonFrame(g2);

        // Text
        g2.setColor(Color.white);
        g2.setFont(new Font("Arial", Font.PLAIN, 30));

        int textX = getXPos() + getXPadding();
        int textY = getYPos() + getYPadding();

        g2.drawString(getText(), textX, textY);

    }

    public void drawButtonFrame(Graphics2D g2) {
        g2.setColor(buttonColor);
        g2.fillRect(getXPos(),getYPos(),getWidth(),getHeight());
        g2.drawRect(getXPos(),getYPos(),getWidth(),getHeight());
        g2.draw3DRect(getXPos(), getYPos(), getWidth(), getHeight(), true);
    }

}
