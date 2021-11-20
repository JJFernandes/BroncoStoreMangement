package model.entities;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="studentprofessor")
@DiscriminatorValue("studentprofessor")
public class StudentProfessor extends Customer {

	@Column(name="enter_date")
	private Date enterDate;
	
	@Column(name="grad_date")
	private Date gradDate;
	
	@Column(name="major")
	private String major;
	
	@Column(name="minor")
	private String minor;
	
	@Column(name="department")
	private String department;
	
	@Column(name="office")
	private String office;
	
	@Column(name="research")
	private String research;

	public StudentProfessor(String bronco_id, Date dob, String phone, Address address, List<Order> orders,
			Date enterDate, Date gradDate, String major, String minor, String department, String office,
			String research) {
		super(bronco_id, dob, phone, address, orders);
		this.enterDate = enterDate;
		this.gradDate = gradDate;
		this.major = major;
		this.minor = minor;
		this.department = department;
		this.office = office;
		this.research = research;
	}
	
	
}
