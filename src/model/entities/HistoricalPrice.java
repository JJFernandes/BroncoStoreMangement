package model.entities;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	
	@Column(name="price", precision = 8, scale = 2)
	private BigDecimal price;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="product_id")
	private Product product;
	
	public HistoricalPrice() {}

	public HistoricalPrice(Date date, BigDecimal price) {
		this.date = date;
		this.price = price;
	}
	
	public void setProduct(Product p) {
		this.product = p;
	}
	
	public BigDecimal getPrice() {
		return this.price;
	}
	
	public Date getDate() {
		return this.date;
	}
	
}
