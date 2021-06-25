package info1.game.network;

import info1.game.Game;
import info1.game.components.PopupMessage;
import info1.game.engine.Scenes;
import info1.game.engine.listeners.NetworkListener;
import info1.game.scenes.GameScene;

public class NetworkEvents implements NetworkListener {

    private static NetworkEvents instance;
    private final GameScene gameScene = Scenes.GAME;
    private boolean reload = true;

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
        //System.out.println(Game.engine.getNetwork().getCurrentGame().getGuest());
        //gameScene.setEnemyName(Game.engine.getNetwork().getEnemy().getName());
    }

    @Override
    public void onPlayerWin() {
        if (reload){
            new PopupMessage(Game.engine, "Tu as gagné !", Scenes.GAME);
            reload = false;
        }

    }

    @Override
    public void onPlayerLoose() {

        if (reload){
            new PopupMessage(Game.engine, "Tu as perdu !", Scenes.GAME);
            reload = false;
        }

    }

    public static NetworkEvents getInstance() {
        if(instance == null) instance = new NetworkEvents();
        return instance;
    }

}
