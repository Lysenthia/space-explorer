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
import backend.Planet;
import backend.Ship;

public class SaveGame {
	private Path file;
	private ArrayList<LinkedHashMap<String, String>> planets = new ArrayList<LinkedHashMap<String, String>>();
	private ArrayList<LinkedHashMap<String, String>> crew = new ArrayList<LinkedHashMap<String, String>>();
	private LinkedHashMap<String, ArrayList<LinkedHashMap<String, String>>> consumables = new LinkedHashMap<String, ArrayList<LinkedHashMap<String, String>>>();
	private LinkedHashMap<String, String> state = new LinkedHashMap<String, String>();

	
	public SaveGame(Path file) {
		this.file = file;
	}
	
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
	
	private void addGameState() {
		state.put("curDay", Integer.toString(GameState.getCurrentDay()));
		state.put("endDay", Integer.toString(GameState.getEndDay()));
		state.put("partsNeeded", Integer.toString(GameState.getPartsNeeded()));
		state.put("currentParts", Integer.toString(GameState.getPartsFound()));
		state.put("shields", Integer.toString(Ship.getShields()));
		state.put("money", Integer.toString(Ship.getMoney()));
	}
	
	public void load() throws IOException {
		LinkedHashMap<String, Collection<?>> output;
		Yaml parser = new Yaml();
		InputStream input = new FileInputStream(this.file.toString());
		output = parser.load(input);
		input.close();
		update(output);
	}
	
	private void update(LinkedHashMap<String, Collection<?>> output) throws IOException {
		ArrayList<Planet> planetsList = fetchPlanets(output);
		ArrayList<CrewMemberExtended> crewList = fetchCrew(output);
		ArrayList<Consumable> consumablesList = fetchConsumables(output);
		Ship.clearAll();
		GameState.setAllConsumables(consumablesList);
		crewList.forEach(member -> Ship.addCrewMember(member));
		GameState.setPlanets(planetsList);
	}
	
	/**
	 * 
	 * @param output the LinkedHashMap produced by loading the save file
	 * @return the arraylist of planets in the game
	 * @throws IOException if there is an error whilst parsing the planets
	 */
	@SuppressWarnings("unchecked")
	private ArrayList<Planet> fetchPlanets(LinkedHashMap<String, Collection<?>> output) throws IOException {
		Collection<?> untypedPlanets = output.get("planets");
		ArrayList<Planet> planetsList = new ArrayList<Planet>();
		for (Object untypedPlanet : untypedPlanets) {
			if (!(untypedPlanet instanceof LinkedHashMap<?, ?>)) {
				throw new IOException("Incorrect planet type");
			}
			LinkedHashMap<String, String> typedPlanet = (LinkedHashMap<String, String>) untypedPlanet;
			if (typedPlanet.get("name") == null || typedPlanet.get("description") == null || typedPlanet.get("image") == null) {
				throw new IOException("Error parsing planets");
			}
			planetsList.add(new PlanetExtended(typedPlanet.get("name"), typedPlanet.get("description"), typedPlanet.get("image")));
		}
		return planetsList;
	}
	
	@SuppressWarnings("unchecked")
	private ArrayList<CrewMemberExtended> fetchCrew(LinkedHashMap<String, Collection<?>> output) throws IOException {
		ArrayList<GUIImage> crewImages = CrewMemberImages.getImages();
		HashMap<String, GUIImage> crewImageLookup = new HashMap<String, GUIImage>();
		for (GUIImage image : crewImages) {
			crewImageLookup.put(image.getName().toString(), image);
		}
		crewImageLookup.put(null, CrewMemberImages.getDefaultImage());
		Collection<?> untypedCrew = output.get("crew");
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
				throw new IOException("Error parsing crew members (oob) ");
			}
			member.setParameters(health, tiredness, hunger, ap);
			if (Boolean.valueOf(typedMember.get("plague")) == true) {
				member.giveSpacePlague();
			}
			crewList.add(member);
		}
		return crewList;
	}
	
	@SuppressWarnings("unchecked")
	private ArrayList<Consumable> fetchConsumables(LinkedHashMap<String, Collection<?>> output) throws IOException {
		ArrayList<Consumable> consumables = new ArrayList<Consumable>();
		Collection<?> untypedItems = output.get("items");
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
				if (key == "medical") {
					outItem = new MedicalItem(name, price, effectiveness);
				} else if (key == "cure") {
					outItem = new CureItem(name, price, effectiveness);
				} else if (key == "food") {
					outItem = new FoodItem(name, price, effectiveness);
				} else {
					throw new IOException("Invalid item type");
				}
				outItem.increaseHeld(held);
				consumables.add(outItem);
			}
		}
		return consumables;
	}
}
