package backendGUIExtensions;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import backend.Consumable;
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
		FileWriter writer = new FileWriter(this.file.toString() + ".ses");
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
			savableMember.put("class", memberExtension.getMemberClass().toString());
			savableMember.put("image", memberExtension.getImage().getName().toString());
			savableMember.put("health", Integer.toString(memberExtension.getHealth()));
			savableMember.put("hunger", Integer.toString(memberExtension.getHunger()));
			savableMember.put("ap", Integer.toString(memberExtension.getActionPoints()));
			savableMember.put("tiredness", Integer.toString(memberExtension.getTiredness()));
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
		LinkedHashMap<String, Object> output;
		Yaml parser = new Yaml();
		InputStream input = new FileInputStream(this.file.toString());
		output = parser.load(input);
		System.out.println(output);
	}
}
