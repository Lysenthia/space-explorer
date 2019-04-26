package backend;

import java.util.Random;
/**
 * Stores information regarding a single crew member including their name,
 * class, current health, hunger, tiredness and action points, and whether or
 * not they have the space plague. Also provides actions that a crew member can
 * perform, such as repairing shields.
 * @author hoo42
 * @author rvo16
 * 
 */

public class CrewMember {
	/**
	 * The name of the crew member
	 */
	private String name;
	/**
	 * The number of action points the crew member currently has
	 */
	private int actionPoints;
	/**
	 * The current health of the crew member.
	 * Should be 1-100 if the crew member is alive.
	 * If 0 or below, the crew member is dead
	 */
	private int health;
	/**
	 * The current hunger of the crew member.
	 * Should be 0-100, with 0 being not hungry and 100 being starving
	 */
	private int hunger;
	/**
	 * The current tiredness of the crew member
	 * Should be 0-100, with 0 being not sleepy and 100 being exhausted
	 */
	private int tiredness;
	/**
	 * The class of the crew member
	 * @see CrewClass
	 */
	private CrewClass memberClass;
	/**
	 * Whether or not the crew member has the space plague.
	 * True if they do; false otherwise
	 */
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
	 * Decreases the crew members action point by 1
	 */
	public void useActionPoint() {
		this.actionPoints -= 1;
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
	 */
	public void increaseHunger(int amount) {
		this.hunger += amount;
		if (this.hunger > 100) {
			this.hunger = 100;
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
		this.useActionPoint();
		this.reduceTiredness(100);
	}
	
	/**
	 * Repairs the ships shields by 50 if the crew member is an engineer, other wise repairs the ships shields by 25
	 * Decreases the crew members action points by 1
	 */
	public void repairShields() {
		this.useActionPoint();
		if (this.memberClass == CrewClass.ENGINEER) {
			Ship.repairShields(50);
		} else {
			Ship.repairShields(25);
		}
	}
	
	/**
	 * Searches a planet for items
	 * @param planet the planet to search for an item
	 * @return a special output type containing the type of item found (money, consumable, part, nothing), and details regarding this item
	 */
	public PlanetSearchOutput searchPlanet(Planet planet) {
		this.useActionPoint();
		Consumable item = null;
		int amount = 0;
		PlanetFindableObjects event = null;
		Random rng = new Random();
		if (this.memberClass == CrewClass.SCOUT) {
			event = PlanetFindableObjects.selectRandom(true, planet.getPartFound());
			switch(event) {
				case MONEY:
					amount = rng.nextInt(100) + 1;
					Ship.addMoney(amount);
					break;
				case ITEM:
					item = GameState.getAllConsumable().get(rng.nextInt(GameState.getAllConsumable().size()));
					item.increaseHeld(1);
					break;
				case PART:
					planet.findPart();
					break;
				case NOTHING:
					break;
			}
		} else {
			event = PlanetFindableObjects.selectRandom(false, planet.getPartFound());
			switch(event) {
			case MONEY:
				amount = rng.nextInt(100) + 1;
				Ship.addMoney(amount);
				break;
			case ITEM:
				item = GameState.getAllConsumable().get(rng.nextInt(GameState.getAllConsumable().size()));
				item.increaseHeld(1);
				break;
			case PART:
				planet.findPart();
				break;
			case NOTHING:
				break;
			}
		}
		PlanetSearchOutput out = new PlanetSearchOutput(event, item, amount);
		return out;
	}
	
	/**
	 * Increases the crew members tiredness and hunger by 50, and sets action points to 1 if the crew members tiredness is 100%, otherwise sets action points to 2
	 * If the crew member has the space plague, decreases their health by 25%
	 * @return true if the crew members health is 0% or less;
	 * 		   false otherwise
	 */
	public boolean transitionDay() {
		if (this.memberClass != CrewClass.CYBORG) {
			this.addTiredness(50);
			this.increaseHunger(25);
			if (this.tiredness == 100) {
				this.actionPoints = 1;
			} else {
				this.actionPoints = 2;
			}
			if (this.getHunger() == 100) {
				this.decreaseHealth(15);
			}
		} else {
			this.actionPoints = 2;
		}
		if (this.hasSpacePlague()) {
			this.decreaseHealth(35);
		}
		if (this.health <= 0) {
			return true;
		} else {
			return false;
		}
	}
}
