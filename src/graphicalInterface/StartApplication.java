package graphicalInterface;

import java.io.IOException;
import java.util.ArrayList;

import backend.Consumable;
import backend.GameState;
import backend.Outpost;
import backend.Planet;
import backend.Ship;
import backendGUIExtensions.ConsumableReader;
import backendGUIExtensions.CrewMemberImages;
import backendGUIExtensions.GUIImage;
import backendGUIExtensions.PlanetExtended;

/**
 * Main call method to start game
 * @author hoo42
 * @author rvo16
 *
 */
public class StartApplication {
	
	private static ArrayList<GUIImage> possibleCrewImages;
	private static ArrayList<Consumable> consumables;
	private static boolean blockProcess = false;
	
	public static ArrayList<GUIImage> getPossibleCrewImages() {
		return possibleCrewImages;
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
			consumables = ConsumableReader.fetchConsumables();
			GameState.setAllConsumables(consumables);
		} catch (IOException e) {
			blockProcess = true;
			ErrorWindow.callScreen("Error fetching consumables data", e);
		}
		Outpost.setOutpost("Outpost 9", consumables, 1);
		if (!blockProcess) {
			possibleCrewImages = CrewMemberImages.getImages();
			StartupScreen.callScreen();
		}

	}

}
