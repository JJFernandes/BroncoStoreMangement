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

public class LandingController {

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
				switchToView(event, "/fxml/CustomerDetailsView.fxml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		regProductBtn.setOnAction(event -> {
			
		});
		
		regOrderBtn.setOnAction(event -> {
			
		});

		manOrderBtn.setOnAction(event -> {
	
		});
		
		genReportBtn.setOnAction(event -> {
			
		});
	}
	
	public void switchToView(ActionEvent event, String fxmlPath) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	

}
