package info1.game.engine.listeners;

import info1.game.engine.gameobjects.GameObject;

import java.awt.event.MouseEvent;

public abstract class InteractiveGameObject extends GameObject {

    private boolean isOver = false;
    private boolean isPressed = false;

    /**
     * Appelé quand la souris passe sur l'objet
     */
    public void mouseOver() {};

    /**
     * Appelé quand la souris pars de l'objet
     */
    public void mouseExit() {};

    /**
     * Appelé quand il y a un click (press) sur l'objet
     */
    public void mousePressed(MouseEvent event) {};

    /**
     * Appelé quand le click est relaché (release) de l'objet
     */
    public void mouseReleased(MouseEvent event) {};

    /**
     * @param over true = curseur sur l'objet | false = curseur pas sur l'objet
     */
    public void setOver(boolean over) { isOver = over; }
    public boolean isOver() { return isOver; }

    /**
     * @param pressed true = clique en cours
     */
    public void setPressed(boolean pressed) { isPressed = pressed; }
    public boolean isPressed() { return isPressed; }
}
