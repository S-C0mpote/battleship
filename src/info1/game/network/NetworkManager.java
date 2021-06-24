package info1.game.network;

import com.mashape.unirest.http.exceptions.UnirestException;
import info1.game.engine.listeners.NetworkListener;
import info1.network.BadIdException;
import info1.network.Game;
import info1.network.Network;
import info1.ships.BadCoordException;
import info1.ships.Coord;
import info1.ships.ICoord;
import info1.ships.UncompleteFleetException;

import java.util.Optional;

public class NetworkManager {

    private static final String API = "http://37.187.38.219/api/v0";

    private final GamePlayer user;
    private GamePlayer enemy;
    private GamePlayer playerTurn;

    private Game currentGame;
    private NetworkListener listener;
    private boolean onPlayerJoin = false;
    private boolean onPlayerJoinSent = false;

    private boolean onEnemyTurn = false;
    private boolean onEnemyTurnSent = false;

    private boolean onPlayerTurn = false;
    private boolean onPlayerTurnSent = false;

    /**
     * Connecte le joueur, pour les évènement nous utilisons des boulean car
     * en Multi Threading cela pourrait générer des erreurs de ConcurrentModification
     * (édition d'une liste pendant qu'un autre thread la lit)
     */
    public NetworkManager(GamePlayer user) {
        this.user = user;

        Network.setProxy("srv-proxy-etu-2.iut-nantes.univ-nantes.prive", 3128);
        Network.enableProxy(true);

        new Thread(() -> {
            try {
                Network.suscribeNewPlayer(API, user.getPlayer());
                System.out.println("Connected to " + user.getPlayer().getName() + "!");
            } catch (UnirestException e) { e.printStackTrace(); }

            while (true) {
                try { Thread.sleep(1000); }
                catch (InterruptedException e) {e.printStackTrace();}

                if(currentGame == null) {
                    System.out.println("En attente de jeu...");
                    continue;
                }

                int status = getStatus();

                switch (status) {
                    case 10, -10 -> {
                        if(!onPlayerJoinSent) {
                            System.out.println("Party starting...");
                            onPlayerJoin = true;
                            onPlayerJoinSent = true;
                        }

                        if(status == 10 && !onPlayerTurnSent) {
                            onEnemyTurnSent = false;
                            onPlayerTurnSent = true;
                            onPlayerTurn = true;
                            playerTurn = user;
                        } else if(!onEnemyTurnSent) {
                            onEnemyTurnSent = true;
                            onPlayerTurnSent = false;
                            onEnemyTurn = true;
                            playerTurn = enemy;
                        }
                    }

                    default -> {
                        System.out.println("Status inconnu : " + status);
                    }
                }
            }
        }).start();
    }

    public void update() {
        if(onPlayerJoin) {
            listener.onPlayerJoin();
            onPlayerJoin = false;
        }

        if(onEnemyTurn) {
            listener.onPlayerJoin();
            onEnemyTurn = false;
        }

        if(onPlayerTurn) {
            listener.onPlayerJoin();
            onPlayerTurn = false;
        }
    }

    public void createGame() {
        try {
            currentGame = Network.initNewGame(API, user.getPlayer(), user.getNavyFleet());
        } catch (UnirestException | UncompleteFleetException | BadCoordException e) {
            e.printStackTrace();
        }
    }

    public void leaveGame() {
        currentGame = null;
    }

    public boolean joinGame(int code) {
        try {
            Optional<Game> game = Network.listInitializedGames(API).stream()
                    .filter(g -> g.getId() == code)
                    .findFirst();

            if(game.isEmpty()) return false;
            currentGame = game.get();
            onPlayerJoinSent = true;

            boolean ret = Network.joinGame(API, currentGame, user.getPlayer(), user.getNavyFleet());
            System.out.println(currentGame);
            return ret;
        } catch (UnirestException | UncompleteFleetException | BadCoordException e) { e.printStackTrace(); }
        return false;

    }

    public int getStatus() {
        try {
            return Network.getInfo(API, currentGame, user.getPlayer());
        } catch (UnirestException | BadIdException e) { e.printStackTrace(); }

        return -999;
    }
    public void playOnGrid(ICoord coord) throws BadCoordException, UnirestException {
        if(Network.playOneTurn(API, currentGame, user.getPlayer(), new Coord(coord.toString())) == 1){
            System.out.println("touché");
        }
        System.out.println("raté");
    }

    public Game getCurrentGame() { return currentGame; }
    public GamePlayer getUser() { return user; }

    public NetworkListener getListener() { return listener; }
    public void setListener(NetworkListener listener) { this.listener = listener; }
}
