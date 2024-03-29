package backendTesting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import backend.*
;

/**
 * Junit tests for consumables
 * @author hoo42
 * @author rvo16
 */
class ConsumableTest {
	
	private CureItem basic;
	private CureItem advanced;

	@BeforeEach
	void setUp() throws Exception {
		basic = new CureItem("Basic Cure", 50, 50);
		advanced = new CureItem("Advanced Cure", 100, 100);
	}

	@Test
	void testGetName() {
		assertEquals(basic.getName(), "Basic Cure");
		assertEquals(advanced.getName(), "Advanced Cure");
	}

	@Test
	void testGetPrice() {
		assertEquals(basic.getPrice(), 50);
		assertEquals(advanced.getPrice(), 100);
	}

	@Test
	void testGetHeld() {
		basic.increaseHeld(30);
		advanced.increaseHeld(11);
		assertEquals(basic.getHeld(), 30);
		assertEquals(advanced.getHeld(), 11);
	}

	@Test
	void testIncreaseHeld() {
		assertEquals(basic.getHeld(), 0);
		assertEquals(advanced.getHeld(), 0);
		basic.increaseHeld(5);
		assertEquals(basic.getHeld(), 5);
		assertEquals(advanced.getHeld(), 0);
		advanced.increaseHeld(1024);
		assertEquals(basic.getHeld(), 5);
		assertEquals(advanced.getHeld(), 1024);
	}

	@Test
	void testDecreaseHeld() {
		basic.increaseHeld(10);
		advanced.increaseHeld(100);
		assertEquals(basic.getHeld(), 10);
		assertEquals(advanced.getHeld(), 100);
		basic.decreaseHeld(5);
		assertEquals(basic.getHeld(), 5);
		assertEquals(advanced.getHeld(), 100);
	}
	
	@Test
	void testGetEffectiveness() {
		assertEquals(basic.getEffectiveness(), 50);
		assertEquals(advanced.getEffectiveness(), 100);
	}
	
	@Test
	void testGetItemType() {
		MedicalItem medical = new MedicalItem("bandaid", 10, 10);
		FoodItem food = new FoodItem("bread", 30, 30);
		assertEquals(basic.getItemType(), "Cure");
		assertEquals(advanced.getItemType(), "Cure");
		assertEquals(medical.getItemType(), "Medical");
		assertEquals(food.getItemType(), "Food");
	}

}
