package backendTesting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import backend.*;

class GameStateTest {

	@Test
	void testSetEndDay() {
		GameState.setEndDay(3);
		assertEquals(GameState.getEndDay(), 3);
		assertEquals(GameState.getPartsNeeded(), 1);
	}

	@Test
	void testGetEndDay() {
		fail("Not yet implemented");
	}

	@Test
	void testGetCurrentDay() {
		fail("Not yet implemented");
	}

	@Test
	void testGetPartsNeeded() {
		fail("Not yet implemented");
	}

	@Test
	void testGetPartsFound() {
		fail("Not yet implemented");
	}

	@Test
	void testGetAllConsumable() {
		fail("Not yet implemented");
	}

	@Test
	void testTransitionDay() {
		fail("Not yet implemented");
	}

}
