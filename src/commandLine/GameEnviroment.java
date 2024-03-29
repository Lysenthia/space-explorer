package commandLine;
import java.util.Scanner;
import backend.*;
import java.util.ArrayList;

/**
 * Allows the game to be played through a command line
 * @author hoo42
 * @author rvo16
 */
public class GameEnviroment {
	
	/**
	 * The possible crew members that the player can select at the start of the game
	 */
	private static ArrayList<CrewMember> possibleCrew = new ArrayList<CrewMember>();

	/**
	 * True if the game has reached an ending; false otherwise
	 */
	private static boolean finished = false;
	
	/**
	 * Checks if a string contains a given number of integers
	 * @param s the string to check for integers
	 * @param size the number of integers to check for
	 * @return true if the string contains the correct number of integers;
	 * 		   false otherwise
	 */
	private static boolean hasInteger(String s, int size) {
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
	private static int[] extractInt(String s, int size) {
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
	 * Prints a given array list to the console
	 * @param list the array list to print
	 */
	private static void printList(ArrayList<?> list) {
		for (Object object : list) {
			System.out.println(object);
		}
	}
	
	/**
	 * Lets the player select an option based on the given parameters
	 * @param input the Scanner shared between methods
	 * @param messages the list of methods telling the player their options
	 * @param lowerBound the lower bound that the chosen option must be greater than
	 * @param upperBound the upper bound that the chosen option must be less than
	 * @return the selected option
	 */
	private static int choose(Scanner input, ArrayList<String> messages, int lowerBound, int upperBound) {
		int choice = 0;
		String line = input.nextLine();
		if (hasInteger(line, 1)) {
			choice = extractInt(line, 1)[0];
		}
		while (choice < lowerBound || choice > upperBound) {
			printList(messages);
			line = input.nextLine();
			if (hasInteger(line, 1)) {
				choice = extractInt(line, 1)[0];
			}
		}
		return choice;
	}
	
	/**
	 * Lets the player select an option based on the given parameters
	 * @param input the Scanner shared between methods
	 * @param messages the list of methods telling the player their options
	 * @param bound the bound that the chosen option must be greater or less than depending on isLowerBound
	 * @param isLowerBound true if the given bound is used as a lower bound; false otherwise
	 * @return the selected option
	 */
	private static int choose(Scanner input, ArrayList<String> messages, int bound, boolean isLowerBound) {
		int choice = 0;
		String line = input.nextLine();
		if (hasInteger(line, 1)) {
			choice = extractInt(line, 1)[0];
		}
		while  ((choice < bound && isLowerBound) || (choice > bound && !isLowerBound)) {
			printList(messages);
			line = input.nextLine();
			if (hasInteger(line, 1)) {
				choice = extractInt(line, 1)[0];
			}
		}
		return choice;
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
		Outpost.setOutpost("Outpost 9", 1);
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
		GameState.setPlanets(planets);
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
		ArrayList<String> messages = new ArrayList<String>();
		messages.add("\nHow many days would you like to play for? (between 3 and 10 days): ");
		printList(messages);
		messages.add("Please enter an integer between 3 and 10 inclusive: ");
		int days = choose(input, messages, 3, 10);
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
	 * Returns an ArrayList of possible actions for the player
	 * @return an ArrayList of possible actions for the player
	 */
	private static ArrayList<String> getActions() {
		ArrayList<String> actions = new ArrayList<String>();
		actions.add("What would you like to do? ");
		actions.add("0: Go to a new planet");
		actions.add("1: Check on your crew and access your inventory");
		actions.add(String.format("2: Go to %s", Outpost.getName()));
		actions.add("3: Transition to a new day");
		if (Ship.getOrbiting() != null) {
			actions.add(String.format("4: Search %s for supplies", Ship.getOrbiting().getName()));
			actions.add("5: Quit game");
		} else {
			actions.add("4: Quit game");
		}
		return actions;
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
	 * Generates an array list of messages about planets
	 * @param planets the planets to generate messages for
	 */
	private static ArrayList<String> getFormattedPlanets(ArrayList<Planet> planets) {
		ArrayList<String> formattedPlanets = new ArrayList<String>();
		for (int i = 0; i < planets.size(); i++) {
			Planet cur = planets.get(i);
			formattedPlanets.add(String.format("%d:\tName: %s\tPart Found: %b", i, cur.getName(), cur.getPartFound()));
		}
		return formattedPlanets;
	}
	
	/**
	 * Lets the player select a planet to travel to
	 * @param input the Scanner shared between methods
	 * @return the Planet selected by the player
	 */
	private static Planet selectPlanet(Scanner input) {
		ArrayList<Planet> possibleDestinations = new ArrayList<Planet>(GameState.getPlanets());
		ArrayList<String> messages = new ArrayList<String>();
		possibleDestinations.remove(Ship.getOrbiting());
		messages.add("Please select a planet to travel to:");
		messages.addAll(getFormattedPlanets(possibleDestinations));
		messages.add(String.format("%d: Cancel", possibleDestinations.size()));
		printList(messages);
		System.out.println(String.format("Please enter an integer between 0 and %d inclusive: ", possibleDestinations.size()));
		int choice = choose(input, messages, 0, possibleDestinations.size());
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
		ArrayList<CrewMember> possiblePilots = Ship.getReadyCrew();
		ArrayList<CrewMember> pilots;
		Planet destination;
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
		ArrayList<Consumable> inventory = Ship.getInventory();
		ArrayList<CrewMember> crew = Ship.getShipCrew();
		ArrayList<String> messages = new ArrayList<String>();
		int i = 0;
		if (inventory.size() == 0) {
			System.out.println("\nYour inventory is empty.");
		}
		else {
			messages.add("Please select an item to use: \n");
			messages.add("======INVENTORY======");
			for (i = 0; i < inventory.size(); i++) {
				Consumable item = inventory.get(i);
				messages.add(String.format("%d:\tName: %-30sCost: %-8dType: %-20s\tEffectivness: %-8dHeld: %d", i, item.getName(), item.getPrice(), item.getItemType(), item.getEffectiveness(), item.getHeld()));
			}
			messages.add(String.format("%d: Cancel", inventory.size()));
			printList(messages);
			messages.add(String.format("Please enter an integer between 0 and %d inclusive: ", inventory.size()));
			int choice = choose(input, messages, 0, inventory.size());
			if (choice == inventory.size()) {
				return;
			} else {
				messages.clear();
				Consumable item = inventory.get(choice);
				messages.add("Please select a crew member to use the item on: ");
				for (i = 0; i < crew.size(); i++) {
					CrewMember member = crew.get(i);
					messages.add(String.format("%s: Crew Member: %-15s Tiredness: %-5d Action points: %-5s", i, member.getName(), member.getTiredness(), member.getActionPoints()));
				}
				printList(messages);
				messages.add(String.format("Please enter an integer between 0 and %d inclusive: ", crew.size()));
				choice = choose(input, messages, 0, crew.size());
				CrewMember member = crew.get(choice);
				boolean used = item.use(member);
				if (used) {
					System.out.println(String.format("Crew member %s has used item %s", member.getName(), item.getName()));
				}
				else {
					System.out.println(String.format("Crew member %s has no need to use this item", member.getName()));
				}

			}
		}
	}
	
	/**
	 * Player selected action: Lets the player rest a selected crew member
	 * @param input the Scanner shared between methods
	 */
	private static void restCrewMember(Scanner input) {
		ArrayList<CrewMember> crew = Ship.getShipCrew();
		ArrayList<String> messages = new ArrayList<String>();
		messages.add("\nWho would you like to rest? ");
		for (int i = 0; i < crew.size(); i++) {
			CrewMember member = crew.get(i);
			messages.add(String.format("%s: Crew Member: %-15s Tiredness: %-5d Action points: %-5s", i, member.getName(), member.getTiredness(), member.getActionPoints()));
		}
		messages.add(String.format("%d: Cancel", crew.size()));
		printList(messages);
		int choice = choose(input, messages, 0, crew.size());
		if (choice == crew.size()) {
			return;
		} else {
			CrewMember member = crew.get(choice);
			if (member.getActionPoints() > 0 && member.getActionPoints() <=2 ) {
				member.sleep();
				System.out.println(String.format("Crew member %s feels rested and is now less tired", member.getName()));	
		}
			else {
				System.out.println(String.format("Crew member %s does not have enough action points to do this.", member.getName()));
			}
		}
		
	}
	
	/**
	 * Player action: Lets the player select a crew member and repair shields
	 * @param input the Scanner shared between methods
	 */
	private static void repairShields(Scanner input) {
		ArrayList<CrewMember> crew = Ship.getShipCrew();
		ArrayList<String> messages = new ArrayList<String>();
		messages.add("\nPlease select crew member to repair shields? ");
		messages.add(String.format("Ship Shields: %d", Ship.getShields()));
		for (int i = 0; i < crew.size(); i++) {
			CrewMember member = crew.get(i);
			messages.add(String.format("%s: Crew Member: %-15s Action points: %-5d", i, member.getName(), member.getActionPoints()));
		}
		messages.add(String.format("%d: Cancel", crew.size()));
		printList(messages);
		int choice = choose(input, messages, 0, crew.size());
		if (choice == crew.size()) {
			return;
		} else {
			CrewMember member = crew.get(choice);
			if (member.getActionPoints() > 0 && member.getActionPoints() <=2  && Ship.getShields() < 100) {
				member.repairShields();
				System.out.println(String.format("Crew member %s has repaired the ships shields", member.getName()));	
		}
			else {
				System.out.println(String.format("Crew member %s does not need to do this.", member.getName()));
			}
		}
		
	}
	
	
	
	/**
	 * Player selected action: Lets the player view their crew and inventory
	 * @param input the Scanner shared between methods
	 */
	private static void viewStatus(Scanner input) {
		ArrayList<String> messages = new ArrayList<String>();
		System.out.println();
		for (CrewMember member : Ship.getShipCrew()) {
			System.out.println(String.format("Crew Member: %-15s Health: %-5d Action points: %-5s Hunger Level: %-5s Tiredness Level: %-5s Space Plague Status: %s",
					member.getName(), member.getHealth(), member.getActionPoints(), member.getHunger(), member.getTiredness(), member.hasSpacePlague()));
		}
		System.out.println(String.format("Credits: %d", Ship.getMoney()));
		messages.add("\nWhat would you like to do? ");
		messages.add("0: Use a item.");
		messages.add("1: Rest a crew member (Reduce tiredness)");
		messages.add("2: Repair Shields");
		messages.add("3: Cancel");
		printList(messages);
		messages.add("Please enter an integer between 0 and 2 inclusive: ");
		int choice = choose(input, messages, 0, 2);
		switch (choice) {
		case 0:
			useItem(input);
			break;
		case 1:
			restCrewMember(input);
			break;
		case 2:
			repairShields(input);
		case 3:
			break;
		}
		System.out.println();
	}
	
	/**
	 * Allows the player to purchase items at the outpost
	 * @param input the Scanner shared between methods
	 * @return true if the player has cancelled out of buying;
	 * 		   false otherwise
	 */
	private static boolean purchaseItem(Scanner input) {
		int size = GameState.getAllConsumable().size();
		ArrayList<String> messages = new ArrayList<String>();
		Consumable item;
		int i = 0;
		messages.add(String.format("What would you like to purchase? (Credits: %d)", Ship.getMoney()));
		for (i = 0; i < size; i++) {
			item = GameState.getAllConsumable().get(i);
			messages.add(String.format("%d:\tName: %-30sCost: %-8dType: %-20s\tEffectivness: %-8dHeld: %d", i, item.getName(), item.getPrice(), item.getItemType(), item.getEffectiveness(), item.getHeld()));
		}
		messages.add(String.format("%d: Cancel", i));
		printList(messages);
		messages.remove(0);
		messages.add(0, String.format("Please select one of the below options (Credits: %d)", Ship.getMoney()));
		int choice = choose(input, messages, 0, size);
		if (choice == size) {
			return true;
		}
		item = GameState.getAllConsumable().get(choice);
		messages.clear();
		messages.add(String.format("How many %s would you like to buy?\n(Price: %d Effectivness: %d Held: %d)", item.getName(), item.getPrice(), item.getEffectiveness(), item.getHeld()));
		printList(messages);
		messages.add(0, "Please select a positive integer (Enter 0 to return to item selection)");
		choice = choose(input, messages, 0, true);
		if (choice == 0) {
			System.out.println("Did not buy anything");
		} else {
			boolean purchased = Outpost.purchaseItem(item, choice);
			if (purchased) {
				System.out.println(String.format("Purchased %d %s", choice, item.getName()));
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
		System.out.println(String.format("Welcome to %s", Outpost.getName()));
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
			GameState.setEnding(PossibleEndings.OUT_OF_TIME);
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
					GameState.setEnding(PossibleEndings.SHIP_DESTROYED);
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
		ArrayList<CrewMember> crew = new ArrayList<CrewMember>();
		ArrayList<String> messages = new ArrayList<String>();
		for (CrewMember member : Ship.getShipCrew()) {
			if (member.getActionPoints() > 0) {
				crew.add(member);
			}
		}
		if (crew.size() > 0) {
			messages.add("Please select a crew member to search the planet:");
			for (int i = 0; i < crew.size(); i++) {
				CrewMember member = crew.get(i);
				messages.add(String.format("%d:\tName: %s\tClass: %s\tAction points remaining: %d", i, member.getName(), member.getMemberClass().getClassName(),member.getActionPoints()));
			}
			printList(messages);
			messages.add(String.format("Please enter an integer between 0 and %d inclusive: ", crew.size()));
			int choice = choose(input, messages, 0, crew.size());
			if (choice == crew.size()) {
				System.out.println();
				return;
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
					GameState.setEnding(PossibleEndings.VICTORY);
				} else {
					System.out.println(String.format("Parts left to find: %d", GameState.getPartsNeeded() - GameState.getPartsFound()));
				}
				break;
			}
		} else {
			System.out.println("No crew members can search the planet");
		}
		System.out.println();
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
		ArrayList<String> messages = getActions();
		printList(messages);
		messages.add(String.format("Please enter an integer between 0 and %d inclusive: ", maxChoice));
		int choice = choose(input, messages, 0, maxChoice);
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
			GameState.setEnding(PossibleEndings.QUIT);
			break;
		}
	}
	
	private static int getScore() {
		return Score.getScore();
	}
	
	/**
	 * Ends the game
	 */
	private static void invokeEnding() {
		int score = getScore();
		switch (GameState.getEnding()) {
		case CREW_DEAD:
			System.out.println(String.format("With the death of all the %s's crew, she is left floating through space, a desolate reminder of the perils of space travel...", Ship.getName()));
			System.out.println("GAMEOVER");
			System.out.println(String.format("Your final score was: %d", score));
			break;
		case LOST_IN_SPACE:
			CrewMember lastCrew = Ship.getShipCrew().get(0);
			System.out.println(String.format("With the death of all the %s's crew but %s, %s's future is uncertain, whether they will be rescued by another ship, starve to death, or choke and freeze as the ships life support system fails...", Ship.getName(), lastCrew.getName(), lastCrew.getName()));
			System.out.println("GAMEOVER");
			System.out.println(String.format("Your final score was: %d", score));
			break;
		case OUT_OF_TIME:
			System.out.println(String.format("With the crew unable to repair her Alcubierre drive in time, the %s's negative mass generator failed, causing her and her crew to vanish, never to be seen again...", Ship.getName()));
			System.out.println("GAMEOVER");
			System.out.println(String.format("Your final score was: %d", score));
			break;
		case QUIT:
			System.out.println(String.format("In the face of impossible odds, the %s's crew promptly gave up, and decided it would be easier to jump out of the airlock into the vacuum of space", Ship.getName()));
			break;
		case SHIP_DESTROYED:
			System.out.println(String.format("With the failure of the %s's shields system, her structural integrity failed, causing the ship to break up and expose her entire crew to the harsh void of space, killing all of them...", Ship.getName()));
			System.out.println("GAMEOVER");
			System.out.println(String.format("Your final score was: %d", score));
			break;
		case VICTORY:
			System.out.println(String.format("With the finding of the last part of her Alcubierre drive, the %s's crew were able to perform a patchwork fix, letting her crew escape back to more civilised systems where their ship could undergo permanent repairs", Ship.getName()));
			System.out.println("A WINNER IS YOU");
			System.out.println(String.format("Your final score was: %d", score));
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
