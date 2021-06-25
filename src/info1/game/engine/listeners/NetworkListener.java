package info1.game.engine.listeners;

public interface NetworkListener {

    /**
     * Déclenché quand l'adversaire rejoins la partie créée
     */
    void onEnemyJoin();

    /**
     * Déclenché quand c'est au tour du joueur
     */
    void onPlayerTurn();

    /**
     * Déclenché quand c'est au tour de l'adversaire
     */
    void onEnemyTurn();

    /**
     * Déclenché quand on a gagné
     */
    void onPlayerWin();

    /**
     * Déclenché quand on a perdu
     */
    void onPlayerLoose();
}
