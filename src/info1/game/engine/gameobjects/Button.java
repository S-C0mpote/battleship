package info1.game.engine.gameobjects;

import info1.game.engine.GameEngine;
import info1.game.engine.GameObject;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Button implements GameObject, MouseListener {

    private GameEngine gameEngine;
    private int x, y, width, height;
    private String name;
    private Font font;

    public Button(GameEngine gameEngine, int x, int y, int width, int height, String name, Font font) {
        this.gameEngine = gameEngine;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.name = name;
        this.font = font;
    }

    @Override
    public void update(double delta) {}

    @Override
    public void draw(Graphics2D g2d) {
        //Draw rectangle color
        g2d.setColor(Color.CYAN);
        g2d.fillRect(x, y, width, height);
        g2d.setColor(Color.BLACK);

        //Draw button string
        FontMetrics metrics = g2d.getFontMetrics(font);
        int xf = x + (width - metrics.stringWidth(name)) / 2;
        int yf = y + ((height - metrics.getHeight()) / 2) + metrics.getAscent();
        g2d.setFont(font);
        g2d.drawString(name, xf, yf);
    }


    public void onButtonClick(Graphics2D g2d) {
        g2d.setBackground(Color.RED);
    }

    public String getName(){return this.name;}

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}