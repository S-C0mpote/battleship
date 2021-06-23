package info1.game.engine.gameobjects;

import java.awt.*;

public class LabelIndicator extends GameObject {

    private String text;
    private final Color color;
    private final float fontsize;


    public LabelIndicator(String text, Color color, float size, int x, int y) {
        this.text = text;
        this.color = color;
        this.fontsize = size;
        this.position.x = x;
        this.position.y = y;
    }

    @Override
    public void update(double delta) {}

    @Override
    public void draw(Graphics2D g2d) {
        Font smaller = g2d.getFont().deriveFont(fontsize);
        g2d.setFont(smaller);
        g2d.setColor(color);
        int yTemp = (int) position.y;
        for (String line : text.split("\n"))
            g2d.drawString(line, (int) position.x, yTemp += g2d.getFontMetrics().getHeight());
    }

    public void setText(String text) {
        this.text = text;
    }
    public void setLocation(int x, int y){
        this.position.x = x;
        this.position.y = y;
    }
}
