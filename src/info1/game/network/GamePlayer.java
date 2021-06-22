package info1.game.network;

import info1.game.Game;
import info1.network.Player;
import info1.ships.INavyFleet;

public class GamePlayer {

    private Player player;
    private INavyFleet navyFleet;

    public GamePlayer(String name) {
        this.player = new Player(name);
    }

    public Player getPlayer() { return player; }
    public void setPlayer(Player player) { this.player = player; }

    public INavyFleet getNavyFleet() { return navyFleet; }
    public void setNavyFleet(INavyFleet navyFleet) { this.navyFleet = navyFleet; }

}
