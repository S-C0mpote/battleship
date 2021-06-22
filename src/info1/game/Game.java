package info1.game;

import info1.game.engine.GameEngine;
import info1.game.engine.Scenes;
import info1.game.resources.Fonts;
import info1.game.scenes.MenuScene;
import info1.game.scenes.SetupScene;

public class Game {

    public static void main(String[] args) {
        GameEngine engine = new GameEngine();
        engine.getGameCanvas().setFont(Fonts.MAIN);

        MenuScene.load(engine);
        SetupScene.load(engine);

        engine.start(Scenes.SETUP.getScene());
    }

}
