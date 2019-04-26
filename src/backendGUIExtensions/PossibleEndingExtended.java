package backendGUIExtensions;

public enum PossibleEndingExtended {
	/**
	 * Player does not find all parts in time
	 */
	OUT_OF_TIME(""),
	/**
	 * All crew members are killed
	 */
	CREW_DEAD(""),
	/**
	 * Ship runs out of shields
	 */
	SHIP_DESTROYED(""),
	/**
	 * Only one crew member left
	 */
	LOST_IN_SPACE(""),
	/**
	 * Victory
	 */
	VICTORY(""),
	/**
	 * Player quit game
	 */
	QUIT("");
	
	/**
	 * The splash of the ending
	 */
	private final String image;
	
	/**
	 * Constructs a possible ending with a splash to be displayed
	 * @param image the path to the splash to be displayed
	 */
	private PossibleEndingExtended(String image) {
		this.image = image;
	}
	
	/**
	 * Returns the path to the splash of the ending
	 * @return the path to the splash of the ending
	 */
	public String getImage() {
		return this.image;
	}
}
