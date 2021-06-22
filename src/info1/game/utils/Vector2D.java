package info1.game.utils;

public class Vector2D {

    public double x;
    public double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D copy() {
        return new Vector2D(x, y);
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
}
