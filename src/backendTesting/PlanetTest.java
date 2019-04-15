package backendTesting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import backend.*;

class PlanetTest {
	
	private Planet kerbin;

	@BeforeEach
	void setUp() throws Exception {
		kerbin = new Planet("Kerbin", "A small planet about 1/5 the size of Earth.\n The native species appears to have an early space program.");
	}

	@Test
	void testGetName() {
		assertEquals(kerbin.getName(), "Kerbin");
	}

	@Test
	void testGetDescription() {
		assertEquals(kerbin.getDescription(), "A small planet about 1/5 the size of Earth.\n The native species appears to have an early space program.");
	}

	@Test
	void testGetPartFound() {
		assertEquals(kerbin.getPartFound(), false);
		kerbin.findPart();
		assertEquals(kerbin.getPartFound(), true);
	}

	@Test
	void testFindPart() {
		kerbin.findPart();
		assertEquals(kerbin.getPartFound(), true);
	}

}
