package backendGUIExtensions;

import java.util.ArrayList;
import java.util.stream.Stream;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Allows for the fetching of images for crew member
 * @author hoo42
 * @author rvo16
 */
public final class CrewMemberImages {
	/**
	 * An ArrayList of the images that have been loaded
	 */
	private static ArrayList<GUIImage> images = new ArrayList<GUIImage>();
	/**
	 * The default image for crew members
	 */
	private static GUIImage defaultImage;
	/**
	 * The path to the crew-img resource folder
	 */
	private static Path resourceFolder = Paths.get(System.getProperty("user.dir")+"/resources/crew-img/");

	/**
	 * Returns the ArrayList of GUIImages that have been loaded
	 * @return the ArrayList of GUIImages that have been loaded
	 */
	public static ArrayList<GUIImage> getImages() {
		return images;
	}
	
	/**
	 * Returns the default GUIImage for crew members
	 * @return the default GUIImage for crew members
	 */
	public static GUIImage getDefaultImage() {
		return defaultImage;
	}

	/**
	 * Reads an image and adds a GUIImage of it to the stored images
	 * @param path the path to the image to load
	 */
	public static void generateImage(Path path) {
		try {
			BufferedImage buff = ImageIO.read(path.toFile());
			if (buff != null) {
				GUIImage image = new GUIImage(path, buff);
				if (path.getFileName().toString().matches("default\\..*")) {
					defaultImage = image;
				}
				images.add(image);
			}
		} catch (IOException e) {
			return;
		}
	}
	
	/**
	 * Fetches all crew images from the resource folder and loads them
	 * @throws IOException if an I/O error is thrown when walking the directory
	 * @throws FileNotFoundException if no default image is found
	 */
	public static void fetchImages() throws IOException, FileNotFoundException {
		Stream<Path> contents = Files.walk(resourceFolder);
		contents.forEach(CrewMemberImages::generateImage);
		contents.close();
		if (defaultImage == null) {
			throw new FileNotFoundException("No default image found");
		}
	}
}
