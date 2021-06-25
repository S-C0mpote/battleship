package info1.game;

import info1.game.engine.GameEngine;
import info1.game.engine.Scenes;
import info1.game.resources.Fonts;

public class Game {

    // Activer ou désactiver le proxy :
    public static final boolean USE_PROXY = true;

    public static GameEngine engine = new GameEngine();

    /**
     * On définit la police sur la police préchargé en cache.
     * On charge la scène menu.
     * On démarre le moteur sur la scène menu.
     */
    public static void main(String[] args) {
        engine.getGameCanvas().setFont(Fonts.MAIN);

        Scenes.MENU.load();

        engine.start(Scenes.MENU);
    }
}
