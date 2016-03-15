package org.assignment.bo;

public class Product {

	private int productId;
	private String description;
	private float unitPrice;
	private float startingStock;
	private float unitsInStock;
	private float unitsSold;
	
	public Product(){
		productId = 0;
		description = "";
		unitPrice = 0;
		startingStock = 0;
		unitsInStock = 0;
		unitsSold = 0;
	}

	/**
	 * @return the productId
	 */
	public int getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the unitPrice
	 */
	public float getUnitPrice() {
		return unitPrice;
	}

	/**
	 * @param unitPrice the unitPrice to set
	 */
	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}

	/**
	 * @return the unitsInStock
	 */
	public float getUnitsInStock() {
		return unitsInStock;
	}

	/**
	 * @param unitsInStock the unitsInStock to set
	 */
	public void setUnitsInStock(float unitsInStock) {
		this.unitsInStock = unitsInStock;
	}

	/**
	 * @return the unitsSold
	 */
	public float getUnitsSold() {
		return unitsSold;
	}

	/**
	 * @param unitsSold the unitsSold to set
	 */
	public void setUnitsSold(float unitsSold) {
		this.unitsSold = unitsSold;
	}
	
	/**
	 * @return the startingStock
	 */
	public float getStartingStock() {
		return startingStock;
	}

	/**
	 * @param startingStock the startingStock to set
	 */
	public void setStartingStock(float startingStock) {
		this.startingStock = startingStock;
		this.unitsInStock = startingStock;
	}

	public float getTotal(){
		return unitsSold * unitPrice;
	}
	
	public void addSale(float unitsSold){
		this.unitsSold = this.unitsSold + unitsSold;
		this.unitsInStock = this.unitsInStock - unitsSold;
	}
}
