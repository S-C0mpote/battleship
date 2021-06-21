package info1.game.engine.listeners;

import info1.game.engine.gameobjects.GameObject;

public abstract class InteractiveGameObject extends GameObject {

    private boolean isOver = false;

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
    public void mousePressed() {};

    /**
     * Appelé quand le click est relaché (release) de l'objet
     */
    public void mouseReleased() {};

    /**
     * @param over true = curseur sur l'objet | false = curseur pas sur l'objet
     */
    public void setOver(boolean over) { isOver = over; }
    public boolean isOver() { return isOver; }
}
