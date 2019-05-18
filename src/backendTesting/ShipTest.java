package backendTesting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import backend.*;
import java.util.ArrayList;

/**
 * Junit tests for ship
 * @author hoo42
 * @author rvo16
 *
 */
class ShipTest {

	@BeforeEach
	void setUp() throws Exception {
		Ship.clearAll();
	}

	@Test
	void testGetShields() {
		assertEquals(Ship.getShields(), 100);
	}

	@Test
	void testGetMoney() {
		assertEquals(Ship.getMoney(), 0);
	}

	@Test
	void testAddMoney() {
		assertEquals(Ship.getMoney(), 0);
		Ship.addMoney(500);
		assertEquals(Ship.getMoney(), 500);
	}

	@Test
	void testDecreaseMoney() {
		assertEquals(Ship.getMoney(), 0);
		Ship.addMoney(500);
		assertEquals(Ship.getMoney(), 500);
		Ship.decreaseMoney(250);
		assertEquals(Ship.getMoney(), 250);
	}

	@Test
	void testGetShipCrew() {
		CrewMember alice = new CrewMember("Alice", CrewClass.SCOUT);
		CrewMember bob = new CrewMember("Bob", CrewClass.ENGINEER);
		boolean added;
		ArrayList<CrewMember> crew = new ArrayList<CrewMember>();
		crew.add(alice);
		added = Ship.addCrewMember(alice);
		assertEquals(Ship.getShipCrew(), crew);
		assertTrue(added);
		crew.add(bob);
		added = Ship.addCrewMember(bob);
		assertEquals(Ship.getShipCrew(), crew);
		assertTrue(added);
	}

	@Test
	void testAddCrewMember() {
		CrewMember alice = new CrewMember("Alice", CrewClass.SCOUT);
		ArrayList<CrewMember> crew = new ArrayList<CrewMember>();
		boolean added;
		crew.add(alice);
		added = Ship.addCrewMember(alice);
		assertEquals(Ship.getShipCrew(), crew);
		assertTrue(added);
		added = Ship.addCrewMember(alice);
		assertFalse(added);
	}

	@Test
	void testRemoveCrewMember() {
		CrewMember alice = new CrewMember("Alice", CrewClass.SCOUT);
		CrewMember bob = new CrewMember("Bob", CrewClass.ENGINEER);
		CrewMember charlie = new CrewMember("Charlie", CrewClass.GUARD);
		boolean removed;
		ArrayList<CrewMember> crew = new ArrayList<CrewMember>();
		crew.add(alice);
		Ship.addCrewMember(alice);
		assertEquals(Ship.getShipCrew(), crew);
		crew.add(bob);
		Ship.addCrewMember(bob);
		assertEquals(Ship.getShipCrew(), crew);
		crew.remove(bob);
		removed = Ship.removeCrewMember(bob);
		assertEquals(Ship.getShipCrew(), crew);
		assertTrue(removed);
		removed = Ship.removeCrewMember(bob);
		assertEquals(Ship.getShipCrew(), crew);
		assertFalse(removed);
		removed = Ship.removeCrewMember(charlie);
		assertEquals(Ship.getShipCrew(), crew);
		assertFalse(removed);
	}

	@Test
	void testGetName() {
		assertEquals(Ship.getName(), "White Whale");
	}

	@Test
	void testSetName() {
		assertEquals(Ship.getName(), "White Whale");
		Ship.setName("Flying Carpet");
		assertEquals(Ship.getName(), "Flying Carpet");
	}

	@Test
	void testGetInventory() {
		MedicalItem elixir = new MedicalItem("Elixir", 50, 50);
		MedicalItem potion = new MedicalItem("Potion", 20, 20);
		assertEquals(Ship.getInventory().size(), 0);
		elixir.increaseHeld(5);
		assertEquals(Ship.getInventory().size(), 1);
		potion.increaseHeld(0);
		assertEquals(Ship.getInventory().size(), 1);
		potion.increaseHeld(5);
		assertEquals(Ship.getInventory().size(), 2);
	}

