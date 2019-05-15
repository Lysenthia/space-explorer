package backendGUIExtensions;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.yaml.snakeyaml.Yaml;
import java.time.*;
import java.util.HashMap;

public class SaveSystem {
	private static Path resourceFolder = Paths.get(System.getProperty("user.dir")+"/resources/");
	
	public static boolean saveGame() {
		String name = LocalDateTime.now().toString();
		HashMap<String, HashMap<String, HashMap<String, String>>> data = new HashMap<String, HashMap<String, HashMap<String, String>>>();
		HashMap<String, HashMap<String, String>> planets = new HashMap<String, HashMap<String, String>>();
		//for (Planet planet : )
		//data.put("planets", value)
		return false;
	}
}
