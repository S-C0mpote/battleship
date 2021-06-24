package info1.game.engine.gameobjects.ui;

import info1.game.engine.gameobjects.GameObject;
import info1.game.resources.Fonts;
import info1.game.utils.Direction;
import info1.game.utils.Vector2D;

import java.awt.*;

public class Label extends GameObject {

    private final Color color;
    private final Direction direction;
    private final Font font;

    private String text;
    private boolean build = false;
    private int xf, yf, fontHeight;

    public Label(String text, Color color, float size, Direction direction) {
        this.text = text;
        this.color = color;
        this.direction = direction;
        this.font = Fonts.MAIN.deriveFont(size);
    }

    public Label(String text, Color color, float size){
        this(text, color, size, Direction.LEFT);
    }

    @Override
    public void update(double delta) {}

    @Override
    public void draw(Graphics2D g2d) {
        if(!build) {
            FontMetrics metrics = g2d.getFontMetrics(font);
            fontHeight = metrics.getHeight();

            switch (direction) {
                case LEFT -> {
                    xf = 0;
                    yf = (size.height - metrics.getAscent());
                }
                case CENTER -> {
                    xf = (size.width - metrics.stringWidth(text)) / 2;
                    yf = ((size.height - metrics.getHeight()) / 2) + metrics.getAscent();
                }
                case RIGHT -> {
                    xf = (size.width - metrics.stringWidth(text));
                    yf = size.height;
                }
            }

            build = true;
        }

        g2d.setFont(font);
        g2d.setColor(color);

        int yTemp = 0;
        for (String line : text.split("\n")) {
            g2d.drawString(line, (int) position.x + xf, (int) position.y + yf + yTemp);
            yTemp += fontHeight;
        }

    }

    public void setText(String text) {
        this.text = text;
        build = false;
    }
    public String getText(){ return this.text; }
    public void setLocation(int x, int y){
        this.position.x = x;
        this.position.y = y;
    }
    public Vector2D getLocation(){return new Vector2D(this.position.x, this.position.y);}
}
