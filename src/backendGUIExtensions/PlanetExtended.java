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
	private GUIImage image;

	/**
	 * Creates an instance of an extended planet with a name, description, and path to an image of the planet
	 * @param name the name of the planet
	 * @param description a description of the planet
	 * @param image the path to an image of the planet
	 */
	public PlanetExtended(String name, String description, GUIImage image) {
		super(name, description);
		this.image = image;
	}
	
	/**
	 * Sets the image of the planet
	 * @param image the path to an image of the planet
	 */
	public void setImage(GUIImage image) {
		this.image = image;
	}
	
	/**
	 * Returns the path to the image of the planet
	 * @return the path to the image of the planet
	 */
	public GUIImage getImage() {
		return this.image;
	}

}
