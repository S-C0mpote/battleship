package info1;

import info1.game.engine.GameEngine;
import info1.game.engine.Scene;
import info1.game.engine.Scenes;
import info1.game.engine.gameobjects.Input;

import java.io.IOException;

public class Game {

    public static void main(String[] args) throws IOException {
        GameEngine engine = new GameEngine();

        Scene menu = Scenes.MENU.getScene();

        Input input = new Input(engine, 50, 50, 300, 50);

        menu.addGameObject(input);

        engine.getGameCanvas().addKeyListener(input);
        engine.start(menu);
    }

}
