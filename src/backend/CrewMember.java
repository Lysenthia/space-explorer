package backend;

public class CrewMember {
	private String name;
	private int actionPoints;
	private int health;
	private int hunger;
	private int tiredness;
	private CrewClass memberClass;
	private boolean spacePlague;
	
	public CrewMember(String name, CrewClass memberClass) {
		this.name = name;
		this.memberClass = memberClass;
		this.actionPoints = 2;
		this.health = 100;
		this.hunger = 0;
		this.tiredness = 0;
		this.spacePlague = false;
	}
	
	public String getName() {
		return this.name;
	}
	
	public CrewClass getMemberClass() {
		return this.memberClass;
	}
	
	public int getActionPoints() {
		return this.actionPoints;
	}
	
	public int getHunger() {
		return this.hunger;
	}
	
	public int getHealth() {
		return this.health;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public void increaseHealth(int amount) {
		this.health += amount;
		if (this.health > 100) {
			this.health = 100;
		}
	}
	
	public boolean decreaseHealth(int amount) {
		this.health -= amount;
		if (this.health <= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public void decreaseHunger(int amount) {
		this.hunger -= amount;
		if (this.hunger < 0) {
			this.hunger = 0;
		}
	}
	
	public boolean increaseHunger(int amount) {
		this.hunger += amount;
		if (this.hunger >= 100) {
			return true;
		} else {
			return false;
		}
	}
	
	public int getTiredness() {
		return this.tiredness;
	}
	
	public void addTiredness(int amount) {
		this.tiredness += amount;
		if (this.tiredness >= 100) {
			this.tiredness = 100;
		}
	}
	
	public void reduceTiredness(int amount) {
		this.tiredness -= amount;
		if (this.tiredness < 0) {
			this.tiredness = 0;
		}
	}
	
	public void giveSpacePlague() {
		this.spacePlague = true;
	}
	
	public void removeSpacePlague() {
		this.spacePlague = false;
	}
	
	public boolean hasSpacePlague() {
		return this.spacePlague;
	}
	
	public void sleep() {
		this.actionPoints -= 1;
		this.reduceTiredness(100);
	}
	
	public void repairShields() {
		this.actionPoints -= 1;
		if (this.memberClass == CrewClass.ENGINEER) {
			Ship.repairShields(50);
		} else {
			Ship.repairShields(25);
		}
	}
	
	public void searchPlanet() {
		//TODO
	}
}
