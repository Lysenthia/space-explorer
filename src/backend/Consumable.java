package backend;

public abstract class Consumable {
	private String name;
	private int price;
	private int held;
	private int effectivness;
	
	public abstract void use(CrewMember user);
	
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
	}
	
	public void decreaseHeld(int amount) {
		this.held -= amount;
	}

}
