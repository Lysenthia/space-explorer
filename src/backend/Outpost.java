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
	
	public String getName() {
		return this.name;
	}
	
	public ArrayList<Consumable> getStock() {
		return this.stock;
	}
	
	public boolean addStockItem(Consumable item) {
		if (this.stock.contains(item)) {
			return false;
		} else {
			this.stock.add(item);
			return true;
		}
	}
	
	public boolean removeStockItem(Consumable item) {
		if (this.stock.contains(item)) {
			this.stock.remove(item);
			return true;
		} else {
			return false;
		}
	}
	
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
