package org.assignment.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

import org.assignment.bo.Product;

public class MainService {
	
	private static LinkedList<Product> products = new LinkedList<Product>();
	private static Map<Integer, Float> productTotalsMap = new TreeMap<Integer, Float>();
	private static Map<Integer, Float> customerTotalsMap = new TreeMap<Integer, Float>();
	
//	private static LinkedList<Customer> customers = new 
	
	
	public static void retrieveProducts(){
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(MainService.class.getClassLoader().getResource("ProductData.csv").getFile()));
			String line = br.readLine();
			
			int index = 0;
			while(line != null){
				Product prod = new Product();
				
				if(index != 0){
					
					String[] attribute = line.split(",");
					
					if(attribute.length > 0){
						prod.setProductId(Integer.parseInt(attribute[0].trim()));
						prod.setDescription(attribute[1].trim());
						String unitPrice = attribute[2].trim().substring(1);
						prod.setUnitPrice(Float.parseFloat(unitPrice));

						products.add(prod);
					}
				}
				
				
				
				line = br.readLine();
				index++;
			}
		}catch(FileNotFoundException err){
			System.out.println(err);
		}catch( IOException err){
			System.out.println(err);
		}finally{
			if(br != null){
				try{
					br.close();
				}catch(IOException err){
					
				}
			}
		}
		
	}

	public static void retrieveStartingInventory(){
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(MainService.class.getClassLoader().getResource("InventoryData.csv").getFile()));
			String line = br.readLine();
			
			int index = 0;
			while(line != null){
				if(index != 0){
					
					String[] attribute = line.split(",");
					
					int productId = 0;
					float startingInventory = 0;
					
					if(attribute.length > 0){
						productId = Integer.parseInt(attribute[0].trim());
						String unitPrice = attribute[1].trim();
						if(unitPrice.equals("-")){
							unitPrice = "0";
						}
						startingInventory = Float.parseFloat(unitPrice);
						
						Product prod = getProductByProductId(productId);
						prod.setStartingStock(startingInventory);
					}
				}
				
				line = br.readLine();
				index++;
			}
		}catch(FileNotFoundException err){
			System.out.println(err);
		}catch( IOException err){
			System.out.println(err);
		}finally{
			if(br != null){
				try{
					br.close();
				}catch(IOException err){
					
				}
			}
		}
	}
	
	public static void retrieveSales(){
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(MainService.class.getClassLoader().getResource("SalesData.csv").getFile()));
			String line = br.readLine();
			
			int index = 0;
			while(line != null){
				if(index != 0){
					
					String[] attribute = line.split(",");
					
					int productId = 0;
					int customerId = 0;
					float unitsSold = 0;
					
					if(attribute.length > 0){
						
						customerId = Integer.parseInt(attribute[0].trim());
						productId = Integer.parseInt(attribute[1].trim());
						String unitsSoldText = attribute[2].trim();
						unitsSold = Float.parseFloat(unitsSoldText);
						
						Product prod = getProductByProductId(productId);
						prod.addSale(unitsSold);
						
						// Add Customer Sales
						appendCustomerTotals(customerId,prod.getTotal());
					}
				}
				
				line = br.readLine();
				index++;
			}
		}catch(FileNotFoundException err){
			System.out.println(err);
		}catch( IOException err){
			System.out.println(err);
		}finally{
			if(br != null){
				try{
					br.close();
				}catch(IOException err){
					
				}
			}
		}
	}
	
	public static void calculateProductTotals(){
		for(Product product : MainService.products){
			if(productTotalsMap.get(product.getProductId()) == null){
				productTotalsMap.put(product.getProductId(), product.getTotal());
			}
		}
	}
	
	public static void appendCustomerTotals(int customerId, float value){
		if(customerTotalsMap.get(customerId) == null){
			customerTotalsMap.put(customerId,value);
		} else {
			customerTotalsMap.put(customerId, customerTotalsMap.get(customerId).floatValue() + value);
		}
	} 
	
	public static Product getProductByProductId(int productId){
		if(!(products != null && products.size() > 0)){
			return null;
		}
		
		for(Product product : products){
			if(product.getProductId() == productId){
				return product;
			}
		}
		
		return null;
	}
	
	public static LinkedList<Product> getListOfAllProducts(){
		return MainService.products;	
	}
	
	public static float getTotalNumberOfUnitsSold(){
		
		float unitsSold = 0;
		
		for(Product prod : MainService.products){
			unitsSold += prod.getUnitsSold();
		}
		return unitsSold;
	}
	
	public static float getTotalValue(){
		float totalValue = 0;
		for(Product prod : MainService.products){
			totalValue += prod.getTotal();
		}
		return totalValue;
	}

	public static LinkedList<Product> getListOfProductsBasedOnSalesInDescendingOrder(){
		
		Integer[] productIds = new Integer[productTotalsMap.keySet().size()];
		Float[] productsTotals = new Float[productTotalsMap.keySet().size()];
		
		// Transfer values to Arrays
		int i = 0;
		for(Integer productId : productTotalsMap.keySet()){
			productIds[i] = productId;
			productsTotals[i] = productTotalsMap.get(productId);
			i++;
		}
		
		// Sort the Arrays in descending order based on Value of Purchases
		for(int j = 0; j < productsTotals.length; j++){
			for(int k = 0; k < productsTotals.length; k++){
				if(productsTotals[j] > productsTotals[k]){
					float tempValue = productsTotals[j];
					productsTotals[j] = productsTotals[k];
					productsTotals[k] = tempValue;
					
					int tempCustId = productIds[j];
					productIds[j] = productIds[k];
					productIds[k] = tempCustId;
				}
			}
		}
		LinkedList<Product> productsReturned = new LinkedList<Product>();
		
		for(int j = 0; j < productIds.length; j++){
			productsReturned.add(getProductByProductId(productIds[j]));
		}
		
		return productsReturned;
	}

	public static LinkedList<Integer> getListOfCustomersBasedOnPurchasesInDescendingOrder(){
		
		Integer[] customerIds = new Integer[customerTotalsMap.keySet().size()];
		Float[] customerTotals = new Float[customerTotalsMap.keySet().size()];
		
		// Transfer values to Arrays
		int i = 0;
		for(Integer customerId : customerTotalsMap.keySet()){
			customerIds[i] = customerId;
			customerTotals[i] = customerTotalsMap.get(customerId);
			i++;
		}
		
		// Sort the Arrays in descending order based on Value of Purchases
		for(int j = 0; j < customerTotals.length; j++){
			for(int k = 0; k < customerTotals.length; k++){
				if(customerTotals[j] > customerTotals[k]){
					float tempValue = customerTotals[j];
					customerTotals[j] = customerTotals[k];
					customerTotals[k] = tempValue;
					
					int tempCustId = customerIds[j];
					customerIds[j] = customerIds[k];
					customerIds[k] = tempCustId;
				}
			}
		}
		LinkedList<Integer> customers = new LinkedList<Integer>();
		
		for(int j = 0; j < customerIds.length; j++){
			customers.add(customerIds[j]);
		}
		
		return customers;
	}

}
