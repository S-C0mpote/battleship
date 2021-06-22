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
    public static void load(GameEngine engine) throws BadCoordException, CoordsBadShipException {
        Scene setup = Scenes.SETUP.getScene();

        List<IShip> default_french = new ArrayList<>();
        default_french.add(new AircraftCarrier("monPorteAvion", "E5", "E9"));
        default_french.add(new Battleship("monCuirasse", "B2", "E2"));

        Button fr_lang = new Button(190,49,"France", new Color(0x6A5800));
        Button be_lang = new Button(190,49,"Belgique", new Color(0x6A5800));
        Button valider = new Button(190,49,"Valider", new Color(0x6A5800));
        LabelCenter instruction = new LabelCenter("Placez vos bateaux", Color.WHITE, 35f);

        fr_lang.setPosition(new Vector2D(10, 660));
        be_lang.setPosition(new Vector2D(25 + fr_lang.getSize().width, 660));
        valider.setPosition(new Vector2D(1280 - 200, 660));

        instruction.setSize(new Dimension(1280, 100));

        fr_lang.setClassicImg(Images.BUTTON_YELLOW);
        fr_lang.setOverImg(Images.BUTTON_YELLOW_OVER);
        fr_lang.setPressImg(Images.BUTTON_YELLOW_PRESS);

        be_lang.setClassicImg(Images.BUTTON_YELLOW);
        be_lang.setOverImg(Images.BUTTON_YELLOW_OVER);
        be_lang.setPressImg(Images.BUTTON_YELLOW_PRESS);

        valider.setClassicImg(Images.BUTTON_YELLOW);
        valider.setOverImg(Images.BUTTON_YELLOW_OVER);
        valider.setPressImg(Images.BUTTON_YELLOW_PRESS);

        Grid grid = new Grid(default_french);
        grid.setSize(new Dimension(500, 500));
        grid.setPosition(new Vector2D((1280 / 2d) - grid.getSize().width / 2d,720 / 2 - (grid.getSize().height / 2) ));
        setup.addGameObject(grid);

        for(IShip ship : default_french) {
            ShipObject shipObject = new ShipObject(grid, (Ship) ship, engine);
            setup.addGameObject(shipObject);
        }

        MenuBackground menuBackground = new MenuBackground();
        setup.addGameObject(-1, menuBackground);
        setup.addGameObject(fr_lang);
        setup.addGameObject(be_lang);
        setup.addGameObject(valider);
        setup.addGameObject(instruction);
    }
}
