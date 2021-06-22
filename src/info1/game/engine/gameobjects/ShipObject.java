package info1.game.engine.gameobjects;

import info1.game.engine.GameEngine;
import info1.game.engine.listeners.InteractiveGameObject;
import info1.game.utils.Direction;
import info1.game.utils.Vector2D;
import info1.ships.BadCoordException;
import info1.ships.ICoord;
import info1.ships.IShip;
import info1.ships.Ship;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ShipObject extends InteractiveGameObject {

    private Grid grid;
    private Ship ship;
    private Color color = Color.ORANGE;
    private boolean drag = false;
    private Vector2D marginPosition, initPosition;
    private GameEngine engine;

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
        initPosition = position;
        marginPosition = new Vector2D(engine.getMousePosition().x - position.x, engine.getMousePosition().y - position.y);
    }

    @Override
    public void mouseReleased() {
        engine.getGameCanvas().setCursor(Cursor.getDefaultCursor());
        drag = false;

        int x = (int) Math.round((position.x - grid.getBase().x) / grid.getCellSize());
        int y = (int) Math.round((position.y - grid.getBase().y) / grid.getCellSize());

        List<IShip> filteredShips = grid.getShips().stream()
                .filter(s -> !s.equals(ship))
                .collect(Collectors.toList());

        System.out.println("Vérif...");

        System.out.println(Arrays.toString(filteredShips.toArray()));

        System.out.println(filteredShips.size());

        // Vérification
        for(IShip iShip : filteredShips) {
            for(ICoord coord : iShip.getCoords()) {
                if (ship.getCoords().contains(coord)) {
                    System.out.println("test");
                    position = initPosition;
                    return;
                }
            }
        }

        System.out.println("validé");

        // TODO: Quand on change de direction
        try {
            ship.move(x, y, ship.getOrientation());
        } catch (BadCoordException e) {
            position = initPosition;
            System.out.println("opijfgopizjfgoziràj");
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
