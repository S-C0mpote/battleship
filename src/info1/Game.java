package info1;

import info1.game.engine.GameEngine;
import info1.game.engine.Scenes;

public class Game {

    public static void main(String[] args) {
        GameEngine engine = new GameEngine();

        engine.start(Scenes.MENU.getScene());
    }

}
