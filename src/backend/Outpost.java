package backend;
import java.util.ArrayList;

public class Outpost {
	private String name;
	private ArrayList<Consumable> stock;
	private int priceMultiplier;
	
	public Outpost(String name, ArrayList<Consumable> stock, int priceMultiplier) {
		this.name = name;
		this.stock = stock;
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
	 * @return true if item can be purchased
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
