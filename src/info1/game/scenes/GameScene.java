package info1.game.scenes;

import info1.game.engine.GameEngine;
import info1.game.engine.Scene;
import info1.game.engine.Scenes;
import info1.game.engine.gameobjects.*;
import info1.game.engine.gameobjects.Button;
import info1.game.resources.Images;
import info1.game.utils.Vector2D;
import info1.network.Network;
import info1.ships.IShip;
import info1.ships.NavyFleet;
import info1.ships.Ship;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameScene {

    private static GameEngine engine;
    private static LabelIndicator gameId;
    private static LabelCenter turn;
    private static Grid userGrid;
    private static InteractiveGrid ennemyGrid;
    private static final List<GraphicShipObject> shipObjects = new ArrayList<>();


    public static void load(GameEngine engine){
        GameScene.engine = engine;

        Scene ingame = Scenes.IN_GAME.getScene();

        Button quit = new Button(190,49,"Quitter", new Color(0x6A5800));
        quit.setClassicImg(Images.BUTTON_YELLOW);
        quit.setOverImg(Images.BUTTON_YELLOW_OVER);
        quit.setPressImg(Images.BUTTON_YELLOW_PRESS);
        quit.setPosition(new Vector2D(10, 660));
        quit.setListener(() -> {
                    engine.getNetwork().leaveGame();
                    engine.setScene(Scenes.MENU.getScene());
        });

        ennemyGrid = new InteractiveGrid();
        userGrid = new Grid(engine.getNetwork().getUser());

        userGrid.setSize(new Dimension(300, 300));
        userGrid.setPosition(new Vector2D(
                1280 / 4d - userGrid.getSize().width / 2d - 120,
                720 / 3d - userGrid.getSize().height / 2d));

        ennemyGrid.setSize(new Dimension(500, 500));
        ennemyGrid.setPosition(new Vector2D(
                1280 / 2d,
                userGrid.getPosition().y));

        turn =  new LabelCenter("", new Color(0xF0F0F0), 20f);

        turn.setPosition(new Vector2D(1280 / 2d - 75, 20));
        turn.setSize(new Dimension(1280 / 2, 50));

        gameId = new LabelIndicator("", Color.WHITE, 15f, 10, 10);

        // Cases restantes -1
        LabelIndicator cellRemaining =  new LabelIndicator("Cells remaining : 10 / 100",
                Color.WHITE, 20f, (int) ennemyGrid.getPosition().x, (int) ennemyGrid.getPosition().y + ennemyGrid.getSize().height);
        
        ingame.addGameObject(-1,MenuScene.background);
        ingame.addGameObject(userGrid);
        ingame.addGameObject(ennemyGrid);
        ingame.addGameObject(turn);
        ingame.addGameObject(gameId);
        ingame.addGameObject(cellRemaining);
        ingame.addGameObject(quit);
    }

    public static void start() {
        System.out.println("Start");
        gameId.setText("GameID: " + engine.getNetwork().getCurrentGame().getId());
        setShips();
    }

    public static void playerTurn() {
        turn.setText("à vous de jouer !");
    }

    public static void enemyTurn() {
        turn.setText("à l'adversaire de jouer !");
    }

    private static void setShips() {
        for(IShip ship : engine.getNetwork().getUser().getNavyFleet().getShips()) {
            GraphicShipObject shipObject = new GraphicShipObject(userGrid, (Ship) ship, engine);
            shipObjects.add(shipObject);
            Scenes.IN_GAME.getScene().addGameObject(shipObject);
        }
    }
}
