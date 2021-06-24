package info1.game.scenes;

import info1.game.Game;
import info1.game.components.PopupMessage;
import info1.game.components.PopupSignIn;
import info1.game.components.PopupWaiting;
import info1.game.engine.GameEngine;
import info1.game.engine.Scene;
import info1.game.engine.Scenes;
import info1.game.engine.gameobjects.*;
import info1.game.engine.gameobjects.ui.Button;
import info1.game.engine.gameobjects.ui.Input;
import info1.game.engine.gameobjects.ui.Label;
import info1.game.resources.Images;
import info1.game.utils.Direction;
import info1.game.utils.Vector2D;

import java.awt.*;


public class MenuScene extends Scene {

    private GameEngine engine;
    private MenuBackground background;

    public MenuScene() {
        super("Menu");
        this.engine = Game.engine;
    }

    public void load() {
        MenuScene menu = Scenes.MENU;

        Button joinParty = new Button(190, 49, "Rejoindre une partie", new Color(0x6A5800));
        joinParty.setClassicImg(Images.BUTTON_YELLOW);
        joinParty.setOverImg(Images.BUTTON_YELLOW_OVER);
        joinParty.setPressImg(Images.BUTTON_YELLOW_PRESS);
        joinParty.setPosition(new Vector2D(1280 / 2d - 190 / 2d, 720 / 2d));
        menu.addGameObject(joinParty);

        Button createParty = new Button(190, 49, "Créer une partie", new Color(0x6A5800));
        createParty.setClassicImg(Images.BUTTON_YELLOW);
        createParty.setOverImg(Images.BUTTON_YELLOW_OVER);
        createParty.setPressImg(Images.BUTTON_YELLOW_PRESS);
        createParty.setPosition(new Vector2D(1280 / 2d - 190 / 2d, 720 / 2d + 60));

        menu.addGameObject(createParty);

        Button editFleet = new Button(190, 49, "Gérer ma flotte", new Color(0x6A5800));
        editFleet.setClassicImg(Images.BUTTON_YELLOW);
        editFleet.setOverImg(Images.BUTTON_YELLOW_OVER);
        editFleet.setPressImg(Images.BUTTON_YELLOW_PRESS);
        editFleet.setPosition(new Vector2D(1080, 720 - 59));
        editFleet.setListener(() -> engine.setScene(Scenes.SETUP));
        menu.addGameObject(editFleet);

        Input codeInput = new Input(engine);
        codeInput.setFontSize(12f);
        codeInput.setPadding(10);
        codeInput.setSize(new Dimension(190, 49));
        codeInput.setPosition(new Vector2D(1280 / 2d - 190 / 2d, 720 / 2d - 60));
        codeInput.setLimit(10);
        menu.addGameObject(codeInput);

        background = new MenuBackground();
        menu.addGameObject(-1, background);

        TitleMenu title = new TitleMenu(engine);
        menu.addGameObject(title);

        Label version = new Label("Maxandre GROCHEFORT, Léo ROCHARD, Morgann LERAY, Elouan NAQUIN", Color.WHITE, 10f, Direction.CENTER);
        version.setPosition(new Vector2D(10, 700));
        version.setSize(new Dimension(1280, 20));
        menu.addGameObject(version);

        new PopupSignIn(engine);
        PopupWaiting popupWaiting = new PopupWaiting(engine);

        createParty.setListener(() -> {
            engine.getNetwork().createGame();
            popupWaiting.open(engine.getNetwork().getCurrentGame().getId());
        });

        joinParty.setListener(() -> {
            if (codeInput.getText().equals("") ||
               !codeInput.getText().matches("[0-9]*")) {
                new PopupMessage(engine, "Code non valide");
                return;
            }

            int code = Integer.parseInt(codeInput.getText());

            if(engine.getNetwork().joinGame(code)) {
                Scenes.GAME.start();
                engine.setScene(Scenes.GAME);
            }
        });
    }

    public MenuBackground getBackground() {
        return background;
    }
}
