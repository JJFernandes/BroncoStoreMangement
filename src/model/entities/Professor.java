package model.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("professor")
public class Professor extends Customer {
	
	@Column(name="department")
	private String department;
	
	@Column(name="office")
	private String office;
	
	@Column(name="research")
	private String research;
	
	public Professor() {}

	public Professor(String bronco_id, String name, Date dob, String phone, Address address, String department,
			String office, String research) {
		super(bronco_id, name, dob, phone, address);
		this.department = department;
		this.office = office;
		this.research = research;
	}

}
