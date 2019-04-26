package backend;

/**
 * Allows the creation of food items for decreasing a crew members hunger.
 * Implements {@link Consumable}
 * @author hoo42
 * @author rvo16
 */
public class FoodItem extends Consumable {
	
	/**
	 * Creates an instance of the FoodItem class with name, price and effectiveness
	 * @param name the name of the food item
	 * @param price the price of the food item
	 * @param effectivness the effectiveness of the food item
	 */
	public FoodItem(String name, int price, int effectiveness) {
		super(name, price, effectiveness, "Food");
	}
	
	/**
	 * Returns true if food item is being used and decreases hunger and item held count
	 * 			false otherwise
	 */
	@Override
	public boolean use(CrewMember user) {
		if (user.getHunger() > 0) {
			user.decreaseHunger(effectiveness);
			this.decreaseHeld(1);
			return true;
		} else {
			return false;
		}
		
	}

}
