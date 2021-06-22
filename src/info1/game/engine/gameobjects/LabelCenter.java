package info1.game.engine.gameobjects;

import java.awt.*;

public class LabelCenter extends GameObject {

    private String text;
    private final Color color;
    private final float fontsize;


    public LabelCenter(String text, Color color, float size) {
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
        int xf = (int) position.x + (size.width - metrics.stringWidth(text)) / 2;
        int yf = (int) position.y + ((size.height - metrics.getHeight()) / 2) + metrics.getAscent();

        g2d.setFont(smaller);
        g2d.setColor(color);
        g2d.drawString(text, xf, yf);
    }

    public void setText(String text) {
        this.text = text;
    }
}
