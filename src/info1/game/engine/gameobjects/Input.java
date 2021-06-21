package info1.game.engine.gameobjects;

import info1.game.engine.GameEngine;
import info1.game.engine.GameObject;

import java.awt.*;

public class Input implements GameObject {

    private GameEngine gameEngine;
    private int x, y, width, height;

    public Input(GameEngine gameEngine, int x, int y, int width, int height) {
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
        g2d.setColor(Color.GREEN);
        g2d.fillRect(x, y, width, height);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
