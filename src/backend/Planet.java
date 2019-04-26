package backend;

/**
 * Stores information regarding a single planet including its name, description,
 * whether or not it has a part, has been searched, and has had its part found.
 * @author hoo42
 * @author rvo16
 */

public class Planet {
	/**
	 * Whether or not a part has been found on this planet.
	 * True if one has been found;
	 * false otherwise
	 */
	private boolean partFound;
	/**
	 * The name of the planet
	 */
	private String name;
	/**
	 * A description of the planet, used to add extra detail to the game
	 */
	private String description;
	
	/**
	 * Constructs a Planet given a name and description
	 * @param name
	 * @param description
	 */
	public Planet(String name, String description) {
		this.name = name;
		this.description = description;
		this.partFound = false;
	}
	
	/**
	 * Returns the name of the planet
	 * @return the name of the planet
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Returns the description of the planet
	 * @return the description of the planet
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Returns whether or not the planets part has been found
	 * @return true if the part has been found;
	 * 		   false otherwise
	 */
	public boolean getPartFound() {
		return this.partFound;
	}
	
	/**
	 * Sets the part found flag to true
	 */
	public void findPart() {
		this.partFound = true;
		GameState.findPart();
	}
}
