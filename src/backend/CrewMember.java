package backend;

public class CrewMember {
	private String name;
	private int actionPoints;
	private int health;
	private int hunger;
	private String crewClass;
	
	public CrewMember(String name, String crewClass) {
		this.name = name;
		this.crewClass = crewClass;
		this.actionPoints = 5;
		this.health = 100;
		this.hunger = 100;
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
		this.health = (this.health + amount) % 100;
	}
	
	public boolean decreaseHealth(int amount) {
		this.health -= amount;
		if (this.health >= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public void decreaseHunger(int amount) {
		this.hunger = (this.hunger + amount) % 100;
	}
	
	public boolean increaseHunger(int amount) {
		this.hunger -= amount;
		if (this.hunger >= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public void giveSpacePlague() {
		//TODO
	}
	
	public void removeSpacePlague() {
		//TODO
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
