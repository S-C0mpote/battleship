package info1.game.engine.gameobjects;

import info1.game.utils.Direction;
import info1.game.utils.Vector2D;

import java.awt.*;

public class Label extends GameObject{

    private String text;
    private final Color color;
    private final float fontsize;
    private Direction direction;

    public Label(String text, Color color, float size, Direction direction) {
        this.text = text;
        this.color = color;
        this.fontsize = size;
        this.direction = direction;
    }

    public Label(String text, Color color, float size){
        this(text,color,size,null);
    }

    @Override
    public void update(double delta) {}

    @Override
    public void draw(Graphics2D g2d) {
        //Déplacer / Opti, cc Maxandre
        Font smaller = g2d.getFont().deriveFont(fontsize);
        FontMetrics metrics = g2d.getFontMetrics(smaller);
        g2d.setFont(smaller);
        g2d.setColor(color);

        if (direction == null){
            int yTemp = (int) position.y;
            for (String line : text.split("\n"))
                g2d.drawString(line, (int) position.x, yTemp += g2d.getFontMetrics().getHeight());
        }
        else {
            switch (direction) {
                case LEFT -> {
                    int xfLeft = (int) position.x;
                    int yfLeft = (int) position.y + (size.height - metrics.getAscent());
                    g2d.setFont(smaller);
                    g2d.setColor(color);
                    g2d.drawString(text, xfLeft, yfLeft);
                }
                case CENTER -> {
                    int xfCenter = (int) position.x + (size.width - metrics.stringWidth(text)) / 2;
                    int yfCenter = (int) position.y + ((size.height - metrics.getHeight()) / 2) + metrics.getAscent();
                    g2d.setFont(smaller);
                    g2d.setColor(color);
                    g2d.drawString(text, xfCenter, yfCenter);
                }
                case RIGHT -> {
                    int xfRight = (int) position.x + (size.width - metrics.stringWidth(text));
                    int yfRight = (int) position.y + size.height;
                    g2d.setFont(smaller);
                    g2d.setColor(color);
                    g2d.drawString(text, xfRight, yfRight);
                }
            }
        }
    }

    public void setText(String text) {this.text = text;}
    public String getText(){return this.text;}
    public void setLocation(int x, int y){
        this.position.x = x;
        this.position.y = y;
    }
    public Vector2D getLocation(){return new Vector2D(this.position.x, this.position.y);}
}
