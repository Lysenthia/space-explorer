package backendGUIExtensions;

import java.util.ArrayList;
import java.util.stream.Stream;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class CrewMemberImages {
	private static ArrayList<GUIImage> images = new ArrayList<GUIImage>();
	private static GUIImage defaultImage;
	private static Path resourceFolder = Paths.get(System.getProperty("user.dir")+"/resources/img/");

	public static ArrayList<GUIImage> getImages() {
		return images;
	}
	
	public static GUIImage getDefaultImage() {
		return defaultImage;
	}

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
	public static void fetchImages() throws IOException {
		Stream<Path> contents = Files.walk(resourceFolder);
		contents.forEach(CrewMemberImages::generateImage);
		contents.close();
	}
}
