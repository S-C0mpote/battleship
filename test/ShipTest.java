import info1.ships.*;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Des cas de tests pour tester les différents bateaux
 *
 * @author Arnaud LANOIX
 * @version 1.0
 */


class ShipTest {

    @Test
    public void testCreationPorteAvion1() throws Exception {
        new AircraftCarrier("qsdfg", "E5","E9");
    }

    @Test
    public void testCreationPorteAvion2() throws Exception {
        new AircraftCarrier("qsdfg","E9","E5");
    }

    @Test
    public void testCreationPorteAvion3() throws Exception {
        new AircraftCarrier("qsdfg","J6","J10");
    }

    @Test
    public void testCreationPorteAvion4() throws Exception {
        new AircraftCarrier("qsdfg","A1","A5");
    }

    @Test
    public void testCreationPorteAvion5() throws Exception {
        new AircraftCarrier("qsdfg","A1","E1");    }

    @Test
    public void testCreationPorteAvion6() throws Exception {
        new AircraftCarrier("qsdfg","G5","C5");
    }

    @Test void testSizePorteVavion() throws Exception {
        IShip ship =  new AircraftCarrier("qsdfg","A1","E1");
        assertEquals(ShipCategory.AIRCRAFT_CARRIER.getSize(), ship.getSize());
    }

    @Test void testSizeCuirasse() throws Exception {
        Ship ship =  new Battleship("qsdfg","A1","D1");
        assertEquals(ShipCategory.BATTLESHIP.getSize(), ship.getSize());

    }

    @Test void testSizeCroiseur() throws Exception {
        IShip ship =  new Cruiser("qsdfg","A1","C1");
        assertEquals(ShipCategory.CRUISER.getSize(), ship.getSize());
    }

    @Test void testSizeTorpilleur() throws Exception {
        IShip ship =  new Destroyer("qsdfg","A1","B1");
        assertEquals(ShipCategory.DESTROYER.getSize(), ship.getSize());
    }

    @Test void testSizeSousMarin() throws Exception {
        IShip ship =  new Submarine("qsdfg","A1");
        assertEquals(ShipCategory.SUBMARINE.getSize(), ship.getSize());
    }

    @Test
    public void testMauvaiseCreationPorteAvionBad1() throws Exception {
        assertThrows(CoordsBadShipException.class,
                () ->new AircraftCarrier("qsdfg","E5","E8"));
    }

    @Test
    public void testMauvaiseCreationPorteAvionBad2() throws Exception {
        assertThrows(CoordsBadShipException.class,
                () ->new AircraftCarrier("qsdfg","E8","E5"));
    }

    @Test
    public void testMauvaiseCreationPorteAvionBad3() throws Exception {
        assertThrows(CoordsBadShipException.class,
                () ->new AircraftCarrier("qsdfg","E4","E9"));
    }

    @Test
    public void testMauvaiseCreationPorteAvionBad4() throws Exception {
        assertThrows(CoordsBadShipException.class,
                () ->new AircraftCarrier("qsdfg","G5","D5"));
    }

    @Test
    public void testCreationSousMarin() throws Exception {
        new Submarine("XAZERTY","E5");
    }

    @Test
    public void testMauvaiseCreationSousMarin() throws Exception {
        assertThrows(BadCoordException.class,
                () ->new Submarine("qsdfg", "E11"));
    }

    @Test
    public void testEgalite2PorteAvions() throws Exception {
        IShip pa = new AircraftCarrier("monPorteAvion", "E5","E9");
        assertTrue(pa.equals(pa));
    }

    @Test
    public void testEgalite2PorteAvions_2() throws Exception {
        IShip pa = new AircraftCarrier("monPorteAvion", "E5","E9");
        assertTrue(pa.equals(new AircraftCarrier("monPorteAvion","E5","E9")));
    }

    @Test
    public void testNonEgalite2PorteAvions_positionsDiff() throws Exception {
        IShip pa = new AircraftCarrier("monPorteAvion", "E5","E9");
        assertFalse(pa.equals(new AircraftCarrier("monPorteAvion","J2","F2")));
    }

