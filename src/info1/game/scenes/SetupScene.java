package info1.game.scenes;

import info1.game.engine.GameEngine;
import info1.game.engine.Scene;
import info1.game.engine.Scenes;
import info1.game.engine.gameobjects.*;
import info1.game.engine.gameobjects.Button;
import info1.game.engine.listeners.ButtonListener;
import info1.game.engine.utils.Vector2D;
import info1.game.resources.Images;

import java.awt.*;

public class SetupScene {
    public static void load(GameEngine engine) {


        Button fr_lang = new Button(190,49,"France", new Color(0x6A5800));
        Button be_lang = new Button(190,49,"Belgique", new Color(0x6A5800));
        Button valider = new Button(190,49,"Valider", new Color(0x6A5800));


        fr_lang.setPosition(new Vector2D(10, 660));
        be_lang.setPosition(new Vector2D(25 + fr_lang.getSize().getWidth(), 660));
        valider.setPosition(new Vector2D(1280 - 200, 660));



        fr_lang.setClassicImg(Images.BUTTON_YELLOW);
        fr_lang.setOverImg(Images.BUTTON_YELLOW_OVER);
        fr_lang.setPressImg(Images.BUTTON_YELLOW_PRESS);
        fr_lang.setListener(() -> {
            System.out.println("JDA                  HAK");;
        });

        be_lang.setClassicImg(Images.BUTTON_YELLOW);
        be_lang.setOverImg(Images.BUTTON_YELLOW_OVER);
        be_lang.setPressImg(Images.BUTTON_YELLOW_PRESS);

        valider.setClassicImg(Images.BUTTON_YELLOW);
        valider.setOverImg(Images.BUTTON_YELLOW_OVER);
        valider.setPressImg(Images.BUTTON_YELLOW_PRESS);


        MenuBackground menuBackground = new MenuBackground();

        Scene setup = Scenes.SETUP.getScene();

        setup.addGameObject(-1, menuBackground);
        setup.addGameObject(fr_lang);
        setup.addGameObject(be_lang);
        setup.addGameObject(valider);
    }
}
