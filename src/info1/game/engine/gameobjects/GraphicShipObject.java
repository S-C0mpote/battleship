package info1.game.engine.gameobjects;

import info1.game.engine.GameEngine;
import info1.game.utils.Direction;

import info1.ships.Ship;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class GraphicShipObject extends GameObject {
    private Grid grid;
    private Ship ship;
    private GameEngine engine;

    public GraphicShipObject(Grid grid, Ship ship, GameEngine engine){
        this.engine = engine;
        this.grid = grid;
        this.ship = ship;

        refreshPosition();
    }

    @Override
    public void update(double delta) { }

    @Override
    public void draw(Graphics2D g2d) {
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

        System.out.println(ship.getOrientation());
        if(ship.getOrientation() == Direction.LEFT) {
            af.translate(position.x, position.y);
        }
        else if(ship.getOrientation() == Direction.BOTTOM){
            af.translate(
                    position.x + (size.width - grid.getCellSize()),
                    position.y + (size.height - grid.getCellSize()));
        }
        else if(ship.getOrientation() == Direction.TOP){
            af.translate(
                    position.x + (size.width - grid.getCellSize() / 2d) - 5,
                    position.y + (size.height - grid.getCellSize() / 2d) - 5);
        }
        else if(ship.getOrientation() == Direction.RIGHT){
            af.translate(
                    position.x + (size.width - grid.getCellSize() + grid.getCellSize() / 2d),
                    position.y + (size.height - grid.getCellSize()));
        }
        af.scale(grid.getCellSize() / 50d, grid.getCellSize() / 50d);
        af.rotate(theta, (grid.getCellSize() / 2d), (grid.getCellSize() / 2d));

        return af;
    }

    public void refreshPosition(){
        if(ship.getOrientation() == Direction.LEFT || ship.getOrientation() == Direction.BOTTOM) {
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
        System.out.println("grid : " + grid.getCellSize());
        System.out.println(grid.getPosition());
    }



}
