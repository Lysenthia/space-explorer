package commandLine;
import java.util.Scanner;
import backend.*;
import java.util.ArrayList;

public class GameEnviroment {
	
	private static ArrayList<CrewMember> possibleCrew = new ArrayList<CrewMember>();
	private static Outpost outpost;
	private static ArrayList<Planet> planets = new ArrayList<Planet>();
	private static boolean finished = false;
	
	/**
	 * Checks if a string contains a given number of integers
	 * @param s the string to check for integers
	 * @param size the number of integers to check for
	 * @return true if the string contains the correct number of integers;
	 * 		   false otherwise
	 */
	public static boolean hasInteger(String s, int size) {
		Scanner reader = new Scanner(s.trim());
		int i = 0;
		while (reader.hasNextInt() && i < size) {
			reader.nextInt();
			i++;
		}
		reader.close();
		if (i == size) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Extracts a series of integers from a string
	 * @param s the string to extract the integers from
	 * @param size the maximum number of integers to extract
	 * @return an array containing the extracted integers (May be smaller than size)
	 */
	public static int[] extractInt(String s, int size) {
		Scanner reader = new Scanner(s.trim());
		int[] list = new int[size];
		int i = 0;
		while (reader.hasNextInt() && i < size) {
			list[i] = reader.nextInt();
			i++;
		}
		reader.close();
		int[] out = new int[i];
		System.arraycopy(list, 0, out, 0, i);
		return out;
	}

	/**
	 * Initialises various variables used by the game
	 */
	private static void initialiseVariables() {
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
		outpost = new Outpost("Outpost 9", GameState.getAllConsumable(), 1);
		
		planets.add(new Planet("Mercury", "The closest planet to Sol. Small and very hot."));
		planets.add(new Planet("Venus", "Second from Sol. About the size of Earth, but suffered from runaway greenhouse effect."));
		planets.add(new Planet("Earth", "Third from Sol. The birthplace of humanity, but was abandoned due to it's increasing uninhabitability."));
		planets.add(new Planet("Mars", "The fourth planet from Sol. Small, but larger than Mercury. And very cold."));		
		planets.add(new Planet("Jupiter", "Fifth from Sol. Large, and likely responsible for protecting life on Earth due to it's massive gravitational pull."));
		planets.add(new Planet("Saturn", "Sixth from Sol. Not as large as Jupiter, but still very big. A good place for farming orokin cells. Wait a second..."));
		planets.add(new Planet("Uranus", "Seventh from Sol, and the butt of many poor jokes."));
		planets.add(new Planet("Neptune", "Eight closest planet to Sol. may or may not contain diamonds."));
		planets.add(new Planet("Pluto", "The furthest planet from Sol. Angry at it's exclusion from the title of planet, protesters bombed other space objects and used the material to beef up Pluto to a planet."));
	}
	
	/**
	 * Prints the intro greeting for the game
	 */
	private static void printGreeting() {
		System.out.println("｡･:*:･ﾟ★,｡･:*:･ﾟ☆       Hello!      ｡･:*:･ﾟ★,｡･:*:･ﾟ☆");
		System.out.println("✧･ﾟ: *✧･ﾟ:*  Welcome to Space Explorers!  *:･ﾟ✧*:･ﾟ✧");
		System.out.println("Your spaceships has been broken and its pieces are scattered throughout the surrounding planets.\n" + 
				"You will need to find the missing pieces of your spaceship so that you can repair it and get home.");
	}
	
	/**
	 * Gets the end day for the game
	 * @param input the Scanner shared by various methods
	 */
	private static void getEndDay(Scanner input) {
		System.out.println("\nHow many days would you like to play for? (between 3 and 10 days): ");
		int days = 0;
		String line = input.nextLine();
		if (hasInteger(line, 1)) {
			days = extractInt(line, 1)[0];
		}
		while (days < 3 || days > 10) {
			System.out.println("Please enter an integer between 3 and 10 inclusive: ");
			line = input.nextLine();
			if (hasInteger(line, 1)) {
				days = extractInt(line, 1)[0];
			}
		}
		GameState.setEndDay(days);
		System.out.println(String.format("End day has been set to %d\n%d parts must be found\n", GameState.getEndDay(), GameState.getPartsNeeded()));
	}
	
	/**
	 * Gets the crew members to add to the ship's crew
	 * @param input the Scanner shared between methods
	 */
	private static void getCrewMembers(Scanner input) {
		ArrayList<String> usedNames = new ArrayList<String>();
		int size = possibleCrew.size();
		boolean ready = false;
		String line;
		int[] members = null;
		do {
			System.out.println("Please select between 2-4 crew members\n(Input a single line seperated by spaces of the id of the crew members that you want to add)");
			for (int i = 0; i < size; i++) {
				CrewMember member = possibleCrew.get(i);
				System.out.println(String.format("%d:\tName: %s\tClass: %s", i, member.getName(), member.getMemberClass().getClassName()));
			}
			line = input.nextLine();
			if (hasInteger(line, 2)) {
				members = extractInt(line, 4);
				for (int i = 0; i < members.length; i++) {
					int value = members[i];
					ready = (value >=  0 && value < size);
					if (!ready) {
						break;
					}
				}
			}
		} while (!ready);
		for (int i = 0; i < members.length; i++) {
			CrewMember member = possibleCrew.get(members[i]);
			if (usedNames.contains(member.getName())) {
				Ship.addCrewMember(new CrewMember(member.getName() + i, member.getMemberClass()));
				System.out.println(String.format("Added crew member with name %s and class %s", member.getName() + i, member.getMemberClass().getClassName()));
			} else {
				Ship.addCrewMember(new CrewMember(member.getName(), member.getMemberClass()));
				usedNames.add(member.getName());
				System.out.println(String.format("Added crew member with name %s and class %s", member.getName(), member.getMemberClass().getClassName()));
			}
		}
		System.out.println();
	}
	
	/**
	 * Prints the possible actions for the player to the console
	 */
	private static void printActions() {
		System.out.println("What would you like to do? ");
		System.out.println("0: Go to a new planet");
		System.out.println("1: Check on your crew and access your inventory");
		System.out.println(String.format("2: Go to %s", outpost.getName()));
		System.out.println("3: Transition to a new day");
		if (Ship.getOrbiting() != null) {
			System.out.println(String.format("4: Search %s for supplies", Ship.getOrbiting().getName()));
		}
	}
	
	private static ArrayList<CrewMember> getPilots(Scanner input, ArrayList<CrewMember> pilots) {
		int size = pilots.size();
		ArrayList<CrewMember> selectedPilots = new ArrayList<CrewMember>();
		boolean ready = false;
		String line;
		int[] members = null;
		do {
			System.out.println("Please select 2 crew members");
			for (int i = 0; i < size; i++) {
				CrewMember member = pilots.get(i);
				System.out.println(String.format("%d:\tName: %s\tAction points remaining: %d", i, member.getName(), member.getActionPoints()));
			}
			line = input.nextLine();
			if (hasInteger(line, 2)) {
				members = extractInt(line, 2);
				for (int i = 0; i < members.length; i++) {
					int value = members[i];
					ready = (value >=  0 && value < size);
					if (!ready) {
						break;
					}
				}
				if (members[0] == members[1]) {
					ready = false;
				}
			}
		} while (!ready);
		for (int i = 0; i < members.length; i++) {
			CrewMember member = pilots.get(members[i]);
			selectedPilots.add(new CrewMember(member.getName(), member.getMemberClass()));
		}
		System.out.println();
		return selectedPilots;
	}

	private static void printPlanets(ArrayList<Planet> planetsToPrint) {
		for (int i = 0; i < planetsToPrint.size(); i++) {
			Planet cur = planetsToPrint.get(i);
			System.out.println(String.format("%d:\tName: %s\tPart Found: %b", i, cur.getName(), cur.getPartFound()));
		}
	}
	
	private static Planet selectPlanet(Scanner input) {
		ArrayList<Planet> possibleDestinations = new ArrayList<Planet>(planets);
		possibleDestinations.remove(Ship.getOrbiting());
		System.out.println("Please select a planet to travel to:");
		printPlanets(possibleDestinations);
		int choice = 0;
		String line = input.nextLine();
		if (hasInteger(line, 1)) {
			choice = extractInt(line, 1)[0];
		}
		while (choice < 0 || choice > possibleDestinations.size()) {
			System.out.println("Please select a planet to travel to:");
			printPlanets(possibleDestinations);
			System.out.println(String.format("Please enter an integer between 0 and %d inclusive: ", possibleDestinations.size() - 1));
			line = input.nextLine();
			if (hasInteger(line, 1)) {
				choice = extractInt(line, 1)[0];
			}
		}
		return planets.get(choice);
	}
	
	private static void pilotShip(Scanner input) {
		ArrayList<CrewMember> possiblePilots = new ArrayList<CrewMember>();
		ArrayList<CrewMember> pilots;
		Planet destination;
		for (CrewMember member : Ship.getShipCrew()) {
			if (member.getActionPoints() > 0) {
				possiblePilots.add(member);
			}
		}
		if (possiblePilots.size() >= 2) {
			pilots = getPilots(input, possiblePilots);
			destination = selectPlanet(input);
			Ship.pilot(pilots.get(0), pilots.get(1), destination);
			System.out.println(String.format("Travelled to the planet %s.\nDescription: %s", destination.getName(), destination.getDescription()));
		} else {
			System.out.println("Not enough crew members to pilot the ship");
			System.out.println("In order to travel to a new planet, at least 2 crew members must have 1 or more action points");
		}
		System.out.println();
	}
	
	private static void enterInventory(Scanner input) {
		
	}
	
	private static void enterOutpost(Scanner input) {
		
	}
	
	private static void transitionDay(Scanner input) {
		
	}
	
	private static void searchPlanet(Scanner input) {
		
	}
	
	/**
	 * Gets the action that the player wishes to perform during normal gameplay
	 * @param input the Scanner shared between methods
	 */
	private static void selectAction(Scanner input) {
		int maxChoice;
		if (Ship.getOrbiting() == null) {
			System.out.println("Currently in a heliocentric orbit");
			maxChoice = 3;
		} else {
			System.out.println(String.format("Currently orbiting the planet %s", Ship.getOrbiting().getName()));
			maxChoice = 4;
		}
		printActions();
		int choice = 0;
		String line = input.nextLine();
		if (hasInteger(line, 1)) {
			choice = extractInt(line, 1)[0];
		}
		while (choice < 0 || choice > maxChoice) {
			printActions();
			System.out.println(String.format("Please enter an integer between 0 and %d inclusive: ", maxChoice));
			line = input.nextLine();
			if (hasInteger(line, 1)) {
				choice = extractInt(line, 1)[0];
			}
		}
		switch (choice) {
		case 0:
			pilotShip(input);
			break;
		case 1:
			enterInventory(input);
			break;
		case 2:
			enterOutpost(input);
			break;
		case 3:
			transitionDay(input);
			break;
		case 4:
			searchPlanet(input);
			break;
		}
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		initialiseVariables();
		printGreeting();
		getEndDay(input);
		getCrewMembers(input);
		while (!finished) {
			selectAction(input);
		}
		input.close();
		System.out.println("Done");
		System.exit(0);
	}
}
