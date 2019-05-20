package backend;

/**
 * Stores information regarding the outpost at which the player can purchase items.
 * @author hoo42
 * @author rvo16
 */
public final class Outpost {
	
	/**
	 * The name of the outpost
	 */
	private static String name;
	
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
	 * @param newPriceMultiplier the price multiplier of the outpost
	 */
	public static void setOutpost(String newName, int newPriceMultiplier) {
		name = newName;
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
	 * Returns the price multiplier of the outpost
	 * @return the price multiplier of the outpost
	 */
	public static int getPriceMultiplier() {
		return priceMultiplier;
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
