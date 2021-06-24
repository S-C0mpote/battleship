package info1.game.engine.gameobjects;

import java.awt.*;

public class LabelRight extends GameObject {

    private String text;
    private final Color color;
    private final float fontsize;


    public LabelRight(String text, Color color, float size) {
        this.text = text;
        this.color = color;
        this.fontsize = size;
    }

    @Override
    public void update(double delta) {}

    @Override
    public void draw(Graphics2D g2d) {
        Font smaller = g2d.getFont().deriveFont(fontsize);
        FontMetrics metrics = g2d.getFontMetrics(smaller);
        int xf = (int) position.x + (size.width - metrics.stringWidth(text));
        int yf = (int) position.y + size.height;

        g2d.setFont(smaller);
        g2d.setColor(color);
        g2d.drawString(text, xf, yf);
    }

    public void setText(String text) {
        this.text = text;
    }
    public String getText(){return this.text;}
    public void setLocation(int x, int y){
        this.position.x = x;
        this.position.y = y;
    }
}
