package info1.game.engine;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameCanvas extends Canvas {

    private BufferStrategy bs;
    private Graphics2D g2d;

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
        g2d = (Graphics2D) getBufferStrategy().getDrawGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
    }

    public Graphics2D getGraphics2D() {
        return g2d;
    }

}
