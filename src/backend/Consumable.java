package backend;

public abstract class Consumable {
	protected String name;
	protected int price;
	protected int held;
	protected int effectiveness;
	protected String itemType;
	
	/**
	 * Creates an instance of the consumable class with name, price and effectiveness
	 * @param name the name of the consumable
	 * @param price the price of the consumable
	 * @param itemType the type of item (i.e medical, cure, food)
	 * @param effectivness the effectiveness of the consumable
	 */
	
	public Consumable(String name, int price, int effectiveness, String itemType) {
		this.name = name;
		this.price = price;
		this.effectiveness = effectiveness;
		this.itemType = itemType;
		this.held = 0;
	}
	
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
	 * Gets the effectiveness of a consumable item
	 * @return
	 */
	public int getEffectiveness() {
		return this.effectiveness;
	}

	public String getItemType() {
		return this.itemType;
	}
}
