package info1.game.engine.gameobjects;


import info1.game.resources.Fonts;
import info1.game.utils.Vector2D;
import info1.ships.IShip;

import java.awt.*;
import java.util.List;

public class Grid extends GameObject {
    private List<IShip> ships;

    private int cellSize;
    private Vector2D base;

    public Grid(List<IShip> ships){
        this.ships = ships;
    }

    @Override
    public void update(double delta) {}

    @Override
    public void draw(Graphics2D g2d) {
        int posx = (int) position.x;
        int posy = (int) position.y;

        g2d.setFont(Fonts.MAIN.deriveFont(12f));
        g2d.setColor(new Color(0XF1F1F1));
        g2d.fillRect( (int) position.x , (int) position.y, size.width, size.height);

        //Placement vertical (Lignes)
        for(int i = 1; i < 11; i++){
            posy += cellSize;
            g2d.setColor(Color.BLACK);
            g2d.drawLine(posx ,posy, posx + size.width, posy);
            g2d.setColor(new Color(0xF1F1F1));
            g2d.drawString(i + "", posx - ((int) position.x / 10)  / 2, posy - cellSize / 2);
        }
        int valeur = 65;

        //Placement Horizontal (colonnes)
        for(int i = 0; i < 10; i++){
            g2d.setColor(Color.BLACK);
            g2d.drawLine(posx, (int) position.y, posx, (int) position.y + size.height);
            g2d.setColor(new Color(0xF1F1F1));
            g2d.drawString((char) valeur + "", posx + cellSize / 2, (int) position.y - ((int) position.y / 10) / 2);
            posx += cellSize;
            valeur++;
        }
    }

    @Override
    public void setSize(Dimension size) {
        super.setSize(size);
        cellSize = size.width / 10;
        base = new Vector2D( (1280 / 2d - size.width / 2d),(720 / 2d - size.height / 2d));
    }

    public Vector2D getBase() {
        return base;
    }

    public int getCellSize() {
        return cellSize;
    }

    public List<IShip> getShips() {
        return ships;
    }
}
