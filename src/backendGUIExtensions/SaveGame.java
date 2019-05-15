package backendGUIExtensions;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

import org.yaml.snakeyaml.Yaml;

import backend.Consumable;
import backend.CrewMember;
import backend.GameState;
import backend.Planet;
import backend.Ship;

public class SaveGame {
	private Path file;
	private ArrayList<HashMap<String, String>> planets = new ArrayList<HashMap<String, String>>();
	private ArrayList<HashMap<String, String>> crew = new ArrayList<HashMap<String, String>>();
	private HashMap<String, ArrayList<HashMap<String, String>>> consumables = new HashMap<String, ArrayList<HashMap<String, String>>>();
	private HashMap<String, String> state = new HashMap<String, String>();

	
	public SaveGame(Path file) {
		this.file = file;
	}
	
	public void save() throws IOException {
		//TODO fix issue with hashmaps not being added correctly
		HashMap<String, Object> data = new HashMap<String, Object>();
		addPlanets();
		addCrew();
		addConsumables();
		addGameState();
		data.put("planets", planets);
		data.put("crew", crew);
		data.put("items", consumables);
		data.put("state", state);
		Yaml yaml = new Yaml();
		FileWriter writer = new FileWriter(this.file.toString());
		yaml.dump(data, writer);
	}
	
	private void addPlanets() {
		Planet orbiting = Ship.getOrbiting();
		for (Planet planet : GameState.getPlanets()) {
			PlanetExtended planetExtension = (PlanetExtended) planet;
			HashMap<String, String> savablePlanet = new HashMap<String, String>();
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
			HashMap<String, String> savableMember = new HashMap<String, String>();
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
		consumables.put("medical", new ArrayList<HashMap<String, String>>());
		consumables.put("food", new ArrayList<HashMap<String, String>>());
		consumables.put("cure", new ArrayList<HashMap<String, String>>());
		for (Consumable item : GameState.getAllConsumable()) {
			ArrayList<HashMap<String, String>> type = consumables.get(item.getItemType().toLowerCase());
			HashMap<String, String> savableItem = new HashMap<String, String>();
			savableItem.put("name", item.getName());
			savableItem.put("name", Integer.toString(item.getEffectiveness()));
			savableItem.put("name", Integer.toString(item.getHeld()));
			savableItem.put("name", Integer.toString(item.getPrice()));
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
		//TODO
	}
}
