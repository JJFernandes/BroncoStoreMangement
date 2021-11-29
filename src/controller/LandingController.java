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
import model.logic.ConnectionFactory;

public class LandingController extends BasicController{
	
	private final String customerRegViewPath = "/fxml/CustomerDetailsView.fxml";
	private final String productRegViewPath = "/fxml/ProductDetailsView.fxml";
	private final String productManViewPath = "/fxml/ProductInfoView.fxml";
	private final String orderRegViewPath = "/fxml/OrderRegistrationCustomersView.fxml";
	private final String orderManViewPath = "/fxml/OrderManagementOrdersView.fxml";
	private final String genReportViewPath = "/fxml/GenerateReportView.fxml";

	@FXML
	private Button regCustomer_Btn;
	
	@FXML
	private Button regProduct_Btn;
	
	@FXML
	private Button manProduct_Btn;
	
	@FXML
	private Button regOrder_Btn;
	
	@FXML
	private Button manOrder_Btn;
	
	@FXML
	private Button genReport_Btn;
	
	public void initialize() {
		
		ConnectionFactory.getSession();
		
		regCustomer_Btn.setOnAction(event -> {
			try {
				switchToView(event, customerRegViewPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		regProduct_Btn.setOnAction(event -> {
			try {
				switchToView(event, productRegViewPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		manProduct_Btn.setOnAction(event -> {
			try {
				switchToView(event, productManViewPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		regOrder_Btn.setOnAction(event -> {
			try {
				switchToView(event, orderRegViewPath);
			} catch (IOException e) {
				e.printStackTrace();
			}			
		});

		manOrder_Btn.setOnAction(event -> {
			try {
				switchToView(event, orderManViewPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		genReport_Btn.setOnAction(event -> {
			try {
				switchToView(event, genReportViewPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}	

}
