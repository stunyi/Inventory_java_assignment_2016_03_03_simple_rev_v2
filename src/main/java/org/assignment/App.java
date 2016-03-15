package org.assignment;

import java.util.LinkedList;

import org.assignment.bo.Product;
import org.assignment.service.MainService;

public class App {

	public static void main(String[] args) {
		
		MainService.retrieveProducts();
		MainService.retrieveStartingInventory();
		MainService.retrieveSales();
		MainService.calculateProductTotals();
		
		// List all Products
		listAllProducts();
		
		// List Inventory Change
		listChangeInInventory();
		
		// List Customers in Descending Order
		listCustomersInDescendingOrder();
		
		// List Customers in Descending Order
		listOfProductsInDescendingOrder();
		
	}
	
	private static void listAllProducts(){
		
		LinkedList<Product> products = MainService.getListOfAllProducts();
		
		float totalAmount = MainService.getTotalValue(); 
		float totalNoOfUnitsSold = MainService.getTotalNumberOfUnitsSold();
		
		System.out.println("******************************************************************");
		System.out.println("Sales Data (Listing) - Product");
		System.out.println("******************************************************************");
		
		for(Product prod : products){
			System.out.println(String.format("ID: '%1$s', Name: '%2$s',\n        Unit Price: '%3$s',\n        No of Items Sold: '%4$s',\n        Total Amount: '%5$s'", 
					prod.getProductId(),
					prod.getDescription(),
					prod.getUnitPrice(),
					prod.getUnitsSold(),
					prod.getTotal()));
			
			
		}
		System.out.println("------------------------------------------------------------------");
		System.out.println(String.format("Total Number of Units Sold: '%2$s' ,Total Revenue: '%1$s'", totalAmount,totalNoOfUnitsSold));
		System.out.println("------------------------------------------------------------------");
		System.out.println("******************************************************************");
		System.out.println("==================================================================");
	}

	private static void listChangeInInventory(){
		
		LinkedList<Product> products = MainService.getListOfAllProducts();
		
		System.out.println("******************************************************************");
		System.out.println("Inventory Change - Product");
		System.out.println("******************************************************************");
		
		for(Product prod : products){
			System.out.println(String.format("ID: '%1$s', Name: '%2$s',\n        Starting Inventory: '%3$s',\n        No of Items Sold: '%4$s',\n        Units In Stock: '%5$s'", 
					prod.getProductId(),
					prod.getDescription(),
					prod.getStartingStock(),
					prod.getUnitsSold(),
					prod.getUnitsInStock()));
			
			
		}
		System.out.println("******************************************************************");
		System.out.println("==================================================================");
	}

	private static void listCustomersInDescendingOrder(){
		
		LinkedList<Integer> customerNumbers = MainService.getListOfCustomersBasedOnPurchasesInDescendingOrder();
		
		System.out.println("******************************************************************");
		System.out.println(" Customers in descending Order ");
		System.out.println("******************************************************************");
		
		for(Integer customerNumber : customerNumbers){
			System.out.println(String.format("Customer Number: '%1$s'",customerNumber));	
		}
		
		System.out.println("******************************************************************");
		System.out.println("==================================================================");
	}
	
	private static void listOfProductsInDescendingOrder(){

		LinkedList<Product> products = MainService.getListOfProductsBasedOnSalesInDescendingOrder();

		System.out.println("******************************************************************");
		System.out.println(" Products in descending Order ");
		System.out.println("******************************************************************");

		for(Product product : products){
			System.out.println(String.format("Product ID: '%1$s', Product Name: '%2$s'",product.getProductId(),product.getDescription()));	
		}

		System.out.println("******************************************************************");
		System.out.println("==================================================================");
	}
}
