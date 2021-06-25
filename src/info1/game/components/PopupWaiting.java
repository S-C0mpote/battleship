package info1.game.components;

import info1.game.engine.GameEngine;
import info1.game.engine.Scene;
import info1.game.engine.Scenes;
import info1.game.engine.gameobjects.popup.ModalWaiting;
import info1.game.engine.gameobjects.popup.PopupBackground;
import info1.game.engine.gameobjects.ui.Button;
import info1.game.resources.Images;
import info1.game.utils.Vector2D;

import java.awt.*;

public class PopupWaiting {

    public static PopupWaiting currentPopup;

    private final ModalWaiting modal;
    private final PopupBackground background;
    private final Button cancelButton;
    private final Scene menu = Scenes.MENU;
    private final GameEngine engine;

    /**
     * Création d'une fenêtre à partir de {@link ModalWaiting}
     * Affiche le code de la partie créée et attends qu'un joueur se connecte
     * @param engine L'engine où est lancé le jeu
     */
    public PopupWaiting(GameEngine engine) {
        this.engine = engine;
        currentPopup = this;

        background = new PopupBackground(engine, 0);
        cancelButton = new Button(190, 49, "Annuler", new Color(0x973E00));
        modal = new ModalWaiting(engine, cancelButton, 3);

        modal.setSize(new Dimension(500, 220));
        modal.setPosition(new Vector2D(1280 / 2d - modal.getSize().width / 2d, 720));

        cancelButton.setClassicImg(Images.BUTTON_RED);
        cancelButton.setOverImg(Images.BUTTON_RED_OVER);
        cancelButton.setPressImg(Images.BUTTON_RED_PRESS);
        cancelButton.setPosition(new Vector2D(modal.getPosition().x + modal.getSize().width - 210, 720));

        cancelButton.setListener(() -> {
            background.close();
            modal.close();
            engine.getNetwork().leaveGame();
        });
    }

    public void open(int code) {
        menu.addGameObject(2000, background);
        menu.addGameObject(2001, modal);
        menu.addGameObject(2002, cancelButton);

        background.open();
        modal.open(code);
    }

    public void close(){
        background.close();
        modal.close();
    }

    public void closeWithoutAnimation() {
        menu.removeGameObject(background);
        menu.removeGameObject(modal);
        menu.removeGameObject(cancelButton);
    }
}
