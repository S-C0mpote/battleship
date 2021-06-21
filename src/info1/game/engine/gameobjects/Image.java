package info1.game.engine.gameobjects;

import java.awt.*;
import java.io.IOException;

public class Image extends GameObject {

    private java.awt.Image background;
    private int width, height;

    public Image(int width, int height) throws IOException {
        this.width = width;
        this.height = height;
    }

    @Override
    public void update(double delta) {}

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(background, 0, 0, width, height, null);
    }

    public void setBackground(java.awt.Image background) {
        this.background = background;
    }
}
