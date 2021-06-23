package info1.game.utils;

public enum Direction {
    TOP,
    BOTTOM,
    LEFT,
    RIGHT;

    public Direction getNext() {
        switch (this) {
            case TOP -> { return RIGHT; }
            case RIGHT -> { return BOTTOM; }
            case BOTTOM -> { return LEFT; }
            case LEFT -> { return TOP; }
        }
        return null;
    }
}
