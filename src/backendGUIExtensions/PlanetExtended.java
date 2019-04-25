package backendGUIExtensions;

import backend.Planet;

public class PlanetExtended extends Planet {
	
	private String image;

	public PlanetExtended(String name, String description, String image) {
		super(name, description);
		this.image = image;
	}
	
	public String getImage() {
		return this.image;
	}

}
