package pbeave.wgu.ims3.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import pbeave.wgu.ims3.model.Inventory;
import pbeave.wgu.ims3.model.InHousePart;
import pbeave.wgu.ims3.model.OutsourcePart;

/**
 * @author Patricia Beavers
 * Controller class for add part page
 */


public class AddPartController implements Initializable{
	/**
	 * outsource and inhouse part label
	 */
	@FXML
	public Label partIHorOSLabel;
	/**
	 * Inhouse button
	 */
	@FXML
	private RadioButton InHouseButton;
	/**
	 * outsourced button
	 */
	@FXML
	private RadioButton OutsourcedButton;
	/**
	 * togglegroup for radiobuttons
	 */
	@FXML
	private ToggleGroup radioGroup = new ToggleGroup();
	/**
	 * textfield for part ID
	 */
	@FXML
	TextField partID;
	/**
	 * textfield for part name
	 */
	@FXML
	TextField partName;
	/**
	 * textfield for part inventory
	 */
	@FXML
	TextField partInventory;
	/**
	 * textfield for part price
	 */
	@FXML
	TextField partPrice;
	/**
	 * textfield for part maximum
	 */
	@FXML
	TextField partMax;
	/**
	 * textfield for part minimum
	 */
	@FXML
	TextField partMin;
	/**
	 * textfield for machine id or company name
	 */
	@FXML
	TextField partIHorOS;
	/**
	 * @param event
	 * cancels adding part and returns to main screen
	 * @throws IOException
	 * from return to main
	 */
	@FXML
	void cancelButton(ActionEvent event) throws IOException {
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
	 * changes last parameter to machine id
	 */
	@FXML
	void inHouseRB(ActionEvent event) {
		partIHorOSLabel.setText("Machine ID");
	}
	/**
	 * @param event
	 * changes last parameter to company name
	 */
	@FXML
	void outSourcRB(ActionEvent event) {
		partIHorOSLabel.setText("Company Name");
	}
	/**
	 * @param event
	 * saves new part and returns to main page
	 * @throws IOException if textfields are invalid
	 */
	@FXML
	void saveButton(ActionEvent event) throws IOException {
		try{
			int id = 0;
			String name = partName.getText();
			Double price = Double.parseDouble(partPrice.getText());
			int stock = Integer.parseInt(partInventory.getText());
			int min = Integer.parseInt(partMin.getText());
			int max = Integer.parseInt(partMax.getText());
			int machineID;
			String companyName;
			boolean AddSuccessful = false;
			
			if (name.isEmpty()){
				error(5);				
			}else{
				if(validationMin(min, max) && validationInv(min, max, stock)&& validationMax(min, max)){
					if (InHouseButton.isSelected()){
						try{
						machineID = Integer.parseInt(partIHorOS.getText());
						InHousePart newIHPart = new InHousePart(id, name, price, stock, min, max, machineID);
						newIHPart.setId(Inventory.newPartID());
						Inventory.addPart(newIHPart);
						AddSuccessful = true;
						}catch (Exception e){error(2);}
					}
					if(OutsourcedButton.isSelected()){
						companyName = partIHorOS.getText();
						OutsourcePart newOSPart = new OutsourcePart(id, name, price, stock, min, max, companyName);
						newOSPart.setId(Inventory.newPartID());
						Inventory.addPart(newOSPart);
						AddSuccessful = true;
					}
					if(AddSuccessful){returntoMain(event);}
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
	private void error(int alertType) {
		Alert alert = new Alert(Alert.AlertType.ERROR);

		switch (alertType) {
		case 1:
			alert.setTitle("Error");
			// alert.setHeaderText("Error Adding Part");
			alert.setContentText("Invalid Values or Blank Fields");
			alert.showAndWait();
			break;
		case 2:
			alert.setTitle("Error");
			alert.setContentText("Invalid Values, Machine ID only accepts numbers");
			alert.showAndWait();
			break;
		case 3:
			alert.setTitle("Error");
			alert.setContentText("Invalid Values, Min must be greater than 0 and less than Max");
			alert.showAndWait();
			break;
		case 4:
			alert.setTitle("Error");
			alert.setContentText("Invalid Values, Inventory must be between Min and Max");
			alert.showAndWait();
			break;
		case 5:
			alert.setTitle("Error");
			alert.setContentText("Invalid Values, Name is empty");
			alert.showAndWait();
			break;

		}
	}
	/**
	 * @param event
	 * goes back to main screen
	 * @throws IOException
	 * thrown when MainPage.fxml can not be loaded
	 */
	private void returntoMain(ActionEvent event) throws IOException {
		 Parent parent = FXMLLoader.load(getClass().getResource("/pbeave/wgu/ims3/view/MainPage.fxml"));
	        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        stage.setScene(new Scene(parent));
	        stage.show();
	}
	/**
	 * selects the InHouse button to start out adding part
	 */
	public void initialize(URL loction, ResourceBundle resources){
		InHouseButton.setSelected(true);
	}

}
