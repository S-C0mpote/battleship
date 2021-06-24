package info1.game.engine.gameobjects.ui;

import info1.game.engine.gameobjects.GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Image extends GameObject {

    private BufferedImage image;
    private int width, height;

    public Image(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void update(double delta) {}

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(image, 0, 0, width, height, null);
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
