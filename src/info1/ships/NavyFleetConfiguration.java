package info1.ships;

public final class NavyFleetConfiguration {

    public static NavyFleet getFrenchDefault() {
        NavyFleet fleet = new NavyFleet();

        try {
            fleet.add(new AircraftCarrier("1", "A1", "A5"));
            fleet.add(new Battleship("2", "B1", "B4"));
            fleet.add(new Cruiser("3", "C1", "C3"));
            fleet.add(new Cruiser("4", "D1", "D3"));
            fleet.add(new Destroyer("5", "E1", "E2"));
            fleet.add(new Destroyer("6", "F1", "F2"));
            fleet.add(new Submarine("7", "G1"));
        } catch (BadCoordException | CoordsBadShipException e) {
            e.printStackTrace();
        }

        return fleet;
    }

    public static NavyFleet getBelgianDefault() {
        NavyFleet fleet = new NavyFleet();

        try {
            fleet.add(new Battleship("1", "A1", "A4"));
            fleet.add(new Cruiser("2", "B1", "B3"));
            fleet.add(new Cruiser("3", "C1", "C3"));
            fleet.add(new Destroyer("4", "D1", "D2"));
            fleet.add(new Destroyer("5", "E1", "E2"));
            fleet.add(new Destroyer("6", "F1", "F2"));
            fleet.add(new Submarine("7", "G1"));
            fleet.add(new Submarine("8", "H1"));
            fleet.add(new Submarine("9", "I1"));
            fleet.add(new Submarine("10", "J1"));
        } catch (BadCoordException | CoordsBadShipException e) {
            e.printStackTrace();
        }

        return fleet;
    }

}
