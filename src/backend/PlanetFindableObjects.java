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
	 * @param isScout true if the option to find nothing is not allowed; false otherwise
	 * @param partFound true if a part has already been found on the planet; false otherwise
	 * @return a randomly selected item
	 */
	public static PlanetFindableObjects selectRandom(boolean isScout, boolean partFound) {
		if (partFound) {
			if (isScout) {
				return new PlanetFindableObjects[] {ITEM, MONEY}[new Random().nextInt(2)];
			} else {
				return new PlanetFindableObjects[] {ITEM, MONEY, NOTHING}[new Random().nextInt(3)];
			}
		} else {
			if (isScout) {
				return new PlanetFindableObjects[] {ITEM, MONEY, PART}[new Random().nextInt(3)];
			} else {
				return PlanetFindableObjects.values()[new Random().nextInt(4)];
			}
		}
	}
}
