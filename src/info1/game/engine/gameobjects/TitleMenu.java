package info1.game.engine.gameobjects;

import info1.game.engine.GameEngine;
import info1.game.resources.Images;

import java.awt.*;

public class TitleMenu extends GameObject{

    private int direction = 1;
    private float speed = 100;

    public TitleMenu(GameEngine gameEngine) {
        this.position.x = (gameEngine.getGameCanvas().getWidth() / 2d) - 405 / 2d;
    }

    @Override
    public void update(double delta) {
        if(position.y >= 30){
            direction = 1;
            speed = 90;
        }

        if(position.y <= 0){
            direction = -1;
            speed = 90;
        }

        position.y += (delta / (double) speed) * -direction;

        if((position.y >= 15 && direction == -1) || (position.y <= 15 && direction == 1)) speed += delta / 11;
        if((position.y < 15 && direction == -1) || (position.y > 15 && direction == 1)) speed -= delta / 10;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.RED);
        g2d.drawImage(Images.MENU_LOGO, (int) position.x, (int) position.y + 60, null);
    }
}
