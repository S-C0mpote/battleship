package info1.game.network;

import info1.game.Game;
import info1.game.components.PopupMessage;
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
        PopupMessage popupMessage = new PopupMessage(Game.engine, "You WIN", Scenes.GAME);
        popupMessage.getButton().setListener(() -> {
            popupMessage.getBackground().close();
            popupMessage.getModal().close();

            Game.engine.setScene(Scenes.MENU);
        });
    }

    @Override
    public void onPlayerLoose() {
        PopupMessage popupMessage = new PopupMessage(Game.engine, "You LOOSE", Scenes.GAME);
        popupMessage.getButton().setListener(() -> {
            popupMessage.getBackground().close();
            popupMessage.getModal().close();

            Game.engine.setScene(Scenes.MENU);
        });
    }

    public static NetworkEvents getInstance() {
        if(instance == null) instance = new NetworkEvents();
        return instance;
    }

}
