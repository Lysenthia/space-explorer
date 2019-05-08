package backend;

import java.util.ArrayList;
/**
 * Stores information regarding the current state of the game such as the current and end day,
 * and the number of parts that are needed and have been found.
 * @author hoo42
 * @author rvo16
 */

public class GameState {
	/**
	 * The current day the game is at
	 */
	private static int currentDay = 1;
	/**
	 * The day at which the game will end
	 */
	private static int endDay;
	/**
	 * The number of parts that the player has currently found
	 */
	private static int partsFound = 0;
	/**
	 * The number of parts that the player must find
	 */
	private static int partsNeeded;
	/**
	 * An array list of all possible consumables in the game, even if they are not stocked at an outpost
	 */
	private static ArrayList<Consumable> allConsumables = new ArrayList<Consumable>();
	/**
	 * The ending that the player has reached
	 */
	private static PossibleEndings ending;
	
	/**
	 * Causes an error if someone tries to create an instance of this class
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
	
	/**
	 * Returns a list of all the consumables in the game
	 * @return a list of all the consumables in the game
	 */
	public static ArrayList<Consumable> getAllConsumable() {
		return allConsumables;
	}
	
	/**
	 * Adds a consumable item to the list of all consumables in the game
	 * @param item the consumable to add to the list
	 */
	public static void addConsumable(Consumable item) {
		if (!allConsumables.contains(item)) {
			allConsumables.add(item);
		}
	}

	/**
	 * Transitions an in-game day
	 */
	public static ArrayList<CrewMember> transitionDay() {
		boolean dead;
		ArrayList<CrewMember> deadCrew = new ArrayList<CrewMember>();
		currentDay += 1;
		//Executes two loops over crew members due to different purposes
		for (CrewMember member : Ship.getShipCrew()) {
			switch (member.getMemberClass()) {
			case COOK:
				for (CrewMember crew : Ship.getShipCrew()) {
					crew.decreaseHunger(10);
				}
				break;
			case MEDIC:
				for (CrewMember crew : Ship.getShipCrew()) {
					crew.increaseHealth(10);
				}
				break;
			default:
				break;
			}
		}
		for (CrewMember member : Ship.getShipCrew()) {
			dead = member.transitionDay();
			if (dead) {
				deadCrew.add(member);
			}
		}
		deadCrew.forEach(member -> Ship.removeCrewMember(member));
		return deadCrew;
	}
	
	/**
	 * Increases the number of parts found by 1
	 */
	public static void findPart() {
		partsFound += 1;
	}
	
	/**
	 * Resets GameState to how it would be at the start of a new game
	 * For testing purposes only
	 */
	public static void clear() {
		allConsumables.clear();
		currentDay = 1;
		partsFound = 0;
	}
	
	/**
	 * Replaces the ArrayList of consumables completely with a new array
	 * @param consumables the new ArrayList to replace allConsumables with
	 */
	public static void setAllConsumables(ArrayList<Consumable> consumables) {
		allConsumables = consumables;
	}

	/**
	 * Returns the ending that the player reached
	 * @return the ending that the player reached
	 */
	public static PossibleEndings getEnding() {
		return ending;
	}

	/**
	 * Sets the ending that the player has reached
	 * @param endingToSet the ending that the player has reached
	 */
	public static void setEnding(PossibleEndings endingToSet) {
		ending = endingToSet;
	}
	
}
