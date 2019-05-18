package backendGUIExtensions;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.nio.file.Path;

/**
 * Stores information regarding an image for use in the GUI
 * @author hoo42
 * @author rvo16
 */
public class GUIImage {
	
	/**
	 * The path to the image
	 */
	private Path location;
	
	/**
	 * The filename of the image
	 */
	private Path name;
	
	/**
	 * The actual contents of the image, which can be displayed
	 */
	private BufferedImage contents;

	/**
	 * Creates an instance of a GUI image with a given location and contents
	 * @param location the path to the image
	 * @param contents the actual image
	 */
	public GUIImage(Path location, BufferedImage contents) {
		this.location = location;
		this.name = location.getFileName();
		this.contents = contents;
	}
	
	/**
	 * Returns the path to the image
	 * @return the path to the image
	 */
	public Path getLocation() {
		return this.location;
	}
	
	/**
	 * Returns the filename of the image
	 * @return the filename of the image
	 */
	public Path getName() {
		return this.name;
	}
	
	/**
	 * Returns the filename of the image as a string
	 */
	public String toString() {
		return name.toString();
	}
	
	/**
	 * Returns the actual image scaled smoothly to a given width and height
	 * @param width the width to which the image should be scaled in pixels
	 * @param height the height to which the image should be scaled in pixels
	 * @return an instance of the actual image scaled to the given width and height
	 */
	public Image getContents(int width, int height) {
		if (width < height) {
			return contents.getScaledInstance(width, -1, Image.SCALE_SMOOTH);
		} else if (width > height) {
			return contents.getScaledInstance(-1, height, Image.SCALE_SMOOTH);
		} else {
			return contents.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		}
	}
}
