package model.entities;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
	private String number; //String because Street Number and never used as ints.
	private String street;
	private String city;
	private String state;
	private String zip; //String because Zip Code and never used as ints.
	
	public Address() {}
	
	public Address(String number, String street, String city, String state, String zip) {
		this.number = number;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
	
}
