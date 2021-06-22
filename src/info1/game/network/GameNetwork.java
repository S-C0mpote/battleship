package info1.game.network;

import com.mashape.unirest.http.exceptions.UnirestException;
import info1.network.Game;
import info1.network.Network;
import info1.ships.BadCoordException;
import info1.ships.UncompleteFleetException;

public class GameNetwork {

    private static final String API = "http://37.187.38.219/api/v0";

    private final GamePlayer user;
    private GamePlayer enemy;
    private Game currentGame;

    public GameNetwork(GamePlayer user) {
        this.user = user;

        Network.setProxy("srv-proxy-etu-2.iut-nantes.univ-nantes.prive", 3128);
        Network.enableProxy(false);

        try {
            Network.suscribeNewPlayer(API, user.getPlayer());
        } catch (UnirestException e) { e.printStackTrace(); }
    }

    /**
     * @return Le code de la partie créée
     */
    public boolean createGame() {
        try {
            currentGame = Network.initNewGame(API, user.getPlayer(), user.getNavyFleet());
            return true;
        } catch (UnirestException | UncompleteFleetException | BadCoordException e) { e.printStackTrace(); }

        return false;
    }

    public Game getCurrentGame() { return currentGame; }
    public GamePlayer getUser() { return user; }
}
