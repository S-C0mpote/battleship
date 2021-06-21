package info1.game.engine.gameobjects;

import java.awt.*;

public class LabelGame extends GameObject {

    private String text;
    private final Color color;
    private final float size;

    public LabelGame(String text, Color color, float size) {
        this.text = text;
        this.color = color;
        this.size = size;
    }

    @Override
    public void update(double delta) {}

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setFont(g2d.getFont().deriveFont(size));
        g2d.setColor(color);
        g2d.drawString(text, (int) position.x, (int) position.y);
    }

    public void setText(String text) {
        this.text = text;
    }
}
