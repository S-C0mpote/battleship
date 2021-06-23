package info1.game.network;

import com.mashape.unirest.http.exceptions.UnirestException;
import info1.network.BadIdException;
import info1.network.Game;
import info1.network.Network;
import info1.network.Player;
import info1.ships.BadCoordException;
import info1.ships.UncompleteFleetException;

import java.util.Optional;

public class GameNetwork {

    private static final String API = "http://37.187.38.219/api/v0";

    private final GamePlayer user;
    private GamePlayer enemy;
    private Game currentGame;

    public GameNetwork(GamePlayer user) {
        this.user = user;

        Network.setProxy("srv-proxy-etu-2.iut-nantes.univ-nantes.prive", 3128);
        Network.enableProxy(true);

        try {
            Network.suscribeNewPlayer(API, user.getPlayer());
            System.out.println("Connected to " + user.getPlayer().getName() + "!");
        } catch (UnirestException e) { e.printStackTrace(); }
    }

    public boolean createGame() {
        try {
            currentGame = Network.initNewGame(API, user.getPlayer(), user.getNavyFleet());
            return true;
        } catch (UnirestException | UncompleteFleetException | BadCoordException e) { e.printStackTrace(); }

        return false;
    }

    public boolean joinGame(int code) {
        try {
            Optional<Game> game = Network.listInitializedGames(API).stream()
                    .filter(g -> g.getId() == code)
                    .findFirst();

            if(game.isEmpty()) return false;
            currentGame = game.get();

            return Network.joinGame(API, currentGame, user.getPlayer(), user.getNavyFleet());
        } catch (UnirestException | UncompleteFleetException | BadCoordException e) { e.printStackTrace(); }
        return false;
    }

    public int getStatus() {
        try {
            return Network.getInfo(API, currentGame, user.getPlayer());
        } catch (UnirestException | BadIdException e) { e.printStackTrace(); }

        return -999;
    }

    public Game getCurrentGame() { return currentGame; }
    public GamePlayer getUser() { return user; }
}
