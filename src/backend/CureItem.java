package backend;
import java.util.Random;

public class CureItem extends Consumable {

	public CureItem(String name, int price, int effectivness) {
		super(name, price, effectivness);
	}

	@Override
	public boolean use(CrewMember user) {
		this.decreaseHeld(1);
		if (this.effectivness < 100) {
			int roll;
			Random rng = new Random();
			roll = rng.nextInt(100);
			if (this.effectivness > roll) {
				user.removeSpacePlague();
				return true;
			} else {
				return false;
			}
		} else {
			user.removeSpacePlague();
			return true;
		}
	}

}
