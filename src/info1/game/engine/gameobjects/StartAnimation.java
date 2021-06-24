package info1.game.engine.gameobjects;

import info1.game.utils.Direction;
import info1.game.utils.Vector2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class StartAnimation extends GameObject {

    private boolean animated = true;
    private Vector2D leftPart = new Vector2D(0, 0);
    private String username1, username2;


    public StartAnimation() {
        leftPart.y = 720 / 2d - 200;
        leftPart.x = -1280;
    }

    @Override
    public void update(double delta) {
        if(animated) {
            if(leftPart.y > 720 / 2d - 100) {
                leftPart.x -= delta * 2;
                leftPart.y += delta / 10d;

                if(leftPart.x <= -1280) {
                    leftPart.x = -1280;
                    animated = false;
                }
            } else {
                leftPart.x += delta * 2;
                leftPart.y += delta / 20d;

                if(leftPart.x > -1280 / 2d) {
                    leftPart.x = -1280 / 2d;
                }
            }
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(new Color(0, 0, 0, .5f));
        g2d.fillPolygon(getPolygon());
    }

    public Polygon getPolygon() {
        Polygon polygon = new Polygon();
        polygon.addPoint((int) leftPart.x + 0, (int) leftPart.y + 0);
        polygon.addPoint((int) leftPart.x + 1280, (int) leftPart.y + 0);
        polygon.addPoint((int) leftPart.x + 1180, (int) leftPart.y + 250);
        polygon.addPoint((int) leftPart.x + -100, (int) leftPart.y + 250);
        return polygon;
    }

    public void setUsername1(String username1) {
        this.username1 = username1;
    }

    public void setUsername2(String username2) {
        this.username2 = username2;
    }

    public void start() {
        animated = true;
    }


}
