package info1.game.engine.gameobjects.popup;

import info1.game.engine.GameEngine;
import info1.game.engine.gameobjects.Button;
import info1.game.engine.gameobjects.GameObject;
import info1.game.resources.Fonts;
import info1.game.utils.Vector2D;

import java.awt.*;

public class ModalAlert extends GameObject {

    private final GameEngine engine;
    private boolean closing = false, opening = false;

    private final String message;
    private final Button buttonLinked;

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
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(new Color(0xF1F1F1));
            g2d.fillRoundRect((int) position.x, (int) position.y, size.width, size.height, 10, 10);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        }

        Font fontSize = g2d.getFont().deriveFont(15f);
        FontMetrics metrics = g2d.getFontMetrics(fontSize);
        int xf = (int) position.x + (size.width - metrics.stringWidth(message)) / 2;
        int yf = (int) position.y + (((size.height - 29) - metrics.getHeight()) / 2);

        g2d.setFont(fontSize);
        g2d.setColor(Color.BLACK);
        g2d.drawString(message, xf, yf);

    }

    public void close() {
        this.closing = true;
    }

    public void open() {
        this.opening = true;
    }
}
