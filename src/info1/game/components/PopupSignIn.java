package info1.game.components;

import info1.game.engine.GameEngine;
import info1.game.engine.Scene;
import info1.game.engine.Scenes;
import info1.game.engine.gameobjects.Button;
import info1.game.engine.gameobjects.Input;
import info1.game.engine.gameobjects.popup.PopupBackground;
import info1.game.engine.gameobjects.popup.ModalWelcome;
import info1.game.engine.listeners.NetworkListener;
import info1.game.network.NetworkManager;
import info1.game.network.GamePlayer;
import info1.game.resources.Images;
import info1.game.scenes.GameScene;
import info1.game.scenes.SetupScene;
import info1.game.utils.Vector2D;

import java.awt.*;

public class PopupSignIn {

    private final PopupBackground background;
    private final ModalWelcome modal;
    private final Input input;
    private final Button button;
    private final Scene menu = Scenes.MENU.getScene();

    public PopupSignIn(GameEngine engine) {
        background = new PopupBackground(engine);
        input = new Input(engine);
        button = new Button(190, 49, "Valider", new Color(0x6A5800));
        modal = new ModalWelcome(engine, button, input);

        modal.setSize(new Dimension(500, 220));
        modal.setPosition(new Vector2D(1280 / 2d - modal.getSize().width / 2d, 720 / 2d - modal.getSize().height / 2d));

        input.setSize(new Dimension(modal.getSize().width - 40, 50));
        input.setPosition(new Vector2D(modal.getPosition().x + 20, modal.getPosition().y + 80));
        input.setLimit(20);

        button.setClassicImg(Images.BUTTON_YELLOW);
        button.setOverImg(Images.BUTTON_YELLOW_OVER);
        button.setPressImg(Images.BUTTON_YELLOW_PRESS);
        button.setPosition(new Vector2D(modal.getPosition().x + modal.getSize().width - 210, modal.getPosition().y + 150));

        button.setListener(() -> {
            NetworkManager network = new NetworkManager(new GamePlayer(input.getText()));
            engine.setNetwork(network);

            background.close();
            modal.close();

            SetupScene.load(engine);
            GameScene.load(engine);

            engine.getNetwork().setListener(new NetworkListener() {
                @Override
                public void onPlayerJoin() {
                    engine.setScene(Scenes.IN_GAME.getScene());
                    GameScene.start();
                }

                @Override
                public void onPlayerTurn() {
                    System.out.println("C a toi");
                }

                @Override
                public void onEnemyTurn() {
                    System.out.println("C a lui");
                }
            });

            //engine.setScene(Scenes.IN_GAME.getScene());
        });

        menu.addGameObject(1000, background);
        menu.addGameObject(1001, modal);
        menu.addGameObject(1002, input);
        menu.addGameObject(1003, button);
    }

}
