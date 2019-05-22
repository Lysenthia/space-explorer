package backend;

/**
 * Stores information regarding a consumable item in game.
 * Implemented by {@link MedicalItem}, {@link FoodItem}, {@link CureItem}
 * @author hoo42
 * @author rvo16
 */
public abstract class Consumable {
	
	/**
	 * The name of the Consumable item
	 */
	protected String name;
	
	/**
	 * The price of the Consumable item
	 */
	protected int price;
	
	/**
	 * The number of this item held by the crew
	 */
	protected int held;
	
	/**
	 * The effectiveness of the item.
	 * Should be a positive integer between 1 and 100 inclusive
	 */
	protected int effectiveness;
	
	/**
	 * The name of the type of item (i.e medical, cure, food)
	 */
	protected String itemType;
	
	/**
	 * Creates an instance of the consumable class with name, price and effectiveness
	 * @param name the name of the consumable
	 * @param price the price of the consumable
	 * @param itemType the type of item (i.e medical, cure, food)
	 * @param effectiveness the effectiveness of the consumable
	 */
	public Consumable(String name, int price, int effectiveness, String itemType) {
		this.name = name;
		this.price = price;
		this.effectiveness = effectiveness;
		this.itemType = itemType;
		this.held = 0;
	}
	
	/**
	 * Lets a crew member use a consumable item
	 * @see {@link MedicalItem#use}, {@link CureItem}, {@link FoodItem}
	 * @param user the crew member to use the item
	 * @return true if the item is used;
	 * 		   false otherwise
	 */
	public abstract boolean use(CrewMember user);
	
	/**
	 * Returns the name of the consumable
	 * @return the name of the consumable
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Returns the price of the consumable
	 * @return the price of the consumable
	 */
	public int getPrice() {
		return this.price;
	}
	
	/**
	 * Returns the number of a type of item held at a current time
	 * @return the number of a type of item held at a current time
	 */
	public int getHeld() {
		return this.held;
	}
	
	/**
	 * Increases the number of the type of item held at a current time
	 * @param amount the amount the held item is increased by
	 */
	public void increaseHeld(int amount) {
		this.held += amount;
		Ship.addToInventory(this);
	}
	
	/**
	 * Decreases the number of the type of item held at a current time
	 * @param amount the amount the held item is decreased by
	 */
	public void decreaseHeld(int amount) {
		this.held -= amount;
		Ship.removeFromInventory(this);
	}
	
	/**
	 * Returns the effectiveness of a consumable item
	 * @return the effectiveness of a consumable item
	 */
	public int getEffectiveness() {
		return this.effectiveness;
	}

	/**
	 * Returns the name of the item type
	 * @return the name of the item type
	 */
	public String getItemType() {
		return this.itemType;
	}
}
