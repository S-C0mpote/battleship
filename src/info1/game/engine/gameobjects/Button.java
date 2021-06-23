package info1.game.engine.gameobjects;

import info1.game.engine.listeners.ButtonListener;
import info1.game.engine.listeners.InteractiveGameObject;
import info1.game.resources.Fonts;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Button extends InteractiveGameObject {

    private String name;
    private int yMargin = 0;
    private Color color;
    private boolean clicked = false;

    private ButtonListener listener;

    private BufferedImage currentImg;
    private BufferedImage classicImg;
    private BufferedImage overImg;
    private BufferedImage pressImg;

    public Button(int width, int height, String name, Color color) {
        this.size = new Dimension(width, height);
        this.name = name;
        this.color = color;
    }

    @Override
    public void update(double delta) {
        if(listener != null && clicked) {
            clicked = false;
            listener.onClick();
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(currentImg, (int) position.x, (int) position.y + yMargin, null);

        Font smaller = g2d.getFont().deriveFont(12f);
        FontMetrics metrics = g2d.getFontMetrics(smaller);
        int xf = (int) position.x + (size.width - metrics.stringWidth(name)) / 2;
        int yf = (int) position.y + ((size.height - metrics.getHeight()) / 2) + metrics.getAscent();

        g2d.setFont(smaller);
        g2d.setColor(color);
        g2d.drawString(name, xf, yf + yMargin);
    }

    public String getName(){
        return this.name;
    }

    @Override
    public void mouseOver() {
        currentImg = overImg;
        yMargin = 0;
    }

    @Override
    public void mouseExit() {
        currentImg = classicImg;
        yMargin = 0;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        currentImg = pressImg;
        yMargin = 4;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        currentImg = overImg;
        yMargin = 0;
        clicked = true;
    }

    public void setListener(ButtonListener listener) {
        this.listener = listener;
    }

    public void setOverImg(BufferedImage overImg) {
        this.overImg = overImg;
    }

    public void setClassicImg(BufferedImage classicImg) {
        this.classicImg = classicImg;
        this.currentImg = classicImg;
    }

    public void setPressImg(BufferedImage pressImg) {
        this.pressImg = pressImg;
    }
}
