package info1.game.scenes;

import info1.game.engine.GameEngine;
import info1.game.engine.Scene;
import info1.game.engine.Scenes;
import info1.game.engine.gameobjects.Grid;
import info1.game.engine.gameobjects.LabelCenter;
import info1.game.engine.gameobjects.LabelIndicator;
import info1.game.utils.Vector2D;
import info1.network.Network;
import info1.ships.NavyFleet;

import java.awt.*;

public class GameScene {

    private static GameEngine engine;
    private static LabelIndicator gameId;

    public static void load(GameEngine engine){
        GameScene.engine = engine;

        Scene ingame = Scenes.IN_GAME.getScene();

        Grid emptyGrid = new Grid();
        Grid userGrid = new Grid(engine.getNetwork().getUser());

        userGrid.setSize(new Dimension(200, 200));
        userGrid.setPosition(new Vector2D(
                1280 / 4d - userGrid.getSize().width / 2d - 120,
                720 / 3d - userGrid.getSize().height / 2d));


        emptyGrid.setSize(new Dimension(500, 500));
        emptyGrid.setPosition(new Vector2D(
                1280 / 2d,
                userGrid.getPosition().y));


        LabelCenter turn =  new LabelCenter("VOTRE Tour !",
                Color.WHITE, 20f);

        turn.setPosition(new Vector2D(1280 / 2d - 75, 20));
        turn.setSize(new Dimension(1280 / 2, 50));


        gameId = new LabelIndicator("#0", Color.WHITE, 15f, 10, 10);

        // Cases restantes -1
        LabelIndicator cellRemaining =  new LabelIndicator("Cells remaining : 10 / 100",
                Color.WHITE, 20f, (int) emptyGrid.getPosition().x, (int) emptyGrid.getPosition().y + emptyGrid.getSize().height);
        
        ingame.addGameObject(-1,MenuScene.background);
        ingame.addGameObject(userGrid);
        ingame.addGameObject(emptyGrid);
        ingame.addGameObject(turn);
        ingame.addGameObject(gameId);
        ingame.addGameObject(cellRemaining);
    }

    public static void start() {
        gameId.setText("GameID: " + engine.getNetwork().getCurrentGame().getId());
    }
}
