package backend;

public class MedicalItem extends Consumable {
	
	public MedicalItem(String name, int price, int effectivness) {
		super(name, price, effectivness);
	}

	@Override
	public boolean use(CrewMember user) {
		if (user.getHealth() < 100) {
			user.increaseHealth(effectivness);
			this.decreaseHeld(1);
			return true;
		} else {
			return false;
		}
	}

}
