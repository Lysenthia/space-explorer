package backend;
import java.util.ArrayList;

/**
 * Stores information regarding an outpost at which the player can purchase items.
 * In most games there will be only one outpost, but in an altered game with multiple systems there could be several
 * @author hoo42
 * @author rvo16
 */
public class Outpost {
	/**
	 * The name of the outpost
	 */
	private String name;
	/**
	 * The stock of the outpost (A list of consumables that the player may buy)
	 */
	private ArrayList<Consumable> stock;
	/**
	 * The price multiplier of the outpost.
	 * Prices should be multiplied by this when purchasing items
	 */
	private int priceMultiplier;
	
	/**
	 * Constructs an instance of an outpost with a name, stock, and price multiplier
	 * @param name the name of the outpost
	 * @param stock the consumables purchasable at the outpost
	 * @param priceMultiplier the price multiplier of the outpost
	 */
	public Outpost(String name, ArrayList<Consumable> stock, int priceMultiplier) {
		this.name = name;
		this.stock = new ArrayList<Consumable>(stock);
		this.priceMultiplier = priceMultiplier;
	}
	
	/**
	 * Returns the name of the outpost
	 * @return the name of the outpost
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Returns an array list of the consumable stock
	 * @return stock of item
	 */
	public ArrayList<Consumable> getStock() {
		return this.stock;
	}
	
	/**
	 * Adds an item to consumable list
	 * @param item the item to be added
	 * @return true if stock consumable item is added
	 * 			false otherwise
	 */
	public boolean addStockItem(Consumable item) {
		if (this.stock.contains(item)) {
			return false;
		} else {
			this.stock.add(item);
			return true;
		}
	}
	
	/**
	 * Removes an item from consumable list
	 * @param item the item to be removed
	 * @return true if stock consumable item is removed
	 * 			false otherwise
	 */
	public boolean removeStockItem(Consumable item) {
		if (this.stock.contains(item)) {
			this.stock.remove(item);
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
	public boolean purchaseItem(Consumable item, int amount) {
		int price = item.getPrice() * amount * this.priceMultiplier;
		if (Ship.getMoney() >= price) {
			item.increaseHeld(amount);
			Ship.decreaseMoney(price);
			return true;
		} else {
			return false;
		}
	}

}
