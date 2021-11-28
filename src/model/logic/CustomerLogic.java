package model.logic;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import javafx.collections.FXCollections;
import model.entities.Address;
import model.entities.Customer;
import model.entities.HistoricalPrice;
import model.entities.Product;
import model.entities.Student;
import model.entities.Professor;
import model.entities.StudentProfessor;

public class CustomerLogic {
	
	public static boolean createNewStudentCustomer(
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
			
			CriteriaBuilder builder = s.getCriteriaBuilder();
		    CriteriaQuery<Customer> criteria = builder.createQuery(Customer.class);
		    Root<Customer> root = criteria.from(Customer.class);
		    criteria.select(root).where(root.get("bronco_id").in(bronco_id));
		    
		    try {
		    	s.createQuery(criteria).getSingleResult();
		    	
		    	return false;
		    	
		    } catch(javax.persistence.NoResultException e) {
		    	Customer studentHolder = new Student(bronco_id, name, dob, phone, address, enter, grad, major, minor);
				
		    	s.save(studentHolder);
				
		    	s.getTransaction().commit();
				
				return true;
		    }
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			s.close();
		}
		
		return true;
	}
	
	public static boolean createNewProfessorCustomer(
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
			
			CriteriaBuilder builder = s.getCriteriaBuilder();
		    CriteriaQuery<Customer> criteria = builder.createQuery(Customer.class);
		    Root<Customer> root = criteria.from(Customer.class);
		    criteria.select(root).where(root.get("bronco_id").in(bronco_id));
		    
		    try {
		    	s.createQuery(criteria).getSingleResult();
		    	
		    	return false;
		    	
		    } catch(javax.persistence.NoResultException e) {
		    	Customer professorHolder = new Professor(bronco_id, name, dob, phone, address, dept, office, research);
				
				s.save(professorHolder);
				
		    	s.getTransaction().commit();
				
				return true;
		    }
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			s.close();
		}
		
		return true;
		
	}
	
	public static boolean createNewStudentProfessorCustomer(
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
			
			CriteriaBuilder builder = s.getCriteriaBuilder();
		    CriteriaQuery<Customer> criteria = builder.createQuery(Customer.class);
		    Root<Customer> root = criteria.from(Customer.class);
		    criteria.select(root).where(root.get("bronco_id").in(bronco_id));
		    
		    try {
		    	s.createQuery(criteria).getSingleResult();
		    	
		    	return false;
		    	
		    } catch(javax.persistence.NoResultException e) {
		    	Customer studentProfessorHolder = new StudentProfessor(bronco_id, name, dob, phone, address, enter, grad, major, minor, dept, office, research);
				
				s.save(studentProfessorHolder);
				
		    	s.getTransaction().commit();
				
				return true;
		    }
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			s.close();
		}
		
		return true;
	}

}
