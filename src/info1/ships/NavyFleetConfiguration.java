package info1.ships;

public final class NavyFleetConfiguration {

    public static NavyFleet getFrenchDefault() {
        NavyFleet fleet = new NavyFleet();

        try {
            fleet.add(new AircraftCarrier("un porteavion", "E5", "E9"));
            fleet.add(new Battleship("un cuirassé", "B2", "E2"));
            fleet.add(new Submarine("un sous-marin", "G10"));
            fleet.add(new Cruiser("un croiseur", "B8", "B6")); // reverse
            fleet.add(new Cruiser("un autre croiseur", "J8", "H8")); // reverse
            fleet.add(new Destroyer("un torpilleur", "H3", "H4"));
            fleet.add(new Destroyer("un autre torpilleur", "D9", "C9")); // reverse
        } catch (BadCoordException | CoordsBadShipException e) {
            e.printStackTrace();
        }

        System.out.println(fleet.isComplete());
        System.out.println(fleet.isBelgianConfiguration());
        System.out.println(fleet.isFrenchConfiguration());

        return fleet;
    }

}
