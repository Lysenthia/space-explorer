package backend;

public class CrewMember {
	private String name;
	private int actionPoints;
	private int health;
	private int hunger;
	private String crewClass;
	private boolean spacePlague;
	
	public CrewMember(String name, String crewClass) {
		this.name = name;
		this.crewClass = crewClass;
		this.actionPoints = 5;
		this.health = 100;
		this.hunger = 0;
		this.spacePlague = false;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getCrewClass() {
		return this.crewClass;
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
		//TODO
	}
	
	public void repairShields() {
		//TODO
	}
	
	public void searchPlanet() {
		//TODO
	}
}
