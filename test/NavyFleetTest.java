import info1.ships.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Des cas de tests pour la classe NavyFleet
 *
 * @author Arnaud LANOIX
 * @version 1.0
 */

public class NavyFleetTest {

    INavyFleet maFlotte;
    IShip monPorteAvion, monCuirasse, monSousMarin, monCroiseur, monTorpilleur, autreTorpilleur, autreCroiseur;

    @BeforeEach
    public void init() throws Exception {
        maFlotte = new NavyFleet();
        monPorteAvion = new AircraftCarrier("un porteavion", "E5", "E9");
        monCuirasse = new Battleship("un cuirassé", "B2", "E2");
        monSousMarin = new Submarine("un sous-marin", "G10");
        monCroiseur = new Cruiser("un croiseur", "B8", "B6");
        monTorpilleur = new Destroyer("un torpilleur", "H3", "H4");
        autreTorpilleur = new Destroyer("un autre torpilleur", "D9", "C9");
        autreCroiseur = new Cruiser("un autre croiseur", "J8", "H8");
    }

    @Test
    public void testInit() {
        assertEquals(20, maFlotte.remainingSize());
        assertFalse(maFlotte.isComplete());
        System.out.println(maFlotte.toString());

    }

    @Test
    public void testAjout1bateau() throws Exception {
        assertEquals(0, maFlotte.add(monPorteAvion));
        assertEquals(15, maFlotte.remainingSize());
        assertFalse(maFlotte.isComplete());
        System.out.println(maFlotte.toString());

    }

    @Test
    public void testAjout2bateaux() throws Exception {
        maFlotte.add(monPorteAvion);
        assertEquals(0, maFlotte.add(monCuirasse));
        assertEquals(11, maFlotte.remainingSize());
        assertFalse(maFlotte.isComplete());
        System.out.println(maFlotte.toString());

    }

    @Test
    public void testAjout3bateaux() throws Exception {
        maFlotte.add(monPorteAvion);
        maFlotte.add(monCuirasse);
        assertEquals(0, maFlotte.add(monSousMarin));
        assertEquals(10, maFlotte.remainingSize());
        assertFalse(maFlotte.isComplete());
        System.out.println(maFlotte.toString());

    }

    @Test
    public void testAjout4bateaux() throws Exception {
        maFlotte.add(monPorteAvion);
        maFlotte.add(monCuirasse);
        maFlotte.add(monSousMarin);
        assertEquals(0, maFlotte.add(monCroiseur));
        assertEquals(7, maFlotte.remainingSize());
        assertFalse(maFlotte.isComplete());
        System.out.println(maFlotte.toString());

    }

    @Test
    public void testAjout5bateaux() throws Exception {
        maFlotte.add(monPorteAvion);
        maFlotte.add(monCuirasse);
        maFlotte.add(monSousMarin);
        maFlotte.add(monCroiseur);
        assertEquals(0, maFlotte.add(monTorpilleur));
        assertEquals(5, maFlotte.remainingSize());
        assertFalse(maFlotte.isComplete());
        System.out.println(maFlotte.toString());
    }

    @Test
    public void testAjout6bateaux() throws Exception {
        maFlotte.add(monPorteAvion);
        maFlotte.add(monCuirasse);
        maFlotte.add(monSousMarin);
        maFlotte.add(monCroiseur);
        maFlotte.add(monTorpilleur);
        assertEquals(0, maFlotte.add(autreTorpilleur));
        assertEquals(3, maFlotte.remainingSize());
        assertFalse(maFlotte.isComplete());
        System.out.println(maFlotte.toString());
    }

    @Test
    public void testAjoutFlotteComplete() throws Exception {
        maFlotte.add(monPorteAvion);
        maFlotte.add(monCuirasse);
        maFlotte.add(monSousMarin);
        maFlotte.add(monCroiseur);
        maFlotte.add(monTorpilleur);
        maFlotte.add(autreTorpilleur);
        assertEquals(0, maFlotte.add(autreCroiseur));
        assertEquals(0, maFlotte.remainingSize());
        assertTrue(maFlotte.isComplete());
        System.out.println(maFlotte.toString());
    }

    @Test
    public void testAjout_tailleDepassee() throws Exception {
        maFlotte.add(monPorteAvion);
        maFlotte.add(monCuirasse);
        maFlotte.add(monSousMarin);
        maFlotte.add(monCroiseur);
        maFlotte.add(monTorpilleur);
        maFlotte.add(autreTorpilleur);
        int sizeBeforeAdd = maFlotte.remainingSize();
        assertEquals(-2, maFlotte.add(new AircraftCarrier("tester", "J8", "F8")));
        assertEquals(sizeBeforeAdd, maFlotte.remainingSize());
        System.out.println(maFlotte.toString());

    }

