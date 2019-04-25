package backend;

public class MedicalItem extends Consumable {

	/**
	 * Creates and instance of the medical item with name, price and effectiveness
	 * @param name the name of the medical item
	 * @param price the price of the medical item
	 * @param effectivness the effectiveness of the medical item
	 */
	public MedicalItem(String name, int price, int effectiveness) {
		super(name, price, effectiveness, "Medical");
	}

	/**
	 * Returns true if medical item is being used and increases crew member health and decreases held item count
	 * 			false otherwise
	 */
	@Override
	public boolean use(CrewMember user) {
		if (user.getHealth() < 100) {
			user.increaseHealth(effectiveness);
			this.decreaseHeld(1);
			return true;
		} else {
			return false;
		}
	}

}
