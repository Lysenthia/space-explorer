package backendGUIExtensions;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import backend.Consumable;
import backend.CrewClass;
import backend.CrewMember;
import backend.CureItem;
import backend.FoodItem;
import backend.GameState;
import backend.MedicalItem;
import backend.Outpost;
import backend.Planet;
import backend.Ship;


/**
 * Allows player to save the current game state and load game saves
 * @author hoo42
 * @author rvo16
 */
public class SaveGame {
	/**
	 * Path to the save file
	 */
	private Path file;
	/**
	 * The planets to write to the save
	 */
	private ArrayList<LinkedHashMap<String, String>> planets = new ArrayList<LinkedHashMap<String, String>>();
	/**
	 * The crew to write to the save
	 */
	private ArrayList<LinkedHashMap<String, String>> crew = new ArrayList<LinkedHashMap<String, String>>();
	/**
	 * The consumables to write to the save
	 */
	private LinkedHashMap<String, ArrayList<LinkedHashMap<String, String>>> consumables = new LinkedHashMap<String, ArrayList<LinkedHashMap<String, String>>>();
	/**
	 * The game state and ship state to write to the save
	 */
	private LinkedHashMap<String, String> state = new LinkedHashMap<String, String>();
	/**
	 * The loaded end day
	 */
	private int endDay;
	/**
	 * The loaded current day
	 */
	private int curDay;
	/**
	 * The loaded parts held
	 */
	private int partsHeld;
	/**
	 * The loaded ship shields
	 */
	private int shields;
	/**
	 * The loaded credits held
	 */
	private int money;
	/**
	 * The loaded outpost price multiplier
	 */
	private int priceMultiplier;
	/**
	 * The loaded ship name
	 */
	private String shipName;
	/**
	 * The loaded outpost name
	 */
	private String outpostName;
	
	/**
	 * The loaded initial crew size
	 */
	private int initialCrewSize;

	/**
	 * Constructs an instance of a save file
	 * @param file the path to the save file
	 */
	public SaveGame(Path file) {
		this.file = file;
	}
	
	/**
	 * Saves the current game to the save file
	 * @throws IOException if an error is encountered while writing the save
	 */
	public void save() throws IOException {
		LinkedHashMap<String, Object> data = new LinkedHashMap<String, Object>();
		addPlanets();
		addCrew();
		addConsumables();
		addGameState();
		data.put("planets", planets);
		data.put("crew", crew);
		data.put("items", consumables);
		data.put("state", state);
		DumperOptions options = new DumperOptions();
		options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
		Yaml yaml = new Yaml(options);
		FileWriter writer;
		if (this.file.getFileName().toString().toLowerCase().endsWith(".ses")) {
			writer = new FileWriter(this.file.toString());
		} else {
			writer = new FileWriter(this.file.toString() + ".ses");
		}
		yaml.dump(data, writer);
	}
	
	/**
	 * Adds the planets in the game to the list of planets to write
	 */
	private void addPlanets() {
		Planet orbiting = Ship.getOrbiting();
		for (Planet planet : GameState.getPlanets()) {
			PlanetExtended planetExtension = (PlanetExtended) planet;
			LinkedHashMap<String, String> savablePlanet = new LinkedHashMap<String, String>();
			savablePlanet.put("name", planetExtension.getName());
			savablePlanet.put("description", planetExtension.getDescription());
			savablePlanet.put("image", planetExtension.getImage().getName().toString());
			if (planet == orbiting) {
				savablePlanet.put("orbiting", "true");
			}
			planets.add(savablePlanet);
		}
	}
	
	/**
	 * Adds the crew in the game to the list of crew to write
	 */
	private void addCrew() {
		for (CrewMember member : Ship.getShipCrew()) {
			CrewMemberExtended memberExtension = (CrewMemberExtended) member;
			LinkedHashMap<String, String> savableMember = new LinkedHashMap<String, String>();
			savableMember.put("name", memberExtension.getName());
			savableMember.put("class", memberExtension.getMemberClass().name());
			savableMember.put("image", memberExtension.getImage().getName().toString());
			savableMember.put("health", Integer.toString(memberExtension.getHealth()));
			savableMember.put("hunger", Integer.toString(memberExtension.getHunger()));
			savableMember.put("ap", Integer.toString(memberExtension.getActionPoints()));
			savableMember.put("tiredness", Integer.toString(memberExtension.getTiredness()));
			savableMember.put("plague", String.valueOf(memberExtension.hasSpacePlague()));
			crew.add(savableMember);
		}
	}
	
	/**
	 * Adds the consumables in the game to the list of consumables to write
	 */
	private void addConsumables() {
		consumables.put("medical", new ArrayList<LinkedHashMap<String, String>>());
		consumables.put("food", new ArrayList<LinkedHashMap<String, String>>());
		consumables.put("cure", new ArrayList<LinkedHashMap<String, String>>());
		for (Consumable item : GameState.getAllConsumable()) {
			ArrayList<LinkedHashMap<String, String>> type = consumables.get(item.getItemType().toLowerCase());
			LinkedHashMap<String, String> savableItem = new LinkedHashMap<String, String>();
			savableItem.put("name", item.getName());
			savableItem.put("effectiveness", Integer.toString(item.getEffectiveness()));
			savableItem.put("held", Integer.toString(item.getHeld()));
			savableItem.put("price", Integer.toString(item.getPrice()));
			type.add(savableItem);
		}
	}
	
