package backendGUIExtensions;

import java.util.ArrayList;
import java.util.stream.Stream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class CrewMemberImages {
	private static ArrayList<Image> images = new ArrayList<Image>();
	private static Image defaultImage;
	private static Path resourceFolder = Paths.get("/resources/img/");

	public static ArrayList<Image> getImages() {
		return images;
	}
	
	public static Image getDefaultImage() {
		return defaultImage;
	}

	public static void generateImage(Path path) {
		Image image = new Image(path);
		if (path.getFileName().toString() == "default.*") {
			defaultImage = image;
		}
		images.add(image);
	}
	public static void fetchImages() throws IOException {
		Stream<Path> contents = Files.walk(resourceFolder);
		contents.forEach(CrewMemberImages::generateImage);
		contents.close();
	}
	
	public static void main(String[] args) {
		System.out.println("Working Directory = " +
	              System.getProperty("user.dir"));
		try {
			CrewMemberImages.fetchImages();
			for (Image image : getImages()) {
				System.out.println(image);
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
