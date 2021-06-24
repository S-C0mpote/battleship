package info1.game.network;

import info1.game.engine.listeners.NetworkListener;

public class NetworkEvents implements NetworkListener {

    private static NetworkEvents instance;

    @Override
    public void onPlayerJoin() {

    }

    @Override
    public void onPlayerTurn() {

    }

    @Override
    public void onEnemyTurn() {

    }

    public static NetworkEvents getInstance() {
        if(instance == null) instance = new NetworkEvents();
        return instance;
    }

}
