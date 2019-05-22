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
	
	/**
	 * An array list of all the loaded crew images
	 */
	private static ArrayList<GUIImage> possibleCrewImages;
	/**
	 * An array list of all the found consumables
	 */
	private static ArrayList<Consumable> consumables;
	/**
	 * True if an error was encountered whilst reading the game files; false otherwise
	 */
	private static boolean blockProcess = false;
	
	/**
	 * Returns the list of all loaded crew images
	 * @return the list of all loaded crew images
	 */
	public static ArrayList<GUIImage> getPossibleCrewImages() {
		return possibleCrewImages;
	}

	public static void main(String[] args) {
		Ship.addMoney(150);
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
		Outpost.setOutpost("Outpost 9", 1);
		if (!blockProcess) {
			possibleCrewImages = CrewMemberImages.getImages();
			MainMenuScreen.callScreen();
		}

	}

}