    @Test
    public void testAjout_BateauDejaPresent_1() throws Exception {
        maFlotte.add(monPorteAvion);
        maFlotte.add(monCuirasse);
        maFlotte.add(monSousMarin);
        int sizeBeforeAdd = maFlotte.remainingSize();
        assertEquals(-1, maFlotte.add(monCuirasse));
        assertEquals(sizeBeforeAdd, maFlotte.remainingSize());
        System.out.println(maFlotte.toString());

    }

    @Test
    public void testAjout_BateauDejaPresent_2() throws Exception {
        maFlotte.add(monPorteAvion);
        maFlotte.add(monCuirasse);
        maFlotte.add(monSousMarin);
        int sizeBeforeAdd = maFlotte.remainingSize();
        assertEquals(-1, maFlotte.add(new AircraftCarrier("un porteavion", "E5", "E9")));
        assertEquals(sizeBeforeAdd, maFlotte.remainingSize());
        System.out.println(maFlotte.toString());

    }

    @Test
    public void testAjout_BateauSurXPositionnes_1() throws Exception {
        maFlotte.add(monPorteAvion);
        maFlotte.add(monCuirasse);
        maFlotte.add(monSousMarin);
        int sizeBeforeAdd = maFlotte.remainingSize();
        IShip tester = new Battleship("tester", "E2", "E5");
        assertEquals(-3, maFlotte.add(tester));
        assertEquals(sizeBeforeAdd, maFlotte.remainingSize());
        System.out.println(maFlotte.toString());

    }

    @Test
    public void testAjout_BateauSurXPositionnes_2() throws Exception {
        maFlotte.add(monPorteAvion);
        maFlotte.add(monCuirasse);
        maFlotte.add(monSousMarin);
        int sizeBeforeAdd = maFlotte.remainingSize();
        IShip tester = new Cruiser("tester", "E6", "E8");
        assertEquals(-3, maFlotte.add(tester));
        assertEquals(sizeBeforeAdd, maFlotte.remainingSize());
        System.out.println(maFlotte.toString());

    }

    @Test
    public void testAjout_BateauSurXPositionnes_3() throws Exception {
        maFlotte.add(monPorteAvion);
        maFlotte.add(monCuirasse);
        maFlotte.add(monSousMarin);
        int sizeBeforeAdd = maFlotte.remainingSize();
        IShip tester = new Cruiser("tester", "H10", "F10");
        assertEquals(-3, maFlotte.add(tester));
        assertEquals(sizeBeforeAdd, maFlotte.remainingSize());
        System.out.println(maFlotte.toString());
    }

    @Test
    public void testJsonNavyFleet() throws Exception {
        NavyFleet f = new NavyFleet();
        f.add(new AircraftCarrier("monPorteAvion", "E5", "E9"));
        f.add(new Battleship("monCuirasse", "B2", "E2"));
        f.add(new Submarine("monSousMarin", "G10"));
        f.add(new Cruiser("monCroiseur", "B8", "B6"));
        f.add(new Destroyer("monTorpilleur", "H3", "H4"));
        f.add(new Destroyer("autreTorpilleur", "D9", "C9"));
        f.add(new Cruiser("autreCroiseur", "J8", "H8"));

        System.out.println(f.asJSON().toString());

        assertEquals("[" +
                        "{\"bateau\":\"monSousMarin\"," +
                        "\"coordonnees\":[{\"x\":6,\"y\":9}]}," +
                        "{\"bateau\":\"monTorpilleur\"," +
                        "\"coordonnees\":[{\"x\":7,\"y\":2},{\"x\":7,\"y\":3}]}," +
                        "{\"bateau\":\"autreTorpilleur\"," +
                        "\"coordonnees\":[{\"x\":3,\"y\":8},{\"x\":2,\"y\":8}]}," +
                        "{\"bateau\":\"monCroiseur\"," +
                        "\"coordonnees\":[{\"x\":1,\"y\":7},{\"x\":1,\"y\":6},{\"x\":1,\"y\":5}]}," +
                        "{\"bateau\":\"autreCroiseur\"," +
                        "\"coordonnees\":[{\"x\":9,\"y\":7},{\"x\":8,\"y\":7},{\"x\":7,\"y\":7}]}," +
                        "{\"bateau\":\"monCuirasse\"," +
                        "\"coordonnees\":[{\"x\":1,\"y\":1},{\"x\":2,\"y\":1},{\"x\":3,\"y\":1},{\"x\":4,\"y\":1}]}," +
                        "{\"bateau\":\"monPorteAvion\"," +
                        "\"coordonnees\":[{\"x\":4,\"y\":4},{\"x\":4,\"y\":5},{\"x\":4,\"y\":6},{\"x\":4,\"y\":7},{\"x\":4,\"y\":8}]}" +
                        "]",
                f.asJSON().toString());
    }

