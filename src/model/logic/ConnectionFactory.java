package model.logic;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.entities.Customer;
import model.entities.HistoricalPrice;
import model.entities.Order;
import model.entities.Product;
import model.entities.Professor;
import model.entities.Student;
import model.entities.StudentProfessor;

public class ConnectionFactory {
	
	private static SessionFactory factory = new Configuration()
			.configure("hibernate.cfg.xml")
			.addAnnotatedClass(Customer.class)
			.addAnnotatedClass(Student.class)
			.addAnnotatedClass(Professor.class)
			.addAnnotatedClass(StudentProfessor.class)
			.addAnnotatedClass(Product.class)
			.addAnnotatedClass(HistoricalPrice.class)
			.addAnnotatedClass(Order.class)
			.buildSessionFactory();
	
	public static Session getSession() {	
		return factory.getCurrentSession();
	}

}
