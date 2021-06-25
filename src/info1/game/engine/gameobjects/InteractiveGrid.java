package info1.game.engine.gameobjects;

import com.mashape.unirest.http.exceptions.UnirestException;
import info1.game.engine.GameEngine;
import info1.game.engine.Scenes;
import info1.game.engine.listeners.InteractiveGameObject;
import info1.game.resources.Fonts;
import info1.game.resources.Images;
import info1.game.utils.Vector2D;
import info1.ships.BadCoordException;
import info1.ships.Coord;
import info1.ships.ICoord;
import info1.ships.NavyFleet;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class InteractiveGrid extends InteractiveGameObject {

    private final Color lineColor = new Color(215, 52, 52);

    private int cellSize;
    private ICoord coord;
    private GameEngine engine;
    private List<Vector2D> hit = new ArrayList<>();
    private List<Vector2D> miss = new ArrayList<>();
    private boolean isOver = false;
    private boolean isTurn = false;
    public InteractiveGrid(GameEngine engine){this.engine = engine;}

    @Override
    public void update(double delta) {}

    @Override
    public void draw(Graphics2D g2d) {
        int posx = (int) position.x;
        int posy = (int) position.y;

        g2d.setFont(Fonts.MAIN.deriveFont(12f));

        if(!isTurn) g2d.setColor(new Color(153, 17, 17, 100));
        else g2d.setColor(new Color(215, 52, 52, 100));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fillRoundRect((int) position.x , (int) position.y, size.width, size.height, 10, 10);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);

        for(int i = 0; i < 10; i++){
            if(i > 0) {
                g2d.setColor(lineColor);
                g2d.drawLine(posx, posy, posx + size.width - 1, posy);
            }

            g2d.setColor(new Color(0xF1F1F1));
            g2d.drawString((i + 1) + "", posx - ((int) position.x / 20) + cellSize - 35, posy + cellSize / 2 + 5);
            posy += cellSize;
        }

        char valeur = 65;
        for(int i = 0; i < 10; i++){
            if(i > 0) {
                g2d.setColor(lineColor);
                g2d.drawLine(posx, (int) position.y, posx, (int) position.y + size.height - 1);
            }
            g2d.setColor(new Color(0xF1F1F1));
            g2d.drawString(valeur + "", posx + cellSize / 2 - 3, (int) position.y - ((int) position.y / 20) - 5);
            posx += cellSize;
            valeur++;
        }

        for(Vector2D vector : hit)
            g2d.drawImage(Images.HIT, (int) vector.x, (int) vector.y, null);

        for(Vector2D vector : miss)
            g2d.drawImage(Images.MISS, (int) vector.x, (int) vector.y, null);


        if(engine.getMousePosition() != null && isOver && isTurn) {
            int xCoord = (engine.getMousePosition().x - 1 - (int) position.x) / cellSize;
            int yCoord = (engine.getMousePosition().y - 1 - (int) position.y) / cellSize;

            g2d.setColor(new Color(1f, 1f, 1f, 0.2f));
            g2d.fillRect(
                    xCoord * cellSize + (int) position.x,
                    yCoord * cellSize + (int) position.y,
                    cellSize, cellSize);
        }

    }

    @Override
    public void setSize(Dimension size) {
        super.setSize(size);
        cellSize = size.width / 10;
    }

    @Override
    public void mousePressed(MouseEvent event){
        if(!isTurn) return;

        int coordXclicked = (engine.getMousePosition().x - 1 - (int) position.x) / cellSize + 1;
        int coordYclicked = (engine.getMousePosition().y - 1 - (int) position.y) / cellSize + 1;

        Vector2D cellClicked = new Vector2D(coordXclicked * cellSize + position.x - cellSize,
                                coordYclicked * cellSize + position.y - cellSize);

        try {
            coord = new Coord(intPosToStr(coordXclicked, coordYclicked));

            if(hit.contains(cellClicked) || miss.contains(cellClicked)) return;

            int play = engine.getNetwork().play(coord);

            if(play == 0){
                miss.add(cellClicked);
                Scenes.GAME.getLabelCoule().setText("Manqué");
            } else if(play == 1 || play == 10 || play == 100){
                hit.add(cellClicked);
                Scenes.GAME.getLabelRemaining().setText("" + hit.size() + " / 20");
                Scenes.GAME.getLabelCoule().setText("Touché");

                if(play == 10){
                    Scenes.GAME.getLabelRemaining().setText("" + hit.size() + " / 20");
                    Scenes.GAME.getLabelCoule().setText("Coulé");
                }
                if(play == 100){
                }
            }

            Scenes.GAME.enemyTurn();
        } catch (BadCoordException | UnirestException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mouseOver() {
        isOver = true;
    }

    @Override
    public void mouseExit() {
        isOver = false;
    }

    public void clear() {
        hit.clear();
        miss.clear();
    }

    private String intPosToStr(int x, int y) {
        return String.valueOf((char) (x + 64)) + y;
    }

    public ICoord getSelectedCoords(){
        return coord;
    }

    public void setTurn(boolean turn) {
        isTurn = turn;
    }
}
