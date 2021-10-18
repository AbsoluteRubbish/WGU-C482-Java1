package pbeave.wgu.ims3.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Patricia Beavers
 * Inventory for parts and products.
 * Provides data for application
 */
public class Inventory {
	/**
	 * ID for part. Variable used for unique id
	 */
	private static int partID = 0;
	/**
	 * ID for part. Variable used for unique id
	 */
	private static int productID = 0;
	/**
	 * List for parts
	 */
	private static ObservableList<Part> partData = FXCollections.observableArrayList();
	/**
	 * List for products
	 */
	private static ObservableList<Product> productData = FXCollections.observableArrayList();

	/**
	 * @return a list of parts
	 */
	public static ObservableList<Part>getAllParts(){
		return partData;
	}
/**
 * @return
 * a list of products
 */
	public static ObservableList<Product>getAllProducts(){
		return productData;
	}
	/**
	 * @param part
	 * add parts to part list
	 */
	public static void addPart(Part part){
		partData.add(part);
	}
	/**
	 * @param product
	 * add product to product list
	 */
	public static void addProduct(Product product){
		productData.add(product);
	}
	/**
     * @return unique part ID
     */
	public static int newPartID(){
	return ++partID;	
	}
	/**
     * @return unique product ID
     */
	public static int newProductID(){
		return ++productID;	
		}
	/**
     * search part by Id
     * @return part list
     */
	public static Part lookupPart(int partID){
		Part foundPart = null;
		
		for (Part part : partData){
			if(part.getId() == partID){
				foundPart = part;
			}
		}
		return foundPart;
	}
	/**
     * search product by Id
     * @return product list
     */
	public static Product lookupProduct(int productID){
		Product foundProduct = null;
		
		for (Product product : productData){
			if(product.getId() == productID){
				foundProduct = product;
			}
		}
		return foundProduct;
	}
	/**
     * search part by name
     * @return part list
     */
	public static ObservableList<Part> lookupPart(String partName) {
		ObservableList<Part> foundPart = FXCollections.observableArrayList();
		for (Part part: partData){
			if (part.getName().equals(partName)){
				foundPart.add(part);
			}
		}
		return foundPart;
	}
	/**
     * search product by name
     * @return product list 
     */
	public static ObservableList<Product> lookupProduct(String productName) {
		ObservableList<Product> foundProduct = FXCollections.observableArrayList();
		for (Product product: productData){
			if (product.getName().equals(productName)){
				foundProduct.add(product);
			}
		}
		return foundProduct;
	}
	/**
	 * replaces old part with newly modified part
	 * @param index of product
	 * @param part to be modified
	 */
	public static void updatePart (int index, Part part){
		partData.set(index, part);
	}
	/**
	 * replaces old product with newly modified product
	 * @param index of product
	 * @param product to be modified
	 */
	public static void updateProduct (int index, Product product){
		productData.set(index, product);
	}
	/**
	 * removes part from list
	 * @param part to be removed
	 * @return boolean to remove part
	 */
	public static boolean deletePart(Part part){
		if (partData.contains(part)){
			partData.remove(part);
			return true;
		}else{
			return false;
		}
	}
	/**
	 * removes product from list
	 * @param product to be removed
	 * @return boolean to remove product
	 */
	public static boolean deleteProduct(Product product){
		if (productData.contains(product)){
			productData.remove(product);
			return true;
		}else{
			return false;
		}
	}
	
}
