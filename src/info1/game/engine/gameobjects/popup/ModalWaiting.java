package info1.game.engine.gameobjects.popup;

import info1.game.engine.GameEngine;
import info1.game.engine.gameobjects.Button;
import info1.game.engine.gameobjects.GameObject;
import info1.game.engine.listeners.NetworkListener;
import info1.game.resources.Fonts;
import info1.game.utils.Vector2D;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ModalWaiting extends GameObject {

    private final GameEngine engine;
    private boolean closing = false, opening = false;

    private String code;
    private Button buttonLinked;
    private String dots = "";
    private boolean userJoined = false;

    private double acc = 0;

    public ModalWaiting(GameEngine engine, Button buttonLinked) {
        this.engine = engine;
        this.buttonLinked = buttonLinked;
    }

    @Override
    public void update(double delta) {
        if(closing) {
            position.y += delta * 3;

            buttonLinked.setPosition(new Vector2D(position.x + size.width - 210, position.y + 150));

            if(position.y >= 720) {
                position.y = 720;
                closing = false;

                engine.getCurrentScene().removeGameObject(buttonLinked);
                engine.getCurrentScene().removeGameObject(this);
            }
        }

        if(opening) {
            position.y -= delta * 3;

            buttonLinked.setPosition(new Vector2D(position.x + size.width - 210, position.y + 150));

            if(position.y < 720 / 2d - size.height / 2d) {
                position.y = 720 / 2d - size.height / 2d;
                opening = false;
            }
        }

        acc += delta;

        if(acc >= 250) {
            acc = 0;

            dots = ".".repeat((dots.length() + 1) % 4);
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
            g2d.drawString("En attente d'un adversaire" + dots, (int) position.x + 20, (int) position.y + 35);
            g2d.setFont(Fonts.MAIN.deriveFont(18f));
            g2d.drawString("Voici votre code : " + code, (int) position.x + 20, (int) position.y + 60);
        }
    }

    public void close() {
        this.closing = true;
    }

    public void open(int code) {
        this.opening = true;
        this.code = String.valueOf(code);
    }

}
