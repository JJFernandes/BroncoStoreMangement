package model.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("student")
public class Student extends Customer {

	@Column(name="enter_date")
	private Date enterDate;
	
	@Column(name="grad_date")
	private Date gradDate;
	
	@Column(name="major")
	private String major;
	
	@Column(name="minor")
	private String minor;
	
	public Student() {}

	public Student(String bronco_id, String name, Date dob, String phone, Address address, Date enterDate,
			Date gradDate, String major, String minor) {
		super(bronco_id, name, dob, phone, address);
		this.enterDate = enterDate;
		this.gradDate = gradDate;
		this.major = major;
		this.minor = minor;
	}
	
}
