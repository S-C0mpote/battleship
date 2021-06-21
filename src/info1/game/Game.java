package info1.game;

import info1.game.engine.GameEngine;
import info1.game.engine.Scene;
import info1.game.engine.Scenes;
import info1.game.engine.gameobjects.*;
import info1.game.engine.gameobjects.Button;
import info1.game.engine.utils.Vector2D;
import info1.game.resources.Fonts;
import info1.game.resources.Images;

import java.awt.*;

public class Game {

    public static void main(String[] args) {
        GameEngine engine = new GameEngine();
        engine.getGameCanvas().setFont(Fonts.MAIN);

        Input input = new Input(300, 50);
        input.setPosition(new Vector2D(200, 200));

        Button createParty = new Button(190, 49, "Créer une partie", new Color(0x6A5800));
        createParty.setClassicImg(Images.BUTTON_YELLOW);
        createParty.setOverImg(Images.BUTTON_YELLOW_OVER);
        createParty.setPressImg(Images.BUTTON_YELLOW_PRESS);
        createParty.setPosition(new Vector2D(1280 / 2d - 190 / 2d, 720 - 100));

        MenuBackground menuBackground = new MenuBackground();
        TitleMenu title = new TitleMenu(engine);

        LabelGame version = new LabelGame("Maxandre ROCHEFORT, Léo ROCHARD, Morgann LERAY, Elouan NAQUIN", Color.WHITE, 10f);
        version.setPosition(new Vector2D(10, 710));

        Scene menu = Scenes.MENU.getScene();
        menu.addGameObject(-1, menuBackground);
        menu.addGameObject(createParty);
        menu.addGameObject(input);
        menu.addGameObject(title);
        menu.addGameObject(version);
        engine.start(menu);
    }

}
