package info1.game.scenes;

import info1.game.engine.GameEngine;
import info1.game.engine.Scene;
import info1.game.engine.Scenes;
import info1.game.engine.gameobjects.Grid;
import info1.game.utils.Vector2D;
import info1.ships.NavyFleet;

import java.awt.*;

public class GameScene {
    public static void load(GameEngine engine){
        Scene ingame = Scenes.IN_GAME.getScene();
        NavyFleet currentFleet = (NavyFleet) engine.getNetwork().getUser().getNavyFleet();

        Grid emptyGrid = new Grid();
        Grid userGrid = new Grid(currentFleet);

        userGrid.setSize(new Dimension(200, 200));
        userGrid.setPosition(new Vector2D(
                1280 / 2d - userGrid.getSize().width / 2d,
                720 / 2d - userGrid.getSize().height / 2d));


    }
}
