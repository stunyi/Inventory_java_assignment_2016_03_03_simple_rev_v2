package org.assignment;

import static org.junit.Assert.*;

import org.assignment.service.MainService;
import org.junit.Before;
import org.junit.Test;

public class MainTestClass {
	
	@Before
	public void setUp(){
		MainService.retrieveProducts();
		MainService.retrieveStartingInventory();
		MainService.retrieveSales();
		MainService.calculateProductTotals();
	}
	
	@Test
	public void testIfAllProductsWereCorrectlyReadFromCSV(){
		assertTrue("Verify if the product Unit Price is Correct (Sample)",55 == MainService.getProductByProductId(10002).getUnitPrice());
	}

	@Test
	public void testIfInventoryQuantitiesWereAppendedToProducts(){
		assertTrue("Verify product Inventory Info is Correct",33 == MainService.getProductByProductId(10002).getStartingStock());
	}
	
	@Test
	public void testSampleToVerifySalesWereImportedAndAppendedToProduct(){
		assertTrue("Verify product Sales Info is Correct",20 == MainService.getProductByProductId(10002).getUnitsSold());	
	}
}
