package info1.game.engine.gameobjects;

import info1.game.engine.GameEngine;
import info1.game.engine.listeners.InteractiveGameObject;
import info1.game.utils.Direction;
import info1.game.utils.Vector2D;
import info1.ships.*;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ShipObject extends InteractiveGameObject {

    private Grid grid;
    private Ship ship;
    private GameEngine engine;
    private double zoom = 0;
    private boolean drag = false;
    private Color color = Color.ORANGE;
    private Vector2D marginPosition = new Vector2D(0,0);

    public ShipObject(Grid grid, Ship ship, GameEngine engine){
        this.engine = engine;
        this.grid = grid;
        this.ship = ship;
        refreshPosition();
    }


    @Override
    public void update(double delta) {
        if(drag && zoom < 10){
            zoom += delta * 0.08;
            if(zoom > 10) zoom = 10;
        } else if(zoom > 0 && !drag){
            zoom -= delta * 0.08;
            if(zoom < 0) zoom = 0;
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        if(drag && engine.getMousePosition() != null){
            position.x = Math.min(Math.max(engine.getMousePosition().x - marginPosition.x, grid.getBase().x), grid.getBase().x + grid.getSize().width - size.width);
            position.y = Math.min(Math.max(engine.getMousePosition().y - marginPosition.y, grid.getBase().y), grid.getBase().y + grid.getSize().height - size.height);
        }

        g2d.setColor(color);
        g2d.fillRect((int) (position.x - zoom), (int) (position.y - zoom), (int) (size.width + zoom * 2), (int) (size.height + zoom * 2));
    }

    public void refreshPosition(){
        if(ship.getOrientation() == Direction.GAUCHE || ship.getOrientation() == Direction.HAUT) {
            position.x = (ship.getBack().getX() - 1) * grid.getCellSize() + grid.getBase().x;
            position.y = (ship.getBack().getY() - 1 ) * grid.getCellSize() + grid.getBase().y;
        }else {
            position.x = (ship.getFront().getX() - 1) * grid.getCellSize() + grid.getBase().x;
            position.y = (ship.getFront().getY() - 1 ) * grid.getCellSize() + grid.getBase().y;
        }


        if(ship.getOrientation() == Direction.DROITE || ship.getOrientation() == Direction.GAUCHE) {
            size.width = ship.getSize() * grid.getCellSize();
            size.height = grid.getCellSize();
        }else {
            size.height = ship.getSize() * grid.getCellSize();
            size.width = grid.getCellSize();
        }
    }

    @Override
    public void mousePressed() {
        if(engine.getMousePosition() == null)return;
        drag = true;
        refreshPosition();
        engine.getGameCanvas().setCursor(new Cursor(Cursor.MOVE_CURSOR));
        marginPosition = new Vector2D(engine.getMousePosition().x - position.x, engine.getMousePosition().y - position.y);
    }

    @Override
    public void mouseReleased() {
        engine.getGameCanvas().setCursor(Cursor.getDefaultCursor());
        drag = false;

        int x = (int) Math.round((position.x - grid.getBase().x) / grid.getCellSize());
        int y = (int) Math.round((position.y - grid.getBase().y) / grid.getCellSize());

        System.out.println(position.x - grid.getBase().x);

        System.out.println(x);
        System.out.println(y);

        try {
            ship.move(x + 1, y + 1, ship.getOrientation(), grid.getFleet());

        } catch (BadCoordException e) {}

        refreshPosition();
    }

    @Override
    public void mouseOver() {
        color = Color.RED;
    }

    @Override
    public void mouseExit() {
        color = Color.ORANGE;
    }

    public boolean isDrag() {
        return drag;
    }

}
