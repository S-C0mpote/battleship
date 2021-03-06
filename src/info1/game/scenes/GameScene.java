package info1.game.scenes;

import info1.game.Game;
import info1.game.components.PopupWaiting;
import info1.game.engine.GameEngine;
import info1.game.engine.Scene;
import info1.game.engine.Scenes;
import info1.game.engine.gameobjects.*;
import info1.game.engine.gameobjects.ui.Button;
import info1.game.engine.gameobjects.ui.Label;
import info1.game.resources.Images;
import info1.game.utils.Direction;
import info1.game.utils.Vector2D;
import info1.ships.IShip;
import info1.ships.Ship;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameScene extends Scene {

    private final List<ShipObject> shipObjects = new ArrayList<>();

    private GameEngine engine;
    private Label turn, userLabel, opponentLabel, gameId, labelCoule, remaining;
    private Grid userGrid;
    private InteractiveGrid enemyGrid;
    private StartAnimation startAnimation;

    /**
     * Scène du jeu
     */
    public GameScene() {
        super("Game");
        this.engine = Game.engine;
    }

    public void load(){
        Scene gameScene = Scenes.GAME;

        Button quit = new Button(190,49,"Quitter", new Color(0x973E00));
        quit.setClassicImg(Images.BUTTON_RED);
        quit.setOverImg(Images.BUTTON_RED_OVER);
        quit.setPressImg(Images.BUTTON_RED_PRESS);
        quit.setPosition(new Vector2D(10, 660));
        quit.setListener(() -> {
            engine.getNetwork().leaveGame();
            PopupWaiting.currentPopup.closeWithoutAnimation();
            engine.setScene(Scenes.MENU);
        });

        startAnimation = new StartAnimation();

        enemyGrid = new InteractiveGrid(engine);
        userGrid = new Grid(engine.getNetwork().getUser());

        userGrid.setSize(new Dimension(300, 300));
        userGrid.setPosition(new Vector2D(
                1280 / 4d - userGrid.getSize().width / 2d - 50,
                720 / 3d - userGrid.getSize().height / 2d));

        enemyGrid.setSize(new Dimension(500, 500));
        enemyGrid.setPosition(new Vector2D(
                1280 / 2d,
                userGrid.getPosition().y));

        turn =  new Label("", new Color(0xF0F0F0), 20f, Direction.CENTER);

        turn.setPosition(new Vector2D(1280 / 2d - 75, 20));
        turn.setSize(new Dimension(1280 / 2, 50));

        gameId = new Label("", Color.WHITE, 15f);
        gameId.setPosition(new Vector2D(10, 20));

        opponentLabel = new Label("", Color.WHITE, 15f, Direction.RIGHT);
        opponentLabel.setPosition(new Vector2D(enemyGrid.getPosition().x, enemyGrid.getPosition().y + enemyGrid.getSize().height + 10));
        opponentLabel.setSize(new Dimension(enemyGrid.getSize().width, 15));

        userLabel = new Label("", Color.WHITE, 15f, Direction.RIGHT);
        userLabel.setPosition(new Vector2D(userGrid.getPosition().x, userGrid.getPosition().y + userGrid.getSize().height + 10));
        userLabel.setSize(new Dimension(userGrid.getSize().width, 15));

        labelCoule = new Label("", Color.WHITE, 15f, Direction.LEFT);
        labelCoule.setPosition(new Vector2D(enemyGrid.getPosition().x, enemyGrid.getPosition().y + enemyGrid.getSize().height + 25));
        labelCoule.setSize(new Dimension(enemyGrid.getSize().width, 15));

        remaining =  new Label("", Color.WHITE, 15f, Direction.RIGHT);
        remaining.setPosition(new Vector2D(opponentLabel.getPosition().x, opponentLabel.getPosition().y + opponentLabel.getSize().height));
        remaining.setSize(new Dimension(enemyGrid.getSize().width, 15));



        gameScene.addGameObject(-1, Scenes.MENU.getBackground());
        gameScene.addGameObject(userGrid);
        gameScene.addGameObject(enemyGrid);
        gameScene.addGameObject(turn);
        gameScene.addGameObject(gameId);
        gameScene.addGameObject(userLabel);
        gameScene.addGameObject(opponentLabel);
        gameScene.addGameObject(remaining);
        gameScene.addGameObject(labelCoule);
        gameScene.addGameObject(quit);
        gameScene.addGameObject(2000, startAnimation);
    }

    public void start() {
        gameId.setText("GameID: " + engine.getNetwork().getCurrentGame().getId());

        String userName = engine.getNetwork().getUser().getPlayer().getName();
        String enemyName = engine.getNetwork().getEnemy().getName();

        userLabel.setText(userName);
        opponentLabel.setText(enemyName);
        remaining.setText("0 / 20");

        startAnimation.setUsername1(userName);
        startAnimation.setUsername2(enemyName);
        startAnimation.start();

        // Affichage des bateaux
        for(ShipObject ship : shipObjects) Scenes.GAME.removeGameObject(ship);

        for(IShip ship : engine.getNetwork().getUser().getNavyFleet().getShips()) {
            ShipObject shipObject = new ShipObject(userGrid, (Ship) ship);
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

    public void setEnemyName(String name) {
        opponentLabel.setText(name);
    }

    public Label getLabelCoule() {
        return labelCoule;
    }

    public Label getLabelRemaining() {
        return remaining;
    }
}
