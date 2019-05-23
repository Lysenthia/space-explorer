package backendGUIExtensions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import backend.*;

/**
 * Allows for the reading of the consumables.yaml in the resources folder
 * @author hoo42
 * @author rvo16
 */
public class ConsumableReader {
	/**
	 * The path to the resources folder
	 */
	private static Path resourceFolder = Paths.get(PathFinder.getCurrentPath()+"/resources/");

	/**
	 * Parses and returns the consumables.yaml file, returning an ArrayList of the consumables found within
	 * @return an ArrayList of consumables found in the consumables.yaml file
	 * @throws IOException if an error is encountered whilst fetching or parsing the file
	 */
	public static ArrayList<Consumable> fetchConsumables() throws IOException {
		LinkedHashMap<String, LinkedHashMap<String, ArrayList<LinkedHashMap<String, Object>>>> output;
		ArrayList<Consumable> consumables = new ArrayList<Consumable>();
		ArrayList<LinkedHashMap<String, Object>> workingList;
		Yaml parser = new Yaml();
		InputStream input = new FileInputStream(resourceFolder.toString() + "/game-data/consumables.yaml");
		output = parser.load(input);
		LinkedHashMap<String, ArrayList<LinkedHashMap<String, Object>>> filteredOutput = output.get("consumables");
		//Process medical items
		workingList = filteredOutput.get("medical");
		for (LinkedHashMap<String, Object> item : workingList) {
			consumables.add(new MedicalItem((String) item.get("name"), (int) item.get("price"), (int) item.get("effectiveness")));
		}
		//Process food items
		workingList = filteredOutput.get("food");
		for (LinkedHashMap<String, Object> item : workingList) {
			consumables.add(new FoodItem((String) item.get("name"), (int) item.get("price"), (int) item.get("effectiveness")));
		}
		//Process cure items
		workingList = filteredOutput.get("cure");
		for (LinkedHashMap<String, Object> item : workingList) {
			consumables.add(new CureItem((String) item.get("name"), (int) item.get("price"), (int) item.get("effectiveness")));
		}
		input.close();
		return consumables;
		
	}
}
