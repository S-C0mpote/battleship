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
    private boolean drag = false;
    private Color color = Color.ORANGE;
    private Vector2D marginPosition, initPosition;

    public ShipObject(Grid grid, Ship ship, GameEngine engine){
        this.engine = engine;
        this.grid = grid;
        this.ship = ship;
        position.x = (ship.getFront().getX() - 1) * grid.getCellSize() + grid.getBase().x;
        position.y = (ship.getFront().getY()  - 1 )* grid.getCellSize() + grid.getBase().y;

        if(ship.getOrientation() == Direction.DROITE || ship.getOrientation() == Direction.GAUCHE) {
            size.width = ship.getSize() * grid.getCellSize();
            size.height = grid.getCellSize();
        }

        else if(ship.getOrientation() == Direction.BAS || ship.getOrientation() == Direction.HAUT){
            size.height = ship.getSize() * grid.getCellSize();
            size.width = grid.getCellSize();
        }
    }


    @Override
    public void update(double delta) { }

    @Override
    public void draw(Graphics2D g2d) {
        if(drag && engine.getMousePosition() != null){
            position.x = Math.min(Math.max(engine.getMousePosition().x - marginPosition.x, grid.getBase().x), grid.getBase().x + grid.getSize().width - size.width);
            position.y = Math.min(Math.max(engine.getMousePosition().y - marginPosition.y, grid.getBase().y), grid.getBase().y + grid.getSize().height - size.height);
        }

        g2d.setColor(color);
        g2d.fillRect((int) position.x, (int) position.y, size.width,size.height);
    }

    @Override
    public void mousePressed() {
        drag = true;
        engine.getGameCanvas().setCursor(new Cursor(Cursor.MOVE_CURSOR));
        initPosition = position.copy();
        marginPosition = new Vector2D(engine.getMousePosition().x - position.x, engine.getMousePosition().y - position.y);
    }

    @Override
    public void mouseReleased() {
        engine.getGameCanvas().setCursor(Cursor.getDefaultCursor());
        drag = false;

        int x = (int) Math.round((position.x - grid.getBase().x) / grid.getCellSize());
        int y = (int) Math.round((position.y - grid.getBase().y) / grid.getCellSize());

        try {
            ship.move(x, y, ship.getOrientation(), grid.getFleet());
        } catch (BadCoordException e) {
            position = initPosition;
        }
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
