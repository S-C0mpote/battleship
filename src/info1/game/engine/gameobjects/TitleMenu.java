package info1.game.engine.gameobjects;

import info1.game.engine.GameEngine;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TitleMenu extends GameObject{

    private static BufferedImage img;
    private double y = 10;
    private GameEngine gameEngine;
    private final int WIDTH = 500, HEIGHT = 100;
    private boolean direction = false;
    private float vitesse = 28;

    public TitleMenu(GameEngine gameEngine) {
        if(img == null) {
            try { img = ImageIO.read(new File("assets/ui/background.jpg")); }
            catch (IOException e) { e.printStackTrace(); }
        }
        this.gameEngine = gameEngine;
    }

    @Override
    public void update(double delta) {
        if(y >= 60){
            direction = false;
            vitesse = 28;
        }

        if(y <= 9){
            direction = true;
            vitesse = 28;
        }

        if(direction) {
            y += delta / (double) vitesse;

        }
        else {
            y -= delta / (double)vitesse;

            if(y <= 30)
                vitesse *= 1.00000008;
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.RED);
        g2d.drawRect((gameEngine.getGameCanvas().getWidth()/2)-WIDTH/2, (int)y + 30, WIDTH,HEIGHT);
    }
}
