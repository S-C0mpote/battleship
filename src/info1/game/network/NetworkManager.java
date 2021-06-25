package info1.game.network;

import com.mashape.unirest.http.exceptions.UnirestException;
import info1.game.components.PopupMessage;
import info1.game.engine.Scenes;
import info1.game.engine.listeners.NetworkListener;
import info1.network.BadIdException;
import info1.network.Game;
import info1.network.Network;
import info1.network.Player;
import info1.ships.BadCoordException;
import info1.ships.ICoord;
import info1.ships.UncompleteFleetException;

import java.util.Optional;

public class NetworkManager {

    private static final String API = "http://37.187.38.219/api/v0";

    private final GamePlayer user;
    private Player enemy;

    private Game currentGame;
    private NetworkListener listener;
    private boolean onPlayerJoin = false;
    private boolean onPlayerJoinSent = false;

    private boolean onEnemyTurn = false;

    private boolean onPlayerTurn = false;

    private boolean onPlayerWin = false;
    private boolean onPlayerWinSent = false;

    private boolean onPlayerLoose = false;
    private boolean onPlayerLooseSent = false;

    /**
     * Connecte le joueur, pour les évènement nous utilisons des boulean car
     * en Multi Threading cela pourrait générer des erreurs de ConcurrentModification
     * (édition d'une liste pendant qu'un autre thread l'a lit)
     */
    public NetworkManager(GamePlayer user) {
        this.user = user;

        Network.setProxy("srv-proxy-etu-2.iut-nantes.univ-nantes.prive", 3128);
        Network.enableProxy(true);

        new Thread(() -> {
            try {
                Network.suscribeNewPlayer(API, user.getPlayer());
                System.out.println("Connected to " + user.getPlayer().getName() + "!");
            } catch (UnirestException e) {
                new PopupMessage(info1.game.Game.engine, "Vérifiez le proxy.");
            }

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
                            onPlayerJoin = true;
                            onPlayerJoinSent = true;
                        }

                        if(status == 10) {
                            onPlayerTurn = true;
                        } else {
                            onEnemyTurn = true;
                        }
                    }

                    case 100 -> {
                        if(!onPlayerWinSent) {
                            onPlayerWin = true;
                        }
                    }

                    case -100 -> {
                        if(!onPlayerLooseSent) {
                            onPlayerLoose = true;
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
            // TODO: ON PEUT PAS RECUPERER LE PSEUDO DE L'ADVERSAIRE
            enemy = new Player("Enemy");
            listener.onEnemyJoin();
            onPlayerJoin = false;
        }

        if(onEnemyTurn) {
            enemy = currentGame.getGuest();
            listener.onEnemyTurn();
            onEnemyTurn = false;
        }

        if(onPlayerTurn) {
            listener.onPlayerTurn();
            onPlayerTurn = false;
        }

        if(onPlayerWin) {
            listener.onPlayerWin();
            onPlayerWin = false;
        }

        if(onPlayerLoose) {
            listener.onPlayerLoose();
            onPlayerLoose = false;
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
        onPlayerJoinSent = false;
        onPlayerJoin = false;
    }

    public boolean joinGame(int code) {
        try {
            Optional<Game> game = Network.listInitializedGames(API).stream()
                    .filter(g -> g.getId() == code)
                    .findFirst();

            if(game.isEmpty()) return false;
            currentGame = game.get();
            enemy = currentGame.getInitiator();
            onPlayerJoinSent = true;
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

    public int play(ICoord coord) throws BadCoordException, UnirestException {
        if(currentGame == null) return -999;

        return Network.playOneTurn(API, currentGame, user.getPlayer(), coord);
    }

    public Game getCurrentGame() { return currentGame; }
    public GamePlayer getUser() { return user; }
    public Player getEnemy() { return enemy; }

    public NetworkListener getListener() { return listener; }
    public void setListener(NetworkListener listener) { this.listener = listener; }
}
