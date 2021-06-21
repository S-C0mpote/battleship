package info1.game.engine.gameobjects;

import info1.game.engine.GameEngine;
import info1.game.engine.GameObject;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class Input implements GameObject, KeyListener {

    private final GameEngine gameEngine;
    private final int padding = 5;

    private int x, y, width, height;
    private double accumulator = 0;
    private boolean bar = false;


    private String text = "test";

    public Input(GameEngine gameEngine, int x, int y, int width, int height) {
        this.gameEngine = gameEngine;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void update(double delta) {
        accumulator += delta;

        if(accumulator > 0.5) {
            bar = !bar;

            accumulator = 0;
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.RED);
        g2d.fillRect(x, y, width, height);

        g2d.setColor(Color.BLACK);
        g2d.drawString(text + (bar ? "|" : ""), x + padding, y + height / 2);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        text += e.getKeyChar();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }

    public int getHeight() { return height; }
    public int getWidth() { return width; }
    public void setHeight(int height) { this.height = height; }
    public void setWidth(int width) { this.width = width; }
}

