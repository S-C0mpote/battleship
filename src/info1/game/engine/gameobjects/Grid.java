package info1.game.engine.gameobjects;


import info1.game.resources.Fonts;
import info1.game.utils.Vector2D;
import info1.ships.IShip;
import info1.ships.NavyFleet;

import javax.swing.border.StrokeBorder;
import java.awt.*;
import java.util.List;

public class Grid extends GameObject {
    private NavyFleet fleet;

    private int cellSize;
    private Vector2D base;

    private final Color lineColor = new Color(0xF1F1F1);

    public Grid(NavyFleet fleet){
        this.fleet = fleet;
    }
    public Grid(){this.fleet = null;}

    @Override
    public void update(double delta) {}

    @Override
    public void draw(Graphics2D g2d) {
        int posx = (int) position.x;
        int posy = (int) position.y;

        g2d.setFont(Fonts.MAIN.deriveFont(12f));
        g2d.setColor(new Color(255, 255, 255, 50));

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fillRoundRect((int) position.x , (int) position.y, size.width, size.height, 10, 10);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);

        for(int i = 0; i < 10; i++){
            if(i > 0) {
                g2d.setColor(lineColor);
                g2d.drawLine(posx, posy, posx + size.width - 1, posy);
            }

            g2d.setColor(new Color(0xF1F1F1));
            g2d.drawString((i + 1) + "", posx - ((int) position.x / 20), posy + cellSize / 2 + 5);
            posy += cellSize;
        }

        char valeur = 65;
        for(int i = 0; i < 10; i++){
            if(i > 0) {
                g2d.setColor(lineColor);
                g2d.drawLine(posx, (int) position.y, posx, (int) position.y + size.height - 1);
            }

            g2d.setColor(new Color(0xF1F1F1));
            g2d.drawString(valeur + "", posx + cellSize / 2 - 3, (int) position.y - ((int) position.y / 20));
            posx += cellSize;
            valeur++;
        }
    }

    @Override
    public void setSize(Dimension size) {
        super.setSize(size);
        cellSize = size.width / 10;
        base = new Vector2D((1280 / 2d - size.width / 2d), (720 / 2d - size.height / 2d));
    }

    public Vector2D getBase() {
        return base;
    }
    public int getCellSize() {
        return cellSize;
    }
    public NavyFleet getFleet() {return fleet;}
}
