package backendTesting;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import backend.*;


class CrewMemberTest {
	
	private CrewMember alice;

	@BeforeEach
	void setUp() throws Exception {
		alice = new CrewMember("Alice", CrewClass.SCOUT);
		Ship.clearAll();
		GameState.clear();
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
	void testReduceTiredness() {
		assertEquals(alice.getTiredness(), 0);
		alice.addTiredness(50);
		assertEquals(alice.getTiredness(), 50);
		alice.reduceTiredness(20);
		assertEquals(alice.getTiredness(), 30);
		alice.reduceTiredness(100);
		assertEquals(alice.getTiredness(), 0);
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
		CrewMember bob = new CrewMember("Bob", CrewClass.ENGINEER);
		CrewMember[] crew = {alice, bob};
		MedicalItem elixir = new MedicalItem("Elixir", 10, 100);
		GameState.addConsumable(elixir);
		PlanetSearchOutput var;
		for (CrewMember member : crew) {
			for (int i = 0; i < 20; i++) {
				Planet kerbin = new Planet("Kerbin", "Little Green Men and Women");
				var = member.searchPlanet(kerbin);
				assertEquals(Arrays.asList(PlanetFindableObjects.values()).contains(var.FOUND), true);
				if (var.FOUND == PlanetFindableObjects.ITEM) {
					assertEquals(GameState.getAllConsumable().contains(var.ITEM), true);
				} else if (var.FOUND == PlanetFindableObjects.MONEY) {
					assertEquals(var.MONEY <= 100, true);
				} else if (var.FOUND == PlanetFindableObjects.PART) {
					assertEquals(kerbin.getPartFound(), true);
				} else if (var.FOUND == PlanetFindableObjects.NOTHING) {
					assertEquals(member.getMemberClass() != CrewClass.SCOUT, true);
				}
			}
		}
	}

}
