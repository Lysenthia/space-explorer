package commandLine;
import java.util.Scanner;
import backend.*;
import java.util.ArrayList;

public class GameEnviroment {
	
	public static boolean hasInteger(String s) {
		Scanner reader = new Scanner(s.trim());
		boolean hasInt = reader.hasNextInt();
		reader.close();
		if (hasInt) {
			return true;
		} else {
			return false;
		}
	}
	
	public static int extractInt(String s) {
		Scanner reader = new Scanner(s.trim());
		int value = reader.nextInt();
		reader.close();
		return value;
	}

	public static void main(String[] args) {
		//Initialise Variables
		int days = 0;
		String line;
		Scanner input = new Scanner(System.in);
		ArrayList<CrewMember> possibleCrew = new ArrayList<CrewMember>();
		possibleCrew.add(new CrewMember("Alice", CrewClass.SCOUT));
		possibleCrew.add(new CrewMember("Bob", CrewClass.ENGINEER));
		possibleCrew.add(new CrewMember("Charlie", CrewClass.COOK));
		possibleCrew.add(new CrewMember("Dave", CrewClass.CYBORG));
		possibleCrew.add(new CrewMember("Eve", CrewClass.MEDIC));
		possibleCrew.add(new CrewMember("Fiona", CrewClass.GUARD));
		GameState.addConsumable(new MedicalItem("Bandage", 25, 25));
		GameState.addConsumable(new MedicalItem("First Aid Kit", 50, 50));
		GameState.addConsumable(new MedicalItem("Elixir", 100, 100));
		GameState.addConsumable(new FoodItem("Bread", 30, 30));
		GameState.addConsumable(new FoodItem("Flour", 5, 5));
		GameState.addConsumable(new FoodItem("Rice", 20, 20));
		GameState.addConsumable(new FoodItem("NutriPaste", 100, 100));
		GameState.addConsumable(new FoodItem("Assorted Fruits", 50, 50));
		GameState.addConsumable(new FoodItem("Snapfrozen Curry", 70, 70));
		GameState.addConsumable(new CureItem("Budget Space Plague Cure", 50, 50));
		GameState.addConsumable(new CureItem("Space Plague Cure", 100, 100));
		Outpost outpost = new Outpost("Outpost 9", GameState.getAllConsumable(), 1);
		ArrayList<Planet> planets = new ArrayList<Planet>();
		planets.add(new Planet("Mercury", "The closest planet to Sol. Small and very hot."));
		planets.add(new Planet("Venus", "Second from Sol. About the size of Earth, but suffered from runaway greenhouse effect."));
		planets.add(new Planet("Earth", "Third from Sol. The birthplace of humanity, but was abandoned due to it's increasing uninhabitability."));
		planets.add(new Planet("Mars", "The fourth planet from Sol. Small, but larger than Mercury. And very cold."));		
		planets.add(new Planet("Jupiter", "Fifth from Sol. Large, and likely responsible for protecting life on Earth due to it's massive gravitational pull."));
		planets.add(new Planet("Saturn", "Sixth from Sol. Not as large as Jupiter, but still very big. A good place for farming orokin cells. Wait a second..."));
		planets.add(new Planet("Uranus", "Seventh from Sol, and the butt of many poor jokes."));
		planets.add(new Planet("Neptune", "Eight closest planet to Sol. may or may not contain diamonds."));
		planets.add(new Planet("Pluto", "The furthest planet from Sol. Angry at it's exclusion from the title of planet, protesters bombed other space objects and used the material to beef up Pluto to a planet."));
		/*
		BEGIN GAME PROCESSING
		*/
		System.out.println("Hello!");
		System.out.println("Welcome to Space Explorers!");
		//Select number of days
		System.out.println("How many days would you like to play for? (between 3 and 10 days): ");
		line = input.nextLine();
		if (hasInteger(line)) {
			days = extractInt(line);
		}
		while (days < 3 || days > 10) {
			System.out.println("Please enter an integer between 3 and 10 inclusive: ");
			line = input.nextLine();
			if (hasInteger(line)) {
				days = extractInt(line);
			}
		}
		GameState.setEndDay(days);
		//Select crew
		System.out.println("Please select between 2-4 crew members");
		for (int i = 0; i < possibleCrew.size(); i++) {
			CrewMember member = possibleCrew.get(i);
			System.out.println(String.format("%d:\tName: %s\tClass: %s", i, member.getName(), member.getMemberClass().name()));
		}
		input.close();
		System.out.println("Done");
	}
}
