package info1.ships;

import info1.game.utils.Direction;
import info1.game.utils.Vector2D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * une implémentation "abstraite" d'un bateau quelconque, de taille indéterminée
 */


public abstract class Ship implements IShip {

    private List<ICoord> coords = new ArrayList<>();
    private ICoord front, back;
    private String name;
    private Direction direction;

    /**
     * NB : LA SIGNATURE DU CONSTRUCTEUR DOIT ETRE RESPECTEE
     *
     * construit un bateau quelconque
     * @param name le nom du bateau
     * @param ayFront la coordonnée de la proue du bateau
     * @param ayBack la coordonnée de la poupe du bateau
     * @throws BadCoordException si l'une des coordonnées données ne définit pas une coordonnée alphanumérique correcte
     * @throws CoordsBadShipException si les coordonnées données ne permettent pas de définir un bateau correct :
     *  une ligne, une colonne, de la bonne taille, etc.
     */
    public Ship(String name, String ayFront, String ayBack) throws BadCoordException, CoordsBadShipException {
        this.name = name;

        front = new Coord(ayFront);
        back = new Coord(ayBack);

        if(front.getX() == back.getX()) {
            if(front.getY() > back.getY()) { // e.g. A8 A5
                for(int y = front.getY(); y >= back.getY(); y--) {
                    coords.add(new Coord(intPosToStr(front.getX(), y)));
                }
                direction = Direction.HAUT;
            } else { // e.g. A5 A8 || A5 A5
                for(int y = front.getY(); y <= back.getY(); y++) {
                    coords.add(new Coord(intPosToStr(front.getX(), y)));
                }
                direction = Direction.BAS;
            }
        } else if(front.getY() == back.getY()) {
            if(front.getX() > back.getX()) { // e.g. C5 A5
                for(int x = front.getX(); x >= back.getX(); x--) {
                    coords.add(new Coord(intPosToStr(x, front.getY())));
                }
                direction = Direction.GAUCHE;
            } else { // e.g. A5 C5 || C5 C5
                for(int x = front.getX(); x <= back.getX(); x++) {
                    coords.add(new Coord(intPosToStr(x, front.getY())));
                }
                direction = Direction.DROITE;
            }
        }

        if(this.getSize() != this.getCategory().getSize()) throw new CoordsBadShipException();
    }

    private String intPosToStr(int x, int y) {
        return String.valueOf((char) (x + 64)) + y;
    }

    @Override
    public List<ICoord> getCoords() {
        return coords;
    }

    @Override
    public ICoord getFront() {
        return front;
    }

    @Override
    public ICoord getBack() {
        return back;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        return coords.size();
    }

    @Override
    public String toString() {
        return name + " [" + front + " -> " + back + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ship ship = (Ship) o;
        return Objects.equals(front, ship.front) &&
                Objects.equals(back, ship.back) &&
                Objects.equals(name, ship.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(front, back, name);
    }

    public Direction getOrientation() { return direction; }

    /**
     * @param x doit respecter 1 <= x <= 10
     * @param y doit respecter 1 <= y <= 10
     */
    public void move(int x, int y, Direction direction, NavyFleet fleet) throws BadCoordException {
        List<ICoord> coords = new ArrayList<>();

        switch (direction) {
            case HAUT -> {
                for (int i = getSize() - 1; i >= 0; i--)
                    coords.add(new Coord(intPosToStr(x, y + i)));
            }
            case BAS -> {
                for (int i = 0; i < getSize(); i++)
                    coords.add(new Coord(intPosToStr(x, y + i)));
            }
            case GAUCHE -> {
                for (int i = getSize() - 1; i >= 0; i--)
                    coords.add(new Coord(intPosToStr(x + i, y)));
            }
            case DROITE -> {
                for (int i = 0; i < getSize(); i++)
                    coords.add(new Coord(intPosToStr(x + i, y)));
            }
        }

        if(!fleet.canBePlaced(coords, this)) throw new BadCoordException();

        this.direction = direction;
        this.coords = coords;

        front = coords.get(0);
        back = coords.get(coords.size() - 1);
    }
}
