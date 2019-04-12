package backend;

/**
 * Stores information regarding a single planet including its name, description,
 * whether or not it has a part, has been searched, and has had its part found.
 * @author hoo42
 *
 */
public class Planet {
	private boolean hasPart;
	private boolean partFound;
	private boolean searched;
	private String name;
	private String description;
	
	/**
	 * Constructs a Planet given a name and description
	 * @param name
	 * @param description
	 */
	public Planet(String name, String description) {
		this.name = name;
		this.description = description;
		//TODO: Implement part randomiser
		this.hasPart = false;
		this.partFound = false;
		this.searched = false;
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
	 * Returns whether or no the planet has a drive part on it
	 * @return true if the planet has a part;
	 * 		   false otherwise
	 */
	public boolean getHasPart() {
		return this.hasPart;
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
	 * Returns whether or not the planet has been searched for a part
	 * @return true if the planet has been searched before;
	 * 		   false otherwise
	 */
	public boolean getSearched() {
		return this.searched;
	}
	
	/**
	 * Checks the planet for a part, and sets the searched flag to true
	 * If the planet has a part, sets the part found flag to true
	 * @return true if the planet has a part;
	 * 		   false otherwise
	 */
	public boolean checkForPart() {
		if (this.hasPart) {
			this.partFound = true;
			this.searched = true;
			return true;
		} else {
			this.searched = true;
			return false;
		}
	}

}
