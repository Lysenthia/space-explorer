package backend;

public class FoodItem extends Consumable {

	public FoodItem(String name, int price, int effectivness) {
		super(name, price, effectivness);
	}

	@Override
	public boolean use(CrewMember user) {
		if (user.getHunger() < 100) {
			user.decreaseHunger(effectivness);
			this.decreaseHeld(1);
			return true;
		} else {
			return false;
		}
		
	}

}
