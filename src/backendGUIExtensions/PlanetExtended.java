package backendGUIExtensions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.yaml.snakeyaml.Yaml;

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
	private static Path resourceFolder = Paths.get(System.getProperty("user.dir")+"/resources/");
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
	
	public PlanetExtended(String name, String description, String image) {
		super(name, description);
		Path location = Paths.get(resourceFolder.toString() + "/planet-img/" + image);
		try {
			this.image = new GUIImage(location, ImageIO.read(location.toFile()));
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	public static ArrayList<PlanetExtended> getFromYAML() {
		ArrayList<PlanetExtended> planets = new ArrayList<PlanetExtended>();
		Yaml parser = new Yaml();
		try {
			InputStream input = new FileInputStream(resourceFolder.toString() + "/game-data/planets.yaml");
			ArrayList<LinkedHashMap<String, String>> output = parser.load(input);
			for (LinkedHashMap<String, String> data : output) {
				planets.add(new PlanetExtended(data.get("name"), data.get("description"), data.get("image")));
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return planets;
	}
}
