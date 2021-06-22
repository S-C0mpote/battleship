package info1.game.components;

import info1.game.engine.GameEngine;
import info1.game.engine.Scene;
import info1.game.engine.Scenes;
import info1.game.engine.gameobjects.Button;
import info1.game.engine.gameobjects.Input;
import info1.game.engine.gameobjects.popup.PopupBackground;
import info1.game.engine.gameobjects.popup.PopupModal;
import info1.game.utils.Vector2D;
import info1.game.resources.Images;

import java.awt.*;

public class PopupSignIn {

    private final PopupBackground background;
    private final PopupModal modal;
    private final Input input;
    private final Button button;
    private final Scene menu = Scenes.MENU.getScene();

    public PopupSignIn(GameEngine gameEngine) {
        background = new PopupBackground(gameEngine);
        input = new Input(gameEngine);
        button = new Button(190, 49, "Valider", new Color(0x6A5800));
        modal = new PopupModal(gameEngine, button, input);

        modal.setSize(new Dimension(500, 220));
        modal.setPosition(new Vector2D(1280 / 2d - modal.getSize().width / 2d, 720 / 2d - modal.getSize().height / 2d));

        input.setSize(new Dimension(modal.getSize().width - 40, 50));
        input.setPosition(new Vector2D(modal.getPosition().x + 20, modal.getPosition().y + 80));

        button.setClassicImg(Images.BUTTON_YELLOW);
        button.setOverImg(Images.BUTTON_YELLOW_OVER);
        button.setPressImg(Images.BUTTON_YELLOW_PRESS);
        button.setPosition(new Vector2D(modal.getPosition().x + modal.getSize().width - 210, modal.getPosition().y + 150));

        button.setListener(() -> {
            background.close();
            modal.close();
        });


        menu.addGameObject(1000, background);
        menu.addGameObject(1001, modal);
        menu.addGameObject(1002, input);
        menu.addGameObject(1003, button);
    }

}
