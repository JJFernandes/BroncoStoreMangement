package model.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="products")
public class Product {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="price")
	private double price;
	
	@OneToMany(mappedBy="product", cascade = CascadeType.PERSIST)
	private List<HistoricalPrice> history = new ArrayList<>();
	
	@ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
    		name = "order_product",
    		joinColumns = @JoinColumn(name = "product_id"),
    		inverseJoinColumns = @JoinColumn(name = "order_id")
    		)	
	private List<Order> orders = new ArrayList<>();
	
}