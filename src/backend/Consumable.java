package backend;

public abstract class Consumable {
	protected String name;
	protected int price;
	protected int held;
	protected int effectivness;
	
	public Consumable(String name, int price, int effectivness) {
		this.name = name;
		this.price = price;
		this.effectivness = effectivness;
		this.held = 0;
	}
	public abstract boolean use(CrewMember user);
	
	public String getName() {
		return this.name;
	}
	
	public int getPrice() {
		return this.price;
	}
	
	public int getHeld() {
		return this.held;
	}
	
	public void increaseHeld(int amount) {
		this.held += amount;
		Ship.addToInventory(this);
	}
	
	public void decreaseHeld(int amount) {
		this.held -= amount;
		Ship.removeFromInventory(this);
	}

}
