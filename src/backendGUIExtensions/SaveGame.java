package backendGUIExtensions;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

import org.yaml.snakeyaml.Yaml;

import backend.CrewMember;
import backend.GameState;
import backend.Planet;
import backend.Ship;

public class SaveGame {
	private Path file;
	private ArrayList<HashMap<String, String>> planets = new ArrayList<HashMap<String, String>>();
	private ArrayList<HashMap<String, String>> crew = new ArrayList<HashMap<String, String>>();
	
	public SaveGame(Path file) {
		this.file = file;
	}
	
	public void save() throws IOException {
		HashMap<String, ArrayList<HashMap<String, String>>> data = new HashMap<String, ArrayList<HashMap<String, String>>>();
		addPlanets();
		addCrew();
		data.put("planets", planets);
		data.put("crew", crew);
		Yaml yaml = new Yaml();
		FileWriter writer = new FileWriter(this.file.toString());
		yaml.dump(data, writer);
	}
	
	private void addPlanets() {
		for (Planet planet : GameState.getPlanets()) {
			PlanetExtended planetExtension = (PlanetExtended) planet;
			HashMap<String, String> savablePlanet = new HashMap<String, String>();
			savablePlanet.put("name", planetExtension.getName());
			savablePlanet.put("description", planetExtension.getDescription());
			savablePlanet.put("image", planetExtension.getImage().getName().toString());
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
}
