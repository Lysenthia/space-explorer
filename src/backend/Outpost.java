package backend;
import java.util.ArrayList;

/**
 * Stores information regarding an outpost at which the player can purchase items.
 * In most games there will be only one outpost, but in an altered game with multiple systems there could be several
 * @author hoo42
 * @author rvo16
 */
public final class Outpost {
	
	/**
	 * The name of the outpost
	 */
	private static String name;
	
	/**
	 * The stock of the outpost (A list of consumables that the player may buy)
	 */
	private static ArrayList<Consumable> stock;
	
	/**
	 * The price multiplier of the outpost.
	 * Prices should be multiplied by this when purchasing items
	 */
	private static int priceMultiplier;
	
	/**
	 * Intended to throw an error if someone attempts to make an instance of this class
	 */
	private Outpost() {}
	
	/**
	 * Sets the state of the outpost
	 * @param newName the name of the outpost
	 * @param newStock the consumables purchasable at the outpost
	 * @param newPriceMultiplier the price multiplier of the outpost
	 */
	public static void setOutpost(String newName, ArrayList<Consumable> newStock, int newPriceMultiplier) {
		name = newName;
		stock = new ArrayList<Consumable>(newStock);
		priceMultiplier = newPriceMultiplier;
	}
	
	/**
	 * Returns the name of the outpost
	 * @return the name of the outpost
	 */
	public static String getName() {
		return name;
	}
	
	/**
	 * Returns an array list of the consumable stock
	 * @return stock of item
	 */
	public static ArrayList<Consumable> getStock() {
		return stock;
	}
	
	/**
	 * Adds an item to consumable list
	 * @param item the item to be added
	 * @return true if stock consumable item is added
	 * 			false otherwise
	 */
	public static boolean addStockItem(Consumable item) {
		if (stock.contains(item)) {
			return false;
		} else {
			stock.add(item);
			return true;
		}
	}
	
	/**
	 * Removes an item from consumable list
	 * @param item the item to be removed
	 * @return true if stock consumable item is removed
	 * 			false otherwise
	 */
	public static boolean removeStockItem(Consumable item) {
		if (stock.contains(item)) {
			stock.remove(item);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Purchase item from outpost
	 * @param item the item to be purchased
	 * @param amount the cost of the item
	 * @return true if item can be purchased;
	 * 			false otherwise
	 */
	public static boolean purchaseItem(Consumable item, int amount) {
		int price = item.getPrice() * amount * priceMultiplier;
		if (Ship.getMoney() >= price) {
			item.increaseHeld(amount);
			Ship.decreaseMoney(price);
			return true;
		} else {
			return false;
		}
	}

}
