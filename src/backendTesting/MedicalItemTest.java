package backendTesting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import backend.*;

/**
 * Junit tests for medical items
 * @author hoo42
 * @author rvo16
 *
 */
class MedicalItemTest {

	@Test
	void testUse() {
		CrewMember alice = new CrewMember("Alice", CrewClass.COOK);
		CrewMember bob = new CrewMember("Bob", CrewClass.MEDIC);
		MedicalItem basic = new MedicalItem("Potion", 50, 50);
		MedicalItem advanced = new MedicalItem("Elixir", 100, 75);
		MedicalItem ok = new MedicalItem("Overkill Heal", 1, 10000);
		boolean var;
		basic.increaseHeld(20);
		advanced.increaseHeld(20);
		ok.increaseHeld(20);
		assertEquals(alice.getHealth(), 100);
		assertEquals(bob.getHealth(), 100);
		alice.decreaseHealth(75);
		var = basic.use(alice);
		assertTrue(var);
		assertEquals(alice.getHealth(), 75);
		assertEquals(basic.getHeld(), 19);
		bob.decreaseHealth(10);
		var = advanced.use(bob);
		assertTrue(var);
		assertEquals(bob.getHealth(), 100);
		alice.increaseHealth(50);
		var = ok.use(alice);
		assertFalse(var);
		assertEquals(alice.getHealth(), 100);
		assertEquals(ok.getHeld(), 20);
		alice.decreaseHealth(1);
		var = ok.use(alice);
		assertTrue(var);
		assertEquals(alice.getHealth(), 100);
		assertEquals(ok.getHeld(), 19);
	}

}
