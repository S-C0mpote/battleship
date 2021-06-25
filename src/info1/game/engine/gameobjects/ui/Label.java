package info1.game.engine.gameobjects.ui;

import info1.game.engine.gameobjects.GameObject;
import info1.game.resources.Fonts;
import info1.game.utils.Direction;

import java.awt.*;

public class Label extends GameObject {

    private final Color color;
    private final Direction direction;
    private final Font font;

    private String text;
    private boolean build = false;
    private int xf, yf, fontHeight;

    /**
     * Affiche un texte de position {@link GameObject#getPosition()} et permet de choisir l'orientation du texte grace à une {@link Direction}
     * Il se place par rapport à {@link GameObject#getSize()} (La taille ne modifiera pas la taille du texte mais la taille de la "limite")
     *
     * Exemple :
     * Label de valeur "EXEMPLE", de taille WIDTH, HEIGHT et de {@link Direction#CENTER}
     *
     *          WIDTH
     * ************************
     * *       EXEMPLE        * HEIGHT
     * ************************
     *
     * @param text Valeur du texte à afficher
     * @param color Couleur du texte
     * @param size Taille du texte
     * @param direction Direction du texte
     */
    public Label(String text, Color color, float size, Direction direction) {
        this.text = text;
        this.color = color;
        this.direction = direction;
        this.font = Fonts.MAIN.deriveFont(size);
    }

    public Label(String text, Color color, float size){
        this(text, color, size, Direction.LEFT);
    }

    @Override
    public void update(double delta) {}

    @Override
    public void draw(Graphics2D g2d) {
        if(!build) {
            FontMetrics metrics = g2d.getFontMetrics(font);
            fontHeight = metrics.getHeight();

            switch (direction) {
                case LEFT -> {
                    xf = 0;
                    yf = 0;
                }
                case CENTER -> {
                    xf = (size.width - metrics.stringWidth(text)) / 2;
                    yf = ((size.height - metrics.getHeight()) / 2) + metrics.getAscent();
                }
                case RIGHT -> {
                    xf = (size.width - metrics.stringWidth(text));
                    yf = size.height;
                }
            }

            build = true;
        }

        g2d.setFont(font);
        g2d.setColor(color);

        int yTemp = 0;
        for (String line : text.split("\n")) {
            g2d.drawString(line, (int) position.x + xf, (int) position.y + yf + yTemp);
            yTemp += fontHeight;
        }

        // DEBUG (Voir les zones de textes):
        // g2d.setColor(Color.RED);
        // g2d.fillRect((int) position.x, (int) position.y, size.width, size.height);
    }

    public void setText(String text) {
        this.text = text;
        build = false;
    }

    public String getText(){ return this.text; }
}
