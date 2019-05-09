package backendTesting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import backend.*;

class FoodItemTest {

	@Test
	void testUse() {
		boolean var;
		CrewMember alice = new CrewMember("Alice", CrewClass.COOK);
		CrewMember bob = new CrewMember("Bob", CrewClass.MEDIC);
		FoodItem basic = new FoodItem("Flour", 50, 25);
		FoodItem advanced = new FoodItem("Bread", 100, 75);
		FoodItem ok = new FoodItem("Lamington", 1, 10000);
		basic.increaseHeld(20);
		advanced.increaseHeld(20);
		ok.increaseHeld(20);
		assertEquals(alice.getHunger(), 0);
		assertEquals(bob.getHunger(), 0);
		alice.increaseHunger(75);
		var = basic.use(alice);
		assertTrue(var);
		assertEquals(alice.getHunger(), 50);
		assertEquals(basic.getHeld(), 19);
		var = advanced.use(alice);
		assertTrue(var);
		assertEquals(alice.getHunger(), 0);
		assertEquals(advanced.getHeld(), 19);
		var = ok.use(bob);
		assertFalse(var);
		assertEquals(bob.getHunger(), 0);
		assertEquals(ok.getHeld(), 20);
		bob.increaseHunger(10000);
		assertEquals(bob.getHunger(), 100);
		var = ok.use(bob);
		assertTrue(var);
		assertEquals(bob.getHunger(), 0);
		assertEquals(ok.getHeld(), 19);
	}

}
