package pbeave.wgu.ims3.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * 
 * @author Patricia Beavers
 * Model for product
 */
public class Product {
	/**
	 * id for product
	 */
	private int id;
	/**
	 * name for product
	 */
	private String name;
	/**
	 * price for product
	 */
	private double price;
	/**
	 * stock for product
	 */
	private int stock;
	/**
	 * minimum for product
	 */
	private int min;
	/**
	 * maximum for product
	 */
	private int max;
	/**
	 * product constructor
	 * @param id
	 * @param name
	 * @param price
	 * @param stock
	 * @param min
	 * @param max
	 */
	public Product(int id, String name, double price, int stock, int min, int max) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.min = min;
		this.max = max;
	}
	
	/**
	 * list of associated parts
	 */
	private ObservableList<Part> partAssociated = FXCollections.observableArrayList();
	/**
	 * 
	 * @return list of associated parts
	 */
	public ObservableList<Part> getPartsAssociated() {
		return partAssociated;
	}
	/**
	 * add parts to associated parts
	 * @param part
	 */
	public void addPartsAssociated(Part part) {
		partAssociated.add(part);
	}
	/**
	 * deletes part from associated parts
	 * @param part to delete
	 * @return boolean
	 */
	public boolean deletePartsAssociated(Part part) {
		if (partAssociated.contains(part)) {
			partAssociated.remove(part);
			return true;
		} else {
			return false;
		}
	}
	

	/**
	 * getter for id
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 * setter for id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * getter for name
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 * setter for name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * getter for price
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price
	 * setter for price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * getter for stock
	 * @return the stock
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * @param stock
	 * setter for stock
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}

	/**
	 * getter for minimum
	 * @return the min
	 */
	public int getMin() {
		return min;
	}

	/**
	 * @param min
	 * setter for mimimum
	 */
	public void setMin(int min) {
		this.min = min;
	}

	/**
	 * getter for maximum
	 * @return the max
	 */
	public int getMax() {
		return max;
	}

	/**
	 * @param max
	 *  setter for maximum
	 */
	public void setMax(int max) {
		this.max = max;
	}

}
