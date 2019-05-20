package backend;

/**
 * Determines the final score at the end of the game
 * @author hoo42
 * @author rvo16
 */
public final class Score {
	
	/**
	 * Intended to throw an error if someone attempts to make an instance of this class
	 */
	private Score() {}
	
	/**
	 * Returns the score the player gets at the end of the game
	 * @return
	 */
	public static int getScore() {
		int score = 0;
		score += GameState.getPartsFound() * 30;
		score -= (Ship.getInitialCrewSize() - Ship.getShipCrew().size()) * 50;
		score = score * (10 / Ship.getInitialCrewSize());
		return 0;
	}
}
