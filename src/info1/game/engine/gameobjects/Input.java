package info1.game.engine.gameobjects;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input extends GameObject implements KeyListener {

    private final int padding = 5;

    private int width, height;
    private double accumulator = 0;
    private boolean bar = false;
    private String text = "";

    public Input(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void update(double delta) {
        accumulator += delta;

        if(accumulator > 500) {
            bar = !bar;
            accumulator = 0;
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.RED);
        g2d.fillRoundRect((int) position.x, (int) position.y, width, height, 50, 50);

        g2d.setColor(Color.BLACK);
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 20));
        g2d.drawString(text + (bar ? "|" : ""), (int) position.x + padding, (int) position.y + height / 2);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 8 && !text.isEmpty()) {
            text = text.substring(0, text.length() - 1);
            return;
        }

        if(e.getKeyCode() < 20) return;

        text += e.getKeyChar();
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

}

