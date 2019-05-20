package backendTesting;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import backend.CrewClass;
import backend.CrewMember;
import backend.GameState;
import backend.Score;
import backend.Ship;

class ScoreTest {

	@Test
	void testGetScore() {
		CrewMember alice = new CrewMember("Alice", CrewClass.SCOUT);
		CrewMember bob = new CrewMember("Bob", CrewClass.SCOUT);
		CrewMember charlie = new CrewMember("Charlie", CrewClass.SCOUT);
		CrewMember dave = new CrewMember("Dave", CrewClass.SCOUT);
		Ship.addCrewMember(alice);
		Ship.addCrewMember(bob);
		Ship.addCrewMember(charlie);
		Ship.addCrewMember(dave);
		assertEquals(Score.getScore(), 0);
		GameState.findPart();
		assertEquals(Score.getScore(), 60);
		Ship.clearAll();
		Ship.addCrewMember(alice);
		Ship.addCrewMember(bob);
		Ship.addCrewMember(charlie);
		assertEquals(Score.getScore(), 90);
		Ship.clearAll();
		Ship.addCrewMember(alice);
		Ship.addCrewMember(bob);
		assertEquals(Score.getScore(), 150);
		GameState.findPart();
		assertEquals(Score.getScore(), 300);
		Ship.removeCrewMember(bob);
		assertEquals(Score.getScore(), 50);
	}

}
