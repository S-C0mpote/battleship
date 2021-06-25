package info1.ships;

import info1.game.resources.Images;

/**
 * Classe définissant une navire de catégorie "Porte-Avion" (taille 5)
 */

public class AircraftCarrier extends Ship {

    public AircraftCarrier(String name, String xyFront, String xyBack)
            throws BadCoordException, CoordsBadShipException {
        super(name, xyFront, xyBack);

        setImage(Images.SHIP_AIRCRAFT_CARRIER);
    }

    @Override
    public ShipCategory getCategory() {
        return ShipCategory.AIRCRAFT_CARRIER;
    }

}
