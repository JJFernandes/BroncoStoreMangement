package controller;

import java.io.IOException;
import java.sql.Date;

import javafx.event.ActionEvent;
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
import javafx.stage.Stage;
import model.entities.Address;
import model.logic.CustomerRegistrationLogic;

public class CustomerDetailsController extends BasicController{
	
	private final String landingViewPath = "/fxml/LandingView.fxml";

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
	
	@FXML
	private Button createCustomer_Btn;
	
	@FXML
	private Button cancel_Btn;
	
	
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
		
		cancel_Btn.setOnAction(event -> {
			
			try {
				switchToView(event, landingViewPath);
			}catch (IOException e) {
				e.printStackTrace();
			}
			
		});
		
		createCustomer_Btn.setOnAction(event -> {
			
			if(customerType.getSelectedToggle() == student_radBtn) {
				buttonActionCreateNewStudentCustomer();
			}else if(customerType.getSelectedToggle() == professor_radBtn) {
				buttonActionCreateNewProfessorCustomer();
			}else if(customerType.getSelectedToggle() == studentProf_radBtn) {
				buttonActionCreateNewStudentProfessorCustomer();
			}
			
			
			try {
				switchToView(event, "/fxml/LandingView.fxml");
			}catch (IOException e) {
				e.printStackTrace();
			}
			
			
		});
	}
	
	public void buttonActionCreateNewStudentCustomer() {
		
		String[] addr = this.address_txtF.getText().split(",");
		CustomerRegistrationLogic.createNewStudentCustomer(
				this.broncoID_txtF.getText(),
				this.name_txtF.getText(),
				Date.valueOf(this.dob_datepick.getValue()),
				this.phone_txtF.getText(),
				new Address(addr[0], addr[1], addr[2], addr[3], addr[4]),
				Date.valueOf(this.enter_datepick.getValue()),
				Date.valueOf(this.grad_datepick.getValue()),
				this.major_txtF.getText(),
				this.minor_txtF.getText()
				);
		
	}
	
	public void buttonActionCreateNewProfessorCustomer() {
		
		String[] addr = this.address_txtF.getText().split(",");
		CustomerRegistrationLogic.createNewProfessorCustomer(
				this.broncoID_txtF.getText(),
				this.name_txtF.getText(),
				Date.valueOf(this.dob_datepick.getValue()),
				this.phone_txtF.getText(),
				new Address(addr[0], addr[1], addr[2], addr[3], addr[4]),
				this.dept_txtF.getText(),
				this.office_txtF.getText(),
				this.research_txtF.getText()
				);
		
	}

	public void buttonActionCreateNewStudentProfessorCustomer() {
	
		String[] addr = this.address_txtF.getText().split(",");
		CustomerRegistrationLogic.createNewStudentProfessorCustomer(
				this.broncoID_txtF.getText(),
				this.name_txtF.getText(),
				Date.valueOf(this.dob_datepick.getValue()),
				this.phone_txtF.getText(),
				new Address(addr[0], addr[1], addr[2], addr[3], addr[4]),
				Date.valueOf(this.enter_datepick.getValue()),
				Date.valueOf(this.grad_datepick.getValue()),
				this.major_txtF.getText(),
				this.minor_txtF.getText(),
				this.dept_txtF.getText(),
				this.office_txtF.getText(),
				this.research_txtF.getText()
				);
	
	}
}
