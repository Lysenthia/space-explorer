package commandLine;
import java.util.Scanner;
import backend.*;
import java.util.ArrayList;

public class GameEnviroment {
	
	private static ArrayList<CrewMember> possibleCrew = new ArrayList<CrewMember>();
	private static Outpost outpost;
	private static ArrayList<Planet> planets = new ArrayList<Planet>();
	
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
	
	public static int[] extractInt(String s, int size) {
		Scanner reader = new Scanner(s.trim());
		int[] list = new int[size];
		int i = 0;
		while (reader.hasNextInt() && i < size) {
			list[i] = reader.nextInt();
			i++;
		}
		reader.close();
		return list;
	}

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
	
	private static void printGreeting() {
		System.out.println("｡･:*:･ﾟ★,｡･:*:･ﾟ☆       Hello!      ｡･:*:･ﾟ★,｡･:*:･ﾟ☆");
		System.out.println("✧･ﾟ: *✧･ﾟ:*  Welcome to Space Explorers!  *:･ﾟ✧*:･ﾟ✧");
		System.out.println("Your spaceships has been broken and its pieces are scattered throughout the surrounding planets.\n" + 
				"You will need to find the missing pieces of your spaceship so that you can repair it and get home.");
	}
	
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
	
	private static void getCrewMembers(Scanner input) {
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
					ready = (value >=  0 && value < members.length);
					if (!ready) {
						break;
					}
				}
			}
		} while (!ready);
		for (int i = 0; i < members.length; i++) {
			CrewMember member = possibleCrew.get(members[i]);
			Ship.addCrewMember(new CrewMember(member.getName(), member.getMemberClass()));
			System.out.println(String.format("Added crew member with name %s and class %s", member.getName(), member.getMemberClass().getClassName()));
		}
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		initialiseVariables();
		printGreeting();
		getEndDay(input);
		getCrewMembers(input);
		
		input.close();
		System.out.println("Done");
		System.exit(0);
	}
}
