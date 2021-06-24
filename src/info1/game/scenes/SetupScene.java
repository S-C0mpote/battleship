package info1.game.scenes;

import info1.game.Game;
import info1.game.engine.GameEngine;
import info1.game.engine.Scene;
import info1.game.engine.Scenes;
import info1.game.engine.gameobjects.Grid;
import info1.game.engine.gameobjects.ShipObject;
import info1.game.engine.gameobjects.ui.Button;
import info1.game.engine.gameobjects.ui.Image;
import info1.game.engine.gameobjects.ui.Label;
import info1.game.resources.Images;
import info1.game.utils.Direction;
import info1.game.utils.Vector2D;
import info1.ships.IShip;
import info1.ships.NavyFleetConfiguration;
import info1.ships.Ship;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SetupScene extends Scene {

    private final List<ShipObject> shipObjects = new ArrayList<>();
    private GameEngine engine;
    private Grid grid;

    public SetupScene() {
        super("Setup");
        this.engine = Game.engine;
    }

    public void load() {
        SetupScene setup = Scenes.SETUP;

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
        valider.setListener(() -> engine.setScene(Scenes.MENU));

        Label instruction = new Label("Placez vos bateaux", Color.WHITE, 35f, Direction.CENTER);
        instruction.setSize(new Dimension(1280, 100));

        Label indicator = new Label("Choix de configuration :", Color.WHITE, 15f);
        indicator.setLocation(10, 660);

        grid = new Grid(engine.getNetwork().getUser());
        grid.setSize(new Dimension(500, 500));
        grid.setPosition(new Vector2D(
                1280 / 2d - grid.getSize().width / 2d,
                720 / 2d - grid.getSize().height / 2d));
        setup.addGameObject(grid);

        be_lang.setListener(() -> {
            removeShips();
            engine.getNetwork().getUser().setNavyFleet(NavyFleetConfiguration.getBelgianDefault());
            addShips();
        });

        fr_lang.setListener(() -> {
            removeShips();
            engine.getNetwork().getUser().setNavyFleet(NavyFleetConfiguration.getFrenchDefault());
            addShips();
        });

        addShips();

        Label commands = new Label("Commandes :" + "\n\n" + "       bouger" + "\n\n" + "       tourner",
                Color.WHITE, 20f);
        commands.setLocation(10, (int) (720 / 2d - grid.getSize().height / 2d));
        commands.setSize(new Dimension(100, 100));

        Image leftClick = new Image();
        leftClick.setSize(new Dimension(32, 32));
        leftClick.setPosition(new Vector2D(10, commands.getSize().height * 2 + 15));
        leftClick.setImage(Images.LEFT_CLICK);

        Image rightClick = new Image();
        rightClick.setSize(new Dimension(32, 32));
        rightClick.setPosition(new Vector2D(10, commands.getSize().height * 2 + 60));
        rightClick.setImage(Images.RIGHT_CLICK);

        setup.addGameObject(-1, Scenes.MENU.getBackground());
        setup.addGameObject(fr_lang);
        setup.addGameObject(be_lang);
        setup.addGameObject(valider);
        setup.addGameObject(rightClick);
        setup.addGameObject(leftClick);
        setup.addGameObject(instruction);
        setup.addGameObject(indicator);
        setup.addGameObject(commands);
    }

    private void removeShips() {
        for(ShipObject ship : shipObjects) Scenes.SETUP.removeGameObject(ship);
    }

    private void addShips() {
        for(IShip ship : engine.getNetwork().getUser().getNavyFleet().getShips()) {
            ShipObject shipObject = new ShipObject(grid, (Ship) ship, engine);
            shipObjects.add(shipObject);
            Scenes.SETUP.addGameObject(shipObject);
        }
    }
}
