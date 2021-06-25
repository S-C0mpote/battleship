package info1.game.engine;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameCanvas extends Canvas {

    private Graphics2D g2d;

    /**
     * Création du canvas, définis la taille du canvas aux paramêtres donnés
     * @param width Largeur du canvas
     * @param height Hauteur du canvas
     */
    public GameCanvas(int width, int height) {
        setBackground(Color.BLACK);

        Dimension size = new Dimension(width, height);

        setSize(size);
        setMaximumSize(size);
        setMinimumSize(size);
    }

    /**
     * Initialisation du Canvas.
     * Création d'un double buffering.
     * Récupération du graphics2d provenant du buffer.
     * Définitions des variables de rendu (ex: Antialiasing, Text_Antialiasing)
     */
    public void init() {
        createBufferStrategy(2);
        BufferStrategy bs = getBufferStrategy();
        g2d = (Graphics2D) bs.getDrawGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
    }

    public Graphics2D getGraphics2D() {
        return g2d;
    }

}
