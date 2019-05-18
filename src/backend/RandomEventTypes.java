package backend;

import java.util.Random;
/**
 * Contains the possible random events that may occur on a day transition
 * @author hoo42
 * @author rvo16
 */
public enum RandomEventTypes {
	
	/**
	 * Ship flies through a comically dense asteroid belt
	 */
	ASTEROID_BELT,
	
	/**
	 * A member of the crew catches the space plague
	 */
	SPACE_PLAGUE,
	
	/**
	 * A space pirate sneaks aboard, stealing items from the ship
	 */
	SPACE_PIRATES,
	
	/**
	 * Nothing happens
	 */
	NOTHING;
	
	/**
	 * Randomly selects an event to occur
	 * @return the random event that occurred
	 */
	public static RandomEventTypes getEvent() {
		Random rng = new Random();
		switch (rng.nextInt(5)) {
		case 0:
			return ASTEROID_BELT;
		case 1:
			return SPACE_PLAGUE;
		case 2:
			return SPACE_PIRATES;
		default:
			return NOTHING;
		}

	}
}
