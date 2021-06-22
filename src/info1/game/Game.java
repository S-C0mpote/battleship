package info1.game;

import info1.game.engine.GameEngine;
import info1.game.engine.Scenes;
import info1.game.network.GameNetwork;
import info1.game.resources.Fonts;
import info1.game.scenes.MenuScene;
import info1.game.scenes.SetupScene;
import info1.ships.BadCoordException;
import info1.ships.CoordsBadShipException;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class Game {

    public static GameEngine engine = new GameEngine();

    public static void main(String[] args) {
        engine.getGameCanvas().setFont(Fonts.MAIN);
        MenuScene.load(engine);
        engine.start(Scenes.MENU.getScene());
    }
}
