package info1.game.engine.gameobjects;

import info1.game.engine.GameEngine;
import info1.game.engine.GameObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Image implements GameObject {

    private GameEngine gameEngine;
    private java.awt.Image background;
    private int x, y, width, height;

    public Image(GameEngine gameEngine, int x, int y, int width, int height) throws IOException {
        this.gameEngine = gameEngine;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void update(double delta) {

    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(background,0,0, width, height, null);
    }

    public void setBackground(java.awt.Image background) {
        this.background = background;
    }
}
