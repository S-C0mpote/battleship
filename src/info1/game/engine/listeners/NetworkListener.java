package info1.game.engine.listeners;

public interface NetworkListener {

    /**
     * [ENGINE THREAD]
     * Called (
     */
    void onPlayerJoin();
    void onPlayerTurn();
    void onEnnemyTurn();
}
