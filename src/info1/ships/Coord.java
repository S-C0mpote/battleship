package info1.ships;


import java.util.Objects;

/**
 * une implementation de l'interface ICoord manipulant des coordonnÃ©es alphanumÃ©riques comme "A1", "B6", "J3", etc.
 */

public class Coord implements ICoord {

    private int x, y;

    /**
     * NB : LA SIGNATURE DU CONSTRUCTEUR DOIT ETRE RESPECTEE
     *
     * constructeur d'un objet Coord
     * @param xy la coordonnÃ©e aphanumÃ©rique sous la forme d'une chaine de caractÃ¨res
     * @throws BadCoordException si la chaine de caractÃ¨re ne permet pas de dÃ©finir une coordonnÃ©e alphanumÃ©rique
     */
    public Coord(String xy) throws BadCoordException {
        x = xy.charAt(0) - 64;

        String yStr = xy.split(String.valueOf(xy.charAt(0)))[1];
        y = Integer.parseInt(yStr);

        if(x < 1 || y < 1 || x > 10 || y > 10){
            throw new BadCoordException();
        }
    }

    @Override
    public char getAlphaX() {
        return (char) (this.x + 64);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return this.getAlphaX() + String.valueOf(this.y);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if(o == null || o.getClass()!= this.getClass()){
            return false;
        }

        Coord c = (Coord) o;

        return (this.x == c.getX() && this.y == c.getY());
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
