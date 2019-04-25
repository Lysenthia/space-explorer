package backend;
import java.util.Random;

public class CureItem extends Consumable {
	
	/**
	 * Creates an instance of the cureItem class with the name, price and effectiveness
	 * @param name sets the name of the cureItem
	 * @param price sets the price of the cureItem
	 * @param effectivness sets the effectiveness of the cureItem
	 */
	public CureItem(String name, int price, int effectiveness) {
		super(name, price, effectiveness, "Space Plague Cure");
	}
	
	/**
	 * Returns true if held item is being used and removes space plague
	 * 			false otherwise
	 */
	@Override
	public boolean use(CrewMember user) {
		if (user.hasSpacePlague()) {
			this.decreaseHeld(1);
			if (this.effectiveness < 100) {
				int roll;
				Random rng = new Random();
				roll = rng.nextInt(100);
				if (this.effectiveness > roll) {
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
