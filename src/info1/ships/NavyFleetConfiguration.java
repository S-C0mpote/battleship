package info1.ships;

public final class NavyFleetConfiguration {

    public static NavyFleet getFrenchDefault() {
        NavyFleet fleet = new NavyFleet();

        try {
            fleet.add(new AircraftCarrier("un porteavion", "A1", "A5"));
            fleet.add(new Battleship("un cuirassé", "B1", "B4"));
            fleet.add(new Cruiser("un croiseur", "C1", "C3"));
            fleet.add(new Cruiser("un autre croiseur", "D1", "D3"));
            fleet.add(new Destroyer("un torpilleur", "E1", "E2"));
            fleet.add(new Destroyer("un autre torpilleur", "F1", "F2"));
            fleet.add(new Submarine("un sous-marin", "G1"));
        } catch (BadCoordException | CoordsBadShipException e) {
            e.printStackTrace();
        }

        return fleet;
    }

}
