package backendGUIExtensions;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.nio.file.Path;

public class GUIImage {
	
	private Path location;
	private Path name;
	private BufferedImage contents;

	public GUIImage(Path location, BufferedImage contents) {
		this.location = location;
		this.name = location.getFileName();
		this.contents = contents;
	}
	
	public Path getLocation() {
		return this.location;
	}
	
	public Path getName() {
		return this.name;
	}
	
	public String toString() {
		return name.toString();
	}
	
	public Image getContents(int width, int height) {
		return contents.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	}
}
