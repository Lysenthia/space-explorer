package backendTesting;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import backend.*;


class CrewMemberTest {
	
	private CrewMember alice;

	@BeforeEach
	void setUp() throws Exception {
		alice = new CrewMember("Alice", CrewClass.SCOUT);
	}

	@Test
	void testGetName() {
		assertEquals(alice.getName(), "Alice");
	}

	@Test
	void testGetCrewClass() {
		assertEquals(alice.getMemberClass(), CrewClass.SCOUT);
	}

	@Test
	void testGetActionPoints() {
		assertEquals(alice.getActionPoints(), 2);
		alice.repairShields();
		assertEquals(alice.getActionPoints(), 1);
	}

	@Test
	void testGetHunger() {
		assertEquals(alice.getHunger(), 0);
	}

	@Test
	void testGetHealth() {
		assertEquals(alice.getHealth(), 100);
	}

	@Test
	void testSetHealth() {
		alice.setHealth(35);
		assertEquals(alice.getHealth(), 35);
	}

	@Test
	void testIncreaseHealth() {
		alice.increaseHealth(20);
		assertEquals(alice.getHealth(), 100);
		alice.setHealth(35);
		assertEquals(alice.getHealth(), 35);
		alice.increaseHealth(50);
		assertEquals(alice.getHealth(), 85);
	}

	@Test
	void testDecreaseHealth() {
		boolean var;
		var = alice.decreaseHealth(20);
		assertEquals(var, false);
		assertEquals(alice.getHealth(), 80);
		var = alice.decreaseHealth(90);
		assertEquals(var, true);
	}

	@Test
	void testDecreaseHunger() {
		alice.increaseHunger(70);
		assertEquals(alice.getHunger(), 70);
		alice.decreaseHunger(30);
		assertEquals(alice.getHunger(), 40);
		alice.decreaseHunger(100);
		assertEquals(alice.getHunger(), 0);
	}

	@Test
	void testIncreaseHunger() {
		alice.increaseHunger(30);
		assertEquals(alice.getHunger(), 30);
		alice.increaseHunger(100);
		assertEquals(alice.getHunger(), 100);
	}

	@Test
	void testGiveSpacePlague() {
		alice.giveSpacePlague();
		assertEquals(alice.hasSpacePlague(), true);
	}

	@Test
	void testRemoveSpacePlague() {
		alice.giveSpacePlague();
		assertEquals(alice.hasSpacePlague(), true);
		alice.removeSpacePlague();
		assertEquals(alice.hasSpacePlague(), false);
	}
	
	@Test
	void testHasSpacePlague() {
		assertEquals(alice.hasSpacePlague(), false);
	}

	@Test
	void testSleep() {
		alice.addTiredness(150);
		assertEquals(alice.getTiredness(), 100);
		alice.sleep();
		assertEquals(alice.getTiredness(), 0);
	}

	@Test
	void testRepairShields() {
		CrewMember bob = new CrewMember("Bob", CrewClass.ENGINEER);
		Ship.damageShields(10);
		alice.repairShields();
		assertEquals(Ship.getShields(), 100);
		Ship.damageShields(75);
		assertEquals(Ship.getShields(), 25);
		alice.repairShields();
		assertEquals(Ship.getShields(), 50);
		bob.repairShields();
		assertEquals(Ship.getShields(), 100);
	}

	@Test
	void testSearchPlanet() {
		fail("Not yet implemented");
	}

}
