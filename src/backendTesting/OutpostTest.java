package backendTesting;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import backend.Consumable;
import backend.CureItem;
import backend.FoodItem;
import backend.MedicalItem;
import backend.Outpost;
import backend.Ship;

/**
 * Junit tests for Outpost
 * @author hoo42
 * @author rvo16
 *
 */
class OutpostTest {
	
	ArrayList<Consumable> stock;
	
	@BeforeEach
	void setUp() throws Exception {
		stock = new ArrayList<Consumable>();
		stock.add(new MedicalItem("Medical", 100, 100));
		stock.add(new CureItem("Cure", 100, 100));
		stock.add(new FoodItem("Medical", 100, 100));
		Outpost.setOutpost("Outpost 9", 1);
	}

	@Test
	void testGetName() {
		assertEquals(Outpost.getName(), "Outpost 9");
	}

	@Test
	void testPurchaseItem() {
		Ship.addMoney(1000);
		boolean purchased;
		Consumable item = stock.get(0);
		purchased = Outpost.purchaseItem(item, 1);
		assertTrue(purchased);
		assertTrue(Ship.getInventory().contains(item));
		assertEquals(Ship.getMoney(), 1000 - item.getPrice());
		assertEquals(item.getHeld(), 1);
		purchased = Outpost.purchaseItem(item, 100);
		assertFalse(purchased);
		assertTrue(Ship.getInventory().contains(item));
		assertEquals(Ship.getMoney(), 1000 - item.getPrice());
		assertEquals(item.getHeld(), 1);
	}

}