	/**
	 * Adds the current game state and ship state to the state to write
	 */
	private void addGameState() {
		state.put("curDay", Integer.toString(GameState.getCurrentDay()));
		state.put("endDay", Integer.toString(GameState.getEndDay()));
		state.put("currentParts", Integer.toString(GameState.getPartsFound()));
		state.put("shields", Integer.toString(Ship.getShields()));
		state.put("money", Integer.toString(Ship.getMoney()));
		state.put("shipName", Ship.getName());
		state.put("outpostName", Outpost.getName());
		state.put("outpostMultiplier", Integer.toString(Outpost.getPriceMultiplier()));
		state.put("crewSize", Integer.toString(Ship.getInitialCrewSize()));
	}
	
	/**
	 * Loads a save from the save file
	 * @throws IOException if an error is encountered whilst loading the save
	 */
	public void load() throws IOException {
		LinkedHashMap<String, Object> output;
		Yaml parser = new Yaml();
		InputStream input = new FileInputStream(this.file.toString());
		output = parser.load(input);
		input.close();
		update(output);
	}
	
	/**
	 * Updates the current game to match the loaded save game
	 * @param output the output of the YAML reader
	 * @throws IOException if an error is encountered whilst reading the output
	 */
	private void update(LinkedHashMap<String, Object> output) throws IOException {
		ArrayList<Planet> planetsList = fetchPlanets(output);
		ArrayList<CrewMemberExtended> crewList = fetchCrew(output);
		ArrayList<Consumable> consumablesList = fetchConsumables(output);
		fetchState(output);
		if (planetsList.size() < (int) ((endDay * 2) / 3)) {
			throw new IOException("Error (too few planets)");
		}
		Ship.clearAll();
		GameState.setAllConsumables(consumablesList);
		crewList.forEach(member -> Ship.addCrewMember(member));
		GameState.setPlanets(planetsList);
		GameState.setCurrentDay(curDay);
		GameState.setEndDay(endDay);
		GameState.setPartsFound(partsHeld);
		Ship.setMoney(money);
		Ship.setShields(shields);
		Ship.setName(shipName);
		Outpost.setOutpost(outpostName, priceMultiplier);
		Ship.forceInitialCrewSize(initialCrewSize);
	}
	
	/**
	 * Fetches the planets from the save file
	 * @param output the output of the YAML reader
	 * @return the array list of planets found
	 * @throws IOException if there is an error whilst parsing the planets
	 */
	@SuppressWarnings("unchecked")
	private ArrayList<Planet> fetchPlanets(LinkedHashMap<String, Object> output) throws IOException {
		Collection<?> untypedPlanets = (Collection<?>) output.get("planets");
		ArrayList<Planet> planetsList = new ArrayList<Planet>();
		for (Object untypedPlanet : untypedPlanets) {
			if (!(untypedPlanet instanceof LinkedHashMap<?, ?>)) {
				throw new IOException("Incorrect planet type");
			}
			LinkedHashMap<String, String> typedPlanet = (LinkedHashMap<String, String>) untypedPlanet;
			if (typedPlanet.get("name") == null || typedPlanet.get("description") == null || typedPlanet.get("image") == null) {
				throw new IOException("Error parsing planets");
			}
			PlanetExtended planetOut = new PlanetExtended(typedPlanet.get("name"), typedPlanet.get("description"), typedPlanet.get("image"));
			planetsList.add(planetOut);
			if (typedPlanet.get("orbiting") == "true") {
				Ship.forceOrbit(planetOut);
			}
			
		}
		return planetsList;
	}
	
