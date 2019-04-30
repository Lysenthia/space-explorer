package backendGUIExtensions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;

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
	/**
	 * The path to the resource folder
	 */
	private static Path resourceFolder = Paths.get(System.getProperty("user.dir")+"/resources/");
	private static GUIImage defaultImage;
	/**
	 * Creates an instance of an extended planet with a name, description, and path to an image of the planet
	 * @param name the name of the planet
	 * @param description a description of the planet
	 * @param a GUIImage of the planet
	 */
	public PlanetExtended(String name, String description, GUIImage image) {
		super(name, description);
		this.image = image;
	}
	
	/**
	 * Creates an instance of an extended planet with a name, description, and path to an image of the planet
	 * @param name the name of the planet
	 * @param description a description of the planet
	 * @param image the name of the image of the planet
	 */
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
	
	/**
	 * Gets the default image for a planet (This should be the sun)
	 * @throws IOException if an error is encountered whilst fetching the image
	 */
	public static void fetchDefaultImage() throws IOException {
		Path path = Paths.get(resourceFolder.toString() + "/planet-img/default.jpg");
		GUIImage image = new GUIImage(path, ImageIO.read(path.toFile()));
		defaultImage = image;
	}
	
	/**
	 * Returns the default image for a planet
	 * @return the default image for a planet
	 */
	public static GUIImage getDefaultImage(){
		return defaultImage;
	}

	/**
	 * Parses the planets.yaml file and constructs an ArrayList of PlanetExtended from the read data
	 * @return an ArrayList of PlanetExtended from the YAML file
	 * @throws IOException if a default image is not found
	 */
	public static ArrayList<PlanetExtended> getFromYAML() throws IOException {
		ArrayList<PlanetExtended> planets = new ArrayList<PlanetExtended>();
		Yaml parser = new Yaml();
		InputStream input = new FileInputStream(resourceFolder.toString() + "/game-data/planets.yaml");
		LinkedHashMap<String, ArrayList<LinkedHashMap<String, String>>> output = parser.load(input);
		ArrayList<LinkedHashMap<String, String>> filteredOutput = output.get("planets");
		for (LinkedHashMap<String, String> data : filteredOutput) {
			planets.add(new PlanetExtended(data.get("name"), data.get("description"), data.get("image")));
		}	
		fetchDefaultImage();
		return planets;
	}
}
