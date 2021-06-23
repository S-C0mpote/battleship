package info1.game.engine.gameobjects;

import info1.game.engine.GameEngine;
import info1.game.engine.listeners.InteractiveGameObject;
import info1.game.utils.Direction;
import info1.game.utils.Vector2D;
import info1.ships.BadCoordException;
import info1.ships.Ship;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;

public class ShipObject extends InteractiveGameObject {

    private Grid grid;
    private Ship ship;
    private GameEngine engine;
    private double zoom = 1;
    private boolean drag = false;
    private Vector2D marginPosition = new Vector2D(0,0);

    public ShipObject(Grid grid, Ship ship, GameEngine engine){
        this.engine = engine;
        this.grid = grid;
        this.ship = ship;

        refreshPosition();
    }

    @Override
    public void update(double delta) {
        if(drag && zoom < 1.2){
            zoom += delta * 0.008;
            if(zoom > 1.2) zoom = 1.2;
        } else if(zoom > 1 && !drag){
            zoom -= delta * 0.008;
            if(zoom < 1) zoom = 1;
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        if(drag && engine.getMousePosition() != null){
            position.x = Math.min(
                    Math.max(engine.getMousePosition().x - marginPosition.x, grid.getBase().x),
                    grid.getBase().x + grid.getSize().width - size.width);
            position.y = Math.min(
                    Math.max(engine.getMousePosition().y - marginPosition.y, grid.getBase().y),
                    grid.getBase().y + grid.getSize().height - size.height);
        }


        AffineTransform save = g2d.getTransform();
        g2d.setTransform(this.getTransform());
        g2d.drawImage(ship.getImage(), 0, 0, null);
        g2d.setTransform(save);
    }

    public AffineTransform getTransform() {
        AffineTransform af = new AffineTransform();

        double theta = 0;
        if(ship.getOrientation() == Direction.LEFT) theta = -(Math.PI / 2);
        else if(ship.getOrientation() == Direction.RIGHT) theta = (Math.PI / 2);
        else if(ship.getOrientation() == Direction.BOTTOM) theta = 0;
        else if(ship.getOrientation() == Direction.TOP) theta = Math.PI;

        if(ship.getOrientation() == Direction.LEFT || ship.getOrientation() == Direction.BOTTOM) {
            af.translate(
                    position.x + grid.getCellSize() - zoom * grid.getCellSize(),
                    position.y + grid.getCellSize() - zoom * grid.getCellSize());
        }else {
            af.translate(
                    position.x + (size.width - grid.getCellSize()) + grid.getCellSize() - zoom * grid.getCellSize(),
                    position.y + (size.height - grid.getCellSize()) + grid.getCellSize() - zoom * grid.getCellSize());
        }

        af.rotate(theta,
                    (grid.getCellSize() / 2d),
                    (grid.getCellSize() / 2d));


        return af;
    }

    public void refreshPosition(){
        if(ship.getOrientation() == Direction.LEFT || ship.getOrientation() == Direction.BOTTOM) {
            position.x = (ship.getBack().getX() - 1) * grid.getCellSize() + grid.getBase().x;
            position.y = (ship.getBack().getY() - 1 ) * grid.getCellSize() + grid.getBase().y;
        }
        else {
            position.x = (ship.getFront().getX() - 1) * grid.getCellSize() + grid.getBase().x;
            position.y = (ship.getFront().getY() - 1 ) * grid.getCellSize() + grid.getBase().y;
        }

        if(ship.getOrientation() == Direction.RIGHT || ship.getOrientation() == Direction.LEFT) {
            size.width = ship.getSize() * grid.getCellSize();
            size.height = grid.getCellSize();
        }else {
            size.height = ship.getSize() * grid.getCellSize();
            size.width = grid.getCellSize();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == 3 && !drag) {
            int x = (int) Math.round((position.x - grid.getBase().x) / grid.getCellSize());
            int y = (int) Math.round((position.y - grid.getBase().y) / grid.getCellSize());

            try {
                Direction from = ship.getOrientation();
                Direction to = from.getNext();

                ship.move(x + 1, y + 1, to, grid.getFleet());
            } catch (BadCoordException ignored) {}

            refreshPosition();
        }

        if(engine.getMousePosition() == null) return;

        if(e.getButton() == 1) {
            drag = true;
            refreshPosition();
            engine.getGameCanvas().setCursor(new Cursor(Cursor.MOVE_CURSOR));
            marginPosition = new Vector2D(engine.getMousePosition().x - position.x, engine.getMousePosition().y - position.y);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        if(e.getButton() == 3) {
            this.setPressed(true);
            return;
        }

        engine.getGameCanvas().setCursor(Cursor.getDefaultCursor());
        drag = false;

        int x = (int) Math.round((position.x - grid.getBase().x) / grid.getCellSize());
        int y = (int) Math.round((position.y - grid.getBase().y) / grid.getCellSize());

        try {
            ship.move(x + 1, y + 1, ship.getOrientation(), grid.getFleet());

        } catch (BadCoordException ignored) {}

        refreshPosition();
    }

    @Override
    public void mouseOver() {}

    @Override
    public void mouseExit() {}

    public boolean isDrag() {
        return drag;
    }


}
