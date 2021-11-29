package controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.DateFormat;
import java.text.NumberFormat;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.entities.Order;
import model.logic.OrderLogic;

public class GenerateReportController extends BasicController{

	private final String landingViewPath = "/fxml/LandingView.fxml";
	
	@FXML private TableView<Order> orders_tbl;
	@FXML private TableColumn<Order, Integer> ord_id_col;
	@FXML private TableColumn<Order, String> cust_id_col;
	@FXML private TableColumn<Order, String> cust_name_col;
	@FXML private TableColumn<Order, String> ord_date_col;
	@FXML private TableColumn<Order, String> ord_time_col;
	@FXML private TableColumn<Order, String> ord_price_col;
	
	@FXML DatePicker begin_datepick;
	@FXML DatePicker end_datepick;
	
	@FXML Label numOrder_lbl;
	@FXML Label tot_rev_lbl;
	
	@FXML private Button retrieveOrders_btn;
	@FXML private Button cancel_btn;
	
	private ObservableList<Order> tempList = null;
	private BigDecimal tempPrice = null;
	private int tempCount = 0;
	
	private Alert a = new Alert(AlertType.ERROR);
	
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
		
		tempList = getOrdersFromDB();
		tempPrice = BigDecimal.valueOf(0.0);
		tempCount = 0;
		for(Order o: tempList) {
			tempPrice = tempPrice.add(o.getSubTotalPrice());
			tempCount++;					
		}
		numOrder_lbl.setText(Integer.toString(tempCount));
		tot_rev_lbl.setText(curr.format(tempPrice));
		
		orders_tbl.getSortOrder().add(ord_id_col);
		orders_tbl.setItems(tempList);
		
		
		
		retrieveOrders_btn.setOnAction(event -> {
			if (begin_datepick.getValue() == null || end_datepick.getValue() == null) {
				a.setContentText("Begin and End date ranges must be set");
				a.show();
			} else if (begin_datepick.getValue().compareTo(end_datepick.getValue()) <= 0) {
				tempList = getOrdersDateRangeFromDB();
				tempPrice = BigDecimal.valueOf(0.0);
				tempCount = 0;
				for(Order o: tempList) {
					tempPrice = tempPrice.add(o.getSubTotalPrice());
					tempCount++;					
				}
				numOrder_lbl.setText(Integer.toString(tempCount));
				tot_rev_lbl.setText(curr.format(tempPrice));
				orders_tbl.setItems(tempList);
				orders_tbl.refresh();
			} else if (begin_datepick.getValue().compareTo(end_datepick.getValue()) > 0) {
				a.setContentText("Begin date must be before End Date");
				a.show();
			}
		});
		
		
		
		
		cancel_btn.setOnAction(event -> {
			
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
	
	private ObservableList<Order> getOrdersDateRangeFromDB() {
		return FXCollections.observableList(OrderLogic.getOrderRangeList(Date.valueOf(begin_datepick.getValue()), Date.valueOf(end_datepick.getValue())));
	}
}
