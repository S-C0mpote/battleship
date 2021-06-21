package info1.game.engine.gameobjects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MenuBackground extends GameObject {

    private static BufferedImage img;

    public MenuBackground() {
        if(img == null) {
            try { img = ImageIO.read(new File("assets/ui/background.jpg")); }
            catch (IOException e) { e.printStackTrace(); }
        }
    }

    @Override
    public void update(double delta) {
        position.x = (position.x + delta / 50) % 1280;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(img, (int) position.x, 0, null);
        g2d.drawImage(img, (int) position.x - 1280, 0, null);
    }

}
