package info1.game.network;

import info1.game.Game;
import info1.game.engine.Scenes;
import info1.game.engine.listeners.NetworkListener;
import info1.game.scenes.GameScene;

public class NetworkEvents implements NetworkListener {

    private static NetworkEvents instance;
    private final GameScene gameScene = Scenes.GAME;

    @Override
    public void onEnemyJoin() {
        Game.engine.setScene(gameScene);
        gameScene.start();
    }

    @Override
    public void onPlayerTurn() {
        gameScene.playerTurn();
    }

    @Override
    public void onEnemyTurn() {
        gameScene.enemyTurn();
    }

    @Override
    public void onPlayerWin() {
        System.out.println("You WIN !");
    }

    @Override
    public void onPlayerLoose() {
        System.out.println("You LOOSE !");
    }

    public static NetworkEvents getInstance() {
        if(instance == null) instance = new NetworkEvents();
        return instance;
    }

}
