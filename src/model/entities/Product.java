package model.entities;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
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
	
	@Column(name="price", precision = 8, scale = 2)
	private BigDecimal price;
	
	@OneToMany(mappedBy="product", cascade = CascadeType.PERSIST, orphanRemoval=true)
	private List<HistoricalPrice> history;
	
	public Product() {}
	
	public Product(String name) {
		this.name = name;
	}
	
	public void setPrice(HistoricalPrice hp) {
		if(this.history == null) {
			this.history = new ArrayList<HistoricalPrice>();
		}
		this.price = hp.getPrice();
		this.history.add(hp);
	}
	
	public BigDecimal getPrice() {
		return this.price;
	}
	
}