package backendTesting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import backend.*;
import java.util.ArrayList;

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
		assertEquals(added, true);
		crew.add(bob);
		added = Ship.addCrewMember(bob);
		assertEquals(Ship.getShipCrew(), crew);
		assertEquals(added, true);
	}

	@Test
	void testAddCrewMember() {
		CrewMember alice = new CrewMember("Alice", CrewClass.SCOUT);
		ArrayList<CrewMember> crew = new ArrayList<CrewMember>();
		boolean added;
		crew.add(alice);
		added = Ship.addCrewMember(alice);
		assertEquals(Ship.getShipCrew(), crew);
		assertEquals(added, true);
		added = Ship.addCrewMember(alice);
		assertEquals(added, false);
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
		assertEquals(removed, true);
		removed = Ship.removeCrewMember(bob);
		assertEquals(Ship.getShipCrew(), crew);
		assertEquals(removed, false);
		removed = Ship.removeCrewMember(charlie);
		assertEquals(Ship.getShipCrew(), crew);
		assertEquals(removed, true);
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
		assertEquals(Ship.inInventory(potion), false);
		assertEquals(Ship.inInventory(elixir), false);
		elixir.increaseHeld(1);
		assertEquals(Ship.inInventory(elixir), true);
		Ship.addToInventory(potion);
		assertEquals(Ship.inInventory(potion), false);
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
		assertEquals(Ship.getInventory().contains(elixir), true);
		assertEquals(Ship.getInventory().contains(potion), false);
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
		assertEquals(var, false);
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
		assertEquals(var, false);
		var = Ship.damageShields(75);
		assertEquals(Ship.getShields(), 0);
		assertEquals(var, true);
	}

	@Test
	void testPilot() {
		CrewMember Alice = new CrewMember("Alice", CrewClass.SCOUT);
		CrewMember Bob = new CrewMember("Bob", CrewClass.ENGINEER);
		Planet kerbin = new Planet("Kerbin", "Little green gem");
		assertEquals(Ship.getOrbiting(), null);
		Ship.pilot(Alice, Bob, kerbin);
		assertEquals(Ship.getOrbiting(), kerbin);
	}

}