	/**
	 * Fetches the crew from the save file
	 * @param output the output of the YAML reader
	 * @return the array list of crew found
	 * @throws IOException if there is an error whilst parsing the crew
	 */
	@SuppressWarnings("unchecked")
	private ArrayList<CrewMemberExtended> fetchCrew(LinkedHashMap<String, Object> output) throws IOException {
		ArrayList<GUIImage> crewImages = CrewMemberImages.getImages();
		HashMap<String, GUIImage> crewImageLookup = new HashMap<String, GUIImage>();
		for (GUIImage image : crewImages) {
			crewImageLookup.put(image.getName().toString(), image);
		}
		crewImageLookup.put(null, CrewMemberImages.getDefaultImage());
		Collection<?> untypedCrew = (Collection<?>) output.get("crew");
		ArrayList<CrewMemberExtended> crewList = new ArrayList<CrewMemberExtended>();
		for (Object untypedMember : untypedCrew) {
			if (!(untypedMember instanceof LinkedHashMap<?, ?>)) {
				throw new IOException("Incorrect crew type");
			}
			LinkedHashMap<String, String> typedMember = (LinkedHashMap<String, String>) untypedMember;
			CrewClass memberClass = CrewClass.lookup(typedMember.get("class"));
			if (typedMember.get("name") == null || typedMember.get("health") == null || 
				typedMember.get("image") == null || typedMember.get("hunger") == null ||
				typedMember.get("ap") == null || typedMember.get("tiredness") == null || 
				typedMember.get("plague") == null || memberClass == null) {
				throw new IOException("Error parsing crew members (null value)");
			}
			CrewMemberExtended member = new CrewMemberExtended(typedMember.get("name"), memberClass, crewImageLookup.get(typedMember.get("image")));
			int health = Integer.parseInt(typedMember.get("health"));
			int hunger = Integer.parseInt(typedMember.get("hunger"));
			int tiredness = Integer.parseInt(typedMember.get("tiredness"));
			int ap = Integer.parseInt(typedMember.get("ap"));
			if (health > 100 || health <= 0 || 
				hunger > 100 || hunger < 0 ||
				tiredness > 100 || tiredness < 0 ||
				ap > 2 || ap < 0) {
				throw new IOException("Error parsing crew members (Out of bounds)");
			}
			member.setParameters(health, tiredness, hunger, ap);
			if (Boolean.valueOf(typedMember.get("plague")) == true) {
				member.giveSpacePlague();
			}
			crewList.add(member);
		}
		if (crewList.size() > 4) {
			throw new IOException("Error parsing crew members (too many members)");
		}
		return crewList;
	}
	
	/**
	 * Fetches the consumables from the save file
	 * @param output the output of the YAML reader
	 * @return the array list of consumables found
	 * @throws IOException if there is an error whilst parsing the consumables
	 */
	@SuppressWarnings("unchecked")
	private ArrayList<Consumable> fetchConsumables(LinkedHashMap<String, Object> output) throws IOException {
		ArrayList<Consumable> consumables = new ArrayList<Consumable>();
		Object untypedItems = output.get("items");
		if (!(untypedItems instanceof HashMap<?, ?>)) {
			throw new IOException("Incorrect items type");
		}
		HashMap<String, ArrayList<HashMap<String, String>>> typedItems = (HashMap<String, ArrayList<HashMap<String, String>>>) untypedItems;
		for (String key : typedItems.keySet()) {
			for (HashMap<String, String> item : typedItems.get("medical")) {
				if (item.get("name") == null || item.get("effectiveness") == null || 
					item.get("price") == null || item.get("held") == null) {
						throw new IOException("Error parsing medical items");
				}
				String name = item.get("name");
				int effectiveness = Integer.parseInt(item.get("effectiveness"));
				int price = Integer.parseInt(item.get("price"));
				int held = Integer.parseInt(item.get("held"));
				if (effectiveness < 0 || effectiveness > 100 ||
					price < 0 || held < 0) {
					throw new IOException("Error parsing medical items");
				}
				Consumable outItem;
				if (key.equals("medical")) {
					outItem = new MedicalItem(name, price, effectiveness);
				} else if (key.equals("cure")) {
					outItem = new CureItem(name, price, effectiveness);
				} else if (key.equals("food")) {
					outItem = new FoodItem(name, price, effectiveness);
				} else {
					System.out.println(key);
					throw new IOException("Invalid item type");
				}
				outItem.increaseHeld(held);
				consumables.add(outItem);
			}
		}
		return consumables;
	}
	
	/**
	 * Fetches the game state from the save file
	 * @param output the output of the YAML reader
	 * @throws IOException if there is an error whilst parsing the game state
	 */
	@SuppressWarnings("unchecked")
	private void fetchState(LinkedHashMap<String, Object> output) throws IOException {
		Object untypedState = output.get("state");
		if (!(untypedState instanceof HashMap<?, ?>)) {
			throw new IOException("Incorrect state type");
		}
		HashMap<String, String> typedState = (HashMap<String, String>) untypedState;
		if (typedState.get("curDay") == null ||
			typedState.get("endDay") == null ||
			typedState.get("currentParts") == null ||
			typedState.get("shields") == null ||
			typedState.get("money") == null ||
			typedState.get("shipName") == null ||
			typedState.get("outpostName") == null ||
			typedState.get("outpostMultiplier") == null ||
			typedState.get("crewSize") == null) {
			throw new IOException("Error parsing state (null state)");
		}
		endDay = Integer.parseInt(typedState.get("endDay"));
		curDay = Integer.parseInt(typedState.get("curDay"));
		partsHeld = Integer.parseInt(typedState.get("currentParts"));
		shields = Integer.parseInt(typedState.get("shields"));
		money = Integer.parseInt(typedState.get("money"));
		priceMultiplier = Integer.parseInt(typedState.get("outpostMultiplier"));
		outpostName = typedState.get("outpostName");
		shipName = typedState.get("shipName");
		initialCrewSize = Integer.parseInt(typedState.get("crewSize"));
		if (endDay > 10 || endDay < 2 || partsHeld > (endDay * 2) / 3 ||
			partsHeld < 0 || shields > 100 || shields <= 0 || money < 0 ||
			priceMultiplier <= 0 || initialCrewSize < 2) {
			throw new IOException("Error parsing state (bad value)");
		}
	}
}
