package backendTesting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import backend.*;

class CureItemTest {

	@Test
	void testUse() {
		boolean var;
		CrewMember alice = new CrewMember("Alice", CrewClass.COOK);
		CrewMember bob = new CrewMember("Bob", CrewClass.MEDIC);
		//Effectiveness should never be less than 1, but for testing this is fine
		CureItem basic = new CureItem("Basic Cure", 50, 0);
		CureItem advanced = new CureItem("Advanced Cure", 100, 100);
		basic.increaseHeld(100);
		advanced.increaseHeld(2);
		alice.giveSpacePlague();
		bob.giveSpacePlague();
		assertEquals(basic.getHeld(), 100);
		assertEquals(advanced.getHeld(), 2);
		assertEquals(alice.hasSpacePlague(), true);
		assertEquals(bob.hasSpacePlague(), true);
		var = advanced.use(alice);
		assertEquals(var, true);
		assertEquals(alice.hasSpacePlague(), false);
		assertEquals(advanced.getHeld(), 1);
		var = advanced.use(alice);
		assertEquals(var, false);
		assertEquals(alice.hasSpacePlague(), false);
		assertEquals(advanced.getHeld(), 1);
		for (int i = 0; i < 100; i++) {
			var = basic.use(bob);
			assertEquals(var, true);
		}
		assertEquals(bob.hasSpacePlague(), true);
		assertEquals(basic.getHeld(), 0);
	}

}
