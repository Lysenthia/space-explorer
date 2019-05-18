package backendTesting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import backend.*;
import java.util.ArrayList;


/**
 * Junit tests for game state
 * @author hoo42
 * @author rvo16
 *
 */
class GameStateTest {

	@BeforeEach
	void beforeEach() {
		GameState.clear();
		Ship.clearAll();
	}
	@Test
	void testSetEndDay() {
		GameState.setEndDay(3);
		assertEquals(GameState.getEndDay(), 3);
		assertEquals(GameState.getPartsNeeded(), 2);
	}

	@Test
	void testGetEndDay() {
		for (int i = 3; i < 11; i++) {
			GameState.setEndDay(i);
			assertEquals(GameState.getEndDay(), i);
		}
	}

	@Test
	void testGetCurrentDay() {
		assertEquals(GameState.getCurrentDay(), 1);
		GameState.transitionDay();
		assertEquals(GameState.getCurrentDay(), 2);
	}

	@Test
	void testGetPartsNeeded() {
		GameState.setEndDay(3);
		assertEquals(GameState.getPartsNeeded(), 2);
		GameState.setEndDay(5);
		assertEquals(GameState.getPartsNeeded(), 3);
		GameState.setEndDay(6);
		assertEquals(GameState.getPartsNeeded(), 4);
		GameState.setEndDay(7);
		assertEquals(GameState.getPartsNeeded(), 4);
		GameState.setEndDay(9);
		assertEquals(GameState.getPartsNeeded(), 6);
	}

	@Test
	void testGetPartsFound() {
		Planet kerbin = new Planet("Kerbin", "Exploding rockets");
		assertEquals(GameState.getPartsFound(), 0);
		kerbin.findPart();
		assertEquals(GameState.getPartsFound(), 1);
	}

	@Test
	void testGetAllConsumable() {
		ArrayList<Consumable> list = new ArrayList<Consumable>();
		CureItem cure = new CureItem("Cure", 10, 100);
		list = GameState.getAllConsumable();
		assertEquals(list.size(), 0);
		GameState.addConsumable(cure);
		list = GameState.getAllConsumable();
		assertEquals(list.size(), 1);
	}

	@Test
	void testTransitionDay() {
		CrewMember alice = new CrewMember("Alice", CrewClass.SCOUT);
		CrewMember bob = new CrewMember("Bob", CrewClass.COOK);
		CrewMember charlie = new CrewMember("Charlie", CrewClass.MEDIC);
		CrewMember dave = new CrewMember("Dave", CrewClass.CYBORG);
		Ship.addCrewMember(alice);
		Ship.addCrewMember(bob);
		Ship.addCrewMember(charlie);
		Ship.addCrewMember(dave);
		alice.setHealth(-15);
		bob.decreaseHealth(20);
		dave.giveSpacePlague();
		int day = GameState.getCurrentDay();
		ArrayList<CrewMember> dead = GameState.transitionDay();
		assertEquals(GameState.getCurrentDay(), day + 1);
		assertTrue(dead.contains(alice));
		assertEquals(dead.size(), 1);
		assertEquals(bob.getHealth(), 90);
		assertEquals(charlie.getHunger(), 25);
		assertEquals(dave.getHunger(), 0);
		assertEquals(dave.getHealth(), 75);
		dead = GameState.transitionDay();
		assertEquals(dead.size(), 0);
		assertEquals(bob.getHealth(), 100);
		assertEquals(charlie.getHunger(), 40);
		assertEquals(dave.getHunger(), 0);
		assertEquals(dave.getHealth(), 60);
		charlie.increaseHunger(100);
		dead = GameState.transitionDay();
		assertEquals(dead.size(), 0);
		assertEquals(bob.getHealth(), 100);
		assertEquals(charlie.getHunger(), 100);
		assertEquals(charlie.getHealth(), 85);
		assertEquals(dave.getHunger(), 0);
		assertEquals(dave.getHealth(), 45);
	}
	
	@Test
	void testSetAllConsumables() {
		ArrayList<Consumable> items = new ArrayList<Consumable>();
		MedicalItem medical = new MedicalItem("medical", 1, 1);
		CureItem cure = new CureItem("cure", 1, 1);
		items.add(cure);
		FoodItem food = new FoodItem("Bandage", 1, 1);
		items.add(food);
		assertEquals(GameState.getAllConsumable().size(), 0);
		GameState.addConsumable(food);
		assertEquals(GameState.getAllConsumable().size(), 1);
		GameState.setAllConsumables(items);
		assertEquals(GameState.getAllConsumable().size(), 2);
		items.add(medical);
		GameState.setAllConsumables(items);
		assertEquals(GameState.getAllConsumable().size(), 3);
	}
	
	@Test
	void testAddConsumable() {
		MedicalItem medical = new MedicalItem("medical", 1, 1);
		GameState.addConsumable(medical);
		assertEquals(GameState.getAllConsumable().size(), 1);
		assertTrue(GameState.getAllConsumable().contains(medical));
		GameState.addConsumable(medical);
		assertEquals(GameState.getAllConsumable().size(), 1);
	}
	
	@Test
	void testGetEnding() {
		assertNull(GameState.getEnding());
		GameState.setEnding(PossibleEndings.CREW_DEAD);
		assertEquals(GameState.getEnding(), PossibleEndings.CREW_DEAD);
	}
	
	@Test
	void testSetEnding() {
		GameState.setEnding(PossibleEndings.CREW_DEAD);
		assertEquals(GameState.getEnding(), PossibleEndings.CREW_DEAD);
		GameState.setEnding(PossibleEndings.LOST_IN_SPACE);
		assertEquals(GameState.getEnding(), PossibleEndings.LOST_IN_SPACE);
	}

}
