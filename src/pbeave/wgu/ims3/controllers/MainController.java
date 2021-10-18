package pbeave.wgu.ims3.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import pbeave.wgu.ims3.model.Inventory;
import pbeave.wgu.ims3.model.Part;
import pbeave.wgu.ims3.model.Product;
/**
 * @author Patricia Beavers
 * Controller class for the main page
 * 
 * Runtime error occurred with user does not select part and clicks modify button.
 * It occused due to null value in initialized method of the modify part controller,
 * to prevent this look at partModify() below.
 *
 */
public class MainController implements Initializable{
	/**
	 *part selected by user
	 */
	private static Part modifyPart;
	/**
	 *product selected by user
	 */
	private static Product modifyProduct;
	/**
	 * Text field for product search
	 */
	@FXML
	public TextField partSearch;
	/**
	 *Table view for parts
	 */
	@FXML
	public TableView<Part> partTV;
	/**
	 * Table column for part id
	 */
	@FXML
	private TableColumn<Part, Integer> partID;
	/**
	 * Table column for part name
	 */
	@FXML
	private TableColumn<Part, String> partName;
	/**
	 * Table column for part inventory
	 */
	@FXML
	private TableColumn<Part, Integer> partInventory;
	/**
	 * Table column for part price
	 */
	@FXML
	private TableColumn<Part, Double> partPrice;
	/**
	 * Text field for product search
	 */
	@FXML
	public TextField productSearch;
	/**
	 *Table view for products with columns
	 */
	@FXML
	public TableView<Product> productTV;
	/**
	 * Table column for product id
	 */
	@FXML
	private TableColumn<Product, Integer> productID;
	/**
	 * Table column for product name
	 */
	@FXML
	private TableColumn<Product, String> productName;
	/**
	 * Table column for product inventory
	 */
	@FXML
	private TableColumn<Product, Integer> productInventory;
	/**
	 * Table column for product price
	 */
	@FXML
	private TableColumn<Product, Double> productPrice;
	/**
	 * @return modified part
	 */
	public static Part getModifyPart(){
		return modifyPart;
	}
	/**
	 * @return modified product
	 */
	public static Product getModifyProduct(){
		return modifyProduct;
	}
	/**
	 * @param event
	 * closes program
	 */
	@FXML
	 void exitButton(ActionEvent event){
		System.exit(0);
	}
	/**
	 * @param event
	 * Goes to add part page
	 * @throws IOException
	 * thrown when AddPartPage.fxml can not be loaded
	 */
	@FXML
	 void partAdd(ActionEvent event) throws IOException{
		Parent parent = FXMLLoader.load(getClass().getResource("/pbeave/wgu/ims3/view/AddPartPage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(parent));
        stage.show();
 
	}
	/**
	 * @param event
	 * deletes selected part
	 */
	@FXML
	 void partDelete(ActionEvent event)  {
		Part part = partTV.getSelectionModel().getSelectedItem();
		if(part == null){
			error(3);
		}else{
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Alert");
			alert.setContentText("Delete part?");
			Optional<ButtonType> result = alert.showAndWait();
			if(result.isPresent() && result.get() == ButtonType.OK){
				Inventory.deletePart(part);
			}
		}
	}
	/**
	 * @param event
	 * Goes to modify part page
	 * @throws IOException
	 * thrown when ModifyPartPage.fxml can not be loaded
	 */
	@FXML
	 void partModify(ActionEvent event) throws IOException {
		modifyPart = partTV.getSelectionModel().getSelectedItem();
		if(modifyPart == null){
			error(3);
		}else{
			Parent parent = FXMLLoader.load(getClass().getResource("/pbeave/wgu/ims3/view/ModifyPartPage.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(parent));
            stage.show();
		}
	}
	/**
	 * @param event
	 * searches for part
	 */
	@FXML
	 void partSearch(ActionEvent event){
		ObservableList<Part> partData = Inventory.getAllParts();
		ObservableList<Part> foundPart = FXCollections.observableArrayList();
		String search = partSearch.getText();
		for (Part part: partData){
			if(String.valueOf(part.getId()).contains(search) || part.getName().contains(search)){
				foundPart.add(part);
			}
		}
		partTV.setItems(foundPart);
		if(foundPart.size() == 0){
			error(1);
		}
	}
	/**
	 * @param event
	 * returns all parts when search bar is empty
	 */
	@FXML
	 void partSearchKey(KeyEvent event){
		if(partSearch.getText().isEmpty()){
		partTV.setItems(Inventory.getAllParts());
		}
	}
	/**
	 * 
	 * @param event
	 * Goes to add product pag
	 * @throws IOException
	 * thrown when AddProductPage.fxml can not be loaded
	 */
	@FXML
	 void productAdd(ActionEvent event) throws IOException{
		 Parent parent = FXMLLoader.load(getClass().getResource("/pbeave/wgu/ims3/view/AddProductPage.fxml"));
	        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        stage.setScene(new Scene(parent));
	        stage.show();
	}
	/**
	 * @param event
	 * deletes selected product
	 */
	@FXML
	 void productDelete(ActionEvent event){
		Product product = productTV.getSelectionModel().getSelectedItem();
		if (product == null){
			error(4);
		}else{
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Alert");
			alert.setContentText("Delete product?");
			Optional<ButtonType> result = alert.showAndWait();
			if(result.isPresent() && result.get() == ButtonType.OK){
				ObservableList<Part> partsAssociated = product.getPartsAssociated();
				if(partsAssociated.size() >= 1){
					error(5);
				}else{
					Inventory.deleteProduct(product);
				}
			}
		}
		
	}
	/**
	 * @param event
	 * Goes to modify product page
	 * @throws IOException
	 * thrown when ModifyProductPage.fxml can not be loaded
	 */
	@FXML
	 void productModify(ActionEvent event) throws IOException{
		modifyProduct = productTV.getSelectionModel().getSelectedItem();
		if(modifyProduct == null){
			error(4);
		}else{
			Parent parent = FXMLLoader.load(getClass().getResource("/pbeave/wgu/ims3/view/ModifyProductPage.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(parent));
            stage.show();
		}
	}
	/**
	 * @param event
	 * searches for product
	 */
	@FXML
	 void productSearch(ActionEvent event){
		ObservableList<Product> productData = Inventory.getAllProducts();
		ObservableList<Product> foundProduct = FXCollections.observableArrayList();
		String search = productSearch.getText();
		for(Product product: productData){
			if(String.valueOf(product.getId()).contains(search) || product.getName().contains(search)){
				foundProduct.add(product);
			}
		}
		productTV.setItems(foundProduct);
		if(foundProduct.size() == 0){
			error(2);
		}
	}
	/**
	 * @param event
	 * returns all products when search bar is empty
	 */
	@FXML
	 void productSearchKey(KeyEvent event){
		if(productSearch.getText().isEmpty()){
			productTV.setItems(Inventory.getAllProducts());
		}
	}
	/**
	 * @param alertType
	 * show error messages
	 */
	private void error(int alertType){
		Alert alert = new Alert(Alert.AlertType.ERROR);
		
		switch (alertType){
		case 1:
			alert.setContentText("Part not found");
			alert.showAndWait();
			break;
		case 2:
			alert.setContentText("Product not found");
			alert.showAndWait();
			break;
		case 3:
			alert.setContentText("Part not selected");
			alert.showAndWait();
			break;
		case 4:
			alert.setContentText("Product not selected");
			alert.showAndWait();
			break;
		case 5:
			alert.setContentText("Parts associated with product, can not delete");
			alert.showAndWait();
			break;
		}
	}
	/**
	 * populates part and product table views
	 */
	public void initialize(URL location, ResourceBundle resources){
		//part table
		partTV.setItems(Inventory.getAllParts());
		partID.setCellValueFactory(new PropertyValueFactory<>("id"));
		partName.setCellValueFactory(new PropertyValueFactory<>("name"));
		partInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
		partPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
		
		//product table
		productTV.setItems(Inventory.getAllProducts());
		productID.setCellValueFactory(new PropertyValueFactory<>("id"));
		productName.setCellValueFactory(new PropertyValueFactory<>("name"));
		productInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
		productPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
	}
	
	
}
