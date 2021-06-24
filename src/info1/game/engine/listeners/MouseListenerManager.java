package info1.game.engine.listeners;

import info1.game.engine.GameEngine;
import info1.game.utils.Vector2D;
import info1.ships.BadCoordException;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.List;

public class MouseListenerManager implements MouseListener {

    private final GameEngine gameEngine;

    public MouseListenerManager(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

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
