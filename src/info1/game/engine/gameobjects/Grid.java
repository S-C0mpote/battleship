package info1.game.engine.gameobjects;

import info1.game.engine.listeners.InteractiveGameObject;

import java.awt.*;

public class Grid extends InteractiveGameObject {

    private final Dimension size = new Dimension(450,450);


    public Grid(){
    }

    @Override
    public void setSize(Dimension size) {
        super.setSize(size);
    }

    @Override
    public void update(double delta) {}

    @Override
    public void draw(Graphics2D g2d) {

        int posx = (int) position.x;
        int posy = (int) position.y;

        g2d.setColor(new Color(0XF1F1F1));
        g2d.fillRect( (int) position.x - size.width / 2, (int) position.y, size.width, size.height);

        for(int i = 1; i < 11; i++){
            posy += size.height / 10;
            g2d.setColor(Color.BLACK);
            g2d.drawLine(posx - size.width / 2  ,posy, posx + size.width / 2, posy);
            g2d.setColor(new Color(0xF1F1F1));
            g2d.drawString(i + "", posx - size.width / 2 - ((int)position.x / 10) / 2, posy - (size.height / 10) / 2);
        }
        int valeur = 65;
        for(int i = 0; i < 10; i++){
            posx += size.width / 10;
            g2d.setColor(Color.BLACK);
            g2d.drawLine(posx - size.width / 2, posy - size.height, posx - size.width / 2, posy);
            g2d.setColor(new Color(0xF1F1F1));
            g2d.drawString((char)valeur + "", posx - size.width / 2 - ((int)position.x / 10) / 2, posy - size.height - ((int)position.y / 10) / 2);
            valeur++;
        }
    }
}
