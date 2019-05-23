package backendGUIExtensions;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Gets the current directory that the game is being run from
 * Mainly intended for use from a jar file, as this forces the resources
 * to be added into a source folder in eclipse
 * @author hoo42
 *
 */
@SuppressWarnings("unused")
public final class PathFinder {

	/**
	 * Returns the directory containing that the program is running in 
	 * @return the directory containing that the program is running in 
	 */
	public static String getCurrentPath() {
		String out = null;
		try {
			File file = new File(PathFinder.class.getProtectionDomain().getCodeSource().getLocation().toURI());
			if (file.isFile()) {
				out = Paths.get(file.getPath()).getParent().toAbsolutePath().toString();
			} else {
				out = file.getAbsolutePath();
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return out;
	}
}
