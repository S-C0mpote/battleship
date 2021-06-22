package info1.game.engine.gameobjects;

import info1.game.engine.GameEngine;
import info1.game.engine.listeners.InteractiveGameObject;
import info1.game.resources.Fonts;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input extends InteractiveGameObject implements KeyListener {

    /*
     * TODO: A CHAQUE UTILISATION DE LA TECHNIQUE OPTIMISATION POSSIBLE
     */

    private final int padding = 20;
    private double accumulator = 0;
    private boolean bar = false;
    private Color background = new Color(0xCDCDCD);
    private Color foreground = new Color(0x0A0A0A);
    private String text = "";
    private boolean active = true; // TODO: Activer lors d'un clique et désactiver lors d'un clique en dehors

    public Input(GameEngine engine) {
        engine.getGameCanvas().addKeyListener(this);
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
        // TODO: Voir si ca lag
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(background);
        g2d.fillRoundRect((int) position.x, (int) position.y, size.width, size.height, 10, 10);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);

        Font smaller = g2d.getFont().deriveFont(20f);
        FontMetrics metrics = g2d.getFontMetrics(smaller);
        int yf = (int) position.y + ((size.height - metrics.getHeight()) / 2) + metrics.getAscent();

        g2d.setFont(smaller);
        g2d.setColor(foreground);
        g2d.drawString(text + (bar ? "|" : ""), (int) position.x + padding, yf);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(!active) return;

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

