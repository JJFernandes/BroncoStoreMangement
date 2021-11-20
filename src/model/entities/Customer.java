package model.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="customer_type")
public abstract class Customer {
	
	@Id
	@Column(name="bronco_id")
	private String bronco_id; //String because leading zeroes and never used as ints.

	@Column(name="DOB")
	private Date dob;
	
	@Column(name="phone") //String because potential leading zeroes and never used as ints
	private String phone;
	
	@Embedded
	private Address address;
	
	@OneToMany(mappedBy="customer", cascade = CascadeType.PERSIST)
	private List<Order> orders = new ArrayList<>();
	
	public Customer(String bronco_id, Date dob, String phone, Address address, List<Order> orders) {
		this.bronco_id = bronco_id;
		this.dob = dob;
		this.phone = phone;
		this.address = address;
		this.orders = orders;
	}

}

@Embeddable
class Address {
	private String number; //String because Street Number and never used as ints.
	private String street;
	private String city;
	private String state;
	private String zip; //String because Zip Code and never used as ints.
	
	public Address(String number, String street, String city, String state, String zip) {
		super();
		this.number = number;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
		
}

	
