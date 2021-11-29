package controller;

import java.io.IOException;
import java.math.BigDecimal;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.entities.Customer;
import model.entities.Professor;
import model.entities.Student;
import model.entities.StudentProfessor;
import model.logic.CustomerLogic;

public class OrderRegistrationCustomersController extends BasicController{
	
	private final String landingViewPath = "/fxml/LandingView.fxml";
	private final String orderRegProdViewPath = "/fxml/OrderRegistrationProductsView.fxml";

	@FXML private TableView<Customer> customers_tbl;
	@FXML private TableColumn<Customer, String> cust_id_col;
	@FXML private TableColumn<Customer, String> cust_name_col;
	@FXML private TableColumn<Customer, String> cust_phone_col;
	@FXML private TableColumn<Customer, String> cust_type_col;
	
	@FXML private Label selected_id_lbl;
	@FXML private Label selected_name_lbl;
	@FXML private Label selected_phone_lbl;
	@FXML private Label selected_type_lbl;
	
	@FXML private Button selectCustomer_Btn;
	@FXML private Button cancel_Btn;
	
	public void initialize() {
		
		cust_id_col.setCellValueFactory(new PropertyValueFactory<>("bronco_id"));
		cust_name_col.setCellValueFactory(new PropertyValueFactory<>("name"));
		cust_phone_col.setCellValueFactory(new PropertyValueFactory<>("phone"));
		cust_type_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Customer,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Customer,String> param) {
				if(param.getValue() != null) {
					if (param.getValue().getClass() == Student.class) {
						return new SimpleStringProperty("student");
					} else if (param.getValue().getClass() == Professor.class) {
						return new SimpleStringProperty("professor");
					} else if (param.getValue().getClass() == StudentProfessor.class) {
						return new SimpleStringProperty("studentprofessor");
					}
				}
				
				return null;
			}
		});
		
		customers_tbl.getSelectionModel().selectedItemProperty().addListener((OBS, OLD, NEW) -> {
			selected_id_lbl.setText(NEW.getBronco_id().toString());
			selected_name_lbl.setText(NEW.getName());
			selected_phone_lbl.setText(NEW.getPhone());
			if (NEW.getClass() == Student.class) {
				selected_type_lbl.setText("student");
			} else if (NEW.getClass() == Professor.class) {
				selected_type_lbl.setText("professor");
			} else if (NEW.getClass() == StudentProfessor.class) {
				selected_type_lbl.setText("studentprofessor");
			}
			
			
			selectCustomer_Btn.setOnAction(event -> {
				if(NEW != null) {
					try {
						buttonActionContinueToProductSelection(event);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
			
		});
		
		cancel_Btn.setOnAction(event -> {
			
			try {
				switchToView(event, landingViewPath);
			}catch (IOException e) {
				e.printStackTrace();
			}
			
		});
		
		customers_tbl.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		customers_tbl.getSortOrder().add(cust_id_col);
		customers_tbl.setItems(getCustomersFromDB());;
		customers_tbl.getSelectionModel().selectFirst();
		
		
		
		
		
		
	}
	
	private ObservableList<Customer> getCustomersFromDB() {
		return FXCollections.observableList(CustomerLogic.getCustomerList());
	}
	
	private void buttonActionContinueToProductSelection(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(orderRegProdViewPath));
		Parent parent = loader.load();
		Scene scene = new Scene(parent);
		
		OrderRegistrationProductsController controller = loader.getController();
		controller.initData(customers_tbl.getSelectionModel().getSelectedItem());
		
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
		
	}
	
}
