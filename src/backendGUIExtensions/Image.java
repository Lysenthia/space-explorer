package backendGUIExtensions;

import java.nio.file.Path;

public class Image {
	
	private Path location;
	private Path name;

	public Image(Path location) {
		this.location = location;
		this.name = location.getFileName();
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
}
