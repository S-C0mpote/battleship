package info1.game.engine.listeners;

import info1.game.engine.GameEngine;
import info1.game.utils.Vector2D;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class MouseListenerManager implements MouseListener {

    private final GameEngine gameEngine;

    public MouseListenerManager(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    /**
     * Appelé avant chaque {@link GameEngine#draw()}
     * Si la souris se trouve sur une {@link InteractiveGameObject} cela déclenche {@link InteractiveGameObject#mouseOver()} puis quand il sort {@link InteractiveGameObject#mouseExit()}
     */
    public void updateMousePosition() {
        Point p = gameEngine.getMousePosition();

        if(p == null) return;

        for(InteractiveGameObject igo : gameEngine.getCurrentScene().getInteractiveGO().values()) {
            Vector2D pos = igo.getPosition();
            Dimension size = igo.getSize();

            if(p.x >= pos.x && p.x <= pos.x + size.width && p.y >= pos.y && p.y <= pos.y + size.height) {
                if(!igo.isOver()) {
                    igo.mouseOver();
                    igo.setOver(true);
                }
            }else {
                if(igo.isOver()) {
                    igo.mouseExit();
                    igo.setOver(false);
                }
            }
        }
    }

    /**
     * Lors d'un appuis, on parcours les {@link InteractiveGameObject} dans l'ordre décroissant
     * Si il l'appuis a été fais sur un bouton, cela annule les autres {@link InteractiveGameObject} placé derrière
     * On appelle {@link InteractiveGameObject#mousePressed(MouseEvent)}
     */
    @Override
    public void mousePressed(MouseEvent e) {
        List<InteractiveGameObject> igos = new ArrayList<>(gameEngine.getCurrentScene().getInteractiveGO().values());

        for(InteractiveGameObject igo : igos) {
            Vector2D pos = igo.getPosition();
            Dimension size = igo.getSize();

            if(e.getX() >= pos.x && e.getX() <= pos.x + size.width && e.getY() >= pos.y && e.getY() <= pos.y + size.height) {
                igo.setPressed(true);
                igo.mousePressed(e);
                return;
            }
        }
    }

    /**
     * Lors d'un relachement, on parcours les {@link InteractiveGameObject} et si on l'avais pressé avant on appelle {@link InteractiveGameObject#mouseReleased(MouseEvent)}
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        List<InteractiveGameObject> igos = new ArrayList<>(gameEngine.getCurrentScene().getInteractiveGO().values());

        for(InteractiveGameObject igo : igos) {
            if(igo.isPressed()) {
                igo.setPressed(false);
                igo.mouseReleased(e);
            }
        }
    }

    public void mouseClicked(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

}
