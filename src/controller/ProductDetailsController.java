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

public class ProductDetailsController extends BasicController{
	
	private final String landingViewPath = "/fxml/LandingView.fxml";
	
	@FXML
	private Button cancel_Btn;
	
	public void initialize() {
		
		cancel_Btn.setOnAction(event -> {
			try {
				switchToView(event, landingViewPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
}
