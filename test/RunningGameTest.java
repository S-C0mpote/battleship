import info1.network.BadIdException;
import info1.network.Game;
import info1.network.Network;
import info1.network.Player;
import info1.ships.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * Classe de tests démontrant le déroulement d'une partie en utilisant la librairie Network.
 *
 * ATTENTION : necessite d'avoir développer les classes du package info1.ships.*
 *
 * @author Arnaud Lanoix
 * @version 2.0
 */

public class RunningGameTest {

    public static final String HTTP_LOCALHOST = "http://37.187.38.219/api/v0";

    static Player joueur1;
    static Player joueur2;

    static INavyFleet flotte1;
    static INavyFleet flotte2;

    @BeforeAll
    public static void init() throws Exception {
        joueur1 = new Player("Joueur"+Math.random());
        joueur2 = new Player("Joueur"+Math.random());

        flotte1 = new NavyFleet();
        flotte2 = new NavyFleet();

        Network.suscribeNewPlayer(HTTP_LOCALHOST, joueur1);
        Network.suscribeNewPlayer(HTTP_LOCALHOST, joueur2);

        flotte1.add(new AircraftCarrier("monPorteAvion", "E5", "E9"));
        flotte1.add(new Battleship("monCuirasse", "B2", "E2"));
        flotte1.add(new Submarine("monSousMarin", "G10"));
        flotte1.add(new Cruiser("monCroiseur", "B8", "B6"));
        flotte1.add(new Destroyer("monTorpilleur", "H3", "H4"));
        flotte1.add(new Destroyer("autreTorpilleur", "D9", "C9"));
        flotte1.add(new Cruiser("autreCroiseur", "J8", "H8"));

        flotte2.add(new AircraftCarrier("monPorteAvion", "E5", "E9"));
        flotte2.add(new Battleship("monCuirasse", "B2", "E2"));
        flotte2.add(new Submarine("monSousMarin", "G10"));
        flotte2.add(new Cruiser("monCroiseur", "B8", "B6"));
        flotte2.add(new Destroyer("monTorpilleur", "H3", "H4"));
        flotte2.add(new Destroyer("autreTorpilleur", "D9", "C9"));
        flotte2.add(new Cruiser("autreCroiseur", "J8", "H8"));
    }

    @AfterAll
    public static void finish() throws Exception {
    }

    @Test
    public void info_init() throws Exception {
        Game jeu = Network.initNewGame(HTTP_LOCALHOST, joueur1, flotte1);
        assertEquals(1, Network.getInfo(HTTP_LOCALHOST, jeu, joueur1));
        assertEquals(-1,Network.getInfo(HTTP_LOCALHOST, jeu, joueur2));
    }

    @Test
    public void info_init2() throws Exception {
        Game jeu = Network.initNewGame(HTTP_LOCALHOST, joueur2, flotte1);
        assertEquals(-1, Network.getInfo(HTTP_LOCALHOST, jeu, joueur1));
        assertEquals(1,Network.getInfo(HTTP_LOCALHOST, jeu, joueur2));
    }

    @Test void info_badId() throws Exception {
        Game jeu = Network.initNewGame(HTTP_LOCALHOST, joueur2, flotte1);
        assertThrows(BadIdException.class,
                () -> Network.getInfo(HTTP_LOCALHOST, new Game(Integer.MAX_VALUE), joueur1));
    }

    @Test
    public void info_join() throws Exception {
        Game jeu = Network.initNewGame(HTTP_LOCALHOST, joueur1, flotte1);
        assertTrue(Network.joinGame(HTTP_LOCALHOST, jeu, joueur2, flotte2));
        assertEquals(10,Network.getInfo(HTTP_LOCALHOST, jeu, joueur1));
        assertEquals(-10,Network.getInfo(HTTP_LOCALHOST, jeu, joueur2));
    }


