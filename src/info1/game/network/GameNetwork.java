package info1.game.network;

import com.mashape.unirest.http.exceptions.UnirestException;
import info1.network.Game;
import info1.network.Network;
import info1.network.Player;
import info1.ships.BadCoordException;
import info1.ships.UncompleteFleetException;

public class GameNetwork {

    public static final String API = "http://37.187.38.219/api/v0";

    public final GamePlayer client;
    public GamePlayer enemy;
    public Game currentGame;

    public GameNetwork(GamePlayer client) {
        this.client = client;

        Network.setProxy("srv-proxy-etu-2.iut-nantes.univ-nantes.prive", 3128);
        Network.enableProxy(true);

        try {
            Network.suscribeNewPlayer(API, client.getPlayer());
        } catch (UnirestException e) { e.printStackTrace(); }
    }

    /**
     * @return Le code de la partie créée
     */
    public boolean createGame() {
        try {
            currentGame = Network.initNewGame(API, client.getPlayer(), client.getNavyFleet());
            return true;
        } catch (UnirestException | UncompleteFleetException | BadCoordException e) { e.printStackTrace(); }

        return false;
    }

}
