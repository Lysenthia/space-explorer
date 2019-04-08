package backend;
import java.util.Random;
import java.util.ArrayList;

public class RandomEvent {
	
	public static void asteroidBelt() {
		Ship.damageShields(50);
	}
	
	public static CrewMember spacePlague(Random rng) {
		ArrayList<CrewMember> crew = Ship.getShipCrew();
		int index = rng.nextInt(crew.size());
		CrewMember infectee = crew.get(index);
		infectee.giveSpacePlague();
		return infectee;
	}
	
	public static CrewMember spacePirate(Random rng) {
		boolean spotted = rng.nextBoolean();
		ArrayList<Consumable> inventory = Ship.getInventory();
		if (spotted) {
			ArrayList<CrewMember> crew = Ship.getShipCrew();
			int index = rng.nextInt(crew.size());
			CrewMember spotter = crew.get(index);
			if (spotter.getMemberClass() != CrewClass.GUARD) {
				if (inventory.size() >= 1) {
					int stolen = rng.nextInt(inventory.size());
					inventory.get(stolen).decreaseHeld(1);
				}
			}
			return spotter;
		} else {
			if (inventory.size() >= 1) {
				int stolen = rng.nextInt(inventory.size());
				inventory.get(stolen).decreaseHeld(1);
			}
			if (inventory.size() >= 1) {
				int stolen = rng.nextInt(inventory.size());
				inventory.get(stolen).decreaseHeld(1);
			}
			return null;
		}
	}
	
	public static RandomEventOutput activateRandomEvent() {
		CrewMember member = null;
		int event = 0;
		Random rng = new Random();
		if (rng.nextBoolean()) {
			event = rng.nextInt(3);
			switch (event) {
				case 0:
					asteroidBelt();
					break;
				case 1:
					member = spacePlague(rng);
					break;
				case 2:
					member = spacePirate(rng);
					break;
			}
		}
		RandomEventOutput output = new RandomEventOutput(event, member);
		return output;
	}

}
