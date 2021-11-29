package model.entities;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

@Entity
@Table(name="orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="date")
	private Date date = Date.valueOf(LocalDate.now());
	
	@Column(name="time")
	private Time time = Time.valueOf(LocalTime.now());;
	
	@ManyToOne(cascade= CascadeType.PERSIST)
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@ElementCollection
    @CollectionTable(name = "order_product_map", 
      joinColumns = @JoinColumn(name = "order_id"))
	@MapKeyColumn(name="product_id")
	@Column(name="quantity")
	private Map<Product, Integer> productQuantityMap = new HashMap<Product, Integer>();;
	
	@Column(name="total_price", precision = 8, scale = 2)
	private BigDecimal total_price = new BigDecimal("0.0");;
	
	public Order() {}
	
	public void calculateTotalPrice() {
		this.total_price = new BigDecimal("0.0");
		
		this.productQuantityMap.forEach((k, v) -> {
			
			this.total_price = this.total_price.add(k.getPrice().multiply(BigDecimal.valueOf(v)));
		});
	}
	
	public void addProductAndQuantity(Product p, int q) {
		if(this.productQuantityMap.containsKey(p)) {
			this.productQuantityMap.put(p, this.productQuantityMap.get(p) + q);
		} else {
			this.productQuantityMap.put(p, q);
		}
		
		calculateTotalPrice();
	}
	
	public boolean removeProductAndQuantity(Product p, int q) {
		if(this.productQuantityMap.containsKey(p)) {
			if(this.productQuantityMap.get(p) - q > 0) {
				this.productQuantityMap.put(p, this.productQuantityMap.get(p) - q);
				calculateTotalPrice();
				return false;
			} else {
				this.productQuantityMap.remove(p);
				calculateTotalPrice();
				return true;
			}
		} 
		
		return true;
		
	}
	
	public int getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public Time getTime() {
		return time;
	}

	public BigDecimal getSubTotalPrice() {
		return total_price;
	}
	
	public void setTotalPriceWithDiscount(BigDecimal price) {
		this.total_price = price;
	}

	public Map<Product, Integer> getProductQuantityMap() {
		return this.productQuantityMap;
	}
	
	public void setCustomer(Customer c) {
		this.customer = c;
	}
	
	public Customer getCustomer() {
		return this.customer;
	}
		
}
