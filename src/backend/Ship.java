package backend;
import java.util.ArrayList;

public final class Ship {
	
	private static String name = "White Whale";
	private static ArrayList<CrewMember> shipCrew = new ArrayList<CrewMember>();
	private static ArrayList<Consumable> inventory;
	private static int shipShields = 100;
	private static int money = 0;
	
	private Ship() {} //Intended to throw an error if someone tries to make an instance of this class
	
	public static int getShields() {
		return shipShields;
	}
	
	public static int getMoney() {
		return money;
	}
	
	public static void addMoney(int amount) {
		money += amount;
	}
	
	public static void decreaseMoney(int amount) {
		money -= amount;
	}
	
	public static ArrayList<CrewMember> getShipCrew() {
		return shipCrew;
	}
	
	public static boolean addCrewMember(CrewMember member) {
		if (shipCrew.contains(member)) {
			return false;
		} else {
			shipCrew.add(member);
			return true;
		}
	}
	
	public static boolean removeCrewMember(CrewMember member) {
		if (shipCrew.contains(member)) {
			shipCrew.remove(member);
			return true;
		} else {
			return false;
		}
	}
	
	public static String getName() {
		return name;
	}
	
	public static void setName(String newName) {
		name = newName;
	}
	
	public static ArrayList<Consumable> getInventory() {
		return inventory;
	}
	
	public static boolean inInventory(Consumable item) {
		return inventory.contains(item);
	}
	
	public static void addToInventory(Consumable item) {
		if (!(inInventory(item)) && item.getHeld() > 0) {
			inventory.add(item);
		}
	}
	
	public static void removeFromInventory(Consumable item) {
		if (inInventory(item) && item.getHeld() == 0) {
			inventory.remove(item);
		}
	}
	
	//TODO
	public static void pilot(CrewMember pilotOne, CrewMember pilotTwo) {}
	public static void damageShields() {}
	
}
