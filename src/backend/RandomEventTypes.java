package backend;

import java.util.Random;
/**
 * Contains the possible random events that may occur on a day transition
 * @author hoo42
 * @author rvo16
 */
public enum RandomEventTypes {
	ASTEROID_BELT,
	SPACE_PLAGUE,
	SPACE_PIRATES,
	NOTHING;
	
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
