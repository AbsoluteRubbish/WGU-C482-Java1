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
 * Controller class for add product page
 */

public class AddProductController implements Initializable {
	/**
	 * List for associated parts
	 */
	private ObservableList<Part> partsAssociated = FXCollections.observableArrayList();
	/**
	 *Table view for associated parts
	 */
	@FXML
	private TableView<Part> partsAssociatedTV;
	/**
	 * Table Column for associated part ID
	 */
	@FXML
	private TableColumn<Part, Integer> paID;
	/**
	 * Table Column for associated part name
	 */
	@FXML
	private TableColumn<Part, String> paName;
	/**
	 * Table Column for associated part inventory
	 */
	@FXML
	private TableColumn<Part, Integer> paInventory;
	/**
	 * Table Column for associated part price
	 */
	@FXML
	private TableColumn<Part, Integer> paPrice;
	/**
	 *Table view for non-associated parts
	 */
	@FXML
	private TableView<Part> partTV;
	/**
	 * Table Column for associated part ID
	 */
	@FXML
	private TableColumn<Part,Integer> partID;
	/**
	 * Table Column for associated part name
	 */
	@FXML
	private TableColumn<Part,String> partName;
	/**
	 * Table Column for associated part inventory
	 */
	@FXML
	private TableColumn<Part,Integer> partInventory;
	/**
	 * Table Column for associated part price
	 */
	@FXML
	private TableColumn<Part,Integer> partPrice;
	/**
	 * textfield for searching associated part
	 */
	@FXML
	private TextField partSearch;
	/**
	 * textfield for product id
	 */
	@FXML
	private TextField productID;
	/**
	 * textfield for product name
	 */
	@FXML
	private TextField productName;
	/**
	 * textfield for product inventory
	 */
	@FXML
	private TextField productInventory;
	/**
	 * textfield for product price
	 */
	@FXML
	private TextField productPrice;
	/**
	 * textfield for product maximum
	 */
	@FXML
	private TextField productMax;
	/**
	 * textfield for product minimum
	 */
	@FXML
	private TextField productMin;
	
	/**
	 * @param event
	 * adds part to associated part table
	 */
	@FXML
	void addButton(ActionEvent event){
		Part part = partTV.getSelectionModel().getSelectedItem();
		if (part == null){
			error(5);
		}else{
			partsAssociated.add(part);
			partsAssociatedTV.setItems(partsAssociated);
		}
		
	}
	/**
	 * @param event
	 *cancels and goes back to main screen
	 * @throws IOException
	 * from return to main
	 */
	@FXML
	void cancelButton(ActionEvent event) throws IOException{
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Alert");
		alert.setContentText("Return to the main screen without saving?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent() || result.get() == ButtonType.YES) {
			returntoMain(event);
		}
	}
	/**
	 * @param event
	 *searches for parts in table
	 */
	@FXML
	void searchPart(ActionEvent event){
		ObservableList<Part> partData = Inventory.getAllParts();
		ObservableList<Part> foundPart = FXCollections.observableArrayList();
		String search = partSearch.getText();
		
		for (Part part: partData){
			if (String.valueOf(part.getId()).contains(search) || part.getName().contains(search)){
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
	 * refresh table if search box is empty
	 * returns all parts
	 */
	@FXML
	void searchPartKey(KeyEvent event){
		if (partSearch.getText().isEmpty()){
			partTV.setItems(Inventory.getAllParts());
		}
	}
	/**
	 * @param event
	 *removes part from associated table
	 */
	@FXML
	void removeButton(ActionEvent event){
		Part partSelected = partsAssociatedTV.getSelectionModel().getSelectedItem();
		if(partSelected == null){
			error(5);
		}else{
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Alert");
			alert.setContentText("Remove selected part?");
			Optional<ButtonType> result = alert.showAndWait();
			
			if(result.isPresent() && result.get() == ButtonType.YES){
				partsAssociated.remove(partSelected);
				partsAssociatedTV.setItems(partsAssociated);
			}
		}
	}
	/**
	 * @param event
	 * checks for validation
	 * saves and goes back to main screen
	 * @throws IOException if textfields are invalid
	 */
	@FXML
	void saveButton(ActionEvent event) throws IOException{
		try{
			int id = 0;
			String name = productName.getText();
			Double price = Double.parseDouble(productPrice.getText());
			int stock = Integer.parseInt(productInventory.getText());
			int min = Integer.parseInt(productMin.getText());
			int max = Integer.parseInt(productMax.getText());
			
			if(name.isEmpty()){
				error(6);
			}else{
				if(validationMin(min, max) && validationMax(min,max) && validationInv(min, max, stock)){
					Product newProduct = new Product(id, name, price, stock, min, max);
					
					for(Part part: partsAssociated){
						newProduct.addPartsAssociated(part);
					}
					newProduct.setId(Inventory.newProductID());
					Inventory.addProduct(newProduct);
					returntoMain(event);
				}
			}
		}catch(Exception e){error(1);}
	}
	/**
	 * @param min
	 * @param max
	 * @return  if error, else validation is correct
	 */
	private boolean validationMin(int min, int max) {
		boolean isValid = true;
		if (min <= 0 || min >= max) {
			isValid = false;
			error(3);
		}
		return isValid;
	}
	/**
	 * @param min
	 * @param max
	 * @return  if error, else validation is correct
	 */
	private boolean validationMax(int min, int max) {
		boolean isValid = true;
		if (max <= 0 || min >= max) {
			isValid = false;
			error(3);
		}
		return isValid;
	}
	/**
	 * @param min
	 * @param max
	 * @param stock
	 * @return if error, else validation is correct
	 */
	private boolean validationInv(int min, int max, int stock) {
		boolean isValid = true;
		if (stock < min || stock > max) {
			isValid = false;
			error(4);
		}
		return isValid;
	}
	
	/**
	 * @param alertType
	 * show error messages
	 */
	private void error(int alertType){
		Alert alert = new Alert(Alert.AlertType.ERROR);
		
		switch(alertType){
		case 1:
			alert.setTitle("Error");
			alert.setContentText("Invalid values or blank fields");
			alert.showAndWait();
			break;			
		case 2:
			alert.setTitle("Part not found");
			alert.showAndWait();
			break;
		case 3:
			alert.setTitle("Error");
			alert.setContentText("Min must be less than Max");
			alert.showAndWait();
			break;
		case 4:
			alert.setTitle("Error");
			alert.setContentText("Invenotry must be between Min and Max");
			alert.showAndWait();
			break;
		case 5:
			alert.setTitle("Error");
			alert.setHeaderText("Part not selected");
			alert.showAndWait();
			break;
		case 6:
			alert.setTitle("Error");
			alert.setContentText("Name is empty");
			alert.showAndWait();
			break;
		}
	}
	/**
	 * @param event
	 *goes back to main screen
	 *@throws IOException
	 * thrown when MainPage.fxml can not be loaded
	 */
	private void returntoMain(ActionEvent event) throws IOException{
		Parent parent = FXMLLoader.load(getClass().getResource("/pbeave/wgu/ims3/view/MainPage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(parent));
        stage.show();
	}
	/**
	 * populates table views for part and associated parts 
	 */
	public void initialize(URL location, ResourceBundle resources){
		partID.setCellValueFactory(new PropertyValueFactory<>("id"));
		partName.setCellValueFactory(new PropertyValueFactory<>("name"));
		partInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
		partPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
		partTV.setItems(Inventory.getAllParts());
		
		paID.setCellValueFactory(new PropertyValueFactory<>("id"));
		paName.setCellValueFactory(new PropertyValueFactory<>("name"));
		paInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
		paPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
		
	}
}