    @Test
    public void testListeTrieeBateaux() {
        maFlotte.add(monPorteAvion);
        maFlotte.add(monCuirasse);
        maFlotte.add(monSousMarin);
        maFlotte.add(monCroiseur);
        maFlotte.add(monTorpilleur);
        maFlotte.add(autreCroiseur);
        maFlotte.add(autreTorpilleur);
        List<IShip> expected = Arrays.asList(monSousMarin, monTorpilleur, autreTorpilleur, monCroiseur, autreCroiseur, monCuirasse, monPorteAvion);
        System.out.println(maFlotte);
        assertIterableEquals(expected, maFlotte.getShips());
    }

    @Test void testCategoriePorteAvion() {
        maFlotte.add(monPorteAvion);
        maFlotte.add(monCuirasse);
        maFlotte.add(monSousMarin);
        maFlotte.add(monCroiseur);
        maFlotte.add(monTorpilleur);
        maFlotte.add(autreCroiseur);
        maFlotte.add(autreTorpilleur);
        Set<IShip> set = maFlotte.getShips(ShipCategory.AIRCRAFT_CARRIER);
        assertEquals(1, set.size());
        assertTrue(set.contains(monPorteAvion));
    }

    @Test void testCategorieCuirasse() {
        maFlotte.add(monPorteAvion);
        maFlotte.add(monCuirasse);
        maFlotte.add(monSousMarin);
        maFlotte.add(monCroiseur);
        maFlotte.add(monTorpilleur);
        maFlotte.add(autreCroiseur);
        maFlotte.add(autreTorpilleur);
        Set<IShip> set = maFlotte.getShips(ShipCategory.BATTLESHIP);
        assertEquals(1, set.size());
        assertTrue(set.contains(monCuirasse));
    }

    @Test void testCategorieCroiseur() {
        maFlotte.add(monPorteAvion);
        maFlotte.add(monCuirasse);
        maFlotte.add(monSousMarin);
        maFlotte.add(monCroiseur);
        maFlotte.add(monTorpilleur);
        maFlotte.add(autreCroiseur);
        maFlotte.add(autreTorpilleur);
        Set<IShip> set = maFlotte.getShips(ShipCategory.CRUISER);
        assertEquals(2, set.size());
        assertTrue(set.contains(monCroiseur));
        assertTrue(set.contains(autreCroiseur));
    }

    @Test void testCategorieTorpilleur() {
        maFlotte.add(monPorteAvion);
        maFlotte.add(monCuirasse);
        maFlotte.add(monSousMarin);
        maFlotte.add(monCroiseur);
        maFlotte.add(monTorpilleur);
        maFlotte.add(autreCroiseur);
        maFlotte.add(autreTorpilleur);
        Set<IShip> set = maFlotte.getShips(ShipCategory.DESTROYER);
        assertEquals(2, set.size());
        assertTrue(set.contains(monTorpilleur));
        assertTrue(set.contains(autreTorpilleur));
    }

    @Test void testCategorieSousmarin() {
        maFlotte.add(monPorteAvion);
        maFlotte.add(monCuirasse);
        maFlotte.add(monSousMarin);
        maFlotte.add(monCroiseur);
        maFlotte.add(monTorpilleur);
        maFlotte.add(autreCroiseur);
        maFlotte.add(autreTorpilleur);
        Set<IShip> set = maFlotte.getShips(ShipCategory.SUBMARINE);
        assertEquals(1, set.size());
        assertTrue(set.contains(monSousMarin));
    }

    /* flotte belge =
         1 cuirassÃ©, 2 croiseurs, 3 torpilleurs, 4 sous-marins
    */


    // TODO tests isBelgianConfiguration()

