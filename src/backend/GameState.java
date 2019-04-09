package backend;

public class GameState {
	private static int currentDay;
	private static int endDay;
	private static int partsFound;
	private static int partsNeeded;
	
	private GameState() {}
	
	public static void setEndDay(int day) {
		endDay = day;
		partsNeeded = day/3;
	}
	
	public static int getEndDay() {
		return endDay;
	}
	
	public static int getCurrentDay() {
		return currentDay;
	}
	
	public static int getPartsNeeded() {
		return partsNeeded;
	}
	
	public static int getPartsFound() {
		return partsFound;
	}

	public static void transitionDay() {
		//TODO
	}
	
}
