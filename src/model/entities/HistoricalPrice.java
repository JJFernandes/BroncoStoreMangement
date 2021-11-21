package model.entities;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="historicalprice")
public class HistoricalPrice {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="date")
	private Date date;
	
	@Column(name="price")
	private double price;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="product_id")
	private Product product;
	
	public HistoricalPrice() {}

	public HistoricalPrice(int id, Date date, double price, Product product) {
		this.id = id;
		this.date = date;
		this.price = price;
		this.product = product;
	}
	
}
