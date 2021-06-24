package info1.game.network;

import info1.game.Game;
import info1.game.engine.Scenes;
import info1.game.engine.listeners.NetworkListener;
import info1.game.scenes.GameScene;

public class NetworkEvents implements NetworkListener {

    private static NetworkEvents instance;

    @Override
    public void onPlayerJoin() {
        Game.engine.setScene(Scenes.IN_GAME.getScene());
        GameScene.start();
    }

    @Override
    public void onPlayerTurn() {
        GameScene.playerTurn();
    }

    @Override
    public void onEnemyTurn() {
        GameScene.enemyTurn();
    }

    public static NetworkEvents getInstance() {
        if(instance == null) instance = new NetworkEvents();
        return instance;
    }

}
