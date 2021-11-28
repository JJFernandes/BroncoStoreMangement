package controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.NumberFormat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.entities.HistoricalPrice;
import model.entities.Product;
import model.logic.ProductLogic;

public class ProductInfoController extends BasicController{
	
	private final String landingViewPath = "/fxml/LandingView.fxml";
	private final String productManViewPath = "/fxml/ProductInfoView.fxml";
	
	@FXML private TableView<Product> products_tbl;
	@FXML private TableColumn<Product, Integer> prod_id_col;
	@FXML private TableColumn<Product, String> prod_name_col;
	@FXML private TableColumn<Product, BigDecimal> prod_price_col;
	
	@FXML private Label id_lbl;
	@FXML private Label name_lbl;
	@FXML private Label price_lbl;
	
	@FXML private TableView<HistoricalPrice> history_tbl;
	@FXML private TableColumn<HistoricalPrice, Date> hist_date_col;
	@FXML private TableColumn<HistoricalPrice, BigDecimal> hist_price_col;
	
	@FXML private TextField newPrice_txtF;
	@FXML private Button setNewPrice_Btn;
	
	@FXML private Button cancel_Btn;
	
	
	public void initialize() {
		
		cancel_Btn.setOnAction(event -> {
			
			try {
				switchToView(event, landingViewPath);
			}catch (IOException e) {
				e.printStackTrace();
			}
			
		});
		
		NumberFormat formatter = NumberFormat.getCurrencyInstance();		
		
		hist_date_col.setCellValueFactory(new PropertyValueFactory<>("date"));
		hist_price_col.setCellValueFactory(new PropertyValueFactory<>("price"));
		history_tbl.getSortOrder().add(hist_date_col);
		
		prod_id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
		prod_name_col.setCellValueFactory(new PropertyValueFactory<>("name"));
		prod_price_col.setCellValueFactory(new PropertyValueFactory<>("price"));
		prod_price_col.setCellValueFactory(new PropertyValueFactory<>("price"));
		products_tbl.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		products_tbl.getSortOrder().add(prod_id_col);
		products_tbl.setItems(getProductsFromDB());
		
		products_tbl.getSelectionModel().selectedItemProperty().addListener((OBS, OLD, NEW) -> {
			id_lbl.setText(NEW.getId().toString());
			name_lbl.setText(NEW.getName());
			price_lbl.setText(formatter.format(NEW.getPrice()));
			
			history_tbl.setItems(FXCollections.observableList(NEW.getHistory()));
			
			setNewPrice_Btn.setOnAction(event -> {
				if(NEW != null && !newPrice_txtF.getText().equals("")) {
					buttonActionSetProductPrice(NEW, new BigDecimal(newPrice_txtF.getText()));
					try {
						switchToView(event, productManViewPath);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
			
		});
		
		products_tbl.getSelectionModel().selectFirst();
		
	}
	
	private ObservableList<Product> getProductsFromDB() {
		return ProductLogic.getProductObservableList();
	}
	
	private void buttonActionSetProductPrice(Product p, BigDecimal price) {
		ProductLogic.setProductPrice(p, price);
	}

}
