package info1.game.engine.gameobjects;

import info1.game.engine.utils.Vector2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MenuBackground extends GameObject {

    private static BufferedImage img;
    private double x = 0;

    public MenuBackground() {
        if(img == null) {
            try { img = ImageIO.read(new File("assets/ui/background.jpg")); }
            catch (IOException e) { e.printStackTrace(); }
        }
    }

    @Override
    public void update(double delta) {
        x = (x + delta / 50) % 1280;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(img, (int) x, 0, null);
        g2d.drawImage(img, (int) x - 1280, 0, null);
    }

}
