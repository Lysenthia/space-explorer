package graphicalInterface;

import java.io.IOException;
import java.util.ArrayList;

import backend.Consumable;
import backend.GameState;
import backend.Ship;
import backendGUIExtensions.CosnumableReader;
import backendGUIExtensions.CrewMemberImages;
import backendGUIExtensions.GUIImage;
import backendGUIExtensions.PlanetExtended;

public class StartApplication {
	
	private static ArrayList<GUIImage> possibleCrewImages;
	private static ArrayList<PlanetExtended> planets;
	private static ArrayList<Consumable> consumables;
	private static boolean blockProcess = false;
	
	public static ArrayList<GUIImage> getPossibleCrewImages() {
		return possibleCrewImages;
	}
	
	public static ArrayList<PlanetExtended> getPlanets() {
		return planets;
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
			planets = PlanetExtended.getFromYAML();
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
		if (!blockProcess) {
			possibleCrewImages = CrewMemberImages.getImages();
			StartupScreen.callScreen();
		}

	}

}
