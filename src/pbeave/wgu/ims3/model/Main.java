package pbeave.wgu.ims3.model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Patricia Beavers
 *Inventory management program implements application 
 *for managing parts and products with associated parts
 *
 * A future enhancement would be adding the amount of the same parts
 * for a product. An example would be 2 wheels for a bike, or 3 wheels for a tricycle
 */

public class Main extends Application {
	
	/**
	 * @param primaryStage to set scene
	 * @throws Exception if MainPage.fxml is not found
	 */
	
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/pbeave/wgu/ims3/view/MainPage.fxml"));
		Parent root = loader.load();
		primaryStage.setTitle("Inventory Management System");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

	/**
	 * Main method
	 * creates sample parts and products.
	 * Launches application.
	 * @param args
	 */
	
	public static void main(String[] args) {
		
		int partID = Inventory.newPartID();
		InHousePart breaks = new InHousePart(partID, "Breaks", 15.00, 10, 1, 20, 1658);
		partID = Inventory.newPartID();
		InHousePart wheel = new InHousePart(partID, "Wheel", 11.00, 16, 1, 50, 9764);
		partID = Inventory.newPartID();
		OutsourcePart seat = new OutsourcePart(partID, "Seat", 15.00, 10, 10, 800, "AllSeats Inc");
		Inventory.addPart(breaks);
		Inventory.addPart(wheel);
		Inventory.addPart(seat);

		int productID = Inventory.newProductID();
		Product bike = new Product(productID, "Giant Bike", 299.99, 5, 1, 100);
		bike.addPartsAssociated(breaks);
		bike.addPartsAssociated(wheel);
		bike.addPartsAssociated(seat);
		Inventory.addProduct(bike);

		productID = Inventory.newProductID();
		Product tricycle = new Product(productID, "Tricycle", 99.99, 3, 1, 10);
		tricycle.addPartsAssociated(breaks);
		tricycle.addPartsAssociated(wheel);
		tricycle.addPartsAssociated(seat);
		Inventory.addProduct(tricycle);

		launch(args);
	}

}
