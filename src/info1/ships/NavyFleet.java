package info1.ships;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Classe définissant une flotte de navires
 */

public class NavyFleet implements INavyFleet {

    private final List<IShip> ships = new ArrayList<>();

    private int size = 0;
    private final int MAX_SIZE = 20;

    /**
     * NB : LA SIGNATURE DU CONSTRUCTEUR DOIT ETRE RESPECTEE
     *
     * Construit une nouvelle flotte
     */
    public NavyFleet() { }

    @Override
    public int remainingSize() {
        return MAX_SIZE - size;
    }

    @Override
    public boolean isComplete() {
        return MAX_SIZE == size;
    }


    @Override
    public int add(IShip iShip) {
        int size = iShip.getSize();

        if(ships.contains(iShip)) return -1;
        if(remainingSize() < size) return -2;

        for(IShip ship : ships)
            for(ICoord coord : ship.getCoords())
                if (iShip.getCoords().contains(coord)) return -3;

        this.size += size;

        ships.add(iShip);
        ships.sort(Comparator.comparingInt(a -> a.getCategory().getSize()));

        return 0;
    }

    @Override
    public List<IShip> getShips() {
        return ships;
    }

    @Override
    public Set<IShip> getShips(ShipCategory shipCategory) {
        return ships.stream()
                .filter(ship -> ship.getCategory().equals(shipCategory))
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isBelgianConfiguration() {
        return getShips(ShipCategory.AIRCRAFT_CARRIER).size() == 0
                && getShips(ShipCategory.BATTLESHIP).size() == 1
                && getShips(ShipCategory.CRUISER).size() == 2
                && getShips(ShipCategory.DESTROYER).size() == 3
                && getShips(ShipCategory.SUBMARINE).size() == 4;
    }

    @Override
    public boolean isFrenchConfiguration() {
        return getShips(ShipCategory.AIRCRAFT_CARRIER).size() == 1
            && getShips(ShipCategory.BATTLESHIP).size() == 1
            && getShips(ShipCategory.CRUISER).size() == 2
            && getShips(ShipCategory.DESTROYER).size() == 2
            && getShips(ShipCategory.SUBMARINE).size() == 1;
    }


    @Override
    public String toString() {
        return Arrays.toString(ships.toArray());
    }
}
