package info1.game.engine.gameobjects;


import info1.game.engine.gameobjects.ui.Label;
import info1.game.utils.Vector2D;
import java.awt.*;
import java.util.Arrays;


public class StartAnimation extends GameObject {

    private boolean animated = false;
    private Vector2D leftPart = new Vector2D(0, 0);
    private Vector2D rightPart = new Vector2D(0, 0);
    private String username1, username2;
    private Vector2D speed = new Vector2D(10, 10);
    private double acc = 0;

    @Override
    public void update(double delta) {
        if(animated) {
            if(leftPart.y == 720 / 2d - 300) {
                if (leftPart.x > -1280 / 2d - 40) {
                    leftPart.x += delta / speed.x;
                    speed.x += 0.07 * delta;
                } else {
                    leftPart.x += speed.x / delta;
                }

                if (leftPart.x > -1280 / 2d - 20) {
                    leftPart.y++;
                }
            }else {
                if (leftPart.y < 720 / 2d - 100 && leftPart.y > 720 / 2d - 120) {
                    leftPart.y += delta / speed.y;
                    speed.y += 0.0001 * delta;
                } else {
                    if (leftPart.y >= 720 / 2d - 100) {
                        if(acc >= 1500) {
                            leftPart.y += delta * 1.005 * 4;
                            leftPart.x -= (delta / 2.1) * 4;
                        } else {
                            acc += delta;
                        }
                    } else {
                        leftPart.y += delta * 1.005;
                    }
                }

                if (leftPart.x < -1280) {
                    leftPart.x = -1280;
                    animated = false;
                }
            }

            rightPart.x = -leftPart.x;
            rightPart.y = 720 - leftPart.y - 100;
        }
    }


    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(new Color(0f, 0f, 0f, .7f));
        g2d.fillPolygon(getPolygon(leftPart));
        g2d.fillPolygon(getPolygon(rightPart));

        Font test = g2d.getFont().deriveFont(50f);
        FontMetrics metrics = g2d.getFontMetrics(test);

        int username1Width = (metrics.stringWidth(username1));
        int yf = ((250 - metrics.getHeight()) / 2) + metrics.getAscent();

        g2d.setFont(test);

        g2d.setColor(new Color(0x007BFF));
        g2d.drawString(username1, (int) (leftPart.x + 1200 - username1Width), (int) leftPart.y + yf);
        g2d.setColor(new Color(0xD73434));
        g2d.drawString(username2, (int) rightPart.x, (int) rightPart.y + yf);
    }

    public Polygon getPolygon(Vector2D location) {
        Polygon polygon = new Polygon();
        polygon.addPoint((int) location.x, (int) location.y);
        polygon.addPoint((int) location.x + 1280, (int) location.y);
        polygon.addPoint((int) location.x + 1180, (int) location.y + 250);
        polygon.addPoint((int) location.x + -100, (int) location.y + 250);

        return polygon;
    }

    public void setUsername1(String username1) {
        this.username1 = username1;
    }

    public void setUsername2(String username2) {
        this.username2 = username2;
    }

    public void start() {
        leftPart.y = 720 / 2d - 300;
        leftPart.x = -1280;
        acc = 0;
        animated = true;
    }


}
