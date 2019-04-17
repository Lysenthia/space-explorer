package backendTesting;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import backend.*;
import java.util.ArrayList;

class GameStateTest {

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
		//TODO Complete after fully implementing transition day
		int day = GameState.getCurrentDay();
		GameState.transitionDay();
		assertEquals(GameState.getCurrentDay(), day + 1);
	}

}
