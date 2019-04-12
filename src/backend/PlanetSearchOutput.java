package backend;

/***
 * Contains information regarding the item found when searching a planet
 * Used by CrewMember when searchPlanet() is called
 * @author hoo42
 *
 */
public class PlanetSearchOutput {
	public final PlanetFindableObjects FOUND;
	public final Consumable ITEM;
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
