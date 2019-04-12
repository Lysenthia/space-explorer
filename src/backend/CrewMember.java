package backend;
/**
 * Stores information regarding a single crew member including their name,
 * class, current health, hunger, tiredness and action points, and whether or
 * not they have the space plague. Also provides actions that a crew member can
 * perform, such as repairing shields.
 * @author hoo42
 *
 */
public class CrewMember {
	private String name;
	private int actionPoints;
	private int health;
	private int hunger;
	private int tiredness;
	private CrewClass memberClass;
	private boolean spacePlague;
	
	/**
	 * Constructs a crew member with a name and class
	 * @param name the name of the crew member
	 * @param memberClass the class of the crew member
	 */
	public CrewMember(String name, CrewClass memberClass) {
		this.name = name;
		this.memberClass = memberClass;
		this.actionPoints = 2;
		this.health = 100;
		this.hunger = 0;
		this.tiredness = 0;
		this.spacePlague = false;
	}
	
	/**
	 * Returns the name of the crew member
	 * @return the name of the crew member
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Returns the crew members class
	 * @return the crew members class
	 */
	public CrewClass getMemberClass() {
		return this.memberClass;
	}
	
	/**
	 * Returns the crew members current number of action points
	 * @return the crew members current number of action points
	 */
	public int getActionPoints() {
		return this.actionPoints;
	}
	
	/** 
	 * Returns the crew members current hunger
	 * @return the crew members current hunger
	 */
	public int getHunger() {
		return this.hunger;
	}
	
	/**
	 * Returns the crew members current health
	 * @return the percentage of health the crew member has left
	 */
	public int getHealth() {
		return this.health;
	}
	
	/**
	 * Sets the crew members health to a given percent
	 * @param health the percent to set the crew members health to
	 */
	public void setHealth(int health) {
		this.health = health;
	}
	
	/**
	 * Increases the crew members health by a given amount
	 * @param amount the percentage to increase crew members health by
	 */
	public void increaseHealth(int amount) {
		this.health += amount;
		if (this.health > 100) {
			this.health = 100;
		}
	}
	
	/**
	 * 
	 * @param amount the percentage to decrease the crew members health by
	 * @return true if the crew members health is 0% or less;
	 * 		   false otherwise
	 */
	public boolean decreaseHealth(int amount) {
		this.health -= amount;
		if (this.health <= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Decreases the crew members hunger by a given amount
	 * @param amount the amount to decrease the crew members hunger by
	 */
	public void decreaseHunger(int amount) {
		this.hunger -= amount;
		if (this.hunger < 0) {
			this.hunger = 0;
		}
	}
	
	/**
	 * Increases the crew members hunger by a given amount
	 * @param amount the amount to increase the crew members hunger by
	 * @return true if the crew members hunger has exceeded 100;
	 * 		   false otherwise
	 */
	public boolean increaseHunger(int amount) {
		this.hunger += amount;
		if (this.hunger >= 100) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Returns the current tiredness of the crew member
	 * @return the tiredness of the crew member
	 */
	public int getTiredness() {
		return this.tiredness;
	}
	
	/**
	 * Increases the crew members tiredness by a given amount
	 * @param amount the amount to increase the crew members tiredness by
	 */
	public void addTiredness(int amount) {
		this.tiredness += amount;
		if (this.tiredness > 100) {
			this.tiredness = 100;
		}
	}
	
	/**
	 * Reduces the crew members tiredness by a given amount
	 * @param amount the amount to reduce the crew members tiredness by
	 */
	public void reduceTiredness(int amount) {
		this.tiredness -= amount;
		if (this.tiredness < 0) {
			this.tiredness = 0;
		}
	}
	
	/**
	 * Sets the crew members space plague flag to true
	 */
	public void giveSpacePlague() {
		this.spacePlague = true;
	}
	
	/**
	 * Sets the crew members space plague flag to false
	 */
	public void removeSpacePlague() {
		this.spacePlague = false;
	}
	
	/**
	 * Returns whether or not the crew member has the space plague
	 * @return true if the crew members space plague flag is true;
	 * 		   false if the crew members space plague flag is false
	 */
	public boolean hasSpacePlague() {
		return this.spacePlague;
	}
	
	/**
	 * Reduces the crew members tiredness by 100 and decreases action points by 1
	 */
	public void sleep() {
		this.actionPoints -= 1;
		this.reduceTiredness(100);
	}
	
	/**
	 * Repairs the ships shields by 50 if the crew member is an engineer, other wise repairs the ships shields by 25
	 * Decreases the crew members action points by 1
	 */
	public void repairShields() {
		this.actionPoints -= 1;
		if (this.memberClass == CrewClass.ENGINEER) {
			Ship.repairShields(50);
		} else {
			Ship.repairShields(25);
		}
	}
	
	/**
	 * Searches a planet for an item
	 * TODO
	 */
	public void searchPlanet() {
		//TODO
	}
	
	/**
	 * Increases the crew members tiredness and hunger by 50, and sets action points to 1 if the crew members tiredness is 100%, otherwise sets action points to 2
	 * If the crew member has the space plague, decreases their health by 25%
	 * @return true if the crew members health is 0% or less;
	 * 		   false otherwise
	 */
	public boolean transitionDay() {
		//TODO
		this.addTiredness(50);
		this.increaseHunger(50);
		if (this.tiredness == 100) {
			this.actionPoints = 1;
		} else {
			this.actionPoints = 2;
		}
		if (this.hasSpacePlague()) {
			this.decreaseHealth(25);
		}
		if (this.health <= 0) {
			return true;
		} else {
			return false;
		}
	}
}
