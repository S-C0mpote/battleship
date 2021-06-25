package info1.game.engine.gameobjects.ui;

import info1.game.engine.gameobjects.GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Affiche une image de taille {@link GameObject#getSize()} et de position {@link GameObject#getPosition()} ()}
 */
public class Image extends GameObject {

    private BufferedImage image;

    @Override
    public void update(double delta) {}

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(image, (int) position.x, (int) position.y, size.width, size.height, null);
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
