package info1.game.scenes;

import info1.game.engine.GameEngine;
import info1.game.engine.Scene;
import info1.game.engine.Scenes;
import info1.game.engine.gameobjects.*;
import info1.game.engine.gameobjects.Button;
import info1.game.engine.utils.Vector2D;
import info1.game.resources.Images;

import java.awt.*;

public class MenuScene {

    public static void load(GameEngine engine) {


        Button createParty = new Button(190, 49, "Créer une partie", new Color(0x6A5800));
        createParty.setClassicImg(Images.BUTTON_YELLOW);
        createParty.setOverImg(Images.BUTTON_YELLOW_OVER);
        createParty.setPressImg(Images.BUTTON_YELLOW_PRESS);
        createParty.setPosition(new Vector2D(1280 / 2d - 190 / 2d, 720 - 100));

        MenuBackground menuBackground = new MenuBackground();
        TitleMenu title = new TitleMenu(engine);

        LabelCenter version = new LabelCenter("Maxandre ROCHEFORT, Léo ROCHARD, Morgann LERAY, Elouan NAQUIN", Color.WHITE, 10f);
        version.setPosition(new Vector2D(0, 700));
        version.setSize(new Dimension(1280,10));

        Scene menu = Scenes.MENU.getScene();

        menu.addGameObject(-1, menuBackground);
        menu.addGameObject(createParty);
        menu.addGameObject(title);
        menu.addGameObject(version);
    }

}
