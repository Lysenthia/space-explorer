package backend;

/**
 * Potential classes for crew members
 * @author hoo42
 * @author rvo16
 */
public enum CrewClass {
	
	/**
	 * Guard archetype
	 */
	GUARD("Guard", "Has a chance to prevent items being stolen when space pirates attack."),
	
	/**
	 * Cook archetype
	 */
	COOK("Cook", "Decreases your crew's hunger level slightly each day"),
	
	/**
	 * Scout archetype
	 */
	SCOUT("Scout", "Will always find an item when searching a planet for resources"),
	
	/**
	 * Engineer archetype
	 */
	ENGINEER("Shields Engineer", "Repairs the ship's shields slightly more than other classes"),
	
	/**
	 * Cyborg archetype
	 */
	CYBORG("Cyborg", "Not affected by hunger or tiredness"),
	
	/**
	 * Medic archetype
	 */
	MEDIC("Medic", "Heals your crew's health slightly each day");
	
	/**
	 * The name of the class that will be seen by the player 
	 */
	private final String className;
	
	private final String classDescription;
	
	/**
	 * Construct a class archetype with a player visible name
	 * @param name the name of the class that will be seen by the player
	 */
	private CrewClass(String name, String description) {
		this.className = name;
		this.classDescription = description;
	}
	
	/**
	 * Returns the player visible name for a crew member class
	 * @return the player visible name for a crew member class
	 * @depreciated replaced with the {@link toString} for ease of use
	 */
	public String getClassName() {
		return this.className;
	}
	
	/**
	 * Returns the player visible name for a crew member class
	 */
	public String toString() {
		return this.className;
	}
	
	public String getDescription() {
		return this.classDescription;
	}
}