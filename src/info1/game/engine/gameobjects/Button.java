package info1.game.engine.gameobjects;

import info1.game.engine.listeners.InteractiveGameObject;

import java.awt.*;

public class Button extends InteractiveGameObject {

    private String name;
    private Font font;
    //Image plus tard
    private Color color = Color.CYAN;

    public Button(int width, int height, String name, Font font) {
        this.size = new Dimension(width, height);
        this.name = name;
        this.font = font;
    }

    @Override
    public void update(double delta) {}

    @Override
    public void draw(Graphics2D g2d) {
        //Draw rectangle color

        //TODO : image
        g2d.setColor(color);
        g2d.fillRect((int) position.x, (int) position.y, size.width, size.height);

        //Draw button string
        FontMetrics metrics = g2d.getFontMetrics(font);
        int xf = (int) position.x + (size.width - metrics.stringWidth(name)) / 2;
        int yf = (int) position.y + ((size.height - metrics.getHeight()) / 2) + metrics.getAscent();
        g2d.setFont(font);
        g2d.setColor(Color.BLACK);
        g2d.drawString(name, xf, yf);
    }

    public String getName(){
        return this.name;
    }

    @Override
    public void mouseOver() {
        color = Color.YELLOW;
    }

    @Override
    public void mouseExit() {
        color = Color.CYAN;
    }

    @Override
    public void mousePressed() {
        color = Color.ORANGE;
    }

    @Override
    public void mouseReleased() {
        color = Color.YELLOW;
    }

}
