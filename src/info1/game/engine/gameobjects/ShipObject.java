package info1.game.engine.gameobjects;

import info1.game.utils.Direction;
import info1.ships.Ship;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class ShipObject extends GameObject {

    private Grid grid;
    private Ship ship;
    private AffineTransform transform;

    /**
     * Dessine un bateau aux bonnes coordonées par rapport à la grille
     *
     * @param grid Grille associé au bateau
     * @param ship Bateau à dessiner
     */
    public ShipObject(Grid grid, Ship ship){
        this.grid = grid;
        this.ship = ship;

        refreshPosition();
    }

    @Override
    public void update(double delta) { }

    @Override
    public void draw(Graphics2D g2d) {
        AffineTransform save = g2d.getTransform();
        g2d.setTransform(transform);
        g2d.drawImage(ship.getImage(), 0, 0, null);
        g2d.setTransform(save);
    }

    public AffineTransform calcTransform() {
        AffineTransform af = new AffineTransform();

        double theta = 0;
        switch (ship.getOrientation()) {
            case LEFT -> {
                theta = -(Math.PI / 2);
                af.translate(position.x, position.y + 13);
            }
            case RIGHT -> {
                theta = (Math.PI / 2);
                af.translate(position.x + (size.width - grid.getCellSize()) + 13,
                        position.y + (size.height - grid.getCellSize()));
            }
            case TOP -> {
                theta = 0;
                af.translate(position.x + (size.width - grid.getCellSize()),
                        position.y + (size.height - grid.getCellSize() * ship.getSize()));
            }
            case BOTTOM -> {
                theta = Math.PI;
                af.translate(position.x + (size.width - grid.getCellSize()) + 13,
                        position.y + (size.height - grid.getCellSize()) + 13);
            }
        }

        af.scale(grid.getCellSize() / 50d, grid.getCellSize() / 50d);
        af.rotate(theta, (grid.getCellSize() / 2d), (grid.getCellSize() / 2d));

        return af;
    }

    public void refreshPosition(){
        if(ship.getOrientation() == Direction.LEFT || ship.getOrientation() == Direction.TOP) {
            position.x = (ship.getBack().getX() - 1) * grid.getCellSize() + grid.getPosition().x;
            position.y = (ship.getBack().getY() - 1 ) * grid.getCellSize() + grid.getPosition().y;
        }
        else {
            position.x = (ship.getFront().getX() - 1) * grid.getCellSize() + grid.getPosition().x;
            position.y = (ship.getFront().getY() - 1 ) * grid.getCellSize() + grid.getPosition().y;
        }

        if(ship.getOrientation() == Direction.RIGHT || ship.getOrientation() == Direction.LEFT) {
            size.width = ship.getSize() * grid.getCellSize();
            size.height = grid.getCellSize();
        }else {
            size.height = ship.getSize() * grid.getCellSize();
            size.width = grid.getCellSize();
        }

        transform = calcTransform();
    }



}
