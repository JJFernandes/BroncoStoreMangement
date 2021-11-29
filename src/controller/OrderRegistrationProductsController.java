package controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Map;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.entities.Customer;
import model.entities.Order;
import model.entities.Product;
import model.entities.Professor;
import model.entities.Student;
import model.entities.StudentProfessor;
import model.logic.OrderLogic;
import model.logic.ProductLogic;

public class OrderRegistrationProductsController extends BasicController{

	private final String landingViewPath = "/fxml/LandingView.fxml";
	
	private Customer selectedCustomer = null;
	private Order createdOrder = new Order();
	private double discount = 1.0;
	
	@FXML private Label cust_id_lbl;
	@FXML private Label cust_name_lbl;
	@FXML private Label subtotal_lbl;
	@FXML private Label discount_lbl;
	@FXML private Label total_lbl;
	@FXML private Label date_lbl;
	@FXML private Label time_lbl;
	
	@FXML private Label selected_inv_name_lbl;
	@FXML private Label seleted_order_name_lb;
	
	@FXML private TableView<Product> inventory_prod_tbl;
	@FXML private TableColumn<Product, Integer> inventory_prod_id_col;
	@FXML private TableColumn<Product, String> inventory_prod_name_col;
	@FXML private TableColumn<Product, String> inventory_prod_price_col;
	
	@FXML private TextField addQuantity_txtF;
	@FXML private Button addQuantity_btn;
	
	@FXML private TableView<Map.Entry<Product, Integer>> order_prod_tbl;
	@FXML private TableColumn<Map.Entry<Product, Integer>, String> order_prod_name_col;
	@FXML private TableColumn<Map.Entry<Product, Integer>, Integer> order_prod_quant_col;
	@FXML private TableColumn<Map.Entry<Product, Integer>, String> order_prod_cost_col;
	
	@FXML private TextField removeQuantity_txtF;
	@FXML private Button removeQuantity_btn;
	
	@FXML private Button createOrder_btn;
	@FXML private Button cancel_btn;
	
	private Alert a = new Alert(AlertType.NONE);
	
	public void initData(Customer c) {
		selectedCustomer = c;
		createdOrder.setCustomer(c);
		cust_id_lbl.setText(c.getBronco_id());
		cust_name_lbl.setText(c.getName());
		DateFormat df = DateFormat.getDateInstance();
		date_lbl.setText(df.format(createdOrder.getDate()));
		DateFormat tf = DateFormat.getTimeInstance();
		time_lbl.setText(tf.format(createdOrder.getTime()));
		
		if(c.getClass() == Student.class) {
			discount = 0.95;
		}else {
			discount = 0.85;
		}
		
		discount_lbl.setText(String.format("%.0f%%", (1-discount)*100));
		
	}
	
	public void initialize() {
		
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		
		inventory_prod_id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
		inventory_prod_name_col.setCellValueFactory(new PropertyValueFactory<>("name"));
		inventory_prod_price_col.setCellValueFactory(cellData -> {
			return new SimpleStringProperty(formatter.format(cellData.getValue().getPrice()));
		});
		
		inventory_prod_tbl.getSortOrder().add(inventory_prod_id_col);
		inventory_prod_tbl.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		inventory_prod_tbl.getSelectionModel().selectedItemProperty().addListener((OBS, OLD, NEW) -> {
			selected_inv_name_lbl.setText(NEW.getName());
			addQuantity_btn.setOnAction(event -> {
				if(NEW != null && !addQuantity_txtF.getText().equals("")) {
					createdOrder.addProductAndQuantity(inventory_prod_tbl.getSelectionModel().getSelectedItem(), Integer.valueOf(addQuantity_txtF.getText()));
					order_prod_tbl.setItems(getProductQuantityMapFromOrder());
					order_prod_tbl.refresh();
					subtotal_lbl.setText(formatter.format(createdOrder.getSubTotalPrice()));
					total_lbl.setText(formatter.format(createdOrder.getSubTotalPrice().multiply(BigDecimal.valueOf(discount))));
					createdOrder.setTotalPriceWithDiscount(createdOrder.getSubTotalPrice().multiply(BigDecimal.valueOf(discount)));
				}
			});
			
		});
		inventory_prod_tbl.setItems(getProductsFromDB());
		inventory_prod_tbl.getSelectionModel().selectFirst();
		
		
		
		order_prod_name_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<Product, Integer>, String>, ObservableValue<String>>() {
            
			@Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<Product, Integer>, String> param) {
                return new SimpleStringProperty(param.getValue().getKey().getName());
			}
			
        });
		order_prod_quant_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<Product, Integer>, Integer>, ObservableValue<Integer>>() {
            
			@Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Map.Entry<Product, Integer>, Integer> param) {
                return new SimpleIntegerProperty(param.getValue().getValue()).asObject();
			}
			
        });
	
		order_prod_tbl.getSelectionModel().selectFirst();
		order_prod_tbl.getSelectionModel().selectedItemProperty().addListener((OBS, OLD, NEW) -> {
			
			if (NEW == null) {
				seleted_order_name_lb.setText("None");
			} else {
				seleted_order_name_lb.setText(NEW.getKey().getName());
			}
			
			removeQuantity_btn.setOnAction(event -> {
				if(NEW != null && !removeQuantity_txtF.getText().equals("")) {
					boolean removed = createdOrder.removeProductAndQuantity(order_prod_tbl.getSelectionModel().getSelectedItem().getKey(), Integer.valueOf(removeQuantity_txtF.getText()));
					order_prod_tbl.setItems(getProductQuantityMapFromOrder());
					if(removed) {
						seleted_order_name_lb.setText("None");
					}
					
					order_prod_tbl.refresh();
					
					
					subtotal_lbl.setText(formatter.format(createdOrder.getSubTotalPrice()));
					total_lbl.setText(formatter.format(createdOrder.getSubTotalPrice().multiply(BigDecimal.valueOf(discount))));
					createdOrder.setTotalPriceWithDiscount(createdOrder.getSubTotalPrice().multiply(BigDecimal.valueOf(discount)));
				}
			});
		});
		
		createOrder_btn.setOnAction(event -> {
			buttonActionCreateNewOrder();
			try {
				switchToView(event, landingViewPath);
			}catch (IOException e) {
				e.printStackTrace();
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
	
	private ObservableList<Product> getProductsFromDB() {
		return FXCollections.observableArrayList(ProductLogic.getProductList());
	}
	
	private ObservableList<Map.Entry<Product, Integer>> getProductQuantityMapFromOrder() {
		return FXCollections.observableArrayList(createdOrder.getProductQuantityMap().entrySet());
	}
	
	private void buttonActionCreateNewOrder() {
		boolean success = OrderLogic.createNewOrder(createdOrder);
		if(success == false) {
			a.setAlertType(AlertType.ERROR);
			a.setContentText("Failed to create order");
			a.show();
		} else {
			a.setAlertType(AlertType.CONFIRMATION);
			a.setContentText("Order for  \"" + this.cust_name_lbl.getText() + "\" created successfully.");
			a.show();
		}
	}
}
