package backendTesting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import backend.CrewClass;
import backend.CrewMember;
import backend.CureItem;
import backend.MedicalItem;
import backend.RandomEvent;
import backend.RandomEventOutput;
import backend.Ship;

class RandomEventTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testActivateRandomEvent() {
		CrewMember alice = new CrewMember("Alice", CrewClass.SCOUT);
		CrewMember bob = new CrewMember("Bob", CrewClass.GUARD);
		MedicalItem item = new MedicalItem("Item", 5, 5);
		CureItem item2 = new CureItem("Item2", 3, 4);
		RandomEventOutput event;
		for (int i = 0; i < 20; i++) {
			item.decreaseHeld(item.getHeld());
			item2.decreaseHeld(item2.getHeld());
			Ship.clearAll();
			Ship.addCrewMember(alice);
			Ship.addCrewMember(bob);
			item.increaseHeld(1);
			item2.increaseHeld(1);
			alice.removeSpacePlague();
			bob.removeSpacePlague();
			int shields = Ship.getShields();
			int inventorySize = Ship.getInventory().size();
			event = RandomEvent.activateRandomEvent();
			switch (event.event) {
			case ASTEROID_BELT:
				assertEquals(shields - 50, Ship.getShields());
				break;
			case NOTHING:
				assertEquals(shields, Ship.getShields());
				assertEquals(Ship.getInventory().size(), inventorySize);
				assertFalse(alice.hasSpacePlague());
				break;
			case SPACE_PIRATES:
				if (event.member == null) {
					assertEquals(Ship.getInventory().size(), inventorySize - 2);
				} else if (event.member.getMemberClass() == CrewClass.GUARD) {
					assertEquals(Ship.getInventory().size(), inventorySize);
				} else {
					assertFalse(event.member.getMemberClass() == CrewClass.GUARD);
					assertEquals(Ship.getInventory().size(), inventorySize - 1);
				}
				break;
			case SPACE_PLAGUE:
				assertTrue(event.member.hasSpacePlague());
				break;
			
			}
		}
	}

}
