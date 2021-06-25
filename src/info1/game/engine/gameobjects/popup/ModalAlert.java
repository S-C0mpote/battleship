package info1.game.engine.gameobjects.popup;

import info1.game.engine.GameEngine;
import info1.game.engine.gameobjects.GameObject;
import info1.game.engine.gameobjects.ui.Button;
import info1.game.resources.Fonts;
import info1.game.utils.Vector2D;

import java.awt.*;

public class ModalAlert extends GameObject {

    private final GameEngine engine;
    private final String message;
    private final Button buttonLinked;
    private final Font font = Fonts.MAIN.deriveFont(15f);

    private boolean closing = false, opening = false;
    private boolean build = false;
    private int xf, yf;

    /**
     * Modal utilisé pour afficher un message personnalisé
     */
    public ModalAlert(GameEngine engine, Button buttonLinked, String message) {
        this.engine = engine;
        this.buttonLinked = buttonLinked;
        this.message = message;
    }

    @Override
    public void update(double delta) {
        if(closing) {
            position.y += delta * 3;

            buttonLinked.setPosition(new Vector2D(position.x + 105, position.y + 120));

            if(position.y >= 720) {
                position.y = 720;
                closing = false;

                engine.getCurrentScene().removeGameObject(buttonLinked);
                engine.getCurrentScene().removeGameObject(this);
            }
        }else if(opening) {
            position.y -= delta * 3;

            if(position.y < 720 / 2d - size.height / 2d) {
                position.y = 720 / 2d - size.height / 2d;
                opening = false;
            }

            buttonLinked.setPosition(new Vector2D(position.x + 105, position.y + 120));
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        if(position.y < 720) {
            if(!build) {
                FontMetrics metrics = g2d.getFontMetrics(font);
                xf = (size.width - metrics.stringWidth(message)) / 2;
                yf = (((size.height - 29) - metrics.getHeight()) / 2);
                build = true;
            }

            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(new Color(0xF1F1F1));
            g2d.fillRoundRect((int) position.x, (int) position.y, size.width, size.height, 10, 10);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);

            g2d.setFont(font);
            g2d.setColor(Color.BLACK);
            g2d.drawString(message, (int) position.x + xf, (int) position.y + yf);
        }
    }

    public void close() {
        this.closing = true;
    }

    public void open() {
        this.opening = true;
    }
}
