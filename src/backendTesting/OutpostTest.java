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

class OutpostTest {
	
	ArrayList<Consumable> stock;
	Outpost outpost;
	
	@BeforeEach
	void setUp() throws Exception {
		stock = new ArrayList<Consumable>();
		stock.add(new MedicalItem("Medical", 100, 100));
		stock.add(new CureItem("Cure", 100, 100));
		stock.add(new FoodItem("Medical", 100, 100));
		outpost = new Outpost("Outpost 9", stock, 1);
	}

	@Test
	void testGetName() {
		assertEquals(outpost.getName(), "Outpost 9");
	}

	@Test
	void testGetStock() {
		assertEquals(outpost.getStock(), stock);
	}

	@Test
	void testAddStockItem() {
		MedicalItem item = new MedicalItem("Improved Medical", 2, 2);
		boolean added;
		added = outpost.addStockItem(item);
		assertEquals(added, true);
		assertEquals(outpost.getStock().size(), stock.size() + 1);
		assertEquals(outpost.getStock().contains(item), true);
		added = outpost.addStockItem(item);
		assertEquals(added, false);
		assertEquals(outpost.getStock().size(), stock.size() + 1);
		assertEquals(outpost.getStock().contains(item), true);
	}

	@Test
	void testRemoveStockItem() {
		MedicalItem item = new MedicalItem("Improved Medical", 2, 2);
		outpost.addStockItem(item);
		assertEquals(outpost.getStock().size(), stock.size() + 1);
		assertEquals(outpost.getStock().contains(item), true);
		outpost.removeStockItem(item);
		assertEquals(outpost.getStock().size(), stock.size());
		assertEquals(outpost.getStock().contains(item), false);
	}

	@Test
	void testPurchaseItem() {
		Ship.addMoney(1000);
		boolean purchased;
		Consumable item = stock.get(0);
		purchased = outpost.purchaseItem(item, 1);
		assertEquals(purchased, true);
		assertEquals(Ship.getInventory().contains(item), true);
		assertEquals(Ship.getMoney(), 1000 - item.getPrice());
		assertEquals(item.getHeld(), 1);
		purchased = outpost.purchaseItem(item, 100);
		assertEquals(purchased, false);
		assertEquals(Ship.getInventory().contains(item), true);
		assertEquals(Ship.getMoney(), 1000 - item.getPrice());
		assertEquals(item.getHeld(), 1);
	}

}
