package info1.game.engine.gameobjects.popup;

import info1.game.engine.GameEngine;
import info1.game.engine.gameobjects.Button;
import info1.game.engine.gameobjects.GameObject;
import info1.game.engine.gameobjects.Input;
import info1.game.utils.Vector2D;
import info1.game.resources.Fonts;

import java.awt.*;

public class ModalWelcome extends GameObject {

    private final GameEngine engine;
    private boolean closing = false;

    private Button buttonLinked;
    private Input inputLinked;

    public ModalWelcome(GameEngine engine, Button buttonLinked, Input inputLinked) {
        this.engine = engine;
        this.buttonLinked = buttonLinked;
        this.inputLinked = inputLinked;
    }

    @Override
    public void update(double delta) {
        if(closing) {
            position.y += delta * 3;

            inputLinked.setPosition(new Vector2D(position.x + 20, position.y + 80));
            buttonLinked.setPosition(new Vector2D(position.x + size.width - 210, position.y + 150));

            if(position.y >= 720) {
                position.y = 720;
                closing = false;

                engine.getCurrentScene().removeGameObject(this);
                engine.getCurrentScene().removeGameObject(buttonLinked);
                engine.getCurrentScene().removeGameObject(inputLinked);
            }
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        if(position.y < 720) {
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(new Color(0xF1F1F1));
            g2d.fillRoundRect((int) position.x, (int) position.y, size.width, size.height, 10, 10);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);

            g2d.setColor(new Color(0x0A0A0A));
            g2d.setFont(Fonts.MAIN.deriveFont(20f));
            g2d.drawString("Bienvenue !", (int) position.x + 20, (int) position.y + 35);
            g2d.setFont(Fonts.MAIN.deriveFont(18f));
            g2d.drawString("Veuillez saisir un pseudo.", (int) position.x + 20, (int) position.y + 60);
        }
    }

    public void close() {
        closing = true;

        engine.getGameCanvas().removeKeyListener(inputLinked);
    }

}
