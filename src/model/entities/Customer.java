package model.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="customers")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="customer_type")
public abstract class Customer {
	
	@Id
	@Column(name="bronco_id")
	private String bronco_id; //String because leading zeroes and never used as ints.

	@Column(name="name")
	private String name;
	
	@Column(name="DOB")
	private Date dob;
	
	@Column(name="phone") //String because potential leading zeroes and never used as ints
	private String phone;
	
	@Embedded
	private Address address;
	
	@OneToMany(mappedBy="customer", cascade = CascadeType.PERSIST)
	private List<Order> orders;
	
	public Customer() {}
	
	public Customer(String bronco_id, String name, Date dob, String phone, Address address) {
		this.bronco_id = bronco_id;
		this.name = name;
		this.dob = dob;
		this.phone = phone;
		this.address = address;
		this.orders = new ArrayList<Order>();
	}

}

	
