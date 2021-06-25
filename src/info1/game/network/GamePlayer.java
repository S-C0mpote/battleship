package info1.game.network;

import info1.network.Player;
import info1.ships.INavyFleet;
import info1.ships.NavyFleetConfiguration;

public class GamePlayer {

    private Player player;
    private INavyFleet navyFleet;

    /**
     * Classe regroupant {@link Player} avec le {@link info1.ships.NavyFleet} du {@link Player}
     */
    public GamePlayer(String name) {
        this.player = new Player(name);
        this.navyFleet = NavyFleetConfiguration.getFrenchDefault();
    }

    public Player getPlayer() { return player; }
    public void setPlayer(Player player) { this.player = player; }

    public INavyFleet getNavyFleet() { return navyFleet; }
    public void setNavyFleet(INavyFleet navyFleet) { this.navyFleet = navyFleet; }

}
