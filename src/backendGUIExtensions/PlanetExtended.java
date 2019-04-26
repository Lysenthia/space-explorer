package backendGUIExtensions;

import backend.Planet;

/**
 * Extends the planet class to allow for an image of the planet
 * @author hoo42
 * @author rvo16
 */
public class PlanetExtended extends Planet {
	
	/**
	 * The path to an image of the planet
	 */
	private String image;

	/**
	 * Creates an instance of an extended planet with a name, description, and path to an image of the planet
	 * @param name the name of the planet
	 * @param description a description of the planet
	 * @param image the path to an image of the planet
	 */
	public PlanetExtended(String name, String description, String image) {
		super(name, description);
		this.image = image;
	}
	
	/**
	 * Sets the image of the planet
	 * @param image the path to an image of the planet
	 */
	public void setImage(String image) {
		this.image = image;
	}
	
	/**
	 * Returns the path to the image of the planet
	 * @return the path to the image of the planet
	 */
	public String getImage() {
		return this.image;
	}

}
