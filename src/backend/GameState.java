package backend;

import java.util.ArrayList;
/**
 * Stores information regarding the current state of the game such as the current and end day,
 * and the number of parts that are needed and have been found.
 * @author hoo42
 * @author rvo16
 */

public class GameState {
	private static int currentDay;
	private static int endDay;
	private static int partsFound;
	private static int partsNeeded;
	private static ArrayList<Consumable> allConsumables = new ArrayList<Consumable>();
	
	/**
	 * Causes a runtime error if someone tries to create an instance of this class
	 */
	private GameState() {}
	
	/**
	 * Sets the day after which the game will end, and sets the number of parts that the player will need to find
	 * @param day the day at which the game will end
	 */
	public static void setEndDay(int day) {
		endDay = day;
		partsNeeded = (day * 2) / 3;
	}
	
	/**
	 * Returns the in-game day upon which the game will end
	 * @return the in-game day upon which the game will end
	 */
	public static int getEndDay() {
		return endDay;
	}
	
	/**
	 * Returns the in-game current day
	 * @return the in-game current day
	 */
	
	public static int getCurrentDay() {
		return currentDay;
	}
	
	/**
	 * Returns the number of parts that the player needs to find
	 * @return the number of parts that the player needs to find
	 */
	public static int getPartsNeeded() {
		return partsNeeded;
	}
	
	/**
	 * Returns the number of parts that the user has found
	 * @return the number of parts that the user has found
	 */
	public static int getPartsFound() {
		return partsFound;
	}
	
	public static ArrayList<Consumable> getAllConsumable() {
		return allConsumables;
	}

	/**
	 * Transitions an in-game day
	 */
	public static void transitionDay() {
		//TODO
	}
	
}
