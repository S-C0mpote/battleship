package info1.game.engine.gameobjects.ui;

import info1.game.engine.GameEngine;
import info1.game.engine.listeners.InteractiveGameObject;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

public class Input extends InteractiveGameObject implements KeyListener {

    private final GameEngine engine;
    private final Color background = new Color(0xCDCDCD);
    private final Color foreground = new Color(0x2A2A2A);

    private int padding = 20;
    private double accumulator = 0;
    private boolean bar = false;
    private String text = "";
    private float fontSize = 20f;
    private boolean active = false;
    private int limit = 100;

    /**
     * Zone de texte
     */
    public Input(GameEngine engine) {
        this.engine = engine;
    }

    @Override
    public void update(double delta) {
        if(active) {
            accumulator += delta;

            if(accumulator > 500) {
                bar = !bar;
                accumulator = 0;
            }
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(background);
        g2d.fillRoundRect((int) position.x, (int) position.y, size.width, size.height, 10, 10);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);

        Font smaller = g2d.getFont().deriveFont(fontSize);
        FontMetrics metrics = g2d.getFontMetrics(smaller);
        int yf = (int) position.y + ((size.height - metrics.getHeight()) / 2) + metrics.getAscent();

        g2d.setFont(smaller);
        g2d.setColor(foreground);
        g2d.drawString(text + (bar ? "|" : ""), (int) position.x + padding, yf);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        engine.getCurrentScene().getInteractiveGO().forEach((i, igo) -> {
            if(igo instanceof Input)
                ((Input) igo).setActive(false);
        });

        active = true;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(!active) return;

        if(e.getKeyCode() == 8 && !text.isEmpty()) {
            text = text.substring(0, text.length() - 1);
            return;
        }

        if(e.getKeyCode() < 20) return;
        if(text.length() >= limit) return;

        text += e.getKeyChar();
    }

    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

    public void setText(String text) { this.text = text; }
    public void setFontSize(float fontSize) { this.fontSize = fontSize; }
    public void setPadding(int padding) { this.padding = padding; }
    public void setLimit(int limit){ this.limit = limit; }
    public void setActive(boolean active) {
        this.active = active;
        this.accumulator = 1000;
    }

    public String getText() { return text; }

}

