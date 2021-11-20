package model.entities;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="date")
	private Date date;
	
	@Column(name="time")
	private Time time;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@ManyToMany(mappedBy = "orders")
	private List<Product> products = new ArrayList<>(); //I assume the price can be accessible from each product
	
	@ElementCollection
	private List<Integer> quantities = new ArrayList<>();

	@Column(name="total_price")
	private double total_price;

	public Order(int id, Date date, Time time, Customer customer, List<Product> products, List<Integer> quantities, double total_price) {
		this.id = id;
		this.date = date;
		this.time = time;
		this.customer = customer;
		this.products = products;
		this.quantities = quantities;
		this.total_price = total_price;
	}
	
	
}