    @Test void testEstBelge_OK() throws Exception {
        assertEquals(0,maFlotte.add(monCuirasse));
        assertEquals(0,maFlotte.add(monCroiseur));
        assertEquals(0,maFlotte.add(autreCroiseur));
        assertEquals(0,maFlotte.add(monTorpilleur));
        assertEquals(0,maFlotte.add(autreTorpilleur));
        assertEquals(0,maFlotte.add(new Destroyer("encoreUnTorpilleur", "A5", "B5")));
        assertEquals(0,maFlotte.add(monSousMarin));
        assertEquals(0,maFlotte.add(new Submarine("autre Sous Marin", "J2")));
        assertEquals(0,maFlotte.add(new Submarine("encore autre Sous Marin", "J4")));
        assertEquals(0,maFlotte.add(new Submarine("et un dernier Sous Marin", "J6")));
        assertTrue(maFlotte.isComplete());
        assertTrue(maFlotte.isBelgianConfiguration());
    }

    @Test void testEstBelge_OK_2() throws Exception {
        assertEquals(0,maFlotte.add(new Destroyer("encoreUnTorpilleur", "A5", "B5")));
        assertEquals(0,maFlotte.add(monCuirasse));
        assertEquals(0,maFlotte.add(new Submarine("et un dernier Sous Marin", "J6")));
        assertEquals(0,maFlotte.add(autreCroiseur));
        assertEquals(0,maFlotte.add(monTorpilleur));
        assertEquals(0,maFlotte.add(monCroiseur));
        assertEquals(0,maFlotte.add(new Submarine("encore autre Sous Marin", "J4")));
        assertEquals(0,maFlotte.add(monSousMarin));
        assertEquals(0,maFlotte.add(autreTorpilleur));
        assertEquals(0,maFlotte.add(new Submarine("autre Sous Marin", "J2")));
        assertTrue(maFlotte.isBelgianConfiguration());
    }

    @Test void testEstBelge_KO_1() throws Exception {
        assertEquals(0,maFlotte.add(new Destroyer("encoreUnTorpilleur", "A5", "B5")));
        assertEquals(0,maFlotte.add(monCuirasse));
        assertEquals(0,maFlotte.add(new Submarine("et un dernier Sous Marin", "J6")));
        assertEquals(0,maFlotte.add(autreCroiseur));
        assertEquals(0,maFlotte.add(monTorpilleur));
        assertEquals(0,maFlotte.add(monSousMarin));
        assertEquals(0,maFlotte.add(autreTorpilleur));
        assertEquals(0,maFlotte.add(new Submarine("autre Sous Marin", "J2")));
        assertFalse(maFlotte.isBelgianConfiguration());
    }

    @Test void testEstBelge_KO_2() throws Exception {
        assertEquals(0,maFlotte.add(monCuirasse));
        assertEquals(0,maFlotte.add(new Submarine("et un dernier Sous Marin", "J6")));
        assertEquals(0,maFlotte.add(autreCroiseur));
        assertEquals(0,maFlotte.add(monTorpilleur));
        assertEquals(0,maFlotte.add(monCroiseur));
        assertEquals(0,maFlotte.add(new Submarine("encore autre Sous Marin", "J4")));
        assertEquals(0,maFlotte.add(monSousMarin));
        assertEquals(0,maFlotte.add(autreTorpilleur));
        assertEquals(0,maFlotte.add(new Submarine("autre Sous Marin", "J2")));
        assertFalse(maFlotte.isBelgianConfiguration());
    }

    @Test void testEstBelge_KO_francaise() throws Exception {
        maFlotte.add(monPorteAvion);
        maFlotte.add(monCuirasse);
        maFlotte.add(monCroiseur);
        maFlotte.add(autreCroiseur);
        maFlotte.add(monTorpilleur);
        maFlotte.add(autreTorpilleur);
        maFlotte.add(monSousMarin);
        assertFalse(maFlotte.isBelgianConfiguration());
    }


    /*
      flotte française =
      1 porte-avion, 1 cuirassÃ©, 2 croiseurs, 2 torpilleurs, 1 sous-marin

        maFlotte.add(monPorteAvion);
        maFlotte.add(monCuirasse);
        maFlotte.add(monCroiseur);
        maFlotte.add(autreCroiseur);
        maFlotte.add(monTorpilleur);
        maFlotte.add(autreTorpilleur);
        maFlotte.add(monSousMarin);

     */

    // TODO tests isFrenchConfiguration()

    @Test
    public void testEstFrancaise_OK() {
        maFlotte.add(monPorteAvion);
        maFlotte.add(monCuirasse);
        maFlotte.add(monCroiseur);
        maFlotte.add(autreCroiseur);
        maFlotte.add(monTorpilleur);
        maFlotte.add(autreTorpilleur);
        maFlotte.add(monSousMarin);

        assertTrue(maFlotte.isFrenchConfiguration());
    }


}
