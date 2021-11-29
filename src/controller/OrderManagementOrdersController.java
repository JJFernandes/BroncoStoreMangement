package controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.NumberFormat;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.entities.Customer;
import model.entities.Order;
import model.logic.CustomerLogic;
import model.logic.OrderLogic;

public class OrderManagementOrdersController extends BasicController{
	
	private final String landingViewPath = "/fxml/LandingView.fxml";
	
	@FXML private TableView<Order> orders_tbl;
	@FXML private TableColumn<Order, Integer> ord_id_col;
	@FXML private TableColumn<Order, String> cust_id_col;
	@FXML private TableColumn<Order, String> cust_name_col;
	@FXML private TableColumn<Order, String> ord_date_col;
	@FXML private TableColumn<Order, String> ord_time_col;
	@FXML private TableColumn<Order, String> ord_price_col;
	
	@FXML private Label ord_id_lbl;
	@FXML private Label cust_id_lbl;
	@FXML private Label cust_name_lbl;
	@FXML private Label ord_price_lbl;
	@FXML private Label ord_date_lbl;
	@FXML private Label ord_time_lbl;
	
	@FXML private Button cancel_Btn;

	
	public void initialize() {
		DateFormat df = DateFormat.getDateInstance();
		DateFormat tf = DateFormat.getTimeInstance();
		NumberFormat curr = NumberFormat.getCurrencyInstance();
		
		ord_id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
		cust_id_col.setCellValueFactory(cellData -> {
			return new SimpleStringProperty(cellData.getValue().getCustomer().getBronco_id());
		});
		cust_name_col.setCellValueFactory(cellData -> {
			return new SimpleStringProperty(cellData.getValue().getCustomer().getName());
		});
		ord_date_col.setCellValueFactory(cellData -> {
			return new SimpleStringProperty(df.format(cellData.getValue().getDate()));
		});
		ord_time_col.setCellValueFactory(cellData -> {
			return new SimpleStringProperty(tf.format(cellData.getValue().getTime()));
		});
		ord_price_col.setCellValueFactory(cellData -> {
			return new SimpleStringProperty(curr.format(cellData.getValue().getSubTotalPrice()));
		});
		
		orders_tbl.getSortOrder().add(ord_id_col);
		orders_tbl.getSelectionModel().selectedItemProperty().addListener((OBS, OLD, NEW) -> {
			ord_id_lbl.setText(Integer.toString(NEW.getId()));
			cust_id_lbl.setText(NEW.getCustomer().getBronco_id());
			cust_name_lbl.setText(NEW.getCustomer().getName());
			ord_date_lbl.setText(df.format(NEW.getDate()));
			ord_time_lbl.setText(tf.format(NEW.getTime()));
			ord_price_lbl.setText(curr.format(NEW.getSubTotalPrice()));
		});
		
		orders_tbl.setItems(getOrdersFromDB());
		orders_tbl.getSelectionModel().selectFirst();
		
		cancel_Btn.setOnAction(event -> {
			
			try {
				switchToView(event, landingViewPath);
			}catch (IOException e) {
				e.printStackTrace();
			}
			
		});
			
	}
	
	private ObservableList<Order> getOrdersFromDB() {
		return FXCollections.observableList(OrderLogic.getOrderList());
	}

}
