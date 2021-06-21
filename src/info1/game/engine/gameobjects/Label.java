package info1.game.engine.gameobjects;

import java.awt.*;

public class Label extends GameObject {

    private String text;

    public Label(String text) {
        this.text = text;
    }

    @Override
    public void update(double delta) {}

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.BLUE);
        g2d.drawString(text, (int) position.x, (int) position.y);
    }

    public void setText(String text) {
        this.text = text;
    }
}
