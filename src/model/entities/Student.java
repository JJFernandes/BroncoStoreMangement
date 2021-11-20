package model.entities;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="students")
public class Student extends Customer{

	@Column(name="enter_date")
	private Date enterDate;
	
	@Column(name="grad_date")
	private Date gradDate;
	
	@Column(name="major")
	private String major;
	
	@Column(name="minor")
	private String minor;

	public Student(String bronco_id, Date dob, String phone, Address address, List<Order> orders, Date enterDate,
			Date gradDate, String major, String minor) {
		super(bronco_id, dob, phone, address, orders);
		this.enterDate = enterDate;
		this.gradDate = gradDate;
		this.major = major;
		this.minor = minor;
	}
	
}
