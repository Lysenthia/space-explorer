package backend;

/**
 * Potential endings that the player might reach
 * @author hoo42
 * @author rvo16
 */
public enum PossibleEndings {
	
	/**
	 * Player does not find all parts in time
	 */
	OUT_OF_TIME,
	
	/**
	 * All crew members are killed
	 */
	CREW_DEAD,
	
	/**
	 * Ship runs out of shields
	 */
	SHIP_DESTROYED,
	
	/**
	 * Only one crew member left
	 */
	LOST_IN_SPACE,
	
	/**
	 * Victory
	 */
	VICTORY,
	
	/**
	 * Player quit game
	 */
	QUIT;
}
