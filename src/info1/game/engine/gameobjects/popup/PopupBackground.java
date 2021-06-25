package info1.game.engine.gameobjects.popup;

import info1.game.engine.GameEngine;
import info1.game.engine.listeners.InteractiveGameObject;

import java.awt.*;

public class PopupBackground extends InteractiveGameObject {

    private final GameEngine engine;
    private boolean closing = false, opening;
    private float opacity;

    /**
     * Affiche un fond noir, utilisé par chaque Popup
     * Il étend de {@link InteractiveGameObject} ce qui signifie que les autres {@link InteractiveGameObject} placés dessous ne seront pas déclenché.
     */
    public PopupBackground(GameEngine engine) {
        this(engine, 0.5f);
    }

    public PopupBackground(GameEngine engine, float defaultOpacity) {
        this.size = new Dimension(1280, 720);
        this.engine = engine;
        this.opacity = defaultOpacity;
    }

    @Override
    public void update(double delta) {
        if(closing) {
            opacity -= delta / 5e2;
            if(opacity <= 0) {
                opacity = 0;
                closing = false;
                engine.getCurrentScene().removeGameObject(this);
            }
        } else if(opening) {
            opacity += delta / 5e2;
            if(opacity > 0.5f) {
                opacity = 0.5f;
                opening = false;
            }
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        if(opacity > 0) {
            g2d.setColor(new Color(0, 0, 0, opacity));
            g2d.fillRect(0, 0, size.width, size.height);
        }
    }

    public void close() {
        closing = true;
    }

    public void open() {
        opening = true;
    }
}
