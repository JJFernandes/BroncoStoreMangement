package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

public class CustomerDetailsController {

	@FXML
	private TextField broncoID_txtF;
	
	@FXML
	private TextField name_txtF;
	
	@FXML
	private DatePicker dob_datepick;
	
	@FXML
	private TextField phone_txtF;
	
	@FXML
	private TextField address_txtF;
	
	@FXML
	private ToggleGroup customerType;
	
	@FXML
	private RadioButton student_radBtn;
	
	@FXML
	private RadioButton professor_radBtn;
	
	@FXML
	private RadioButton studentProf_radBtn;
	
	@FXML
	private GridPane studentForm;
	
	@FXML
	private DatePicker enter_datepick;
	
	@FXML
	private DatePicker grad_datepick;
	
	@FXML
	private TextField major_txtF;
	
	@FXML
	private TextField minor_txtF;
	
	@FXML
	private GridPane professorForm;
	
	@FXML
	private TextField dept_txtF;
	
	@FXML
	private TextField office_txtF;
	
	@FXML
	private TextField research_txtF;
	
	
	public void initialize() {
		
		customerType.selectedToggleProperty().addListener(observable -> {
					if(customerType.getSelectedToggle() == student_radBtn) {
						studentForm.setDisable(false);
						professorForm.setDisable(true);
					}else if(customerType.getSelectedToggle() == professor_radBtn) {
						studentForm.setDisable(true);
						professorForm.setDisable(false);
					}else if(customerType.getSelectedToggle() == studentProf_radBtn) {
						studentForm.setDisable(false);
						professorForm.setDisable(false);
					}
					
				});
	}
}
