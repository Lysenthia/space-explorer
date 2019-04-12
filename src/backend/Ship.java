package backend;
import java.util.ArrayList;

public final class Ship {
	
	private static String name = "White Whale";
	private static ArrayList<CrewMember> shipCrew = new ArrayList<CrewMember>();
	private static ArrayList<Consumable> inventory;
	private static int shipShields = 100;
	private static int money = 0;
	
	private Ship() {} //Intended to throw an error if someone tries to make an instance of this class
	/**
	 * Gets the value of the ship's shields at the current time
	 * @return the value of the ship's shields
	 */
	public static int getShields() {
		return shipShields;
	}
	/**
	 * Gets the amount of money the player has
	 * @return the amount of money the player has
	 */
	public static int getMoney() {
		return money;
	}
	/**
	 * Adds an amount to the current amount of money the player has
	 * @param amount the amount of money to be added to the player's money
	 */
	public static void addMoney(int amount) {
		money += amount;
	}
	/**
	 * Decreases an amount to the current amount of money the player has
	 * @param amount the amount of money to be subtracted from the player's money
	 */
	public static void decreaseMoney(int amount) {
		money -= amount;
	}
	/**
	 * Displays a list of the current crew members on the ship
	 * @return the list of crew members of the ship
	 */
	public static ArrayList<CrewMember> getShipCrew() {
		return shipCrew;
	}
	/**
	 * Adds a new crew member to the ship crew
	 * @param member the member to be added to the ship crew
	 * @return true if crew member is added to to the ship crew
	 * 			false otherwise
	 */
	public static boolean addCrewMember(CrewMember member) {
		if (shipCrew.contains(member)) {
			return false;
		} else {
			shipCrew.add(member);
			return true;
		}
	}
	/**
	 * Removes a crew member from the ship crew
	 * @param member the member to be removed from the ship crew
	 * @return true if the crew member is removed from the ships crew
	 * 			false otherwise
	 */
	public static boolean removeCrewMember(CrewMember member) {
		if (shipCrew.contains(member)) {
			shipCrew.remove(member);
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Gets the name of the ship
	 * @return the name of the ship
	 */
	public static String getName() {
		return name;
	}
	/**
	 * Sets the name of the ship
	 * @param newName the new name of the ship
	 */
	public static void setName(String newName) {
		name = newName;
	}
	/**
	 * A list containing the consumables in the inventory
	 * @return
	 */
	public static ArrayList<Consumable> getInventory() {
		return inventory;
	}
	/**
	 * Method to check if the inventory contains an item
	 * @param item the item to be checked against
	 * @return true if the ship's inventory contains the item
	 * 			false otherwise
	 */
	public static boolean inInventory(Consumable item) {
		return inventory.contains(item);
	}
	/**
	 * Method to add an item to the ship's inventory
	 * @param item the item to be added to the ships inventory
	 */
	public static void addToInventory(Consumable item) {
		if (!(inInventory(item)) && item.getHeld() > 0) {
			inventory.add(item);
		}
	}
	/**
	 * Method to remove an item from the ship's inventory
	 * @param item the item to be removed from the ship's inventory
	 */
	public static void removeFromInventory(Consumable item) {
		if (inInventory(item) && item.getHeld() == 0) {
			inventory.remove(item);
		}
	}
	/**
	 * Method to repair the shields on the ship
	 * @param amount the value to repair the ship's shields by
	 */
	public static void repairShields(int amount) {
		shipShields += amount;
		if (shipShields > 100) {
			shipShields = 100;
		}	
	}
	/**
	 * Method that damages the ship's shields
	 * @param amount the amount of damage to the ship's shields
	 * @return true if the shield is damaged
	 * 			false otherwise
	 */
	public static boolean damageShields(int amount) {
		shipShields -= amount;
		if (shipShields > 0) {
			return false;
		} else {
			return true;
		}
	}
	/**
	 * Method to set the two crew member pilot's of the ship
	 * @param pilotOne the first pilot of the ship
	 * @param pilotTwo the second pilot of the ship
	 */
	public static void pilot(CrewMember pilotOne, CrewMember pilotTwo) {
		//TODO
	}
	
}