    @Test
    public void play_nontouche() throws Exception {
        Game jeu = Network.initNewGame(HTTP_LOCALHOST, joueur1, flotte1);
        assertTrue(Network.joinGame(HTTP_LOCALHOST, jeu, joueur2, flotte2));
        assertEquals(0, Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur1, new Coord("A1")));
    }

    @Test
    public void play_touche1() throws Exception {
        Game jeu = Network.initNewGame(HTTP_LOCALHOST, joueur1, flotte1);
        assertTrue(Network.joinGame(HTTP_LOCALHOST, jeu, joueur2, flotte2));

        assertEquals(1, Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur1, new Coord("E5")));
    }

    @Test
    public void play_touche2() throws Exception {
        Game jeu = Network.initNewGame(HTTP_LOCALHOST, joueur1, flotte1);
        assertTrue(Network.joinGame(HTTP_LOCALHOST, jeu, joueur2, flotte2));

        assertEquals(1, Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur1, new Coord("C9")));
    }

    @Test
    public void play_touche3() throws Exception {
        Game jeu = Network.initNewGame(HTTP_LOCALHOST, joueur1, flotte1);
        assertTrue(Network.joinGame(HTTP_LOCALHOST, jeu, joueur2, flotte2));
        assertEquals(1, Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur1, new Coord("D2")));
    }

    @Test
    public void play_toucheCoule_pa() throws Exception {
        Game jeu = Network.initNewGame(HTTP_LOCALHOST, joueur1, flotte1);
        assertTrue(Network.joinGame(HTTP_LOCALHOST, jeu, joueur2, flotte2));

        // AircraftCarrier("monPorteAvion", "E5", "E9"));

        assertEquals(1, Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur1, new Coord("E5")));
        Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur2, new Coord("A1"));
        assertEquals(1, Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur1, new Coord("E6")));
        Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur2, new Coord("A1"));
        assertEquals(1, Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur1, new Coord("E7")));
        Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur2, new Coord("A1"));
        assertEquals(1, Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur1, new Coord("E8")));
        Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur2, new Coord("A1"));
        assertEquals(10, Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur1, new Coord("E9")));
    }

    @Test
    public void info_toucheCoule_pa() throws Exception {
        Game jeu = Network.initNewGame(HTTP_LOCALHOST, joueur1, flotte1);
        assertTrue(Network.joinGame(HTTP_LOCALHOST, jeu, joueur2, flotte2));

        // AircraftCarrier("monPorteAvion", "E5", "E9"));

        Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur1, new Coord("E5"));
        assertEquals(-10,Network.getInfo(HTTP_LOCALHOST, jeu, joueur1));
        assertEquals(10,Network.getInfo(HTTP_LOCALHOST, jeu, joueur2));
        Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur2, new Coord("A1"));
        assertEquals(10,Network.getInfo(HTTP_LOCALHOST, jeu, joueur1));
        assertEquals(-10,Network.getInfo(HTTP_LOCALHOST, jeu, joueur2));
        Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur1, new Coord("E6"));
        assertEquals(-10,Network.getInfo(HTTP_LOCALHOST, jeu, joueur1));
        assertEquals(10,Network.getInfo(HTTP_LOCALHOST, jeu, joueur2));
        Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur2, new Coord("A1"));
        assertEquals(10,Network.getInfo(HTTP_LOCALHOST, jeu, joueur1));
        assertEquals(-10,Network.getInfo(HTTP_LOCALHOST, jeu, joueur2));
        Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur1, new Coord("E7"));
        assertEquals(-10,Network.getInfo(HTTP_LOCALHOST, jeu, joueur1));
        assertEquals(10,Network.getInfo(HTTP_LOCALHOST, jeu, joueur2));
        Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur2, new Coord("A1"));
        assertEquals(10,Network.getInfo(HTTP_LOCALHOST, jeu, joueur1));
        assertEquals(-10,Network.getInfo(HTTP_LOCALHOST, jeu, joueur2));
        Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur1, new Coord("E8"));
        assertEquals(-10,Network.getInfo(HTTP_LOCALHOST, jeu, joueur1));
        assertEquals(10,Network.getInfo(HTTP_LOCALHOST, jeu, joueur2));
        Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur2, new Coord("A1"));
        assertEquals(10,Network.getInfo(HTTP_LOCALHOST, jeu, joueur1));
        assertEquals(-10,Network.getInfo(HTTP_LOCALHOST, jeu, joueur2));
        Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur1, new Coord("E9"));
        assertEquals(-10,Network.getInfo(HTTP_LOCALHOST, jeu, joueur1));
        assertEquals(10,Network.getInfo(HTTP_LOCALHOST, jeu, joueur2));
    }





    @Test
    public void play_toucheCoule_cu() throws Exception {
        Game jeu = Network.initNewGame(HTTP_LOCALHOST, joueur1, flotte1);
        assertTrue(Network.joinGame(HTTP_LOCALHOST, jeu, joueur2, flotte2));

        // Battleship("monCuirasse", "B2", "E2"));

        assertEquals(1, Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur1, new Coord("D2")));
        Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur2, new Coord("A1"));
        assertEquals(1, Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur1, new Coord("C2")));
        Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur2, new Coord("A1"));
        assertEquals(1, Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur1, new Coord("B2")));
        Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur2, new Coord("A1"));
        assertEquals(10, Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur1, new Coord("E2")));
    }


    @Test
    public void play_toucheCoule_sb() throws Exception {
        Game jeu = Network.initNewGame(HTTP_LOCALHOST, joueur1, flotte1);
        assertTrue(Network.joinGame(HTTP_LOCALHOST, jeu, joueur2, flotte2));

        // Submarine("monSousMarin", "G10"));

        assertEquals(10, Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur1, new Coord("G10")));
    }

    @Test
    public void play_toucheCoule_cr1() throws Exception {
        Game jeu = Network.initNewGame(HTTP_LOCALHOST, joueur1, flotte1);
        assertTrue(Network.joinGame(HTTP_LOCALHOST, jeu, joueur2, flotte2));

        // Cruiser("monCroiseur", "B8", "B6"));

        assertEquals(1, Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur1, new Coord("B8")));
        Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur2, new Coord("A1"));
        assertEquals(1, Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur1, new Coord("B7")));
        Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur2, new Coord("A1"));
        assertEquals(10, Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur1, new Coord("B6")));
    }

    @Test
    public void play_toucheCoule_de1() throws Exception {
        Game jeu = Network.initNewGame(HTTP_LOCALHOST, joueur1, flotte1);
        assertTrue(Network.joinGame(HTTP_LOCALHOST, jeu, joueur2, flotte2));

        // Destroyer("monTorpilleur", "H3", "H4"));

        assertEquals(1, Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur1, new Coord("H3")));
        Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur2, new Coord("A1"));
        assertEquals(10, Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur1, new Coord("H4")));
    }

    @Test
    public void play_toucheCoule_de2() throws Exception {
        Game jeu = Network.initNewGame(HTTP_LOCALHOST, joueur1, flotte1);
        assertTrue(Network.joinGame(HTTP_LOCALHOST, jeu, joueur2, flotte2));

        // Destroyer("autreTorpilleur", "D9", "C9"));

        assertEquals(1, Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur1, new Coord("D9")));
        Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur2, new Coord("A1"));
        assertEquals(10, Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur1, new Coord("C9")));
    }

    @Test
    public void play_toucheCoule_cr2() throws Exception {
        Game jeu = Network.initNewGame(HTTP_LOCALHOST, joueur1, flotte1);
        assertTrue(Network.joinGame(HTTP_LOCALHOST, jeu, joueur2, flotte2));

        // Cruiser("autreCroiseur", "J8", "H8"));

        assertEquals(1, Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur1, new Coord("J8")));
        Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur2, new Coord("A1"));
        assertEquals(1, Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur1, new Coord("I8")));
        Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur2, new Coord("A1"));
        assertEquals(10, Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur1, new Coord("H8")));
    }

    @Test
    public void play_toucheCouler_gagne() throws Exception {
        Game jeu = Network.initNewGame(HTTP_LOCALHOST, joueur1, flotte1);
        assertTrue(Network.joinGame(HTTP_LOCALHOST, jeu, joueur2, flotte2));

        assertEquals(1, Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur1, new Coord("J8")));
        Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur2, new Coord("A1"));
        assertEquals(1, Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur1, new Coord("I8")));
        Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur2, new Coord("A1"));
        assertEquals(10, Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur1, new Coord("H8")));

        Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur2, new Coord("A1"));
        assertEquals(1, Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur1, new Coord("D9")));
        Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur2, new Coord("A1"));
        assertEquals(10, Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur1, new Coord("C9")));

        Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur2, new Coord("A1"));
        assertEquals(1, Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur1, new Coord("H3")));
        Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur2, new Coord("A1"));
        assertEquals(10, Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur1, new Coord("H4")));

        Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur2, new Coord("A1"));
        assertEquals(1, Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur1, new Coord("B8")));
        Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur2, new Coord("A1"));
        assertEquals(1, Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur1, new Coord("B7")));
        Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur2, new Coord("A1"));
        assertEquals(10, Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur1, new Coord("B6")));

        Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur2, new Coord("A1"));
        assertEquals(10, Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur1, new Coord("G10")));

        Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur2, new Coord("A1"));
        assertEquals(1, Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur1, new Coord("D2")));
        Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur2, new Coord("A1"));
        assertEquals(1, Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur1, new Coord("C2")));
        Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur2, new Coord("A1"));
        assertEquals(1, Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur1, new Coord("B2")));
        Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur2, new Coord("A1"));
        assertEquals(10, Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur1, new Coord("E2")));

        Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur2, new Coord("A1"));
        assertEquals(1, Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur1, new Coord("E5")));
        Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur2, new Coord("A1"));
        assertEquals(1, Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur1, new Coord("E6")));
        Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur2, new Coord("A1"));
        assertEquals(1, Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur1, new Coord("E7")));
        Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur2, new Coord("A1"));
        assertEquals(1, Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur1, new Coord("E8")));
        Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur2, new Coord("A1"));
        assertEquals(100, Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur1, new Coord("E9")));

        assertEquals(-100,Network.getInfo(HTTP_LOCALHOST, jeu, joueur2));
        assertEquals(100,Network.getInfo(HTTP_LOCALHOST, jeu, joueur1));
    }

    @Test
    public void play_toucheCoule_pasMonTour() throws Exception {
        Game jeu = Network.initNewGame(HTTP_LOCALHOST, joueur1, flotte1);
        assertTrue(Network.joinGame(HTTP_LOCALHOST, jeu, joueur2, flotte2));

        assertEquals(-10, Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur2, new Coord("A1")));
    }

    @Test
    public void play_toucheCoule_pasMonTour2() throws Exception {
        Game jeu = Network.initNewGame(HTTP_LOCALHOST, joueur1, flotte1);
        assertTrue(Network.joinGame(HTTP_LOCALHOST, jeu, joueur2, flotte2));

        assertEquals(1, Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur1, new Coord("J8")));
        Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur2, new Coord("A1"));
        assertEquals(1, Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur1, new Coord("I8")));
        Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur2, new Coord("A1"));
        assertEquals(10, Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur1, new Coord("H8")));

        assertEquals(-10, Network.playOneTurn(HTTP_LOCALHOST, jeu, joueur1, new Coord("D9")));
    }

}
