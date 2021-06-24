package info1.game.scenes;

import info1.game.Game;
import info1.game.engine.GameEngine;
import info1.game.engine.Scene;
import info1.game.engine.Scenes;
import info1.game.engine.gameobjects.*;
import info1.game.engine.gameobjects.Button;
import info1.game.resources.Images;
import info1.game.utils.Vector2D;
import info1.ships.IShip;
import info1.ships.Ship;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameScene extends Scene {

    private GameEngine engine;
    private LabelIndicator gameId;
    private LabelCenter turn;
    private Grid userGrid;
    private InteractiveGrid enemyGrid;
    private final List<GraphicShipObject> shipObjects = new ArrayList<>();

    public GameScene() {
        super("Game");
        this.engine = Game.engine;
    }

    public void load(){
        Scene inGameScene = Scenes.GAME;

        Button quit = new Button(190,49,"Quitter", new Color(0x6A5800));
        quit.setClassicImg(Images.BUTTON_RED);
        quit.setOverImg(Images.BUTTON_RED_OVER);
        quit.setPressImg(Images.BUTTON_RED_PRESS);
        quit.setPosition(new Vector2D(10, 660));
        quit.setListener(() -> {
                    engine.getNetwork().leaveGame();
                    engine.setScene(Scenes.MENU);
        });

        enemyGrid = new InteractiveGrid(engine);
        userGrid = new Grid(engine.getNetwork().getUser());

        userGrid.setSize(new Dimension(300, 300));
        userGrid.setPosition(new Vector2D(
                1280 / 4d - userGrid.getSize().width / 2d - 120,
                720 / 3d - userGrid.getSize().height / 2d));

        enemyGrid.setSize(new Dimension(500, 500));
        enemyGrid.setPosition(new Vector2D(
                1280 / 2d,
                userGrid.getPosition().y));

        turn =  new LabelCenter("", new Color(0xF0F0F0), 20f);

        turn.setPosition(new Vector2D(1280 / 2d - 75, 20));
        turn.setSize(new Dimension(1280 / 2, 50));

        gameId = new LabelIndicator("", Color.WHITE, 15f, 10, 10);

        inGameScene.addGameObject(-1, Scenes.MENU.getBackground());
        inGameScene.addGameObject(userGrid);
        inGameScene.addGameObject(enemyGrid);
        inGameScene.addGameObject(turn);
        inGameScene.addGameObject(gameId);
        inGameScene.addGameObject(quit);
    }

    public void start() {
        System.out.println("Start");
        gameId.setText("GameID: " + engine.getNetwork().getCurrentGame().getId());

        // Affichage des bateaux
        for(GraphicShipObject ship : shipObjects) Scenes.SETUP.removeGameObject(ship);

        for(IShip ship : engine.getNetwork().getUser().getNavyFleet().getShips()) {
            GraphicShipObject shipObject = new GraphicShipObject(userGrid, (Ship) ship, engine);
            shipObjects.add(shipObject);
            Scenes.GAME.addGameObject(shipObject);
        }

        // Clear de la grille
        enemyGrid.clear();
    }

    public void playerTurn() {
        turn.setText("à vous de jouer !");
        enemyGrid.setTurn(true);
    }

    public void enemyTurn() {
        turn.setText("à l'adversaire de jouer !");
        enemyGrid.setTurn(false);
    }
}
