package info1.game.engine.gameobjects;


import info1.game.utils.Vector2D;
import java.awt.*;


public class StartAnimation extends GameObject {

    private boolean animated = true;
    private Vector2D leftPart = new Vector2D(0, 0);
    private String username1, username2;
    private double vitesse = 200;
    private double vitesse2 = 10;
    private int part = 1;


    public StartAnimation() {
        leftPart.y = 720 / 2d - 300;
        leftPart.x = -1280;
    }

    @Override
    public void update(double delta) {
        if(animated) {
            if(leftPart.y >= 720 / 2d - 300) {
                System.out.println("condition 1 : " + leftPart.y);

                if(leftPart.y < 720 / 2d && leftPart.y > 720 / 2d - 20){
                    leftPart.y += delta * (delta/ vitesse);
                    vitesse *= 1.00001 ;
                }else{
                    leftPart.y += delta * 1.005;
                    if(leftPart.y >= 720 / 2d) leftPart.x -= vitesse / delta;
                }

                if(leftPart.x <= -1280) {
                    leftPart.x = -1280;
                    animated = false;
                    part = 1;
                }
            }else{
                System.out.println("condition 2 : " + leftPart.y);
                if(leftPart.y == 720 / 2d - 300) {
                    if (leftPart.x > -1280 + 640 - 10) {
                        leftPart.x += delta * (delta / vitesse2);
                        vitesse2 *= 1.001;
                    } else {
                        leftPart.x += vitesse2 / delta;
                    }

                    if (leftPart.x > -1280 / 2d) {
                        leftPart.x = -1280 / 2d;
                    }
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
