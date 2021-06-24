package info1.game.components;

import info1.game.engine.GameEngine;
import info1.game.engine.Scene;
import info1.game.engine.Scenes;
import info1.game.engine.gameobjects.Button;
import info1.game.engine.gameobjects.LabelCenter;
import info1.game.engine.gameobjects.popup.ModalAlert;
import info1.game.engine.gameobjects.popup.PopupBackground;
import info1.game.resources.Images;
import info1.game.utils.Vector2D;

import java.awt.*;

public class PopupMessage {

    private final PopupBackground background;
    private final ModalAlert modal;
    private final Button validateButton;
    private final Scene menu = Scenes.MENU;

    public PopupMessage(GameEngine engine, String message) {

        background = new PopupBackground(engine, 0);

        validateButton = new Button(190, 49, "Ok", new Color(0x6A5800));
        modal = new ModalAlert(engine, validateButton, message);

        modal.setSize(new Dimension(400, 180));
        modal.setPosition(new Vector2D(1280 / 2d - modal.getSize().width / 2d, 720));

        validateButton.setClassicImg(Images.BUTTON_YELLOW);
        validateButton.setOverImg(Images.BUTTON_YELLOW_OVER);
        validateButton.setPressImg(Images.BUTTON_YELLOW_PRESS);

        validateButton.setListener(() -> {
            background.close();
            modal.close();
        });

        menu.addGameObject(3000, background);
        menu.addGameObject(3001, modal);
        menu.addGameObject(3002, validateButton);

        background.open();
        modal.open();
    }
}
