package backend;
import java.util.Random;

public class CureItem extends Consumable {
	
	/**
	 * Creates an instance of the cureItem class with the name, price and effectiveness
	 * @param name sets the name of the cureItem
	 * @param price sets the price of the cureItem
	 * @param effectivness sets the effectiveness of the cureItem
	 */
	public CureItem(String name, int price, int effectivness) {
		super(name, price, effectivness);
	}
	
	/**
	 * Returns true if held item is being used and removes space plague
	 * 			false otherwise
	 */
	@Override
	public boolean use(CrewMember user) {
		if (user.hasSpacePlague()) {
			this.decreaseHeld(1);
			if (this.effectivness < 100) {
				int roll;
				Random rng = new Random();
				roll = rng.nextInt(100);
				if (this.effectivness > roll) {
					user.removeSpacePlague();
				}
			} else {
				user.removeSpacePlague();
			}
			return true;
		} else {
		return false;
		}
	}

}