    @Test
    public void testEgalite2sousMarins() throws Exception {
        IShip sb = new Submarine("monSousMarin", "G10");
        assertTrue(sb.equals(sb));
    }

    @Test
    public void testEgalite2sousMarins_2() throws Exception {
        IShip sb = new Submarine("monSousMarin", "G10");
        assertTrue(sb.equals(new Submarine("monSousMarin", "G10")));
    }

    @Test
    public void testPasEgalite2sousMarins() throws Exception {
        IShip sb = new Submarine("monSousMarin", "G10");
        assertFalse(sb.equals(new Submarine("AZE", "G10")));
    }

    @Test
    public void testPasEgalite2sousMarins_2() throws Exception {
        IShip sb = new Submarine("monSousMarin", "G10");
        assertFalse(sb.equals(new Submarine("monSousMarin", "A10")));
    }

    @Test
    public void testPasEgalite2sousMarins_3() throws Exception {
        IShip sb = new Submarine("monSousMarin", "G10");
        assertFalse(sb.equals(new Submarine("monSousMarin", "G8")));
    }

    @Test
    public void testPasEgalite1sousMarin1Croiseur_1() throws Exception {
        IShip sb = new Submarine("monSousMarin", "G10");
        assertFalse(sb.equals(new Cruiser("monSousMarin", "G10", "E10")));
    }

    @Test
    public void testPasEgalite1sousMarin1Croiseur_2() throws Exception {
        IShip sb = new Submarine("monSousMarin", "G10");
        assertFalse(sb.equals(new Cruiser("monSousMarin", "E10", "G10")));
    }

    @Test
    public void testPasEgalite1Croiseur1SouMarin_1() throws Exception {
        IShip sb = new Cruiser("monSousMarin", "G10", "E10");
        assertFalse(sb.equals(new Submarine("monSousMarin", "G10")));
    }

    @Test
    public void testPasEgalite1Croiseur1sousMarin_2() throws Exception {
        IShip sb = new Cruiser("monSousMarin", "E10", "G10");
        assertFalse(sb.equals(new Submarine("monSousMarin", "G10")));
    }

    @Test
    public void testComparaison_sousmarinCroiseur()throws Exception {
        IShip sb = new Submarine("monSousMarin", "G10");
        Ship cr = new Cruiser("monSousMarin", "G10", "E10");
        assertTrue(sb.compareTo(cr) < 0);
    }

    @Test
    public void testComparaison_CroiseurSousmarin()throws Exception {
        Ship sb = new Submarine("monSousMarin", "G10");
        IShip cr = new Cruiser("monSousMarin", "G10", "E10");
        assertTrue(cr.compareTo(sb) > 0);
    }

    @Test
    public void testComparaison_CroiseurIdentique()throws Exception {
        IShip cr1 = new Cruiser("monSousMarin", "G10", "E10");
        Ship cr2 = new Cruiser("monSousMarin", "G10", "E10");
        assertTrue(cr1.compareTo(cr2) == 0);
    }

    @Test
    public void testComparaison_CroiseurSame()throws Exception {
        Ship cr1 = new Cruiser("monSousMarin", "G10", "E10");
        assertTrue(cr1.compareTo(cr1) == 0);
    }

    @Test
    public void testComparaison_2CroiseursNomDiff()throws Exception {
        IShip cr1 = new Cruiser("aaa", "G10", "E10");
        Ship cr2 = new Cruiser("zzz", "G10", "E10");
        assertTrue(cr1.compareTo(cr2) < 0);
    }

    @Test
    public void testComparaison_2CroiseursNomIdentique()throws Exception {
        IShip cr1 = new Cruiser("abc", "C3", "E3");
        Ship cr2 = new Cruiser("abc", "G10", "E10");
        assertTrue(cr1.compareTo(cr2) < 0);
    }

