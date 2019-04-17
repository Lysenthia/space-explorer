package backend;

public class FoodItem extends Consumable {
	
	/**
	 * Creates an instance of the FoodItem class with name, price and effectiveness
	 * @param name the name of the food item
	 * @param price the price of the food item
	 * @param effectivness the effectiveness of the food item
	 */
	public FoodItem(String name, int price, int effectivness) {
		super(name, price, effectivness);
	}
	
	/**
	 * Returns true if food item is being used and decreases hunger and item held count
	 * 			false otherwise
	 */
	@Override
	public boolean use(CrewMember user) {
		if (user.getHunger() > 0) {
			user.decreaseHunger(effectivness);
			this.decreaseHeld(1);
			return true;
		} else {
			return false;
		}
		
	}

}
