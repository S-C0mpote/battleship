package info1.game.engine;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameCanvas extends Canvas {

    private BufferStrategy bs;

    public GameCanvas(int width, int height) {
        setBackground(Color.BLACK);

        Dimension size = new Dimension(width, height);

        setSize(size);
        setMaximumSize(size);
        setMinimumSize(size);
    }

    public void init() {
        createBufferStrategy(2);
        bs = getBufferStrategy();
    }

    public Graphics2D getGraphics2D() {
        return (Graphics2D) super.getBufferStrategy().getDrawGraphics();
    }

}
