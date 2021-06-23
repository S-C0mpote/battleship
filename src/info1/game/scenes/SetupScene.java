package info1.game.scenes;

import info1.game.engine.GameEngine;
import info1.game.engine.Scene;
import info1.game.engine.Scenes;
import info1.game.engine.gameobjects.*;
import info1.game.engine.gameobjects.Button;
import info1.game.utils.Vector2D;
import info1.game.resources.Images;
import info1.ships.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SetupScene {
    public static void load(GameEngine engine) {
        Scene setup = Scenes.SETUP.getScene();

        Button fr_lang = new Button(190,49,"France", new Color(0x6A5800));
        fr_lang.setClassicImg(Images.BUTTON_YELLOW);
        fr_lang.setOverImg(Images.BUTTON_YELLOW_OVER);
        fr_lang.setPressImg(Images.BUTTON_YELLOW_PRESS);
        fr_lang.setPosition(new Vector2D(10, 660));

        Button be_lang = new Button(190,49,"Belgique", new Color(0x6A5800));
        be_lang.setClassicImg(Images.BUTTON_YELLOW);
        be_lang.setOverImg(Images.BUTTON_YELLOW_OVER);
        be_lang.setPressImg(Images.BUTTON_YELLOW_PRESS);
        be_lang.setPosition(new Vector2D(25 + fr_lang.getSize().width, 660));

        Button valider = new Button(190,49,"Valider", new Color(0x6A5800));
        valider.setClassicImg(Images.BUTTON_YELLOW);
        valider.setOverImg(Images.BUTTON_YELLOW_OVER);
        valider.setPressImg(Images.BUTTON_YELLOW_PRESS);
        valider.setPosition(new Vector2D(1280 - 200, 660));
        valider.setListener(() -> engine.setScene(Scenes.MENU.getScene()));

        LabelCenter instruction = new LabelCenter("Placez vos bateaux", Color.WHITE, 35f);
        instruction.setSize(new Dimension(1280, 100));

        LabelIndicator indicator = new LabelIndicator("Choix de configuration :", Color.WHITE, 15f, 10, 630);

        NavyFleet currentFleet = (NavyFleet) engine.getNetwork().getUser().getNavyFleet();

        Grid grid = new Grid(currentFleet);
        grid.setSize(new Dimension(500, 500));
        grid.setPosition(new Vector2D(
                1280 / 2d - grid.getSize().width / 2d,
                720 / 2d - grid.getSize().height / 2d));
        setup.addGameObject(grid);

        for(IShip ship : currentFleet.getShips()) {
            ShipObject shipObject = new ShipObject(grid, (Ship) ship, engine);
            setup.addGameObject(shipObject);
        }

        LabelIndicator commands = new LabelIndicator("Commandes :" + "\n\n" + "gauche : bouger" + "\n" + "droite : tourner",
                Color.WHITE, 20f, 10, (int) (720 / 2d - grid.getSize().height / 2d));

        setup.addGameObject(-1, MenuScene.background);
        setup.addGameObject(fr_lang);
        setup.addGameObject(be_lang);
        setup.addGameObject(valider);
        setup.addGameObject(instruction);
        setup.addGameObject(indicator);
        setup.addGameObject(commands);
    }
}
