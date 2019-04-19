package backend;
import java.util.Random;
import java.util.ArrayList;

/**
 * Implements random events that occur when a day is transitioned
 * @author hoo42
 * @author rvo16
 *
 */
public class RandomEvent {
	
	/**
	 * Ship flies through a comically unrealistic asteroid belt, damaging the shields by 50%
	 */
	private static void asteroidBelt() {
		Ship.damageShields(50);
	}
	
	/**
	 * A crew member catches the space plague
	 * @param rng random number generator
	 * @return crew member that caught the space plague
	 */
	private static CrewMember spacePlague() {
		Random rng = new Random();
		ArrayList<CrewMember> crew = Ship.getShipCrew();
		int index = rng.nextInt(crew.size());
		CrewMember infectee = crew.get(index);
		infectee.giveSpacePlague();
		return infectee;
	}
	
	/**
	 * Space pirates sneak aboard the ship, stealing 2 items
	 * They have a 50% chance of being caught by a crew member, and if so only 1 item is stolen
	 * If the pirates are caught by a crew member of the GUARD archetype, no items are stolen
	 * @param rng random number generator
	 * @return crew member that caught the space pirates (null if they were not caught)
	 */
	private static CrewMember spacePirate() {
		Random rng = new Random();
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
	
	/**
	 * Has a 1/2 chance of activating one of the random events, with each event having a 1/3 chance of occurring
	 * if a random event occurs
	 * @return a special output type containing the crew member involved in the random event and the event that occurred 
	 */
	public static RandomEventOutput activateRandomEvent() {
		CrewMember member = null;
		RandomEventTypes event = RandomEventTypes.getEvent();
		switch (event) {
			case ASTEROID_BELT:
				asteroidBelt();
				break;
			case SPACE_PLAGUE:
				member = spacePlague();
				break;
			case SPACE_PIRATES:
				member = spacePirate();
				break;
			case NOTHING:
				break;
			}
		RandomEventOutput output = new RandomEventOutput(event, member);
		return output;
	}

}
