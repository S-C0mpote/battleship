package info1.game.engine;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameCanvas extends Canvas {

    private BufferStrategy bs;

    public GameCanvas() {
        createBufferStrategy(2);
        bs = getBufferStrategy();
    }

    public BufferStrategy getBs() {
        return bs;
    }

    public Graphics2D getGraphics2D() {
        return (Graphics2D) super.getGraphics();
    }

}
