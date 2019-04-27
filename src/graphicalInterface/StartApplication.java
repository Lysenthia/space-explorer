package graphicalInterface;

import java.io.IOException;
import java.util.ArrayList;

import backendGUIExtensions.CrewMemberImages;
import backendGUIExtensions.GUIImage;

public class StartApplication {
	
	private static ArrayList<GUIImage> possibleCrewImages;
	private static boolean blockProcess = false;
	
	public static ArrayList<GUIImage> getPossibleCrewImages() {
		return possibleCrewImages;
	}

	public static void main(String[] args) {
		try {
			CrewMemberImages.fetchImages();
		} catch (IOException e) {
			blockProcess = true;
			ErrorWindow.callScreen("Error fetching crew member images", e);
		}
		if (!blockProcess) {
			possibleCrewImages = CrewMemberImages.getImages();
			StartupScreen.callScreen();
		}

	}

}
