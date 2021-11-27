package model.logic;

import java.sql.Date;

import org.hibernate.Session;

import model.entities.Address;
import model.entities.Customer;
import model.entities.Student;
import model.entities.Professor;
import model.entities.StudentProfessor;

public class CustomerRegistrationLogic {
	
	public static void  createNewStudentCustomer(
			String bronco_id, 
			String name,
			Date dob,
			String phone,
			Address address,
			Date enter,
			Date grad,
			String major,
			String minor) {
		
		Session s = null;
		
		try {
			s = ConnectionFactory.getSession();
			
			s.beginTransaction();
			
			//create StudentProfessor customer
			Customer studentHolder = new Student(bronco_id, name, dob, phone, address, enter, grad, major, minor);
			
			//persist StudentProfessor to database
			s.save(studentHolder);
			
			s.getTransaction().commit();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			s.close();
		}
	}
	
	public static void  createNewProfessorCustomer(
			String bronco_id, 
			String name,
			Date dob,
			String phone,
			Address address,
			String dept,
			String office,
			String research) {
		
		Session s = null;
		
		try {
			s = ConnectionFactory.getSession();
			
			s.beginTransaction();
			
			//create StudentProfessor customer
			Customer professorHolder = new Professor(bronco_id, name, dob, phone, address, dept, office, research);
			
			//persist StudentProfessor to database
			s.save(professorHolder);
			
			s.getTransaction().commit();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			s.close();
		}
	}
	
	public static void  createNewStudentProfessorCustomer(
			String bronco_id, 
			String name,
			Date dob,
			String phone,
			Address address,
			Date enter,
			Date grad,
			String major,
			String minor,
			String dept,
			String office,
			String research) {
		
		Session s = null;
		
		try {
			s = ConnectionFactory.getSession();
			
			s.beginTransaction();
			
			//create StudentProfessor customer
			Customer studentProfessorHolder = new StudentProfessor(bronco_id, name, dob, phone, address, enter, grad, major, minor, dept, office, research);
			
			//persist StudentProfessor to database
			s.save(studentProfessorHolder);
			
			s.getTransaction().commit();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			s.close();
		}
	}

}
