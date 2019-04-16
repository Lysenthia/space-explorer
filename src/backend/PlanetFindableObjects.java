package backend;

import java.util.Random;

/**
 * Potential items found on a planet
 * @author hoo42
 * @author rvo16
 */
public enum PlanetFindableObjects {
	/**
	 * Consumable
	 */
	ITEM,
	/**
	 * Money
	 */
	MONEY,
	/**
	 * A part for the ship
	 */
	PART,
	/**
	 * Nothing
	 */
	NOTHING;
	
	/**
	 * Randomly selects an item that can be found on a planet
	 * @param reduced true if the option to find nothing is not allowed, false otherwise
	 * @return a randomly selected item
	 */
	public static PlanetFindableObjects selectRandom(boolean reduced) {
		if (reduced) {
			return new PlanetFindableObjects[] {ITEM, MONEY, PART}[new Random().nextInt(3)];
		} else {
			return PlanetFindableObjects.values()[new Random().nextInt(4)];
		}
	}
}
