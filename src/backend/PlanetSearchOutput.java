package backend;

/***
 * Contains information regarding the item found when searching a planet
 * Used by CrewMember when searchPlanet() is called
 * @author hoo42
 * @author rvo16
 */
public class PlanetSearchOutput {
	/**
	 * What was found on the planet (i.e. a consumable, credits, a part, or nothing)
	 */
	public final PlanetFindableObjects FOUND;
	/**
	 * The consumable found on the planet.
	 * null if a consumable was not found
	 */
	public final Consumable ITEM;
	/**
	 * The number of credits found on the planet.
	 * null if no credits were found
	 */
	public final int MONEY;
	
	/**
	 * 
	 * @param f the type of item found
	 * @param c the consumable found
	 * @param i the amount of money found
	 */
	PlanetSearchOutput(PlanetFindableObjects f, Consumable c, int i) {
		this.FOUND = f;
		this.ITEM = c;
		this.MONEY = i;
	}

}
