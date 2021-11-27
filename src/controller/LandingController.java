package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LandingController extends BasicController{
	
	private final String customerRegViewPath = "/fxml/CustomerDetailsView.fxml";
	private final String productRegViewPath = "/fxml/ProductDetailsView.fxml";

	@FXML
	private Button regCustomerBtn;
	
	@FXML
	private Button regProductBtn;
	
	@FXML
	private Button regOrderBtn;
	
	@FXML
	private Button manOrderBtn;
	
	@FXML
	private Button genReportBtn;
	
	public void initialize() {
		
		regCustomerBtn.setOnAction(event -> {
			try {
				switchToView(event, customerRegViewPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		regProductBtn.setOnAction(event -> {
			try {
				switchToView(event, productRegViewPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		regOrderBtn.setOnAction(event -> {
			
		});

		manOrderBtn.setOnAction(event -> {
	
		});
		
		genReportBtn.setOnAction(event -> {
			
		});
	}	

}
