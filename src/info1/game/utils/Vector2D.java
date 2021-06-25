package info1.game.utils;


import java.util.Objects;

/**
 * Simple classe de vecteur
 */
public class Vector2D {

    public double x;
    public double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D copy(){
        return new Vector2D(x,y);
    }

    public void addVector(Vector2D toAdd) {
        this.x += toAdd.x;
        this.y += toAdd.y;
    }

    public void addX(double x) { this.x += x; }
    public void addY(double x) { this.x += x; }

    @Override
    public String toString() {
        return "(" +
                "x=" + x +
                ", y=" + y +
                ')';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2D vector2D = (Vector2D) o;
        return Double.compare(vector2D.x, x) == 0 &&
                Double.compare(vector2D.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
