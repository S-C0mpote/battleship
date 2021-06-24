package info1.game.utils;

public enum Direction {
    BOTTOM,
    TOP,
    LEFT,
    RIGHT,
    CENTER;

    public Direction getNext() {
        switch (this) {
            case BOTTOM -> { return RIGHT; }
            case RIGHT -> { return TOP; }
            case TOP -> { return LEFT; }
            case LEFT -> { return BOTTOM; }
        }
        return null;
    }
}
