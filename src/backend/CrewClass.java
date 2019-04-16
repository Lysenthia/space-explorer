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
	GUARD("Guard"),
	
	/**
	 * Cook archetype
	 */
	COOK("Cook"),
	
	/**
	 * Scout archetype
	 */
	SCOUT("Scout"),
	
	/**
	 * Engineer archetype
	 */
	ENGINEER("Shields Engineer"),
	
	/**
	 * Cyborg archetype
	 */
	CYBORG("Cyborg"),
	
	/**
	 * Medic archetype
	 */
	MEDIC("Medic");
	
	private final String className;
	
	/**
	 * Construct a class archetype with a player visible name
	 * @param name the name of the class that will be seen by the player
	 */
	private CrewClass(String name) {
		this.className = name;
	}
	
	/**
	 * Returns the player visible name for a crew member class
	 * @return
	 */
	public String getClassName() {
		return this.className;
	}
	
}