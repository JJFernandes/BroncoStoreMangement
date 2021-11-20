package model.entities;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="professors")
public class Professor extends Customer {
	
	@Column(name="department")
	private String department;
	
	@Column(name="office")
	private String office;
	
	@Column(name="research")
	private String research;

	public Professor(String bronco_id, Date dob, String phone, Address address, List<Order> orders, String department,
			String office, String research) {
		super(bronco_id, dob, phone, address, orders);
		this.department = department;
		this.office = office;
		this.research = research;
	}

}
