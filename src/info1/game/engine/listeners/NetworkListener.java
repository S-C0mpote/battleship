package info1.game.engine.listeners;

public interface NetworkListener {
    
    void onEnemyJoin();
    void onPlayerTurn();
    void onEnemyTurn();
    
    void onPlayerWin();
    void onPlayerLoose();
}
