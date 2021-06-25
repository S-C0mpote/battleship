package info1.game;

import info1.game.engine.GameEngine;
import info1.game.engine.Scenes;
import info1.game.resources.Fonts;

public class Game {

    public static final boolean USE_PROXY = true;

    public static GameEngine engine = new GameEngine();

    public static void main(String[] args) {
        engine.getGameCanvas().setFont(Fonts.MAIN);

        Scenes.MENU.load();

        engine.start(Scenes.MENU);
    }
}
