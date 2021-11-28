package controller;

import java.io.IOException;
import java.math.BigDecimal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.logic.ProductLogic;

public class ProductDetailsController extends BasicController{
	
	private final String landingViewPath = "/fxml/LandingView.fxml";
	
	@FXML
	private TextField name_txtF;
	
	@FXML
	private TextField price_txtF;
	
	@FXML
	private Button createProduct_Btn;
	
	@FXML
	private Button cancel_Btn;
	
	private Alert a = new Alert(AlertType.NONE);
	
	public void initialize() {
		
		
		
		createProduct_Btn.setOnAction(event -> {
			buttonActionCreateNewProduct();		
		});
		
		cancel_Btn.setOnAction(event -> {
			try {
				switchToView(event, landingViewPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
	
	private void buttonActionCreateNewProduct() {
		boolean success = ProductLogic.createNewProduct(this.name_txtF.getText(), new BigDecimal(price_txtF.getText()));
		if(success == false) {
			a.setAlertType(AlertType.ERROR);
			a.setContentText("Product name \"" + this.name_txtF.getText() + "\" already exists.");
			a.show();
		} else {
			a.setAlertType(AlertType.CONFIRMATION);
			a.setContentText("Product \"" + this.name_txtF.getText() + "\" created successfully.");
			a.show();
		}
		
	}
}
