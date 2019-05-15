package graphicalInterface;

import java.io.IOException;
import java.util.ArrayList;

import backend.Consumable;
import backend.GameState;
import backend.Outpost;
import backend.Planet;
import backend.Ship;
import backendGUIExtensions.CosnumableReader;
import backendGUIExtensions.CrewMemberImages;
import backendGUIExtensions.GUIImage;
import backendGUIExtensions.PlanetExtended;

public class StartApplication {
	
	private static ArrayList<GUIImage> possibleCrewImages;
	private static ArrayList<Consumable> consumables;
	private static boolean blockProcess = false;
	private static Outpost outpost;
	
	public static ArrayList<GUIImage> getPossibleCrewImages() {
		return possibleCrewImages;
	}
	
	public static Outpost getOutpost() {
		return outpost;
	}

	public static void main(String[] args) {
		Ship.addMoney(500);
		try {
			CrewMemberImages.fetchImages();
		} catch (IOException e) {
			blockProcess = true;
			ErrorWindow.callScreen("Error fetching crew member images", e);
		}
		try {
			ArrayList<PlanetExtended> planets = PlanetExtended.getFromYAML();
			ArrayList<Planet> castedPlanets = new ArrayList<Planet>();
			castedPlanets.addAll(planets);
			GameState.setPlanets(castedPlanets);
		} catch (IOException e) {
			blockProcess = true;
			ErrorWindow.callScreen("Error fetching planet data", e);
		}
		try {
			consumables = CosnumableReader.fetchConsumables();
			GameState.setAllConsumables(consumables);
		} catch (IOException e) {
			blockProcess = true;
			ErrorWindow.callScreen("Error fetching consumables data", e);
		}
		outpost = new Outpost("Outpost 9", consumables, 1);
		if (!blockProcess) {
			possibleCrewImages = CrewMemberImages.getImages();
			StartupScreen.callScreen();
		}

	}

}
