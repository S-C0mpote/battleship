package info1.game.engine.gameobjects.ui;

import info1.game.engine.listeners.ButtonListener;
import info1.game.engine.listeners.InteractiveGameObject;
import info1.game.resources.Fonts;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Button extends InteractiveGameObject {

    private final Font font = Fonts.MAIN.deriveFont(12f);

    private String name;
    private int yMargin = 0;
    private Color color;
    private boolean clicked = false;
    private boolean build = false;
    private int xf, yf;

    private ButtonListener listener;

    private BufferedImage currentImg;
    private BufferedImage classicImg;
    private BufferedImage overImg;
    private BufferedImage pressImg;

    /**
     * Affiche un bouton de taille, nom et couleur customisable
     *
     * @param width Largeur du bouton
     * @param height Hauteur du bouton
     * @param name Texte affiché sur le bouton
     * @param color Couleur du texte du bouton
     */
    public Button(int width, int height, String name, Color color) {
        this.size = new Dimension(width, height);
        this.name = name;
        this.color = color;
    }

    @Override
    public void update(double delta) {
        if(listener != null && clicked) {
            clicked = false;
            listener.onClick();
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        if(!build) {
            FontMetrics metrics = g2d.getFontMetrics(font);
            xf = (size.width - metrics.stringWidth(name)) / 2;
            yf = ((size.height - metrics.getHeight()) / 2) + metrics.getAscent();
            build = true;
        }

        g2d.drawImage(currentImg, (int) position.x, (int) position.y + yMargin, null);
        g2d.setFont(font);
        g2d.setColor(color);
        g2d.drawString(name, (int) position.x + xf, (int) position.y + yf + yMargin);
    }

    public String getName(){
        return this.name;
    }

    @Override
    public void mouseOver() {
        currentImg = overImg;
        yMargin = 0;
    }

    @Override
    public void mouseExit() {
        currentImg = classicImg;
        yMargin = 0;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        currentImg = pressImg;
        yMargin = 4;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        currentImg = overImg;
        yMargin = 0;
        clicked = true;
    }

    public void setListener(ButtonListener listener) {
        this.listener = listener;
    }

    /**
     * @param overImg Image affiché lorsque la souris est placé dessus
     */
    public void setOverImg(BufferedImage overImg) {
        this.overImg = overImg;
    }

    /**
     * @param classicImg Image affiché par défaut
     */
    public void setClassicImg(BufferedImage classicImg) {
        this.classicImg = classicImg;
        this.currentImg = classicImg;
    }

    /**
     * @param pressImg Image affiché lors d'un clique
     */
    public void setPressImg(BufferedImage pressImg) {
        this.pressImg = pressImg;
    }
}
