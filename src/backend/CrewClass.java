package backend;

import java.util.HashMap;

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
	SCOUT("Scout", "Will always find something when searching a planet for resources"),
	
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
	
	/**
	 * A description of the class
	 */
	private final String classDescription;
	
	/**
	 * A lookup table used to find a crew class based on a string
	 */
	private static final HashMap<String, CrewClass> lookupTable = new HashMap<String, CrewClass>(buildTable());
	
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
	
	/**
	 * Returns the description of the class
	 * @return the description of the class
	 */
	public String getDescription() {
		return this.classDescription;
	}
	
	/**
	 * Returns the lookup table based on the name of a crew class and the crew class
	 * @return the lookup table
	 */
	private static HashMap<String, CrewClass> buildTable() {
		HashMap<String, CrewClass> table = new HashMap<String, CrewClass>();
		for (CrewClass cClass : CrewClass.values()) {
			table.put(cClass.name(), cClass);
		}
		return table;
	}
	
	/**
	 * Looks up the given string in the lookup table and returns the corresponding class if it exists; otherwise returns null
	 * @param name of crew class
	 * @return the crew class; null otherwise
	 */
	public static CrewClass lookup(String name) {
		if (lookupTable.containsKey(name)) {
			return lookupTable.get(name);
		} else {
			return null;
		}
	}
}