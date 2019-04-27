package graphicalInterface;

import java.io.IOException;
import java.util.ArrayList;

import backendGUIExtensions.CrewMemberImages;
import backendGUIExtensions.GUIImage;
import backendGUIExtensions.PlanetExtended;

public class StartApplication {
	
	private static ArrayList<GUIImage> possibleCrewImages;
	private static ArrayList<PlanetExtended> planets;
	private static boolean blockProcess = false;
	
	public static ArrayList<GUIImage> getPossibleCrewImages() {
		return possibleCrewImages;
	}
	
	public static ArrayList<PlanetExtended> getPlanets() {
		return planets;
	}

	public static void main(String[] args) {
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
		if (!blockProcess) {
			possibleCrewImages = CrewMemberImages.getImages();
			StartupScreen.callScreen();
		}

	}

}