    @Test
    public void testListCoord_1() throws Exception {
        IShip cr1 = new Cruiser("abc", "C3", "E3");
        List<ICoord> expected = Arrays.asList(
                new Coord("C3"), new Coord("D3"), new Coord("E3")
        );
        assertIterableEquals(expected,cr1.getCoords());
    }

    @Test
    public void testListCoord_2() throws Exception {
        IShip cr1 = new Cruiser("abc", "E3", "C3");
        Object[] expected = {new Coord("E3"), new Coord("D3"), new Coord("C3")};
        assertArrayEquals(expected,cr1.getCoords().toArray());
    }

    @Test
    public void testListCoord_3() throws Exception {
        IShip ship = new AircraftCarrier("abc", "C5", "G5");
        List<ICoord> expected = Arrays.asList(
                new Coord("C5"), new Coord("D5"), new Coord("E5"), new Coord("F5"), new Coord("G5")
        );
        assertIterableEquals(expected, ship.getCoords());
    }

    @Test
    public void testListCoord_4() throws Exception {
        IShip ship = new AircraftCarrier("abc", "C3", "C7");
        List<ICoord> expected = Arrays.asList(
                new Coord("C3"), new Coord("C4"), new Coord("C5"), new Coord("C6"), new Coord("C7")
        );
        assertIterableEquals(expected, ship.getCoords());
    }

    @Test
    public void testListCoord_5() throws Exception {
        IShip ship = new AircraftCarrier("abc", "C7", "C3");
        List<ICoord> expected = Arrays.asList(
                new Coord("C7"), new Coord("C6"), new Coord("C5"), new Coord("C4"), new Coord("C3")
        );
        assertIterableEquals(expected, ship.getCoords());
    }

    @Test
    public void testListCoord_6() throws Exception {
        IShip ship = new Submarine("abc", "C3");
        List<ICoord> expected = Arrays.asList(
                new Coord("C3")
        );
        assertIterableEquals(expected, ship.getCoords());
    }

    @Test
    public void testJSonShip_1() throws Exception {
        IShip ship = new Submarine("monSousMarin", "G10");
        JSONObject jObj = ship.asJSON();
        System.out.println(jObj.toString());
        assertEquals("{\"bateau\":\"monSousMarin\"," +
                        "\"coordonnees\":[{\"x\":6,\"y\":9}]}",
                jObj.toString());

    }

    @Test
    public void testJSonShip_2() throws Exception {
        IShip ship = new Cruiser("cruiser", "E10", "G10");
        JSONObject jObj = ship.asJSON();
        System.out.println(jObj.toString());
        assertEquals("{\"bateau\":\"cruiser\"," +
                        "\"coordonnees\":[{\"x\":4,\"y\":9}," +
                        "{\"x\":5,\"y\":9}," +
                        "{\"x\":6,\"y\":9}]}",
                jObj.toString());
    }

    @Test
    public void testJSonShip_3() throws Exception {
        IShip ship = new AircraftCarrier("abc", "C3", "C7");
        JSONObject jObj = ship.asJSON();
        System.out.println(jObj.toString());
        assertEquals("{\"bateau\":\"abc\"," +
                        "\"coordonnees\":[{\"x\":2,\"y\":2}," +
                        "{\"x\":2,\"y\":3}," +
                        "{\"x\":2,\"y\":4}," +
                        "{\"x\":2,\"y\":5}," +
                        "{\"x\":2,\"y\":6}]}",
                jObj.toString());
    }

    @Test
    public void testJSonShip_4() throws Exception {
        IShip ship = new AircraftCarrier("abc", "C7", "C3");
        JSONObject jObj = ship.asJSON();
        System.out.println(jObj.toString());
        assertEquals("{\"bateau\":\"abc\"," +
                        "\"coordonnees\":[" +
                        "{\"x\":2,\"y\":6}," +
                        "{\"x\":2,\"y\":5}," +
                        "{\"x\":2,\"y\":4}," +
                        "{\"x\":2,\"y\":3}," +
                        "{\"x\":2,\"y\":2}" +
                        "]}",
                jObj.toString());
    }


}