	@Test
	void testInInventory() {
		MedicalItem elixir = new MedicalItem("Elixir", 50, 50);
		MedicalItem potion = new MedicalItem("Potion", 20, 20);
		assertFalse(Ship.inInventory(potion));
		assertFalse(Ship.inInventory(elixir));
		elixir.increaseHeld(1);
		assertTrue(Ship.inInventory(elixir));
		Ship.addToInventory(potion);
		assertFalse(Ship.inInventory(potion));
	}

	@Test
	void testAddToInventory() {
		MedicalItem elixir = new MedicalItem("Elixir", 50, 50);
		MedicalItem potion = new MedicalItem("Potion", 20, 20);
		assertEquals(Ship.getInventory().size(), 0);
		elixir.increaseHeld(5);
		assertEquals(Ship.getInventory().size(), 1);
		Ship.addToInventory(potion);
		assertEquals(Ship.getInventory().size(), 1);
		assertTrue(Ship.getInventory().contains(elixir));
		assertFalse(Ship.getInventory().contains(potion));
	}

	@Test
	void testRemoveFromInventory() {
		MedicalItem elixir = new MedicalItem("Elixir", 50, 50);
		MedicalItem potion = new MedicalItem("Potion", 20, 20);
		assertEquals(Ship.getInventory().size(), 0);
		elixir.increaseHeld(5);
		assertEquals(Ship.getInventory().size(), 1);
		potion.increaseHeld(0);
		assertEquals(Ship.getInventory().size(), 1);
		potion.increaseHeld(5);
		assertEquals(Ship.getInventory().size(), 2);
		elixir.decreaseHeld(5);
		assertEquals(Ship.getInventory().size(), 1);
		Ship.removeFromInventory(potion);
		assertEquals(Ship.getInventory().size(), 1);
	}

	@Test
	void testRepairShields() {
		assertEquals(Ship.getShields(), 100);
		boolean var = Ship.damageShields(25);
		assertEquals(Ship.getShields(), 75);
		assertFalse(var);
		Ship.repairShields(10);
		assertEquals(Ship.getShields(), 85);
		Ship.repairShields(1000);
		assertEquals(Ship.getShields(), 100);
	}

	@Test
	void testDamageShields() {
		assertEquals(Ship.getShields(), 100);
		boolean var = Ship.damageShields(25);
		assertEquals(Ship.getShields(), 75);
		assertFalse(var);
		var = Ship.damageShields(75);
		assertEquals(Ship.getShields(), 0);
		assertTrue(var);
	}

	@Test
	void testPilot() {
		CrewMember alice = new CrewMember("Alice", CrewClass.SCOUT);
		CrewMember bob = new CrewMember("Bob", CrewClass.ENGINEER);
		Planet kerbin = new Planet("Kerbin", "Little green gem");
		assertNull(Ship.getOrbiting());
		Ship.pilot(alice, bob, kerbin);
		assertEquals(Ship.getOrbiting(), kerbin);
	}
	
	@Test
	void testGetReadyCrew() {
		CrewMember alice = new CrewMember("Alice", CrewClass.SCOUT);
		CrewMember bob = new CrewMember("Bob", CrewClass.ENGINEER);
		Ship.addCrewMember(alice);
		Ship.addCrewMember(bob);
		assertEquals(Ship.getReadyCrew().size(), 2);
		alice.useActionPoint();
		assertEquals(Ship.getReadyCrew().size(), 2);
		bob.useActionPoint();
		assertEquals(Ship.getReadyCrew().size(), 2);
		alice.useActionPoint();
		assertEquals(Ship.getReadyCrew().size(), 1);
		bob.useActionPoint();
		assertEquals(Ship.getReadyCrew().size(), 0);
	}
	
	@Test
	void testForceOrbit() {
		Planet kerbin = new Planet("Kerbin", "More boosters");
		assertNull(Ship.getOrbiting());
		Ship.forceOrbit(kerbin);
		assertEquals(Ship.getOrbiting(), kerbin);
	}

}
