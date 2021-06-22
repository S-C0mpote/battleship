package info1.game.engine.gameobjects;

import info1.game.utils.Vector2D;

import java.awt.*;

public abstract class GameObject {

    protected Vector2D position = new Vector2D(0, 0);
    protected Dimension size = new Dimension(0, 0);

    /**
    * Appellé quand on doit mettre à jour l'objet
     * @param delta différence de temps entre les frames
    */
    public abstract void update(double delta);

    /**
    * Appellé quand on doit dessiner le game object
    * @param g2d le truc pour dessiner je suppose
    */
    public abstract void draw(Graphics2D g2d);

    /**
     * Définis la position du game object
     */
    public void setPosition(Vector2D position) {
        this.position = position;
    }

    /**
     * Définis la taill du game object
     */
    public void setSize(Dimension size) {
        this.size = size;
    }

    /**
     * @return Position du GameObject
     */
    public Vector2D getPosition() {
        return position;
    }

    /**
     * @return Taille du GameObject
     */
    public Dimension getSize() {
        return size;
    }
}
