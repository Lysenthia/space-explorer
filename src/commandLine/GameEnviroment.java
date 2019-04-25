package commandLine;
import java.util.Scanner;
import backend.*;
import java.util.ArrayList;

public class GameEnviroment {
	
	private static ArrayList<CrewMember> possibleCrew = new ArrayList<CrewMember>();
	private static Outpost outpost;
	private static ArrayList<Planet> planets = new ArrayList<Planet>();
	private static boolean finished = false;
	private static PossibleEndings ending;
	
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
	 * Prints the introduction greeting for the game
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
			System.out.println("5: Quit game");
		} else {
			System.out.println("4: Quit game");
		}
	}
	
	/**
	 * Lets the player select 2 pilots from a given list 
	 * @param input the Scanner shared between methods
	 * @param pilots a list of possible pilots for the ship
	 * @return an ArrayList of the 2 selected pilots
	 */
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
			System.out.println(String.format("%d:\tCancel", pilots.size()));
			line = input.nextLine();
			if (hasInteger(line, 2)) {
				members = extractInt(line, 2);
				for (int i = 0; i < members.length; i++) {
					int value = members[i];
					ready = (value >=  0 && value <= size);
					if (!ready) {
						break;
					}
				}
				if (members[0] == pilots.size() || members[1] == pilots.size()) {
					return null;
				}
				if (members[0] == members[1]) {
					ready = false;
				}
			} else if (hasInteger(line, 1)) {
				if (extractInt(line, 1)[0] == pilots.size()) {
					return null;
				}
			}
		} while (!ready);
		for (int i = 0; i < members.length; i++) {
			selectedPilots.add(pilots.get(members[i]));
		}
		System.out.println();
		return selectedPilots;
	}

	/**
	 * Prints a given ArrayList of planets with numbering and whether or not a part has been found on that planet
	 * @param planetsToPrint the ArrayList of planets to print
	 */
	private static void printPlanets(ArrayList<Planet> planetsToPrint) {
		for (int i = 0; i < planetsToPrint.size(); i++) {
			Planet cur = planetsToPrint.get(i);
			System.out.println(String.format("%d:\tName: %s\tPart Found: %b", i, cur.getName(), cur.getPartFound()));
		}
	}
	
	/**
	 * Lets the player select a planet to travel to
	 * @param input the Scanner shared between methods
	 * @return the Planet selected by the player
	 */
	private static Planet selectPlanet(Scanner input) {
		ArrayList<Planet> possibleDestinations = new ArrayList<Planet>(planets);
		possibleDestinations.remove(Ship.getOrbiting());
		System.out.println("Please select a planet to travel to:");
		printPlanets(possibleDestinations);
		System.out.println(String.format("%d: Cancel", possibleDestinations.size()));
		int choice = 0;
		String line = input.nextLine();
		if (hasInteger(line, 1)) {
			choice = extractInt(line, 1)[0];
		}
		while (choice < 0 || choice > possibleDestinations.size()) {
			System.out.println("Please select a planet to travel to:");
			printPlanets(possibleDestinations);
			System.out.println(String.format("%d: Cancel", possibleDestinations.size()));
			System.out.println(String.format("Please enter an integer between 0 and %d inclusive: ", possibleDestinations.size()));
			line = input.nextLine();
			if (hasInteger(line, 1)) {
				choice = extractInt(line, 1)[0];
			}
		}
		if (choice == possibleDestinations.size()) {
			return null;
		} else {
			return possibleDestinations.get(choice);
		}
	}
	
	/**
	 * Player selected action: Lets the player travel to a new planet
	 * @param input the Scanner shared between methods
	 */
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
			if (pilots == null) {
				return;
			}
			destination = selectPlanet(input);
			if (destination == null) {
				return;
			}
			CrewMember pilotOne = pilots.get(0);
			CrewMember pilotTwo = pilots.get(1);
			Ship.pilot(pilotOne, pilotTwo, destination);
			System.out.println(String.format("%s and %s piloted the %s to %s.", pilotOne.getName(), pilotTwo.getName(), Ship.getName(), destination.getName()));
			System.out.println(String.format("Travelled to the planet %s.\nDescription: %s", destination.getName(), destination.getDescription()));
		} else {
			System.out.println("Not enough crew members to pilot the ship");
			System.out.println("In order to travel to a new planet, at least 2 crew members must have 1 or more action points");
		}
		System.out.println();
	}
	
	
	
	private static void useItem(Scanner input) {
		//TODO
	}
	
	private static void restCrewMember(Scanner input) {
		//TODO
	}
	
	/**
	 * Player selected action: Lets the player view their crew and inventory
	 * @param input the Scanner shared between methods
	 */
	private static void viewStatus(Scanner input) {
		for (CrewMember member : Ship.getShipCrew()) {
			System.out.println(String.format("Crew Member: %-15s Health: %-5d Action points: %-5s Hunger Level: %-5s Tiredness Level: %-5s Space Plague Status: %s", member.getName(), member.getHealth(), member.getActionPoints(), member.getHunger(), member.getTiredness(), member.hasSpacePlague()));
		}
		System.out.println("\nWhat would you like to do? ");
		System.out.println("0: Use a item.");
		//Displays list of inventory to use
		System.out.println("1: Rest a crew member (Reduce tiredness)");
		//Display crew member status and select one
		System.out.println("2: Cancel");
		//Cancel back to main inventory
		int choice = 0;
		String line = input.nextLine();
		if (hasInteger(line, 1)) {
			choice = extractInt(line, 1)[0];
		}
		while (choice < 0 || choice > 2) {
			System.out.println("What would you like to do ?");
			System.out.println("0: Use a item.");
			//Displays list of inventory to use
			System.out.println("1: Rest a crew member (Reduce tiredness)");
			//Display crew member status and select one
			System.out.println("2: Cancel");
			//Cancel back to main inventory
			System.out.println("Please enter an integer between 0 and 3 inclusive: ");
			line = input.nextLine();
			if (hasInteger(line, 1)) {
				choice = extractInt(line, 1)[0];
			}
		}
		
		switch (choice) {
		case 0:
			//TODO
		case 1:
			//TODO
		case 2:
			return;
		}
	}
	
	/**
	 * Allows the player to purchase items at the outpost
	 * @param input the Scanner shared between methods
	 * @return true if the player has cancelled out of buying;
	 * 		   false otherwise
	 */
	private static boolean purchaseItem(Scanner input) {
		int size = outpost.getStock().size();
		Consumable item;
		int i = 0;
		System.out.println(String.format("What would you like to purchase? (Credits: %d)", Ship.getMoney()));
		for (i = 0; i < size; i++) {
			item = outpost.getStock().get(i);
			System.out.println(String.format("%d:\tName: %-30sCost: %-8dType: %-20s\tEffectivness: %-8dHeld: %d", i, item.getName(), item.getPrice(), item.getItemType(), item.getEffectiveness(), item.getHeld()));
		}
		System.out.println(String.format("%d: Cancel", i));
		int choice = 0;
		String line = input.nextLine();
		if (hasInteger(line, 1)) {
			choice = extractInt(line, 1)[0];
		}
		while (choice < 0 || choice > size) {
			System.out.println(String.format("Please select one of the below options (Credits: %d)", Ship.getMoney()));
			for (i = 0; i < size; i++) {
				item = outpost.getStock().get(i);
				System.out.println(String.format("%d:\tName: %-30sCost: %-8dType: %-20s\tEffectivness: %-8dHeld: %d", i, item.getName(), item.getPrice(), item.getItemType(), item.getEffectiveness(), item.getHeld()));
			}
			System.out.println(String.format("%d: Cancel", i));
			line = input.nextLine();
			if (hasInteger(line, 1)) {
				choice = extractInt(line, 1)[0];
			}
		}
		if (choice == size) {
			return true;
		}
		item = outpost.getStock().get(choice);
		System.out.println(String.format("How many %s would you like to buy?\n(Price: %d Effectivness: %d Held: %d)", item.getName(), item.getPrice(), item.getEffectiveness(), item.getHeld()));
		choice = 0;
		line = input.nextLine();
		if (hasInteger(line, 1)) {
			choice = extractInt(line, 1)[0];
		}
		while (choice < 0) {
			System.out.println("Please select a positive integer (Enter 0 to return to item selection)");
			line = input.nextLine();
			if (hasInteger(line, 1)) {
				choice = extractInt(line, 1)[0];
			}
		}
		if (choice == 0) {
			System.out.println("Did not buy anything");
		} else {
			if (Ship.getMoney() >= item.getPrice()) {
				System.out.println(String.format("Purchased %d %s", choice, item.getName()));
				item.increaseHeld(choice);
			} else {
				System.out.println(String.format("Cannot afford to buy this many %s", item.getName().toLowerCase()));
			}
		}
		return false;
	}
	
	/**
	 * Player selected action: Lets the player travel to the outpost and buy items
	 * @param input the Scanner shared between methods
	 */
	private static void enterOutpost(Scanner input) {
		System.out.println(String.format("Welcome to %s", outpost.getName()));
		boolean done = false;
		while(!done) {
			done = purchaseItem(input);
			System.out.println();
		}
		System.out.println("Leaving the outpost");
		System.out.println();
	}
	
	/**
	 * Player selected action: Lets the player transition to a new day
	 * @param input
	 */
	private static void transitionDay(Scanner input) {
		ArrayList<CrewMember> deadCrew;
		deadCrew = GameState.transitionDay();
		RandomEventOutput event;
		System.out.println(String.format("Advanced to day %d", GameState.getCurrentDay()));
		if (GameState.getCurrentDay() >= GameState.getEndDay()) {
			finished = true;
			ending = PossibleEndings.OUT_OF_TIME;
		} else {
			if (deadCrew.size() > 1) {
				System.out.println("Due to the effects of the space plague, the following crew members have died:");
				for (CrewMember member: deadCrew) {
					System.out.println(member.getName());
				}
			} else if (deadCrew.size() == 1) {
				System.out.println(String.format("Due to the effects of the space plague, %s has died", deadCrew.get(0).getName()));
			}
			event = RandomEvent.activateRandomEvent();
			switch (event.event) {
			case ASTEROID_BELT:
				System.out.println("Whilst floating through space, your ship encountered a comically unrealistic asteroid belt.");
				System.out.println("Impact with an asteroid caused your ship to sustain 50% damage to its shields.");
				if (Ship.getShields() <= 0) {
					finished = true;
					ending = PossibleEndings.SHIP_DESTROYED;
				} else {
					System.out.println(String.format("The %s's shields are now at %d", Ship.getName(), Ship.getShields()));
				}
				break;
			case NOTHING:
				break;
			case SPACE_PIRATES:
				System.out.println("Whilst floating in space, a space pirate managed to sneak onboard your ship.");
				if (event.member == null) {
					System.out.println("They managed to make off with two items before being found");
				} else {
					if (event.member.getMemberClass() == CrewClass.GUARD) {
						System.out.println(String.format("Fortunately, your %s %s managed to find them, and dispatched of them before they could steal anything", CrewClass.GUARD.getClassName().toLowerCase(),event.member.getName()));
					} else {
						System.out.println(String.format("Fortunately %s managed to find them, but the pirate managed to make off with an item", event.member.getName()));
					}
				}
				break;
			case SPACE_PLAGUE:
				System.out.println(String.format("Whilst floating in space, %s contracted the space plague. This will decrease their health by 25 each day.", event.member.getName()));
				System.out.println("In order to cure them, head to the outpost and buy a cure");
				break;		
			}
		}
	}
	
	/**
	 * Player selected action: Lets the player search a planet for supplies
	 * @param input
	 */
	private static void searchPlanet(Scanner input) {
		//TODO Add ability to cancel action
		ArrayList<CrewMember> crew = new ArrayList<CrewMember>();
		for (CrewMember member : Ship.getShipCrew()) {
			if (member.getActionPoints() > 0) {
				crew.add(member);
			}
		}
		System.out.println("Please select a crew member to search the planet:");
		for (int i = 0; i < crew.size(); i++) {
			CrewMember member = crew.get(i);
			System.out.println(String.format("%d:\tName: %s\tClass: %s\tAction points remaining: %d", i, member.getName(), member.getMemberClass().getClassName(),member.getActionPoints()));
		}
		int choice = 0;
		String line = input.nextLine();
		if (hasInteger(line, 1)) {
			choice = extractInt(line, 1)[0];
		}
		while (choice < 0 || choice >= crew.size()) {
			System.out.println("Please select a crew member to search the planet:");
			for (int i = 0; i < crew.size(); i++) {
				CrewMember member = crew.get(i);
				System.out.println(String.format("%d:\tName: %s\tClass: %s\tAction points remaining: %d", i, member.getName(), member.getMemberClass().getClassName(),member.getActionPoints()));
			}
			System.out.println(String.format("Please enter an integer between 0 and %d inclusive: ", crew.size() - 1));
			line = input.nextLine();
			if (hasInteger(line, 1)) {
				choice = extractInt(line, 1)[0];
			}
		}
		CrewMember searcher = crew.get(choice);
		PlanetSearchOutput result = searcher.searchPlanet(Ship.getOrbiting());
		switch(result.FOUND) {
		case ITEM:
			System.out.println(String.format("%s managed to find a %s", searcher.getName(), result.ITEM.getName()));
			System.out.println(String.format("The number of %s held is now %d", result.ITEM.getName(), result.ITEM.getHeld()));
			break;
		case MONEY:
			System.out.println(String.format("%s managed to find a %d credits", searcher.getName(),result.MONEY));
			System.out.println(String.format("Total credits: %d", Ship.getMoney()));
			break;
		case NOTHING:
			System.out.println(String.format("%s attempted to find something of use, however they could not find anything due to their own inexperience", searcher.getName()));
			break;
		case PART:
			System.out.println(String.format("%s managed to find a part for the %s's hyperdrive! Note: You can no longer find hyperdrive parts on %s", searcher.getName(), Ship.getName(), Ship.getOrbiting().getName()));
			if (GameState.getPartsNeeded() == GameState.getPartsFound()) {
				finished = true;
				ending = PossibleEndings.VICTORY;
			} else {
				System.out.println(String.format("Parts left to find: %d", GameState.getPartsNeeded() - GameState.getPartsFound()));
			}
			break;
		}
		
	}
	
	/**
	 * Gets the action that the player wishes to perform during normal game play
	 * @param input the Scanner shared between methods
	 */
	private static void selectAction(Scanner input) {
		int maxChoice;
		if (Ship.getOrbiting() == null) {
			System.out.println("Currently in a heliocentric orbit");
			maxChoice = 4;
		} else {
			System.out.println(String.format("Currently orbiting the planet %s", Ship.getOrbiting().getName()));
			maxChoice = 5;
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
			viewStatus(input);
			break;
		case 2:
			enterOutpost(input);
			break;
		case 3:
			transitionDay(input);
			break;
		case 4:
			if (maxChoice == 5) {
			searchPlanet(input);
			break;
			}
		case 5:
			finished = true;
			ending = PossibleEndings.QUIT;
			break;
		}
	}
	
	/**
	 * Ends the game
	 */
	private static void invokeEnding() {
		switch (ending) {
		case CREW_DEAD:
			System.out.println(String.format("With the death of all the %s's crew, she is left floating through space, a desolate reminder of the perils of space travel...", Ship.getName()));
			System.out.println("GAMEOVER");
			break;
		case LOST_IN_SPACE:
			CrewMember lastCrew = Ship.getShipCrew().get(0);
			System.out.println(String.format("With the death of all the %s's crew but %s, %s's future is uncertain, whether they will be rescued by another ship, starve to death, or choke and freeze as the ships life support system fails...", Ship.getName(), lastCrew.getName(), lastCrew.getName()));
			System.out.println("GAMEOVER");
			break;
		case OUT_OF_TIME:
			System.out.println(String.format("With the crew unable to repair her Alcubierre drive in time, the %s's negative mass generator failed, causing her and her crew to vanish, never to be seen again...", Ship.getName()));
			System.out.println("GAMEOVER");
			break;
		case QUIT:
			System.out.println(String.format("In the face of impossible odds, the %s's crew promptly gave up, and decided it would be easier to jump out of the airlock into the vacuum of space", Ship.getName()));
			break;
		case SHIP_DESTROYED:
			System.out.println(String.format("With the failure of the %s's shields system, her structural integrity failed, causing the ship to break up and expose her entire crew to the harsh void of space, killing all of them...", Ship.getName()));
			System.out.println("GAMEOVER");
			break;
		case VICTORY:
			System.out.println(String.format("With the finding of the last part of her Alcubierre drive, the %s's crew were able to perform a patchwork fix, letting her crew escape back to more civilised systems where their ship could undergo permanent repairs", Ship.getName()));
			System.out.println("A WINNER IS YOU");
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
		invokeEnding();
		input.close();
		System.out.println("Closing game");
		System.exit(0);
	}
}
